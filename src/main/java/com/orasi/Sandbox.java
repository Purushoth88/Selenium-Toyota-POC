package com.orasi;

import org.testng.annotations.Test;

import com.orasi.utils.Base64Coder;

public class Sandbox {
	Base64Coder base = new Base64Coder();
	
	@Test
	public void main(){
		System.out.println(base.encodeString("sarahjane328"));
		System.out.println(base.encodeString("912bf2a5-6123-4a9f-9265-c662d7c7c60c"));
	}
}
