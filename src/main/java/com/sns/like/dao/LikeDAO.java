package com.sns.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {
	
	public Integer selectLikeByUserId(int userId);
	
	public int deleteLikeByUserId(int userId);
	
	public int insertLike(
			@Param("userId") int userId
			, @Param("postId") int postId);
}
