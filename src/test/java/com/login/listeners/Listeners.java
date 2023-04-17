package com.login.listeners;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import utils.ScreenshotUtil;

public class Listeners extends ScreenshotUtil implements ITestListener {

	public void onTestFailure(ITestResult result) {
		try {
			if (takeScreenShot()) {
				System.out.println("Screenshot taken");
			}
		} catch (IOException e) {
			System.out.println("Screenshot not taken");
		}
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Test success");
	}

	public void onTestStart(ITestResult result) {
		System.out.println("Test Start");
	}
}
