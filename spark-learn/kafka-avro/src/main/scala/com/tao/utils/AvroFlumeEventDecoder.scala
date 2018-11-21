package com.tao.utils

import java.io.ByteArrayInputStream
import java.util

import org.apache.avro.io.{BinaryDecoder, DecoderFactory}
import org.apache.avro.specific.SpecificDatumReader
import org.apache.flume.Event
import org.apache.flume.event.EventBuilder
import org.apache.flume.source.avro.AvroFlumeEvent
import org.apache.kafka.common.serialization.Deserializer

/**
  * Created by emanxia on 16/6/12.
  */
class AvroFlumeEventDecoder extends Deserializer[Event] {

  private val reader: SpecificDatumReader[AvroFlumeEvent] = new SpecificDatumReader[AvroFlumeEvent](classOf[AvroFlumeEvent])
  private var decoder: BinaryDecoder = null.asInstanceOf[BinaryDecoder]

  override def configure(map: util.Map[String, _], b: Boolean): Unit = {}

  override def deserialize(s: String, bytes: Array[Byte]): Event = {
    val inputStream = new ByteArrayInputStream(bytes)
    decoder = DecoderFactory.get.directBinaryDecoder(inputStream, decoder)
    val avroEvent: AvroFlumeEvent = reader.read(null, decoder)
    EventBuilder.withBody(avroEvent.getBody.array, toStringJavaMap(avroEvent.getHeaders))
  }

  def toStringJavaMap(charSeqMap: util.Map[CharSequence, CharSequence]): util.HashMap[String, String] = {
    val stringMap = new util.HashMap[String, String]()
    import scala.collection.JavaConversions._
    for ((k: CharSequence, v: CharSequence) <- charSeqMap)
      stringMap.put(k.toString, v.toString)
    stringMap
  }

  override def close(): Unit = {}
}
