package com.example.rabbitdemo;

import com.example.rabbitdemo.test.Producer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RabbitDemoApplication {

	private final Producer producer;

	public RabbitDemoApplication(Producer producer) {
		this.producer = producer;
	}

	public static void main(String[] args) {
		SpringApplication.run(RabbitDemoApplication.class, args);
	}

	@GetMapping("/send")
	public String send() {
		producer.publish("hello rabbit");
		return "ok";
	}

}
