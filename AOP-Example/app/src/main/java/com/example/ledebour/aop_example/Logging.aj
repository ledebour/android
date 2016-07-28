package com.example.ledebour.aop_example;


public aspect Logging {

	private static final String TAG = "AOP";

	pointcut androidLifecicleAndImportantChanges(): 
		(execution(* android.app.Activity+.on*(..)) || execution(* android.support.v4.app.FragmentActivity+.on*(..)) || execution( * android.app.Service+.on*(..)));

	before(): androidLifecicleAndImportantChanges(){
		android.util.Log.d(TAG, thisJoinPoint.getSignature().toShortString());
	}
	
}
