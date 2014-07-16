/**
 *
 */
package fr.cedrik.util;

import static org.junit.Assert.assertArrayEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.cedrik.email.fs.mbox.MBoxrdZipWriter;

/**
 * @author C&eacute;drik LIME
 */
public class AppleDoubleTest {
	private static final byte[] AD_TEXT_TYPE = new byte[] {
			0x00, 0x05, 0x16, 0x07,  0x00, 0x02, 0x00, 0x00,
			0x4D, 0x61, 0x63, 0x20, 0x4F, 0x53, 0x20, 0x58, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x00,
			0x02,
			0x00, 0x00, 0x00, 0x09,  0x00, 0x00, 0x00, 0x32,  0x00, 0x00, 0x00, 0x20,
			0x00, 0x00, 0x00, 0x02,  0x00, 0x00, 0x00, 0x52,  0x00, 0x00, 0x00, 0x00,
			0x54, 0x45, 0x58, 0x54,  0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
	};

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTEXTtype() {
		assertArrayEquals(AD_TEXT_TYPE, MBoxrdZipWriter.APPLE_DOUBLE__TEXT_TYPE);
	}

}