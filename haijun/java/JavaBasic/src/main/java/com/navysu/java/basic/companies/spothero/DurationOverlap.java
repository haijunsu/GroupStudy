package com.navysu.java.basic.companies.spothero;

import java.util.Collections;
import java.util.List;

public class DurationOverlap {
	/**
	 * Checking giving durations overlap status
	 * @param durations 
	 * @return
	 */
	public boolean isAnyOverlap(List<Duration> durations) {
		if (durations.size() == 1) return false;
		// 
		Collections.sort(durations, (a, b) -> {
			if (a.start > b.start) {
				return - 1;
			} else {
				return 1;
			}
			
		});
		Duration pre = durations.get(0);
		for (int i = 1; i < durations.size(); i++) {
			Duration current = durations.get(i);
			if (isOverlap(pre, current)) {
				return true;
			}
			if (current.end > pre.end) {
				pre = current;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	public boolean isOverlap(Duration first, Duration second) {
		if (first.start < second.start) {
			//
			if (first.end > second.start) {
				return true;
			}
		} else {
			if (second.end > first.start) {
				return true;
			}
		}
		return false;
	}
}

class Duration {
	int start; // start hour
	int end;

	public Duration(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public String toString() {
		return "Duration [start=" + start + ", end=" + end + "]";
	}

}
