package com.rsystem.uniprint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rsystem.uniprint.domain.PrintRequest;
import com.rsystem.uniprint.domain.TestCase;
import com.rsystem.uniprint.executor.TestCaseExecutor;

import java.io.FileInputStream;

public class Test {

	public static void main(String[] args) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		PrintRequest request = mapper.readValue(new FileInputStream(System.getProperty("user.dir").toString()+ "\\print.json"), PrintRequest.class);
		System.out.println(request.toString());
		for (TestCase testCase : request.getTestCases()) {
			TestCaseExecutor testCaseExecutor = new TestCaseExecutor(testCase);
			testCaseExecutor.init();
		}
	}

}
