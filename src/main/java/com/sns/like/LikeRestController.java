package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
public class LikeRestController {
	
	// /like?postId=13  @RequestParam
	// /like/13         @PathVariable
	@GetMapping("/like/{postId}")
	public Map<String, Object> like(
			@PathVariable int postId
			, HttpSession session) {
		
		int userId = (int)session.getAttribute("userId");
		
		
		Map<String, Object> result = new HashMap<>();
		
		return result;
	}
	
}