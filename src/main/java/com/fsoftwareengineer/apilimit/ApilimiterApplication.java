package com.fsoftwareengineer.apilimit;

import com.fsoftwareengineer.apilimit.ratelimiter.TokenBucketLimiter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApilimiterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApilimiterApplication.class, args);
	}

}
