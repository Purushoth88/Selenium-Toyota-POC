package com.orasi;

import org.testng.annotations.Test;

import com.orasi.utils.Base64Coder;

public class Sandbox {
	Base64Coder base = new Base64Coder();
	
	@Test
	public void main(){
		System.out.println(base.encodeString(""));
		System.out.println(base.encodeString(""));
	}
}
