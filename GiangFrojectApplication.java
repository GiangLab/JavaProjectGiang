package com.example.GiangFroject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync //  Thêm dòng này để bật xử lý bất đồng bộ
public class GiangFrojectApplication {
	public static void main(String[] args) {
		SpringApplication.run(GiangFrojectApplication.class, args);
	}
}
