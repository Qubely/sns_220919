package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sns.comment.bo.CommentBO;
import com.sns.like.bo.LikeBO;
import com.sns.post.bo.PostBO;
import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.model.CardView;

import jakarta.servlet.http.HttpSession;

@Controller
public class TimelineController {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private TimelineBO timelineBO;
	
	@Autowired
	private LikeBO likeBO; 
	
	@GetMapping("/timeline/timeline_view")
	public String timelineView(
			Model model
			, HttpSession session) {
		model.addAttribute("viewName", "timeline/timeline");
		
		List<CardView> cardList = timelineBO.generateCardList();
		model.addAttribute("cardList", cardList);
		
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId != null) {
			Integer rowCount = likeBO.getLikeByUserId(userId);
			if (rowCount != null) {
				model.addAttribute("isLike", rowCount);
			}
		}
		
		return "template/layout";
	}
	
}
