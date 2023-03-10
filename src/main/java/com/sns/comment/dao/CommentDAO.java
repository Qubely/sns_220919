package com.sns.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.comment.model.Comment;

@Repository
public interface CommentDAO {

	public int insertComment(
			@Param("postId") int postId,
			@Param("content") String content,
			@Param("userId") int userId);
	
	public List<Comment> selectCommentList();
	
	public List<Comment> selectCommentListByPostId(int postId);
	
	public void deleteCommentByPostId(int postId);
	
	public int deleteCommentByIdPostIdUserId(
			@Param("commentId") int commentId,
			@Param("postId") int postId,
			@Param("userId") int userId);
	
}
