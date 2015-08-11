package com.orasi.api.restServices;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

import com.orasi.utils.Screenshot;
import com.orasi.utils.TestEnvironment;
import com.orasi.utils.TestReporter;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

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
	public void postTestToMustard() throws Exception{		
//		postTest();
		PostFile();
	}
	
	public void PostFile() throws Exception {
		nameValuePairs.add(new BasicNameValuePair("status", "fail"));
		nameValuePairs.add(new BasicNameValuePair("comment", "Testing Screenshots"));
		nameValuePairs.add(new BasicNameValuePair("test_name", "TestName"));
		nameValuePairs.add(new BasicNameValuePair("device_id", "123456789"));	
		
		StringBody status = new StringBody("fail");
		StringBody comment = new StringBody("Testing Screenshots");
		StringBody test_name = new StringBody("TestName");
		StringBody device_id = new StringBody("123456789");
		
		//Define the fully qualified path
		String imagePath = "C:\\Users\\temp\\Desktop\\tardis.jpg";
		//Create a default HTTP client
		HttpClient httpclient = HttpClients.createDefault();
		//Create an HTTP POST object with the Mustard IP address
	    HttpPost httppost = new HttpPost(mustardIp);

	    //Create a multipart entity that will hold the image and  be added to the post request
	    MultipartEntityBuilder mpEntity = MultipartEntityBuilder.create();
	    //Set mode for the multipart entity - may not be needed
//	    mpEntity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

	    /*
	     * TRY SENDING THE IMAGE AS A FILE
	     */
		File file = new File(imagePath);
//		File file2 = ((TakesScreenshot) ).getScreenshotAs(OutputType.FILE);
		/*
		 * USING FILEBODY
		 */
	    FileBody fileBody = new FileBody(file);
		mpEntity.addPart("screenshot", fileBody);
		mpEntity.addPart("status", status);
		mpEntity.addPart("comment", comment);
		mpEntity.addPart("test_name", test_name);
		mpEntity.addPart("device_id", device_id);
		
	    /*
	     * USING CONTENTBODY
	     */
//		ContentBody cbFile = new FileBody(file);
//	    mpEntity.addPart("screenshot", cbFile);
	    
	    /*
	     * TRY SENDING THE IMAGE AS A BYTE ARRAY
	     */
//	    ByteArrayBody byteBody = new ByteArrayBody(Files.readAllBytes(Paths.get(imagePath)), "myScreenshot");
//	    mpEntity.addPart("screenshot", byteBody);
	    
	    //Build the multipart entity into an HTTP entity
	    HttpEntity entity = mpEntity.build();

	    //Use the HTTP entity to define the entity used by the HTTP POST object
	    httppost.setEntity(entity);
//	    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
	    
	    System.out.println("executing request " + httppost.getRequestLine());
	    //Use the HTTP POST object with the HTTP client to execute the POST
	    HttpResponse response = httpclient.execute(httppost);
	    //Grab the response entity
	    HttpEntity resEntity = response.getEntity();

	    //Output the status
	    System.out.println(response.getStatusLine());
	    //If the response is not null, output the entire response
	    if (resEntity != null) {
	      System.out.println(EntityUtils.toString(resEntity));
	    }
	    
	    resEntity.getContent().close();
	    httpclient.getConnectionManager().shutdown();
	}
}
