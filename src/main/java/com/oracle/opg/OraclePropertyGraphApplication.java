package com.oracle.opg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.oracle.*"})
public class OraclePropertyGraphApplication {

	public static void main(String[] args) {

		SpringApplication.run(OraclePropertyGraphApplication.class, args);
		System.out.println("---------started--------------");
	}

}
