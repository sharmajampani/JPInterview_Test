/**
 * 
 */
package com.jp.interview.fx.conversion.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jp.interview.fx.conversion.Entity;

/**
 * Helper class for doing amount calculations per day and 
 * sorting the map required for finding the outgoing and incoming transaction amounts entity ranking.
 *
 */
public class FXConversionHelper {

	protected final static Log log = LogFactory.getLog(FXConversionHelper.class);
	
	public static void printIncomingSettlementAmountsInUSD(Map<Date, List<Entity>> incomingTransactionsSettledPerDay,Map<String,BigDecimal> incomingEntityAmounts) {
		if(!incomingTransactionsSettledPerDay.isEmpty()){
			log.info("-------------------------------- The Incoming amount(s) in USD ------------------------------");
			for (Map.Entry<Date, List<Entity>> entry : incomingTransactionsSettledPerDay.entrySet()) {
				List<Entity> incomingEntityList = entry.getValue();
				BigDecimal incomingAmount = new BigDecimal(0.00);
				for (Entity incomingEntity : incomingEntityList) {
					BigDecimal calculateUSDConversion = incomingEntity.getEntityUnitPrice().multiply(new BigDecimal(incomingEntity.getTransaction().getTransactionUnits())).multiply(incomingEntity.getTransaction().getAgreedFX());
					incomingAmount = incomingAmount.add(calculateUSDConversion);
					//Populating map for printing ranking information.
					incomingEntityAmounts.put(incomingEntity.getEntityName(), calculateUSDConversion);
				}
				log.info("The USD incoming Amount for Date :: " + entry.getKey() + " is ::: " + incomingAmount);
			}
			log.info("---------------------------------------------------------------------------------------------");
		}
	}

	public static void printOutgoingSettlementAmountsInUSD(Map<Date, List<Entity>> outgoingTransactionsSettledPerDay,Map<String,BigDecimal> outgoingEntityAmounts) {
		if(!outgoingTransactionsSettledPerDay.isEmpty()){
			log.info("-------------------------------- The Outgoing amount(s) in USD ------------------------------");
			for (Map.Entry<Date, List<Entity>> entry : outgoingTransactionsSettledPerDay.entrySet()) {
				List<Entity> outgoingEntityList = entry.getValue();
				BigDecimal outgoingAmount = new BigDecimal(0.00);
				for (Entity outgoingEntity : outgoingEntityList) {
					BigDecimal calculateUSDConversion = outgoingEntity.getEntityUnitPrice().multiply(new BigDecimal(outgoingEntity.getTransaction().getTransactionUnits())).multiply(outgoingEntity.getTransaction().getAgreedFX());
					outgoingAmount = outgoingAmount.add(calculateUSDConversion);
					//Populating map for printing ranking information.
					outgoingEntityAmounts.put(outgoingEntity.getEntityName(), calculateUSDConversion);
				}
				log.info("The USD ougoing Amount for Date :: " + entry.getKey() + " is ::: " + outgoingAmount);
			}
			log.info("---------------------------------------------------------------------------------------------");
		}
	}

	public static void insertValuesInotMap(Map<Date, List<Entity>> transactionsSettledPerDay,
			Entity checkEntity) {
		if (!transactionsSettledPerDay.containsKey(checkEntity.getTransaction().getTransactionAdjustedSettlementDate())) {
		    List<Entity> outgoingEntityList = new ArrayList<Entity>();
		    outgoingEntityList.add(checkEntity);
		    transactionsSettledPerDay.put(checkEntity.getTransaction().getTransactionAdjustedSettlementDate(), outgoingEntityList);
		} else {
			transactionsSettledPerDay.get(checkEntity.getTransaction().getTransactionAdjustedSettlementDate()).add(checkEntity);
		}
	}
	
	public static Map<String, BigDecimal> sortByValue(Map<String, BigDecimal> unsortMap) {

        List<Map.Entry<String, BigDecimal>> list =
                new LinkedList<Map.Entry<String, BigDecimal>>(unsortMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, BigDecimal>>() {
            public int compare(Map.Entry<String, BigDecimal> o1,
                               Map.Entry<String, BigDecimal> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<String, BigDecimal> sortedMap = new LinkedHashMap<String, BigDecimal>();
        for (Map.Entry<String, BigDecimal> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
}
