package com.uwl.service.challenge.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.uwl.common.Search;
import com.uwl.service.challenge.ChallengeDAO;
import com.uwl.service.domain.Challenge;
import com.uwl.service.domain.Reward;
import com.uwl.service.domain.SchoolRank;

@Repository("challengeDAOImpl")
public class ChallengeDAOImpl implements ChallengeDAO{
	
	//Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	//Constructor
	public ChallengeDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	//setSqlSession
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	

	@Override
	public void addChallenge(Challenge challenge) throws Exception {
		sqlSession.insert("ChallengeMapper.addChallenge", challenge);
	}
	
	@Override
	public void updateChallenge(Challenge challenge) throws Exception {
		sqlSession.update("ChallengeMapper.updateChallenge", challenge);
	}
	
	@Override
	public void deleteChallenge(Challenge challenge) throws Exception {
		sqlSession.update("ChallengeMapper.deleteChallenge", challenge);
	}
	
	@Override
	public List<Challenge> getAdminChallengeList(Search search) throws Exception {
		return sqlSession.selectList("ChallengeMapper.getAdminChallengeList",search);
	}

	@Override
	public Challenge getChallengeAdmin(int challNo) throws Exception {
		return sqlSession.selectOne("ChallengeMapper.getChallengeAdmin", challNo);
	}

	@Override
	public List<Challenge> getChallengeList() throws Exception {
		return sqlSession.selectList("ChallengeMapper.getChallengeList");
	}

	@Override
	public Map<String, Object> getCompleteChallengeList(Search search, String userId) throws Exception {
		
		Map<String, Object> completeMap = new HashMap<String, Object>();
		
		System.out.println("getCompleteChallengeList의 search : " + search);
		System.out.println("getCompleteChallengeList의 userId : " + userId);
		
		completeMap.put("search", search);
		completeMap.put("userId", userId);
		
		List<Reward> completeList = sqlSession.selectList("ChallengeMapper.getCompleteChallengeList", completeMap);
		
		completeMap.put("totalCount", sqlSession.selectOne("ChallengeMapper.getTotalCount", search));
		completeMap.put("list", completeList);
		
		return completeMap;
	}


	@Override
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("ChallengeMapper.getTotalCount",search);
	}

	@Override
	public int getTotalCountOne(String userId) throws Exception {
		return sqlSession.selectOne("ChallengeMapper.getTotalCountOne",userId);
	}
	
	
	


}
