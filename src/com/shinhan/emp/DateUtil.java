package com.shinhan.emp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static String convertToString(Date d1) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(d1);
		
		return str;
	}
	
	public static Date convertToDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		try {
			d1 = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return d1;
	}
	
	public static java.sql.Date convertToSQLDate(Date d) {
		return new java.sql.Date(d.getTime());
	}

}
