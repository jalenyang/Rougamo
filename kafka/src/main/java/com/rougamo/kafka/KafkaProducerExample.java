package com.rougamo.kafka;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaProducerExample {
    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.140.0.61:9092");
        props.put("client.id", "DemoProducer");
        props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        int messageNo = 1;
        while (true) {
            String messageStr = "Message_" + messageNo;
            try {
                producer.send(new ProducerRecord("streams-plaintext-input", messageNo, messageStr)).get();
                System.out.println("Sent message: (" + messageNo + ", " + messageStr + ")");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            ++messageNo;
            Thread.sleep(60000);
        }
    }
}
