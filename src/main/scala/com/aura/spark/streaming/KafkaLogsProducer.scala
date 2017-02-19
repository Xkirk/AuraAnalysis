package com.aura.spark.streaming

import java.util.Properties

import com.aura.config.Config
import com.aura.util.FileUtil
import kafka.producer.{KeyedMessage, Producer, ProducerConfig}

import org.apache.spark.Logging

object KafkaLogsProducer extends Logging {

  def main(args: Array[String]): Unit = {

    val topic = Config.topic
    val brokers = Config.brokerList
    val props = new Properties()
    props.put("metadata.broker.list", brokers)
    props.put("serializer.class", "kafka.serializer.StringEncoder")

    val kafkaConfig = new ProducerConfig(props)
    val producer = new Producer[String, String](kafkaConfig)

    (1 to 7).map(i => s"data/logs/aura2016120$i.log").foreach { path =>
      logInfo(s"read log from $path and replay to kafka $topic")
      FileUtil.readFileAsLines(path).foreach( line => {
        producer.send(new KeyedMessage[String, String](topic, line))
        log.debug(s"send message: $line")
      })
    }
  }

}
