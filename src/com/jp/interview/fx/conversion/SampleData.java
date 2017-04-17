/**
 * 
 */
package com.jp.interview.fx.conversion;

import java.math.BigDecimal;
import java.util.Date;

import com.jp.interview.fx.converstion.util.DateUtil;

/**
 * Class to create the required sample data.
 *
 */
public class SampleData {

	public static Transaction createTransaction(String transactionType,String currency,BigDecimal agreedFX,Date instructionDate,Date settlementDate,Long transactionUnits){
		Transaction transaction = new Transaction();
		transaction.setTransactionType(transactionType);
		transaction.setCurrency(currency);
		transaction.setAgreedFX(agreedFX);
		transaction.setTransactionInstructionDate(instructionDate);
		transaction.setTransactionSettlementDate(settlementDate);
		transaction.setTransactionUnits(transactionUnits);
		
		String dayOfWeek = DateUtil.getDayOfWeek(settlementDate);
		Boolean isDayInWorkWeek = Boolean.FALSE;
		if("AED".equals(currency) || "SAR".equals(currency)){
			isDayInWorkWeek = DateUtil.isDayInWorkWeekForAEDAndSAR(dayOfWeek);
		} else {
			isDayInWorkWeek = DateUtil.isDayInWorkWeek(dayOfWeek);
		}
		
		if(!isDayInWorkWeek){
			transaction.setTransactionAdjustedSettlementDate(DateUtil.getNextWorkingDay(settlementDate, currency));
		} else {
			transaction.setTransactionAdjustedSettlementDate(settlementDate);
		}
		return transaction;
	}
	
	public static Entity createEntity(String entityName,String currency,BigDecimal unitPrice,Transaction transaction){
		Entity entity = new Entity();
		entity.setEntityName(entityName);
		entity.setCurrency(currency);
		entity.setTransaction(transaction);
		entity.setEntityUnitPrice(unitPrice);
		return entity;
	}
}
