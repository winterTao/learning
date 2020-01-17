package com.tao.flink

import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time


/**
 * @author DongTao
 * @since 2020-01-16
 */
object WorldCount {

    case class WordWithCount(word: String, count: Long)

    def main(args: Array[String]): Unit = {
        // 获取执行器的环境
        val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

        //获取数据: 从socket中获取
        val textDataStream = env.socketTextStream("127.0.0.1", 8888, '\n')
        val tupDataStream = textDataStream.flatMap(_.split(" ")).map(WordWithCount(_, 1))

        //groupby: 按照指定的字段聚合
        val windowDstram = tupDataStream.keyBy("word").timeWindow(Time.seconds(5), Time.seconds(1)) //窗口bsize=5秒, slid=1s
        windowDstram.sum("count").print()


        env.execute("Socket Window WordCount")
    }

}
