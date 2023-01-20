package com.sns.comment.model;

import com.sns.user.model.User;

// 댓글 한개와 매핑
public class CommentView {
	
	// 댓글 한개
	private Comment comment;
	
	// 댓글 쓴 유저 정보
	private User user;

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
