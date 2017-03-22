package com.lq.common.util.custom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.lq.config.SMSConfigManager;

public class SMSUtil {

	private static String smsIp;
	private static int smsPort;
	private static int timeout;
	
	static{
		smsIp = SMSConfigManager.getConfig("ip");
		smsPort = Integer.parseInt(SMSConfigManager.getConfig("port"));
		timeout = Integer.parseInt(SMSConfigManager.getConfig("timeout"));
	}
	
	public static String sendMessage(String phone, String message){
		String str = "false";
		Socket socket = null;
		BufferedReader reader = null;
		PrintWriter writer = null;
		try {
			socket = new Socket(smsIp, smsPort);
			socket.setSoTimeout(timeout * 1000);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			@SuppressWarnings("unused")
			String datetime = sdf.format(Calendar.getInstance().getTime());
			String param = "M=" + phone +",I=" + message; 
			
			writer.println(param);
			writer.flush();

			if(socket.getInputStream().available() > 0){
				String result = reader.readLine();
				System.out.println("发送结果2:" + result);
			} else {
				String result = reader.readLine();
				System.out.println("发送结果3:" + result);
			}
			str = "true";
		} catch (SocketTimeoutException e) {
			str = "false";
		} catch (UnknownHostException e) {
			str = "false";
		} catch (IOException e) {
			str = "false";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return str;
	}

}
