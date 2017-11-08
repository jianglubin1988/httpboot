package sjs.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@RestController
@SpringBootApplication
//@EnableAutoConfiguration
public class App {

//	@RequestMapping(value = "/home", method = RequestMethod.GET)
//	@ResponseBody
//	public String home() {
//		return "Hello, Spring Boot";
//	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
}
