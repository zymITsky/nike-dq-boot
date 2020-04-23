
package com.nike.app.dq.boot.util.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleFormater {

	private final static String SHORT_SIMPLE_DATE_FORMATE = "yyyy-MM-dd";
	private final static String SHORT_SIMPLE_DATETIME_FORMATE = "yyyy-MM-dd HH:mm:ss";
	private final static String SIMPLE_DATETIME_FORMATE  = "yyyyMMddHHmmss";

	private final static SimpleDateFormat sdf = new SimpleDateFormat(SHORT_SIMPLE_DATE_FORMATE);
	private final static SimpleDateFormat sdtf = new SimpleDateFormat(SHORT_SIMPLE_DATETIME_FORMATE);
	private final static SimpleDateFormat sdtf1 = new SimpleDateFormat(SIMPLE_DATETIME_FORMATE);

	public final static String simpleFormate(Date date) {
		return sdf.format(date);
	}

	public final static Date simpleFormate(String date) {
		Date d = null;
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	public final static String simpleDatetimeFormate(Date date) {
		return sdtf.format(date);
	}

	public final static String simpleDateTimeFormate(Date date) {
		return sdtf1.format(date);
	}
}