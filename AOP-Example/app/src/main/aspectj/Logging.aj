package com.example.ledebour.aop_example;


public aspect Logging {

	private static final String TAG = "AOP";

	pointcut androidLifecicleAndImportantChanges(): 
		(execution(* com.example.ledebour.aop_example.MainActivity+.on*(..)));

	before(): androidLifecicleAndImportantChanges(){
		android.util.Log.d(TAG, thisJoinPoint.getSignature().toShortString());
	}
	
}
