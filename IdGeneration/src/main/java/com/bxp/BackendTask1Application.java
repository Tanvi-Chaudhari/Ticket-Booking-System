//package com.bxp;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@SpringBootApplication
//public class BackendTask1Application {
//
//	public static void main(String[] args) {
//		SpringApplication.run(BackendTask1Application.class, args);
//	}
//
//}
//
//// Define a RestController to handle HTTP requests
//@RestController
//@RequestMapping("/api") // Optional, base path for the controller
//class WelcomeController {
//
//	@GetMapping("/welcome")
//	public String welcome() {
//		return "Hello World";
//	}
//}

package com.bxp;

import java.time.LocalDate;
import java.time.LocalTime;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.bxp.dto.CustomerDTO;
import com.bxp.service.CustomerServiceImpl;

@SpringBootApplication
public class BackendTask1Application implements CommandLineRunner {

	public static final Log LOGGER = LogFactory.getLog(BackendTask1Application.class);

	@Autowired
	CustomerServiceImpl customerService;

	@Autowired
	Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(BackendTask1Application.class,args);
	}

	public void run(String... args) throws Exception {
		addCustomer();
	}

	public void addCustomer() {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setEmailId("Trushi006@gmail.com");
		customerDTO.setName("Trushi");
		customerDTO.setCreationTime(LocalTime.now());
		try {
			Integer id = customerService.addCustomer(customerDTO);
			System.out.println("--------------------------------------------------");
			System.out.println( "Ticket generated successfully with id:  " + id);
			System.out.println("--------------------------------------------------");
		} catch (Exception e) {
			if (e.getMessage() != null)
				System.out.println(e);
			LOGGER.info(environment.getProperty(e.getMessage(),"Something went wrong."));
		}

	}

}
