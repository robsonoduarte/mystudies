package br.com.mystudies.service.temp;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MatchersBravoxtest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {
	
		Matcher<Date> matcher = 
				MatchersBravox.inPeriod(DateUtils.parseDate("30/10/1976", "dd/MM/yyyy"));
		
		assertNotNull(matcher);		
		assertFalse(matcher.matches(new String()));		
		assertTrue(matcher.matches(DateUtils.parseDate("01/10/1976", "dd/MM/yyyy")));
		assertTrue(matcher.matches(DateUtils.parseDate("20/10/1976", "dd/MM/yyyy")));
		assertTrue(matcher.matches(DateUtils.parseDate("30/10/1976", "dd/MM/yyyy")));
		assertFalse(matcher.matches(DateUtils.parseDate("01/11/1976", "dd/MM/yyyy")));
			
	}

}
