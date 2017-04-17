/**
 * 
 */
package com.jp.interview.fx.conversion;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jp.interview.fx.conversion.helper.FXConversionHelper;

/**
 * Main class to be invoked to read the sample data, do the required calculations conversions and printing the output.
 *
 */
public class FXConversion {

	protected final static Log log = LogFactory.getLog(FXConversion.class);
	private static final String ENTITY_SELL = "S";
	
	private static final String ENTITY_BUY = "B";
	
	private static final SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy");
	
	public static void main(String[] args) {
		List<Entity> entityList = new ArrayList<Entity>();
		Transaction transaction = null;
		Entity entity = null;
		
		//Get sample data created to do the calculations.
		try {
			
			//Transaction Instruction - 1
			transaction = SampleData.createTransaction(ENTITY_BUY,"SGP",new BigDecimal(0.50),formatDate.parse("01-Jan-2016"),formatDate.parse("02-Jan-2016"),new Long(200));
			entity = SampleData.createEntity("foo1","SGP",new BigDecimal(100.25),transaction);
			entityList.add(entity);
			
			//Transaction Instruction - 2
			transaction = SampleData.createTransaction(ENTITY_SELL,"AED",new BigDecimal(0.22),formatDate.parse("05-Jan-2016"),formatDate.parse("07-Jan-2016"),new Long(450));
			entity = SampleData.createEntity("bar1","AED",new BigDecimal(150.5),transaction);
			entityList.add(entity);
			
			//Transaction Instruction - 3
			transaction = SampleData.createTransaction(ENTITY_BUY,"SGP",new BigDecimal(0.50),formatDate.parse("03-Jan-2016"),formatDate.parse("07-Jan-2016"),new Long(1000));
			entity = SampleData.createEntity("foo2","SGP",new BigDecimal(100.25),transaction);
			entityList.add(entity);
			
			//Transaction Instruction - 4
			transaction = SampleData.createTransaction(ENTITY_SELL,"AED",new BigDecimal(0.22),formatDate.parse("08-Jan-2016"),formatDate.parse("09-Jan-2016"),new Long(600));
			entity = SampleData.createEntity("bar2","AED",new BigDecimal(150.5),transaction);
			entityList.add(entity);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//Create a map with list of incoming/SELL transactions with settlement date as a key of the map.
		Map<Date,List<Entity>> incomingTransactionsSettledPerDay = new HashMap<Date,List<Entity>>();
		
		//Create a map with list of outgoing/BUY transactions with settlement date as a key of the map.
		Map<Date,List<Entity>> outgoingTransactionsSettledPerDay = new HashMap<Date,List<Entity>>();
		
		if(entityList != null && !entityList.isEmpty()) {
			for (Entity checkEntity : entityList) {
				if(ENTITY_BUY.equals(checkEntity.getTransaction().getTransactionType())) {
					FXConversionHelper.insertValuesInotMap(outgoingTransactionsSettledPerDay, checkEntity);
				} else if(ENTITY_SELL.equals(checkEntity.getTransaction().getTransactionType())){
					FXConversionHelper.insertValuesInotMap(incomingTransactionsSettledPerDay, checkEntity);
				}
			}
		}
		
		//Printing outgoing settlement amounts in USD.
		
		Map<String,BigDecimal> outgoingEntityAmounts = new HashMap<String,BigDecimal>();
		FXConversionHelper.printOutgoingSettlementAmountsInUSD(outgoingTransactionsSettledPerDay,outgoingEntityAmounts);
		
		//Printing Ranking for Outgoing amounts.
		outgoingEntityAmounts = FXConversionHelper.sortByValue(outgoingEntityAmounts);
		log.info("------Outgoing Rankings in ascending order (Rank 1-Rank 10), eg., Rank 1 - Highest Outgoing Amount Instruction----------- ");
		for (Map.Entry<String,BigDecimal> entry : outgoingEntityAmounts.entrySet()) {
			log.info("Entity Name :  "+entry.getKey() + " and the Outgoing Instruction Amount is : "+entry.getValue());
		}
		log.info("------------------------------------------------------------------------------------------------------------------------ ");
		
		//Printing incoming settlement amounts in USD.
		Map<String,BigDecimal> incomingEntityAmounts = new HashMap<String,BigDecimal>();
		FXConversionHelper.printIncomingSettlementAmountsInUSD(incomingTransactionsSettledPerDay,incomingEntityAmounts);
		
		//Printing Ranking for Incoming amounts.
		incomingEntityAmounts = FXConversionHelper.sortByValue(incomingEntityAmounts);
		log.info("-------Incoming Rankings in ascending order (Rank 1-Rank 10), eg., Rank 1 - Highest Incoming Amount Instruction----------- ");
		for (Map.Entry<String,BigDecimal> entry : incomingEntityAmounts.entrySet()) {
			log.info("Entity Name :  "+entry.getKey() + " and the Incoming Instruction Amount is : "+entry.getValue());
		}
		log.info("------------------------------------------------------------------------------------------------------------------------ ");
		
	}

}
