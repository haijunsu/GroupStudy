package com.navysu.java.basic.companies.standard.cognition;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.navysu.java.basic.companies.standard.cognition.RomanAndDecimal;
import com.navysu.java.basic.companies.standard.cognition.RomanDecimal;

class RomanAndDecimalTest {
	
	static RomanAndDecimal romanAndDecimal;

	static List<RomanDecimal> testCases;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		romanAndDecimal = new RomanAndDecimal();
		testCases = new ArrayList<>();
		testCases.add(new RomanDecimal("I", 1));
		testCases.add(new RomanDecimal("IV", 4));
		testCases.add(new RomanDecimal("V", 5));
		testCases.add(new RomanDecimal("VIII", 8));
		testCases.add(new RomanDecimal("IX", 9));
		testCases.add(new RomanDecimal("X", 10));
		testCases.add(new RomanDecimal("XIV", 14));
		testCases.add(new RomanDecimal("XV", 15));
		testCases.add(new RomanDecimal("XXV", 25));
		testCases.add(new RomanDecimal("XIX", 19));
		testCases.add(new RomanDecimal("XXVIII", 28));
		testCases.add(new RomanDecimal("XXX", 30));
		testCases.add(new RomanDecimal("XL", 40));
		testCases.add(new RomanDecimal("XLII", 42));
		testCases.add(new RomanDecimal("XLV", 45));
		testCases.add(new RomanDecimal("XLIV", 44));
		testCases.add(new RomanDecimal("XLVI", 46));
		testCases.add(new RomanDecimal("XLIX", 49));
		testCases.add(new RomanDecimal("L", 50));
		testCases.add(new RomanDecimal("LIV", 54));
		testCases.add(new RomanDecimal("LXI", 61));
		testCases.add(new RomanDecimal("LXIX", 69));
		testCases.add(new RomanDecimal("XC", 90));
		testCases.add(new RomanDecimal("XCVII", 97));
		testCases.add(new RomanDecimal("C", 100));
		testCases.add(new RomanDecimal("CXXX", 130));
		testCases.add(new RomanDecimal("CXXIX", 129));
		testCases.add(new RomanDecimal("CXVI", 116));
	}

	@Test
	void testRoman2Decimal() {
		for (RomanDecimal value: testCases) {
			assertEquals(value.getDecimal(), romanAndDecimal.roman2Decimal(value.getRoman()));
		}
	}

	@Test
	void testDecimal2Roman() {
		for (RomanDecimal value: testCases) {
			assertEquals(value.getRoman(), romanAndDecimal.decimal2Roman(value.getDecimal()));
		}
	}
}
