package com.orasi.api.restServices;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public class SeleniumJobInfo {
	private String description;
	private String displayName;
	private String name;
	private String buildable;
	private String url;
	private String nextBuildNumber;
	
	public String getDescription() {return description;}
	public String getDisplayName() {return displayName;}
	public String getName() {return name;}
	public String getBuildable() {return buildable;}
	public String getUrl() {return url;	}
	public String getNextBuildNumber() {return nextBuildNumber;}
	
	public static String lastBuildURL = "http://jenkins.orasi.com/job/OpenSandbox/job/Toyota-SauceLabs/lastBuild/api/json";
	public static String lastBuildNumber = "https://jenkins.orasi.com/job/OpenSandbox/job/Toyota-SauceLabs/api/json?tree=builds[number]";
	
	private static RestService rest = new RestService();
	private String restResponse = "";
	
	public static String getLastJenkinsBuildNumber() throws ClientProtocolException, IOException{
		String buildNumber = rest.sendGetRequest(lastBuildNumber);
		return buildNumber;
	}
}
