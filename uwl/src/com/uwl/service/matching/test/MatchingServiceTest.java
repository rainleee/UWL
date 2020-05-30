package com.uwl.service.matching.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uwl.common.Search;
import com.uwl.service.domain.Item;
import com.uwl.service.domain.Matching;
import com.uwl.service.matching.MatchingService;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })

public class MatchingServiceTest {

	@Autowired
	@Qualifier("matchingServiceImpl")
	private MatchingService matchingService;
	
	//@Test
	public void testAddMatching() throws Exception {
		
		Matching matching = new Matching();
		matching.setFirstUserId("user02");
		matching.setSecondUserId("user07");
		
		matchingService.addMatching(matching);
		
		matching = matchingService.getMatching("user02");
		
		Assert.assertEquals("user02", matching.getFirstUserId());
		Assert.assertEquals("user07", matching.getSecondUserId());
	}
	
	//@Test
	public void testGetMatching() throws Exception {
		
		Matching matching = new Matching();
		
		matching = matchingService.getMatching("user02");
		
		Assert.assertEquals("user02", matching.getFirstUserId());
		Assert.assertEquals("user07", matching.getSecondUserId());
		
		Assert.assertNotNull(matchingService.getMatching("user02"));
	}
	
	//@Test
	public void testUpdateMatchingStatus() throws Exception {
		
		Matching matching = matchingService.getMatching("user02");
		Assert.assertNotNull(matching);
		
		Assert.assertEquals("user02", matching.getFirstUserId());
		Assert.assertEquals("user07", matching.getSecondUserId());
		
		matching.setMatchingStatus("1");
		
		matchingService.updateMatchingStatus(matching);
		
		matching = matchingService.getMatching("user02");
		Assert.assertNotNull(matching);
		
		Assert.assertEquals("user02", matching.getFirstUserId());
		Assert.assertEquals("user07", matching.getSecondUserId());
		Assert.assertEquals("1", matching.getMatchingStatus());
	}
	
	@Test
	public void testGetMatchingList() throws Exception {
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		String firstUserId = "user02";
		System.out.println("firstUserId : " + firstUserId);
		Map<String, Object> map = matchingService.getMatchingList(search);
		System.out.println("map : " + map);
		
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(2, list.size());
		System.out.println("list : " + list);
		
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println("totalCount : " + totalCount);
	}
	
	//@Test
	public void testGetItem() throws Exception {
		Item item = new Item();
		
		item = matchingService.getItem(10001);
		
		Assert.assertEquals(10001, item.getItemNo());
		Assert.assertEquals("user02", item.getFirstUserId());
		Assert.assertEquals("1", item.getItemCategory());
		Assert.assertEquals("1", item.getItemDefault());
		
		Assert.assertNotNull(10001);
	}
	
	//@Test
	public void testGetItemList() throws Exception {
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		String firstUserId = "user02";
		System.out.println("firstUserId : " + firstUserId);
		Map<String, Object> map = matchingService.getItemList(search, firstUserId);
		System.out.println("map : " + map);
		
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(2, list.size());
		System.out.println("list : " + list);
		
		Integer totalItem = (Integer)map.get("totalItem");
		System.out.println("totalItem : " + totalItem);
	}
	
	//@Test
	public void testUpdateSpear() throws Exception {
		Item item = matchingService.getItem(10001);
		
		item.setSecondUserId("user07");
		item.setUseResult("3");
		
		matchingService.updateSpear(item);
		
		item = matchingService.getItem(10001);
		Assert.assertNotNull(item);
		
		Assert.assertEquals("user02", item.getFirstUserId());
		Assert.assertEquals("user07", item.getSecondUserId());
		Assert.assertEquals("3", item.getUseResult());
		
	}
	
	//@Test
	public void testUpdateShield() throws Exception {
		Item item = matchingService.getItem(10046);
		
		item.setSecondUserId("user02");
		
		matchingService.updateShield(item);
		
		item = matchingService.getItem(10046);
		Assert.assertNotNull(item);
		
		Assert.assertEquals("user07", item.getFirstUserId());
		Assert.assertEquals("user02", item.getSecondUserId());
	}
}
