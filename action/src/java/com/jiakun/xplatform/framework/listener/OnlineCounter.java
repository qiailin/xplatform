package com.jiakun.xplatform.framework.listener;

/**
 * 
 * @author
 * 
 */
public final class OnlineCounter {

	private static long online = 0;

	private OnlineCounter() {

	}

	public static long getOnline() {
		return online;
	}

	public static void raise() {
		online++;
	}

	public static void reduce() {
		online--;
	}

}
