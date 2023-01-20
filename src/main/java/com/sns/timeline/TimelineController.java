package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sns.comment.bo.CommentBO;
import com.sns.post.bo.PostBO;
import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.model.CardView;

@Controller
public class TimelineController {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private TimelineBO timelineBO;
	
	@GetMapping("/timeline/timeline_view")
	public String timelineView(Model model) {
		model.addAttribute("viewName", "timeline/timeline");
		
//		List<Post> postList = postBO.getPostList();
//		model.addAttribute("postList", postList);
		
//		List<Comment> commentList = commentBO.getCommentList();
//		model.addAttribute("commentList", commentList);
		
		List<CardView> cardList = timelineBO.generateCardList();
		model.addAttribute("cardList", cardList);
		
		
		return "template/layout";
	}
	
}
