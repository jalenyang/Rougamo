package org.pork.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@EnableAutoConfiguration
@SpringBootApplication
public class Application {

    @Autowired
    private KafkaTemplate<String, String> template;

    @RequestMapping("/send")
    @ResponseBody
    String send(String topic, String key, String data) {
        System.out.println("trying to send now...");
        template.send(topic, key, data);
        return "success";
    }

    @KafkaListener(topics = "terminalEvent")
    public void consumer(ConsumerRecord<?, ?> cr) {
        System.out.println(String.format("topic:%s, key: %s, value: %s",cr.topic(),cr.key(),cr.value()));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
