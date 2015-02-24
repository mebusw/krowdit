package com.tadosoft.krowdit.util;

import java.text.SimpleDateFormat;

public class DateFormatter {
	private static SimpleDateFormat sdf = null;

	/**
	 * A singleton wrapper of java.text.SimpleDateFormat to format a Date string with format "MM-dd-yyyy HH:mm:ss"
	 * @return formatted date string
	 * 
	 * @see Date
	 * @see SimpleDateFormat
	 */
	public synchronized static SimpleDateFormat getInstance() {
		if (null == sdf) {
			sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		}
		return sdf;
	}
}
