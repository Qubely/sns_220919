package com.sns.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {
	
	public Integer selectLikeByPostId(int postId);
	
	public Integer selectLikeByUserIdPostId(
			@Param("userId") int userId
			, @Param("postId") int postId);
	
	public Boolean isDuplicantLikeByUserIdPostId(
			@Param("userId") int userId
			, @Param("postId") int postId);
	
	public int deleteLikeByUserId(
			@Param("userId") int userId
			, @Param("postId") int postId);
	
	public int insertLike(
			@Param("userId") int userId
			, @Param("postId") int postId);
	
	
}
