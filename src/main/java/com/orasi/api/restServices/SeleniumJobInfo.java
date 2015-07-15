package com.orasi.api.restServices;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.Test;

import com.orasi.utils.TestReporter;

import java.security.*;

public class SeleniumJobInfo {
	private String description;
	private String displayName;
	private String name;
	private String buildable;
	private String url;
	private String nextBuildNumber;

	public String getDescription() {
		return description;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getName() {
		return name;
	}

	public String getBuildable() {
		return buildable;
	}

	public String getUrl() {
		return url;
	}

	public String getNextBuildNumber() {
		return nextBuildNumber;
	}

	public static String lastBuildURL = "http://jenkins.orasi.com/job/OpenSandbox/job/Toyota-SauceLabs/lastBuild/api/json";
	public static String lastBuildNumber = "https://jenkins.orasi.com/job/OpenSandbox/job/Toyota-SauceLabs/api/json?tree=builds[number]";

	private static RestService rest = new RestService();
	private String restResponse = "";

	public SeleniumJobInfo() {

	}

	public static String getLastJenkinsBuildNumber() throws ClientProtocolException, IOException {
		String buildNumber = rest.sendGetRequest(lastBuildNumber);
		return buildNumber;
	}
	
	@Test
	public void getJennkinsBuildNumber(){
/*		Security.insertProviderAt( new Provider() {
		}, 1);
		KeyStore keyStore = KeyStore.getInstance("JKS");
		String fileName = System.getProperty("java.home") + 
		   "/lib/security/cacerts";
		FileInputStream stream = new FileInputStream(new File(fileName));
		keyStore.load( stream, "/Toyota/-.orasi.com".toCharArray());*/
		
		try {
			TestReporter.log(getLastJenkinsBuildNumber());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
