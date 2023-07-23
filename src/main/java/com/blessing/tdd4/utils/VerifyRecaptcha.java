package com.blessing.tdd4.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;

public class VerifyRecaptcha {

	public static final String url = "https://www.google.com/recaptcha/api/siteverify";
	public static final String secret = "6LffHyETAAAAAPsme2jZyXjRMashIzKpuxa6YDCH";
	private final static String USER_AGENT = "Mozilla/5.0";
	public static JsonReader jsonReader1;
	public static JsonObject jsonObject1;
	public static Json Json1;

	@SuppressWarnings("static-access")
	public static boolean verify(String gRecaptchaResponse) throws IOException {
		if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
			return false;
		}
		
		try{
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		// add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String postParams = "secret=" + secret + "&response="
				+ gRecaptchaResponse;

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(postParams);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + postParams);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());
		
		//parse JSON response and return 'success' value
		jsonReader1 = Json1.createReader(new StringReader(response.toString()));
		jsonObject1 = jsonReader1.readObject();
		jsonReader1.close();
		
		return jsonObject1.getBoolean("success");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("I came here");
			return false;
		}
	}
	/*
	public static void main(String[] args) throws IOException{
		boolean verify = VerifyRecaptcha.verify("test");
		System.out.println("verify test: "+verify);
	}
	*/
}
