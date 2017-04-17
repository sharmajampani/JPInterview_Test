/**
 * 
 */
package com.jp.interview.fx.conversion;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 *
 */
public class Transaction {

	private String transactionType;
	
	private BigDecimal agreedFX;
	
	private Date transactionInstructionDate;
	
	private Date transactionSettlementDate;
	
	private Long transactionUnits;
	
	private String currency;
	
	private Date transactionAdjustedSettlementDate;

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public BigDecimal getAgreedFX() {
		return agreedFX;
	}

	public void setAgreedFX(BigDecimal agreedFX) {
		this.agreedFX = agreedFX;
	}

	public Date getTransactionInstructionDate() {
		return transactionInstructionDate;
	}

	public void setTransactionInstructionDate(Date transactionInstructionDate) {
		this.transactionInstructionDate = transactionInstructionDate;
	}

	public Date getTransactionSettlementDate() {
		return transactionSettlementDate;
	}

	public void setTransactionSettlementDate(Date transactionSettlementDate) {
		this.transactionSettlementDate = transactionSettlementDate;
	}

	public Long getTransactionUnits() {
		return transactionUnits;
	}

	public void setTransactionUnits(Long transactionUnits) {
		this.transactionUnits = transactionUnits;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getTransactionAdjustedSettlementDate() {
		return transactionAdjustedSettlementDate;
	}

	public void setTransactionAdjustedSettlementDate(Date transactionAdjustedSettlementDate) {
		this.transactionAdjustedSettlementDate = transactionAdjustedSettlementDate;
	}
	
	
}
