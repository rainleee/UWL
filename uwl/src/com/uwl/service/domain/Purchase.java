package com.uwl.service.domain;

import java.sql.Date;

public class Purchase {

	// Field
	private String userId;
	private int purchaseNo;
	private String itemName;
	private String itemCategory;
	private int price;
	private String paymentOption;
	private String importId;
	private Date purchaseDate;
	private String refundOption;

	// 추가된 변수명
	// itemCategory, importId
	// purchaseNumber => No로 수정 (table과 매칭)

	// Constructor
	public Purchase() {
		// TODO Auto-generated constructor stub
	}

	// Method
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getPurchaseNo() {
		return purchaseNo;
	}

	public void setPurchaseNo(int purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPaymentOption() {
		return paymentOption;
	}

	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}

	public String getImportId() {
		return importId;
	}

	public void setImportId(String importId) {
		this.importId = importId;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getRefundOption() {
		return refundOption;
	}

	public void setRefundOption(String refundOption) {
		this.refundOption = refundOption;
	}

	@Override
	public String toString() {
		return "Purchase [userId=" + userId + ", purchaseNo=" + purchaseNo + ", itemName=" + itemName
				+ ", itemCategory=" + itemCategory + ", price=" + price + ", paymentOption=" + paymentOption
				+ ", importId=" + importId + ", purchaseDate=" + purchaseDate + ", refundOption=" + refundOption + "]";
	}

}
