package com.uwl.service.reward.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.uwl.service.domain.Reward;
import com.uwl.service.reward.RewardDAO;

@Repository("rewardDAOImpl")
public class RewardDAOImpl implements RewardDAO{
	
	//field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	//constructor
	public RewardDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	//setSqlSession
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//포인트, 활동점수 둘 다 이 method를 이용함
	@Override
	public List<Reward> getUserBothPointList(String userId) throws Exception {
		return sqlSession.selectList("RewardMapper.getUserBothPointList", userId);
	}

	@Override
	public void increasePoint(Reward reward) throws Exception {
		sqlSession.insert("RewardMapper.increasePoint", reward);
	}

	@Override
	public void decreasePoint(Reward reward) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	
	


}
