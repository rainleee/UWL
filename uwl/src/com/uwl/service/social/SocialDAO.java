package com.uwl.service.social;

import java.util.List;

import com.uwl.common.Search;
import com.uwl.service.domain.Ask;
import com.uwl.service.domain.Post;

public interface SocialDAO {

	public void addTimeline(Post post) throws Exception;
	
	public Post getTimeline(int postNo) throws Exception;
	
	public List<Post> getTimelineList(String userId, Search search) throws Exception;
	
	public void updateTimeline(Post post) throws Exception;
	
	public void deleteTimeline(int postNo) throws Exception;
	
	public int getTimelineTotalCount(String userId, Search search) throws Exception;
	
	public void addQuestion(Post post) throws Exception;
	
	public void replyQuestion(Post post) throws Exception;
	
	public void updateQuestionStatus(int postNo) throws Exception;
	
	public List<Ask> getAskList(String userId, Search search, String questionStatus) throws Exception;
	
	public void rejectQuestion(int postNo) throws Exception;
	
	public int getAskTotalCount(String userId, String questionStatus) throws Exception;
}
