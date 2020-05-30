package com.uwl.service.matching;

import java.util.List;

import com.uwl.common.Search;
import com.uwl.service.domain.Item;
import com.uwl.service.domain.Matching;


public interface MatchingDAO {

	public void addMatching(Matching matching) throws Exception;
	
	public Matching getMatching(String firstUserId) throws Exception;
	
	public List<Matching> getMatchingList(Search search) throws Exception;
	
	
	
	public void updateMatchingStatus(Matching matching) throws Exception;
	
	public int getTotalCount(Search search) throws Exception;
	
	public int getTotalFlower(Search search, String firstUserId) throws Exception;
	
	public Item getItem(int itemNo) throws Exception;
	
	public List<Item> getItemList(Search search, String firstUseId) throws Exception;
	
	public void updateSpear(Item item) throws Exception;
	
	public void updateShield(Item item) throws Exception;
	
	public int getTotalItem(Search search, String firstUserId) throws Exception;
}
