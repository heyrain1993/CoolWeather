package com.coolweather.util;

public interface CallbackListener {
	void onFinish(String response);
	void onError(Exception e);
}
