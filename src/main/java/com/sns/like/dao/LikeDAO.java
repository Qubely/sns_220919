package com.sns.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {
	
	
//	public Integer selectLikeByUserIdPostId(
//			@Param("userId") int userId
//			, @Param("postId") int postId);
	
//	public Boolean isDuplicantLikeByUserIdPostId(
//			@Param("userId") int userId
//			, @Param("postId") int postId);
	
//	public int deleteLikeByUserId(
//			@Param("userId") int userId
//			, @Param("postId") int postId);
	
//	public int insertLike(
//			@Param("userId") int userId
//			, @Param("postId") int postId);
	
//	public Integer selectLikeByPostId(int postId);
	
//	public boolean existLike(
//			@Param("postId") int postId, 
//			@Param("userId") int userId);
	
	public void insertLike(
			@Param("postId") int postId, 
			@Param("userId") int userId);
	
	public void deleteLikeByPostIdUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId);
	
	public int selectLikeCountByPostIdOrUserId(
			@Param("postId") int postId, 
			@Param("userId") Integer userId);
	
	
}
