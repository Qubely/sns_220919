package com.sns.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sns.like.dao.LikeDAO;

@Controller
public class TestController {
	
	@Autowired
	private LikeDAO likeDAO;
	
	@GetMapping("/test1")
	@ResponseBody
	public String test1() {
		return "Hello world!!!";
	}
	
	@GetMapping("/test2")
	@ResponseBody
	public Map<String, Object> test2() {
		Map<String, Object> result = new HashMap<>();
		result.put("키1", 111);
		result.put("키2", 211);
		result.put("키3", 333);
		return result;
		
	}
	
	@GetMapping("/test3")
	public String test3() {
		return "test/test";
	}
	
	
}
