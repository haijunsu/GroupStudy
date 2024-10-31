package com.navysu.java.basic.companies.spothero;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DurationOverlapTest {

	static DurationOverlap durationOverlap;

	@BeforeAll
	static void init() {
		durationOverlap = new DurationOverlap();
	}

	@Test
	void testIsAnyOverlap() {

		// anyOverlapping([(1,3), (5,7), (2,4), (6,8)]) would return true because (1,3)
		// and (2,4) overlap
		int[] starts = new int[] { 1, 5, 2, 6 };
		int[] ends = new int[] { 3, 7, 4, 8 };
		List<Duration> testCases = new ArrayList<>();
		for (int i = 0; i < starts.length; i++) {
			Duration first = new Duration(starts[i], ends[i]);
			testCases.add(first);
		}
		assertTrue(durationOverlap.isAnyOverlap(testCases));

		testCases.clear();
		// anyOverlapping([(1,3), (7,9), (4,6), (10,13)]) would return false because
		// none overlap
		starts = new int[] { 1, 7, 4, 10 };
		ends = new int[] { 3, 9, 6, 13 };
		for (int i = 0; i < starts.length; i++) {
			Duration first = new Duration(starts[i], ends[i]);
			testCases.add(first);
		}
		assertFalse(durationOverlap.isAnyOverlap(testCases));
	}

	@Test
	void testIsOverlap() {
		// fail("Not yet impltemented");
		int[] starts = new int[] { 1, 3, 5, 6, 3, 4, 6, 6 };
		int[] ends = new int[] { 2, 5, 7, 8, 5, 6, 7, 7 };
		boolean[] result = new boolean[] { false, false, true, false, true, false, true };

		for (int i = 0; i < starts.length - 1; i++) {
			Duration first = new Duration(starts[i], ends[i]);
			Duration second = new Duration(starts[i + 1], ends[i + 1]);
			assertEquals(result[i], durationOverlap.isOverlap(first, second));
		}
	}

}
