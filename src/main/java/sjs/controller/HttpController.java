package sjs.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sjs.User;

@RestController
@EnableAutoConfiguration
@RequestMapping("/http")
public class HttpController extends BaseController{
	
	@RequestMapping("/index")
	public String index() {
		return "Index Page";
	}

	@RequestMapping("/user")
	public String user(User user) {
		return "Hello World!" + user.getAge() + " age " + user.getName();
	}
	
	@RequestMapping("/json")
	public String json() throws Exception{
		return super.getJsonParam().toString();
	}
}
