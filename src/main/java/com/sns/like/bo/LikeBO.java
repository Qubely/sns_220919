package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeDAO;

@Service
public class LikeBO {
	
	@Autowired
	private LikeDAO likeDAO;
	
	public Integer getLikeByPostId(int postId) {
		return likeDAO.selectLikeByPostId(postId);
	}
	
	public Integer getLikeByUserIdPostId(int userId, int postId) {
		return likeDAO.selectLikeByUserIdPostId(userId, postId);
	}
	
	public Boolean isDuplicantLikeByUserIdPostId(int userId, int postId) {
		return likeDAO.isDuplicantLikeByUserIdPostId(userId, postId);
	}
	
	public int deleteLikeByUserIdPostId(int userId, int postId) {
		return likeDAO.deleteLikeByUserId(userId, postId);
	}
	
	public int addLike(int userId, int postId) {
		return likeDAO.insertLike(userId, postId);
	}
	
}
