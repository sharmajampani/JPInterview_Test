/**
 * 
 */
package com.jp.interview.fx.conversion;

import java.math.BigDecimal;

/**
 *
 *
 */
public class Entity {

	private String entityName;
	
	private BigDecimal entityUnitPrice;
	
	private String currency;
	
	private Transaction transaction;

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public BigDecimal getEntityUnitPrice() {
		return entityUnitPrice;
	}

	public void setEntityUnitPrice(BigDecimal entityUnitPrice) {
		this.entityUnitPrice = entityUnitPrice;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
}
