package com.uwl.service.purchase;

import java.util.List;

import com.uwl.common.Search;
import com.uwl.service.domain.Purchase;

public interface PurchaseDAO {
	
	public void addPurchase(Purchase purchase) throws Exception;
	
	public Purchase getPurchase(int purchaseNo) throws Exception;
	
	public List<Purchase> getPurchaseList(String userId, Search search) throws Exception;
	
	public int getTotalCount(String userId, Search search) throws Exception;
	
	public void refundPurchase(int purchaseNo) throws Exception;
}
