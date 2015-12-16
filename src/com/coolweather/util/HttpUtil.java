package com.coolweather.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 向服务器发出网络请求，服务器返回数据
 * 将返回的数据连接成一长条字符串
 * @author Administrator
 *
 */
public class HttpUtil {
	public static void sendHttpRequest(final String address,final CallbackListener listener){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				HttpURLConnection connection=null;
				try{
					URL url=new URL(address);//创建URL对象
					connection=(HttpURLConnection)url.openConnection();//获取网络链接
					connection.setRequestMethod("GET");//设置链接相关属性
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					
					InputStream in=connection.getInputStream();
					BufferedReader reader=new BufferedReader(new InputStreamReader(in));//获取读取信息的输出流
					//读取返回的信息，并将它们连接成字符串
					StringBuilder response=new StringBuilder();
					String line;
					while((line=reader.readLine())!=null){
						response.append(line);
					}
					if(listener!=null){
						System.out.println("网络请求数据"+response.toString());
						listener.onFinish(response.toString());
					}
				}catch(Exception e){
					if(listener!=null){
						listener.onError(e);
					}
				}finally{
					if(connection!=null){
						connection.disconnect();
					}
				}
			}
		}).start();
	}
}
