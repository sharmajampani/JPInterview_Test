package com.jp.interview.fx.conversion.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import com.jp.interview.fx.conversion.Entity;
import com.jp.interview.fx.conversion.SampleData;
import com.jp.interview.fx.conversion.Transaction;

/**
 * 
 * This class is to test the sample data class.
 *
 */
public class TestSampleData {

	private static final SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy");
	
	@Test
	public void createTransaction() {
		try {
			Transaction transaction = SampleData.createTransaction("B","USD",new BigDecimal(1.00),formatDate.parse("04-Jan-2016"),formatDate.parse("05-Jan-2016"),new Long(200));
			assertNotNull(transaction);
			assertEquals("B", transaction.getTransactionType());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void createEntity() {
		try {
			Transaction transaction = SampleData.createTransaction("B","USD",new BigDecimal(1.00),formatDate.parse("04-Jan-2016"),formatDate.parse("05-Jan-2016"),new Long(200));
			assertNotNull(transaction);
			assertEquals("B", transaction.getTransactionType());
			Entity entity = SampleData.createEntity("testEntity", "USD", new BigDecimal(100.00), transaction);
			assertNotNull(entity);
			assertEquals("USD", entity.getCurrency());
			assertEquals("B", entity.getTransaction().getTransactionType());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
}
