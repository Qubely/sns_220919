package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/comment")
@RestController
public class CommentRestController {
	
	@Autowired
	private CommentBO commentBO;
	
	@PostMapping("/create")
	public Map<String, Object> createComment(
			@RequestParam("postId") int postId
			, @RequestParam("content") String content
			, HttpSession session) {
		
		int userId = (int)session.getAttribute("userId");
		int row = commentBO.addComment(postId, content, userId);
		
		Map<String, Object> result = new HashMap<>();
		if (row > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "댓글 추가에 실패했습니다. 관리자에게 문의하세요.");
		}
		
		return result;
	}
	
}
