package com.uwl.service.matching;

import java.util.List;
import java.util.Map;

import com.uwl.common.Search;
import com.uwl.service.domain.Item;
import com.uwl.service.domain.Matching;

public interface MatchingService {

	public void addMatching(Matching matching) throws Exception;
	
	public Matching getMatching(String firstUserId) throws Exception;
	
	public Map<String, Object> getMatchingList(Search search) throws Exception;
	
	public void updateMatchingStatus(Matching matching) throws Exception;
	
	
	
	public Item getItem(int itemNo) throws Exception;
	
	public Map<String, Object> getItemList(Search search, String firstUserId) throws Exception;
	
	public void updateSpear(Item item) throws Exception;
	
	public void updateShield(Item item) throws Exception;
	
	
}
