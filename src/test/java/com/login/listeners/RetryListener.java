package com.login.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import utils.RetryImplementation;

public class RetryListener implements IAnnotationTransformer{
public void transform(ITestAnnotation annotation, Class testclass, Constructor testConstructor, Method testMethod) {
	annotation.setRetryAnalyzer(RetryImplementation.class);
}
	
}
