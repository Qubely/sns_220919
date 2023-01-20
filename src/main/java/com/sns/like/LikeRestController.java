package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sns.like.bo.LikeBO;

import jakarta.servlet.http.HttpSession;

@RestController
public class LikeRestController {
	
	@Autowired
	private LikeBO likeBO;
	
	// /like?postId=13  @RequestParam
	// /like/13         @PathVariable
	@GetMapping("/like/{postId}")
	public Map<String, Object> like(
			@PathVariable int postId
			, HttpSession session) {
		
		Map<String, Object> result = new HashMap<>();
		
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId != null) {
			Integer rowCount = likeBO.getLikeByUserId(userId);
			if (rowCount > 0) {
				rowCount = likeBO.deleteLikeByUserId(userId);
			} else {
				rowCount = likeBO.addLike(userId, postId);
			}
			if (rowCount > 0) {
				result.put("code", 1);
				result.put("result", "성공");
			} else {
				result.put("code", 500);
				result.put("errorMessage", "좋아요 실패. 관리자에게 문의하세요.");
			}
		} else {
			result.put("code", 500);
			result.put("errorMessage", "좋아요는 로그인 후 누를 수 있습니다.");
		}
		
		
		return result;
	}
	
}
