/**
 * 
 */
package org.sword.lang;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.sword.lang.DateTimeUtil;


/**
 * @author ChengNing
 * @date   2014年10月30日
 */
public class DateTimeUtilTest {

	@Test
	public void testGetDateTimeStringString() {

	}

	@Test
	public void testGetDateTimeString() {

	}
	
	@Test
	public void testGetDate(){

	}
	
	@Test
	public void testGetTime(){

	}

	@Test
	public void testToDateString() {
		//fail("Not yet implemented");
	}

	@Test
	public void testToday() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void testGetToday(){
	}

	@Test
	public void testNow() {
	}

	@Test
	public void testCurrentTime() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCurrentDate() {
		///fail("Not yet implemented");
	}

	@Test
	public void testCurrent() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void testFormat(){
		String t = "Test2014-09-06";
		Date dt = DateTimeUtil.getDate("2014-09-06 00:00:00");
		String s = DateTimeUtil.format("Test{yyyy-MM-dd}",dt);
		System.out.println(t);
		System.out.println(s);
		assertEquals(s, t);
	}
}
