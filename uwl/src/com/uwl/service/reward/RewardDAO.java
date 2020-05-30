package com.uwl.service.reward;

import java.util.List;

import com.uwl.service.domain.Purchase;
import com.uwl.service.domain.Reward;

public interface RewardDAO {
	
	//user의 포인트내역, 활동점수를 조회한다
	public List<Reward> getUserBothPointList(String userId) throws Exception;
	
	//획득 포인트, 활동점수 
	public void increasePoint(Reward reward) throws Exception;
	
	//purchase해서 감소되는 포인트를 나타내는 로직
	public void decreasePoint(Reward reward) throws Exception;
	
	
}
