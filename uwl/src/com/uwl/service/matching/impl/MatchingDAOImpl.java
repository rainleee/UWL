package com.uwl.service.matching.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.uwl.common.Search;
import com.uwl.service.domain.Item;
import com.uwl.service.domain.Matching;

import com.uwl.service.matching.MatchingDAO;

@Repository("matchingDAOImpl")
public class MatchingDAOImpl implements MatchingDAO{
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public MatchingDAOImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public void addMatching(Matching matching) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("MatchingMapper.addMatching", matching);
	}

	@Override
	public Matching getMatching(String firstUserId) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("MatchingMapper.getMatching", firstUserId);
	}

	@Override
	public List<Matching> getMatchingList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("MatchingMapper.getMatchingList", search);
	}

	

	@Override
	public void updateMatchingStatus(Matching matching) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("MatchingMapper.updateMatchingStatus", matching);
	}

	@Override
	public int getTotalCount(Search search) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("MatchingMapper.getTotalCount", search);
	}

	@Override
	public int getTotalFlower(Search search, String firstUserId) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("search", search);
		map.put("firstUserId", firstUserId);
		return sqlSession.selectOne("MatchingMapeer.getTotalFlower", map);
	}

	@Override
	public Item getItem(int itemNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("MatchingMapper.getItem", itemNo);
	}

	@Override
	public List<Item> getItemList(Search search, String firstUserId) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("search", search);
		map.put("firstUserId", firstUserId);
		return sqlSession.selectList("MatchingMapper.getItemList", map);
	}

	

	

	@Override
	public void updateSpear(Item item) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("MatchingMapper.updateSpear", item);
	}

	@Override
	public void updateShield(Item item) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("MatchingMapper.updateShield", item);
	}

	@Override
	public int getTotalItem(Search search, String firstUserId) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("search", search);
		map.put("firstUserId", firstUserId);
		return sqlSession.selectOne("MatchingMapper.getTotalItem", map);
	}

	

}
