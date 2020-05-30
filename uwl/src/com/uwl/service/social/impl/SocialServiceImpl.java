package com.uwl.service.social.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uwl.common.Search;
import com.uwl.service.domain.Ask;
import com.uwl.service.domain.Post;
import com.uwl.service.social.SocialDAO;
import com.uwl.service.social.SocialService;

@Service("socialServiceImpl")
public class SocialServiceImpl implements SocialService{

	@Autowired
	@Qualifier("socialDAOImpl")
	private SocialDAO socialDAO;
	public void setSocialDAO(SocialDAO socialDAO) {
		this.socialDAO = socialDAO;
	}
	
	@Override
	public void addTimeline(Post post) throws Exception {
		socialDAO.addTimeline(post);
	}
	@Override
	public Post getTimeline(int postNo) throws Exception {
		return socialDAO.getTimeline(postNo);
	}
	//searchCondition = 0  : 공개한 게시글만 출력   1 : 나만 보기한 게시글 까지 출력 
	@Override
	public Map<String, Object> getTimelineList(String userId, Search search) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		int totalCount = socialDAO.getTimelineTotalCount(userId, search);
		List<Post> list =  socialDAO.getTimelineList(userId, search);
		map.put("totalCount", totalCount);
		 map.put("list", list);
		return map;
	}
	
	@Override
	public void updateTimeline(Post post) throws Exception {
		socialDAO.updateTimeline(post);
	}
	@Override
	public void deleteTimeline(int postNo) throws Exception {
		socialDAO.deleteTimeline(postNo);
	}
	
	@Override
	public void addQuestion(Post post) throws Exception {
		socialDAO.addQuestion(post);
	}
	
	@Override
	public void replyQuestion(Post post, int questionPostNo) throws Exception {
		socialDAO.replyQuestion(post);
		socialDAO.updateQuestionStatus(questionPostNo);
	}
	// questionStatus 1. 미답변된 질문목록  2. 답변된 질문 목록
	@Override
	public Map<String, Object> getAskList(String userId, Search search, String questionStatus) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		int totalCount = socialDAO.getAskTotalCount(userId, questionStatus);
		List<Ask> list =  socialDAO.getAskList(userId, search, questionStatus);
		map.put("list", list);
		map.put("totalCount", totalCount);
		
		return map;
	}
	
	@Override
	public void rejectQuestion(int postNo) throws Exception {
		socialDAO.rejectQuestion(postNo);
	}

}
