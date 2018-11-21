package com.tao

import com.tao.utils.AvroFlumeEventDecoder
import com.typesafe.config.{Config, ConfigFactory}
import org.apache.flume.Event
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * @author DongTao
  * @since 2018-11-21
  */
object StreamingAvro {

  def main(args: Array[String]) {

    //加载conf配置文件
    val conf = ConfigFactory.load()

    val ssc = setupSsc(conf)
    ssc.start()
    ssc.awaitTermination()
  }

  /**
    * 初始化 streamingContext
    */
  def setupSsc(conf: Config): StreamingContext = {

    //创建sparkConf
    val sparkConf = new SparkConf()
    sparkConf.setAppName("test_avro").setMaster("local[*]")

    sparkConf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")

    //配置ss的批次时间
    //    val batchDuration = conf.getInt("batchDuration")
    val batchDuration = 10
    val ssc = new StreamingContext(sparkConf, Seconds(batchDuration))

    //配置ss的checkpoint文件的位置
    //    val checkpointDir = conf.getString("checkpointDirForBehavior")
    val checkpointDir = "/tmp/checkpoint/test001"
    ssc.checkpoint(checkpointDir)

    val topics = new java.util.ArrayList[TopicPartition]()
    val offsets = new java.util.HashMap[TopicPartition, java.lang.Long]()

    topics.add(new TopicPartition("test001", 0))
    offsets.put(new TopicPartition("test001", 0), 0L)

    val kafkaParams = new java.util.HashMap[String, Object]()
    kafkaParams.put("bootstrap.servers", "10.24.3.101:9092,10.24.3.102:9092,10.24.3.103:9092")
    //    kafkaParams.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    //    kafkaParams.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    kafkaParams.put("key.deserializer", classOf[StringDeserializer])
    kafkaParams.put("value.deserializer", classOf[AvroFlumeEventDecoder])

    //创建DStream
    val stream = KafkaUtils.createDirectStream[String, Event](
      ssc,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Assign[String, Event](topics, kafkaParams, offsets)
    )

    //配置DStream checkpoint的时间间隔
    //    val checkPointInterval = conf.getInt("checkpointInterval")
    val checkPointInterval = 900
    stream.checkpoint(Seconds(checkPointInterval))

    //操作DStream
    stream.foreachRDD { rdd => {
      if (!rdd.isEmpty()) {

        rdd.foreach(println(_))
        println(rdd.count())
        println("---------------")
        val databody: RDD[String] = rdd.map(x => {
          new String(x.value().getBody, "utf-8")
        })
        val datahead = rdd.map(x => {
          x.value().getHeaders
        })


        datahead.foreach(println(_))
        databody.foreach(println(_))

      }
    }
    }
    ssc
  }

}
