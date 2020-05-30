package com.uwl.service.matching.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uwl.common.Search;
import com.uwl.service.domain.Item;
import com.uwl.service.domain.Matching;
import com.uwl.service.matching.MatchingDAO;
import com.uwl.service.matching.MatchingService;

@Service("matchingServiceImpl")
public class MatchingServiceImpl implements MatchingService{
	
	@Autowired
	@Qualifier("matchingDAOImpl")
	private MatchingDAO matchingDAO;
	public void setMatchingDAO(MatchingDAO matchingDAO) {
		this.matchingDAO = matchingDAO;
	}
	
	public MatchingServiceImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public void addMatching(Matching matching) throws Exception {
		// TODO Auto-generated method stub
		matchingDAO.addMatching(matching);
	}

	@Override
	public Matching getMatching(String firstUserId) throws Exception {
		// TODO Auto-generated method stub
		return matchingDAO.getMatching(firstUserId);
	}

	@Override
	public Map<String, Object> getMatchingList(Search search) throws Exception {
		// TODO Auto-generated method stub
		List<Matching> list = matchingDAO.getMatchingList(search);
		int totalCount = matchingDAO.getTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));
		return map;
	}

	@Override
	public void updateMatchingStatus(Matching matching) throws Exception {
		// TODO Auto-generated method stub
		matchingDAO.updateMatchingStatus(matching);
	}

	

	@Override
	public Item getItem(int itemNo) throws Exception {
		// TODO Auto-generated method stub
		return matchingDAO.getItem(itemNo);
	}

	@Override
	public Map<String, Object> getItemList(Search search, String firstUserId) throws Exception {
		// TODO Auto-generated method stub
		List<Item> list = matchingDAO.getItemList(search, firstUserId);
		int totalItem = matchingDAO.getTotalItem(search, firstUserId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalItem", new Integer(totalItem));
		
		return map;
	}

	@Override
	public void updateSpear(Item item) throws Exception {
		// TODO Auto-generated method stub
		matchingDAO.updateSpear(item);
	}

	@Override
	public void updateShield(Item item) throws Exception {
		// TODO Auto-generated method stub
		matchingDAO.updateShield(item);
	}

	

}
