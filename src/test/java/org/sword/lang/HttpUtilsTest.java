package org.sword.lang;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sword.lang.HttpUtils;

public class HttpUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		String https = HttpUtils.post("https://kyfw.12306.cn/otn/");
		System.out.println(https);
	}
	
	public void testHttpPost(){
		String http = HttpUtils.post("http://kyfw.12306.cn/otn/");
		System.out.println(http);
	}

}
