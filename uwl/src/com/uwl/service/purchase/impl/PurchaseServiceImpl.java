package com.uwl.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uwl.common.Search;
import com.uwl.service.domain.Purchase;
import com.uwl.service.domain.Reward;
import com.uwl.service.purchase.PurchaseDAO;
import com.uwl.service.purchase.PurchaseService;
import com.uwl.service.reward.RewardDAO;

@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	@Qualifier("purchaseDAOImpl")
	private PurchaseDAO purchaseDAO;
	
	@Autowired
	@Qualifier("rewardDAOImpl")
	private RewardDAO rewardDAO;

	public void setPurchaseDAO(PurchaseDAO purchaseDAO) {
		this.purchaseDAO = purchaseDAO;
	}

	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		purchaseDAO.addPurchase(purchase);
		if (purchase.getPaymentOption().equals("3")) {
			// decreasePoint
		}
	}

	@Override
	public Purchase getPurchase(int purchaseNo) throws Exception {
		return purchaseDAO.getPurchase(purchaseNo);
	}

	// 구매목록확인 userId : 구매자, searchCondition = 1 : 아이템 종류 searchKeyword ( 1 : 창 , 2 : 방패)  
	//	searchCondition = 2 : 구매취소된 것만 출력 (searchKeyword 입력 필요 없음)
	@Override
	public Map<String, Object> getPurchaseList(String userId, Search search) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", purchaseDAO.getPurchaseList(userId, search));
		map.put("totalCount", purchaseDAO.getTotalCount(userId, search));
		
		return map;
	}

	@Override
	public void refundPurchase(int purchaseNo) throws Exception {
		purchaseDAO.refundPurchase(purchaseNo);
	}
}