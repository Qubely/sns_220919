package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeDAO;

@Service
public class LikeBO {
	
	@Autowired
	private LikeDAO likeDAO;
	
	public Integer getLikeByUserId(int userId) {
		return likeDAO.selectLikeByUserId(userId);
	}
	
	public int deleteLikeByUserId(int userId) {
		return likeDAO.deleteLikeByUserId(userId);
	}
	
	public int addLike(int userId, int postId) {
		return likeDAO.insertLike(userId, postId);
	}
	
}
