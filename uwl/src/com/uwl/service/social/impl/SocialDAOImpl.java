package com.uwl.service.social.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.uwl.common.Search;
import com.uwl.service.domain.Ask;
import com.uwl.service.domain.Post;
import com.uwl.service.social.SocialDAO;

@Repository("socialDAOImpl")
public class SocialDAOImpl implements SocialDAO{

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void addTimeline(Post post) throws Exception {
		sqlSession.insert("SocialMapper.addTimeline", post);
	}
	
	@Override
	public Post getTimeline(int postNo) throws Exception {
		return sqlSession.selectOne("SocialMapper.getTimeline", postNo);
	}
	
	@Override
	public List<Post> getTimelineList(String userId, Search search) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("search", search);
		return sqlSession.selectList("SocialMapper.getTimelineList", map);
	}
	
	@Override
	public void updateTimeline(Post post) throws Exception {
		sqlSession.update("SocialMapper.updateTimeline", post);
	}
	
	@Override
	public void deleteTimeline(int postNo) throws Exception {
		sqlSession.update("SocialMapper.deleteTimeline", postNo);
	}
	
	@Override
	public int getTimelineTotalCount(String userId, Search search) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("search", search);
		return sqlSession.selectOne("SocialMapper.getTimelineTotalCount", map);
	}
	
	@Override
	public void addQuestion(Post post) throws Exception {
		sqlSession.insert("SocialMapper.addQuestion", post);
	}
	
	@Override
	public void replyQuestion(Post post) throws Exception {
		sqlSession.insert("SocialMapper.replyQuestion", post);
	}
	
	@Override
	public void updateQuestionStatus(int postNo) throws Exception {
		sqlSession.update("SocialMapper.updateQuestionStatus", postNo);
	}
	
	@Override
	public List<Ask> getAskList(String userId, Search search, String questionStatus) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("search", search);
		map.put("questionStatus", questionStatus);
		return sqlSession.selectList("SocialMapper.getAskList", map);
	}
	
	@Override
	public void rejectQuestion(int postNo) throws Exception {
		sqlSession.delete("SocialMapper.rejectQuestion", postNo);
	}
	
	@Override
	public int getAskTotalCount(String userId, String questionStatus) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("questionStatus", questionStatus);
		return sqlSession.selectOne("SocialMapper.getAskTotalCount", map);
	}

}
