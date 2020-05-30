package com.uwl.service.challenge.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uwl.common.Search;
import com.uwl.service.challenge.ChallengeDAO;
import com.uwl.service.challenge.ChallengeService;
import com.uwl.service.domain.Challenge;
import com.uwl.service.domain.Reward;
import com.uwl.service.domain.SchoolRank;
import com.uwl.service.reward.RewardDAO;

@Service("challengeServiceImpl")
public class ChallengeServiceImpl implements ChallengeService{
	
	//Field
	@Autowired
	@Qualifier("challengeDAOImpl")
	private ChallengeDAO challengeDAO;
	
	@Autowired
	@Qualifier("rewardDAOImpl")
	private RewardDAO rewardDAO;
	
	//Setter
	public void setChallengeDAO(ChallengeDAO challengeDAO) {
		this.challengeDAO = challengeDAO;
	}
	
	public void setRewardDAO(RewardDAO rewardDAO) {
		this.rewardDAO = rewardDAO;
	}



	//Constructor
	public ChallengeServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	//Method
	@Override
	public void addChallenge(Challenge challenge) throws Exception {
		challengeDAO.addChallenge(challenge);
	}
	
	@Override
	public void updateChallenge(Challenge challenge) throws Exception {
		challengeDAO.updateChallenge(challenge);
	}
	
	@Override
	public void deleteChallenge(Challenge challenge) throws Exception {
		challengeDAO.deleteChallenge(challenge);
	}
	
	@Override
	public Map<String, Object> getAdminChallengeList(Search search) throws Exception {
		
		List<Challenge> list = challengeDAO.getAdminChallengeList(search);
		int totalCount = challengeDAO.getTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}

	@Override
	public Challenge getChallengeAdmin(int challNo) throws Exception {
		return challengeDAO.getChallengeAdmin(challNo);
	}


	@Override
	public List<Challenge> getChallengeList() throws Exception {
		
//		Map<String, Object> map = new HashMap<String,Object>();
//		map.put("list", list);
//		map.put("totalCount", new Integer(totalCount));
		
		List<Challenge> list = challengeDAO.getChallengeList();
		
		return list;
	}

	@Override
	public Map<String, Object> getCompleteChallengeList(Search search, String userId) throws Exception {
		
		System.out.println("ChallengeService의 search : " + search);
		
		int totalCount = challengeDAO.getTotalCountOne(userId);
		
		Map<String, Object> completeMap = challengeDAO.getCompleteChallengeList(search, userId);
		
		completeMap.put("totalCount", new Integer(totalCount));
		
		return completeMap;
	}


	@Override
	public void completeChallenge(Reward reward) throws Exception {
		rewardDAO.increasePoint(reward);
		
	}

}
