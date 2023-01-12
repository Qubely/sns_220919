package com.sns.like.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {
	
	public List<Map<String, Object>> selectLikeListTest();
	
}
