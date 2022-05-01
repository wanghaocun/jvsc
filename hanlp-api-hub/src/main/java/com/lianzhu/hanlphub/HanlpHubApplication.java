package com.lianzhu.hanlphub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author wanghc
 * @since 2020-07-28
 **/
@SpringBootApplication
@EnableScheduling
public class HanlpHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(HanlpHubApplication.class, args);
	}

}
