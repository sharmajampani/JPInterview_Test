package com.jp.interview.fx.conversion.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.jp.interview.fx.conversion.helper.FXConversionHelper;

public class TestFXConversionHelper {

	@Test
	public void testMapSortDescending() {
		Map<String,BigDecimal> mapToSort = new HashMap<String,BigDecimal>();
		mapToSort.put("Rank 3", new BigDecimal(6000));
		mapToSort.put("Rank 4", new BigDecimal(4000));
		mapToSort.put("Rank 1", new BigDecimal(10000));
		mapToSort.put("Rank 5", new BigDecimal(1000));
		mapToSort.put("Rank 2", new BigDecimal(8000));
		
		Map<String,BigDecimal> sortedMap = FXConversionHelper.sortByValue(mapToSort);
		List<String> sortedList = new ArrayList<String>(sortedMap.keySet());
		assertNotNull(sortedList);
		assertEquals("Rank 1", sortedList.get(0));
		assertEquals("Rank 3", sortedList.get(2));
		assertEquals("Rank 5", sortedList.get(4));
	}
}
