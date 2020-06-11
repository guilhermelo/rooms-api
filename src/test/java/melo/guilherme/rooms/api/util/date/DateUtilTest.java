package melo.guilherme.rooms.api.util.date;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

import melo.guilherme.rooms.api.config.exception.BusinessException;

public class DateUtilTest {
	
	private DateUtil dateUtil;
	
	@Before
	public void setup() {
		this.dateUtil = new DateUtil();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenDateIsEmpty() {
		
		String date = "";
		
		this.dateUtil.parseDatetime(date);
	}
	
	@Test(expected = BusinessException.class)
	public void shouldThrowExceptionWhenDateIsNull() {
		
		String date = null;
		
		this.dateUtil.parseDatetime(date);
	}
	
	@Test
	public void shouldParseDatetime() {
		
		String date = "2020-04-30 10:30:00";
		
		LocalDateTime localDateTime = this.dateUtil.parseDatetime(date);
		
		assertEquals(2020, localDateTime.getYear());
		assertEquals(Month.APRIL, localDateTime.getMonth());
		assertEquals(30, localDateTime.getDayOfMonth());
	}

}
