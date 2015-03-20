import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author suhaijun
 *
 */
public class StrStrTest {

	private StrStr str;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		str = new StrStr();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		str = null;
	}

	/**
	 * Test method for {@link StrStr#strStr(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testStrStr() {
		String haytack = "ABC ABCDAB ABCDABCDABDE";
		String needle = "ABCDABD";
		assertEquals(15, str.strStr(haytack, needle));
		needle = "CDABD";
		assertEquals(17, str.strStr(haytack, needle));
		needle = "C";
		assertEquals(2, str.strStr(haytack, needle));
		needle = "CDAB";
		assertEquals(6, str.strStr(haytack, needle));
//		fail("Not yet implemented");
	}
}
