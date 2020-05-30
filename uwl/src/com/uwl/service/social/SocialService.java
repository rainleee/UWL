package com.uwl.service.social;

import java.util.List;
import java.util.Map;

import com.uwl.common.Search;
import com.uwl.service.domain.Post;

public interface SocialService {

	public void addTimeline(Post post) throws Exception;
	
	public Post getTimeline(int postNo) throws Exception;
	
	public Map<String,Object> getTimelineList(String userId, Search search) throws Exception;
	
	public void updateTimeline(Post post) throws Exception;
	
	public void deleteTimeline(int postNo) throws Exception;
	
	public void addQuestion(Post post) throws Exception;
	
	public void replyQuestion(Post post, int questionPostNo) throws Exception;
	
	public Map<String, Object> getAskList(String userId, Search search, String questionStatus) throws Exception;
	
	public void rejectQuestion(int postNo) throws Exception;
}
