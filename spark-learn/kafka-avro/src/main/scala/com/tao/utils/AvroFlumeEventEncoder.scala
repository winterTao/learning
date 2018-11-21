package com.tao.utils

import java.io.ByteArrayOutputStream

import kafka.serializer.Encoder
import kafka.utils.VerifiableProperties
import org.apache.avro.io.{BinaryEncoder, EncoderFactory}
import org.apache.avro.specific.SpecificDatumWriter
import org.apache.flume.source.avro.AvroFlumeEvent

/**
  * Created by emanxia on 16/6/12.
  */
class AvroFlumeEventEncoder(props: VerifiableProperties = null) extends Encoder[AvroFlumeEvent] {

  private val writer: SpecificDatumWriter[AvroFlumeEvent] = new SpecificDatumWriter[AvroFlumeEvent](classOf[AvroFlumeEvent])
  private var encoder: BinaryEncoder = null.asInstanceOf[BinaryEncoder]
  private var tempOutStream = new ByteArrayOutputStream()

  override def toBytes(event: AvroFlumeEvent): Array[Byte] = {
    tempOutStream.reset()
    encoder = EncoderFactory.get.directBinaryEncoder(tempOutStream, encoder)
    writer.write(event, encoder)
    tempOutStream.toByteArray
  }
}
