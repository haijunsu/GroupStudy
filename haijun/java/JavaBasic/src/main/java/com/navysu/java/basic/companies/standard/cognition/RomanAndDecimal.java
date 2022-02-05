package com.navysu.java.basic.companies.standard.cognition;

/**
 * https://literacy.kent.edu/Minigrants/Cinci/romanchart.htm
 * 
 * @author navy
 *
 */
public class RomanAndDecimal {
	private static String[] Xs = new String[] { "", "X", "XX", "XXX", "XXXX" };

	private static String[] Ones = new String[] { "", "I", "II", "III" };

	public int roman2Decimal(String roman) {
		char[] romanChars = roman.toCharArray();
		int value = 0;
		for (int i = 0; i < romanChars.length; i++) {
			char curr = romanChars[i];
			switch (curr) {
			case 'I':
				if (i < romanChars.length - 1 && isSubtract(curr, romanChars[i + 1])) {
					++i;
					if (romanChars[i] == 'X') {
						value += 9;
					} else {
						value += 4;
					}
				} else {
					value += 1;
				}
				break;
			case 'X':
				if (i < romanChars.length - 1 && isSubtract(curr, romanChars[i + 1])) {
					++i;
					if (romanChars[i] == 'L') {
						value += 40;
					} else {
						value += 90;
					}
				} else {
					value += 10;
				}
				break;
			case 'V':
				value += 5;
				break;
			case 'L':
				value += 50;
				break;
			case 'C':
				value += 100;
				break;
			}
		}
		return value;
	}

	private boolean isSubtract(char current, char after) {
		switch (current) {
		case 'I':
			return after == 'X' || after == 'V';
		case 'X':
			return after == 'L' || after == 'C';
		}
		return false;
	}

	private String handle40(int decimal, int low, int charValue, char ch) {
		StringBuilder strbld = new StringBuilder();
		if (decimal >= low) {
			if (decimal >= charValue) {
				strbld.append(ch);
				strbld.append(decimal2Roman(decimal - charValue));
			} else {
				strbld.append("X").append(ch);
				strbld.append(decimal2Roman(decimal - low));
			}
		}
		return strbld.toString();
	}

	public String decimal2Roman(int decimal) {
		//
		StringBuilder strbld = new StringBuilder();
		if (decimal >= 90) {
			strbld.append(handle40(decimal, 90, 100, 'C'));
		} else if (decimal >= 40 && decimal < 90) {
			strbld.append(handle40(decimal, 40, 50, 'L'));
		} else if (decimal >= 9 && decimal < 40) {
			int tens = decimal / 10;
			strbld.append(Xs[tens]);
			int remain = decimal % 10;
			if (remain < 9) {
				strbld.append(decimal2Roman(remain));
			} else {
				strbld.append("IX");
			}
		} else {
			if (decimal < 4) {
				strbld.append(Ones[decimal]);
			} else if (decimal == 4) {
				strbld.append("IV");
			} else {
				strbld.append("V");
				strbld.append(decimal2Roman(decimal - 5));
			}
		}
		System.out.println(decimal + " roman: " + strbld.toString());
		return strbld.toString();

	}

}
