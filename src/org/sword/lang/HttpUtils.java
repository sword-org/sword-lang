/**
 * 
 */
package org.sword.lang;



import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.sword.lang.http.HttpsUtils;

/**
 * http 工具集
 * @author chengn
 * @date 2014年12月12日
 */
public class HttpUtils {
	private static Logger logger = Logger.getLogger(HttpUtils.class);

	public static final int timeout = 10;

	/**
	 * post 请求
	 * 支持https
	 * @param url
	 * @return
	 */
	public static String post(String url) {
		return post(url, null);
	}
	
	/**
	 * post 请求 带参数
	 * 支持https
	 * @param url
	 * @param data
	 * @return
	 */
	public static String post(String url, String data){
		if(url.startsWith("https"))
			return HttpsUtils.post(url,data);
		return HttpPost(url, data);
	}
	
	/**
	 * get请求，支持https
	 * @param url
	 * @return
	 */
	public static String get(String url){
		if(url.startsWith("https"))
			return HttpsUtils.get(url);
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
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			if(data != null && !data.equals("")){
				HttpEntity reqEntity = new StringEntity(data);
				post.setEntity(reqEntity);
			}
			HttpResponse response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();
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
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet get = new HttpGet(url);
			HttpResponse response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			return entity != null ? EntityUtils.toString(entity) : null;
		} catch (Exception e) {
			logger.error("get请求异常，" + e.getMessage() + "\n get url:" + url);
			e.printStackTrace();
		}
		return null;
	}
}
