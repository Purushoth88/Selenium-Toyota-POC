package com.orasi.api.restServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

public class Mustard {
	private String testName = "SimpleTest";
	private String deviceID = "123456789";
	private String status = "pass";
	private String comment = "Testing Comments";

	public String mustardIp = "http://10.238.242.85:3000/projects/1/results";

	private RestService rest = new RestService();
	private String restResponse = "";
	private NameValuePair nameValuePair;
	private List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

	public Mustard(String testName, String deviceID, String status, String comment){
		this.testName = testName;
		this.deviceID = deviceID;
		this.status = status;
		this.comment = comment;
	}
	
	public Mustard(){ }

	public void postTest() throws ClientProtocolException, IOException {
		addNameValueParis();
		restResponse = rest.sendPostRequest(this.mustardIp, this.nameValuePairs);
	}
	
	public void addNameValueParis(){
		nameValuePairs.add(new BasicNameValuePair("test_name", this.testName));
		nameValuePairs.add(new BasicNameValuePair("device_id", this.deviceID));
		nameValuePairs.add(new BasicNameValuePair("status", this.status));
		nameValuePairs.add(new BasicNameValuePair("comment", this.comment));
	}
	
	@Test
	public void postTestToMustard() throws ClientProtocolException, IOException{		
		postTest();
	}
}
