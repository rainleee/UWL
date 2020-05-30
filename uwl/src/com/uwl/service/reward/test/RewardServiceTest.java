package com.uwl.service.reward.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uwl.service.domain.Challenge;
import com.uwl.service.domain.Reward;
import com.uwl.service.domain.User;
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
public class RewardServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("rewardServiceImpl")
	private RewardService rewardService;
	
	//@Test
	public void getUserBothPointList()throws Exception{
		List<Reward> rewardUser = new ArrayList<Reward>();
		rewardUser = rewardService.getUserBothPointList("user01");
		
		Assert.assertEquals(4,rewardUser.size());
		//System.out.println("===>" + rewardUser.size());
	}
	
	@Test
	public void testIncreasePoint() throws Exception{
		
		Challenge challenge = new Challenge();
		challenge.setChallNo(10002);
		
		Reward reward = new Reward();
		reward.setUserId("user60");
		reward.setChallenge(challenge);
		
		rewardService.increasePoint(reward);
		
		Assert.assertEquals(10002, challenge.getChallNo());
	
		
		
	}
	
	
}
