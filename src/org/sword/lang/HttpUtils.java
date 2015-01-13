/**
 * 
 */
package org.sword.lang;



import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * 
 * @author chengn
 * @date 2014年12月12日
 */
public class HttpUtils {
	private static Logger logger = Logger.getLogger(HttpUtils.class);

	public static final int timeout = 10;

	/**
	 * post 请求
	 * 
	 * @param url
	 * @return
	 */
	public static String post(String url) {
		return post(url, null);
	}
	
	/**
	 * 
	 * @param url
	 * @param data
	 * @return
	 */
	public static String post(String url, String data){
		return HttpPost(url, data);
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	public static String get(String url){
		return httpGet(url);
	}

	/**
	 * post 请求
	 * 
	 * @param url
	 * @param data
	 * @return
	 */
	private static String HttpPost(String url, String data) {
		try {
			HttpEntity entity = Request.Post(url)
					.bodyString(data,ContentType.create("text/html", Consts.UTF_8))
					.execute().returnResponse().getEntity();
			return entity != null ? EntityUtils.toString(entity) : null;
		} catch (Exception e) {
			logger.error("post请求异常，" + e.getMessage() + "\n post url:" + url);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 发送get请求
	 * 
	 * @param url
	 * @return
	 */
	private static String httpGet(String url) {
		try {
			HttpEntity entity = Request.Post(url).
					execute().returnResponse().getEntity();
			return entity != null ? EntityUtils.toString(entity) : null;
		} catch (Exception e) {
			logger.error("get请求异常，" + e.getMessage() + "\n get url:" + url);
			e.printStackTrace();
		}
		return null;
	}
}
