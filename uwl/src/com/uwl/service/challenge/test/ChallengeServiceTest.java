package com.uwl.service.challenge.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uwl.common.Search;
import com.uwl.service.challenge.ChallengeDAO;
import com.uwl.service.challenge.ChallengeService;
import com.uwl.service.domain.Challenge;
import com.uwl.service.domain.Reward;
import com.uwl.service.domain.User;
import com.uwl.service.reward.RewardDAO;
import com.uwl.service.reward.RewardService;
import com.uwl.service.user.UserService;



/*
 *	FileName :  UserServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)

//==> Meta-Data 를 다양하게 Wiring 하자...
//@ContextConfiguration(locations = { "classpath:config/context-*.xml" })
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
																	"classpath:config/context-aspect.xml",
																	"classpath:config/context-mybatis.xml",
																	"classpath:config/context-transaction.xml" })
public class ChallengeServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("challengeServiceImpl")
	private ChallengeService challengeService;
	
	@Autowired
	@Qualifier("rewardServiceImpl")
	private RewardService rewardService;
	
	//@Test
	public void testAddChallenge()throws Exception{
		
		Challenge challenge = new Challenge();
		//challenge.setChallNo(10010);
		challenge.setChallCategory("2");
		challenge.setChallTitle("TEST 제목입니다");
		challenge.setChallContent("TEST 내용입니다.");
		challenge.setChallReward(6000);
		
		challengeService.addChallenge(challenge);
		
	
		
//		challengeService.getChallengeAdmin(challNo);
//		
		Assert.assertEquals("2", challenge.getChallCategory());
		Assert.assertEquals("TEST 제목입니다", challenge.getChallTitle());
		Assert.assertEquals("TEST 내용입니다.", challenge.getChallContent());
	}
	
	//@Test
	public void testUpdateChallenge() throws Exception{
		
		Challenge challenge = challengeService.getChallengeAdmin(10011);
		
		Assert.assertEquals("2", challenge.getChallCategory());
		Assert.assertEquals("TEST 제목입니다", challenge.getChallTitle());
		Assert.assertEquals("TEST 내용입니다.", challenge.getChallContent());
		
		System.out.println("testupdateChallenge 전 : " + challenge);
		
		challenge.setChallCategory("3");
		challenge.setChallTitle("TEST UPDATE제목입니다");
		challenge.setChallContent("TEST UPDATE내용입니다.");
		challenge.setChallReward(3000);
		
		challengeService.updateChallenge(challenge);
		
//		Assert.assertEquals("2", challenge.getChallCategory());
//		Assert.assertEquals("TEST 제목입니다", challenge.getChallTitle());
//		Assert.assertEquals("TEST 내용입니다.", challenge.getChallContent());
		
		System.out.println("testupdateChallenge 후 : " + challenge);
	}
	
	//@Test
	public void testGetChallengeAdmin() throws Exception{
		
		Challenge challenge = new Challenge();
		challenge.setChallNo(10001);
//		challenge.setChallCategory("3");
//		challenge.setChallTitle("TEST UPDATE제목입니다");
//		challenge.setChallContent("TEST UPDATE내용입니다.");
//		challenge.setChallReward(3000);
		
		challenge = challengeService.getChallengeAdmin(challenge.getChallNo());
		
//		Assert.assertEquals("3", challenge.getChallCategory());
//		Assert.assertEquals("TEST UPDATE제목입니다", challenge.getChallTitle());
//		Assert.assertEquals("TEST UPDATE내용입니다.", challenge.getChallContent());
//		Assert.assertEquals(3000, challenge.getChallReward());
	}
	
	//@Test
	public void testDeleteChallenge() throws Exception{
		
		Challenge challenge = challengeService.getChallengeAdmin(10001);
		
		Assert.assertEquals("1", challenge.getChallCategory());
		Assert.assertEquals("제목2", challenge.getChallTitle());
		Assert.assertEquals("내용엔어떤내용이들어있나요", challenge.getChallContent());
		
		System.out.println("testupdateChallenge 전 : " + challenge);
		
		challenge.setViewStatus("3");
		
		challengeService.updateChallenge(challenge);
		
		
		System.out.println("testupdateChallenge 후 : " + challenge);
		
	}
	
	//@Test
	public void testGetAdminChallengeList() throws Exception{
		
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("2");
		search.setSearchKeyword("업데이트");
		
		Map<String, Object> map = challengeService.getAdminChallengeList(search);
		
		List<Object> list = (List<Object>) map.get("list");
		Assert.assertEquals(8, list.size());
		
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
	}
	
	//@Test
	public void testGetChallengeList() throws Exception{
		
		List<Challenge> list = challengeService.getChallengeList();
		
		System.out.println("담긴정보 확인 : " + list);
		
	}
	
	//@Test
	public void testGetCompleteChallengeList() throws Exception{
		
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(5);
		
		String userId = "user41";
		
		Map<String, Object> map = challengeService.getCompleteChallengeList(search, userId);
		
		List<Reward> list = (ArrayList<Reward>)map.get("list");
		
		
		System.out.println("testGetCompleteChallengeList list : " + list.size());
		System.out.println("map 담긴정보 확인 : " + map);
		
		Assert.assertEquals(3, list.size());
		
	}
	
	@Test
	public void testCompleteChallenge() throws Exception{
		
		Challenge challenge = new Challenge();
		challenge.setChallNo(10004);
		
		Reward reward = new Reward();
		reward.setUserId("user55");
		reward.setChallenge(challenge);
		
		rewardService.increasePoint(reward);
		
		Assert.assertEquals(10004, challenge.getChallNo());
	
		
	}
	
}
