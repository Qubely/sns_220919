package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeDAO;

@Service
public class LikeBO {
	
	@Autowired
	private LikeDAO likeDAO;
	
	public Integer getLikeCountByPostId(int postId) {
		return likeDAO.selectLikeCountByPostIdOrUserId(postId, null);
	}
	
	public boolean existLike(Integer userId, int postId) {
		if (userId == null) {
			return false;
		}
		return likeDAO.selectLikeCountByPostIdOrUserId(postId, userId) > 0 ? true : false;
	}
	
	public void likeToggle(int postId, int userId) {
		// 좋아요 있는지 확인
		if (likeDAO.selectLikeCountByPostIdOrUserId(postId, userId) > 0) {
			// 있으면 제거
			likeDAO.deleteLikeByPostIdUserId(postId, userId);
		} else {
			// 없으면 추가
			likeDAO.insertLike(postId, userId);
		}
	}
	
	
//	public Integer getLikeByUserIdPostId(int userId, int postId) {
//		return likeDAO.selectLikeByUserIdPostId(userId, postId);
//	}
	
//	public int deleteLikeByUserIdPostId(int userId, int postId) {
//		return likeDAO.deleteLikeByUserId(userId, postId);
//	}
//	
//	public int addLike(int userId, int postId) {
//		return likeDAO.insertLike(userId, postId);
//	}
	
	
	
}
