package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.model.CommentView;
import com.sns.like.bo.LikeBO;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.timeline.model.CardView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class TimelineBO {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	// 로그인이 되지 않은 사람도 카드 목록이 보여야 한다.
	public List<CardView> generateCardList(Integer userId) {
		List<CardView> cardViewList = new ArrayList<>();
		
		// 글목록 가져오기(post)
		// postList 반복문 => CardView => cardViewList에 넣는다.
		
		List<Post> postList = postBO.getPostList();
		for (Post post : postList) {
			CardView card = new CardView();
			
			// 글 내용
			card.setPost(post);
			
			// 글쓴이 정보
			User user = userBO.getUserById(post.getUserId());
			card.setUser(user);
			
			// 댓글
			List<CommentView> commentList = commentBO.generateCommentViewByPostId(post.getId());
			card.setCommentList(commentList);
			
			// 내가 좋아요를 눌렀는지 filledLike
			if (userId != null) {
				boolean filledLike = likeBO.isDuplicantLikeByUserIdPostId(userId, post.getId());
				card.setFilledLike(filledLike);
			}
			
			// 좋아요 개수
			Integer likeCount = likeBO.getLikeByPostId(post.getId());
			card.setLikeCount(likeCount);
			
			cardViewList.add(card);
		}
		
		return cardViewList;
	}
	
}
