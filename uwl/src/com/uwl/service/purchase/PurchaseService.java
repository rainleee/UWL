package com.uwl.service.purchase;

import java.util.List;
import java.util.Map;

import com.uwl.common.Search;
import com.uwl.service.domain.Purchase;

public interface PurchaseService {
	
	public void addPurchase(Purchase purchase) throws Exception;
	
	public Purchase getPurchase(int purchaseNo) throws Exception;
	
	public Map<String, Object> getPurchaseList(String userId, Search search) throws Exception;
	
	public void refundPurchase(int purchaseNo) throws Exception;
}
