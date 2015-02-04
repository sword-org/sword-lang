/**
 * 
 */
package org.sword.lang;



import java.io.File;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
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
		return post(url, "");
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
	
	/** 发送http post请求
	 * @param url       url
	 * @param instream  post数据的字节流
	 * @return
	 */
	public static String post(String url, InputStream instream){
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			if(instream != null){
				HttpEntity reqEntity = new InputStreamEntity(instream, -1);
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
	 * 上传文件
	 * @param url    URL
	 * @param file   需要上传的文件
	 * @return
	 */
	public static String postFile(String url,File file){
		return postFile(url, null, file);
	}
	
	/**
	 * 上传文件
	 * @param url    URL
	 * @param name   文件的post参数名称
	 * @param file   上传的文件
	 * @return
	 */
	public static String postFile(String url,String name,File file){
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			if(file != null){
				FileBody fileBody = new FileBody(file);
				MultipartEntity  reqEntity = new MultipartEntity();
				reqEntity.addPart(name, fileBody);
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
	
	/**
	 * 下载文件
	 * @param url   URL
	 * @return      文件的二进制流，客户端使用outputStream输出为文件
	 */
	public static byte[] getFile(String url){
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet get = new HttpGet(url);
			HttpResponse response = httpClient.execute(get);
			HttpEntity resEntity = response.getEntity();
			return EntityUtils.toByteArray(resEntity);
		} catch (Exception e) {
			logger.error("postFile请求异常，" + e.getMessage() + "\n post url:" + url);
			e.printStackTrace();
		}
		return null;
	}
}
