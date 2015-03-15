package com.yb.local;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static String getToday()
	  {
	    String time = "";
	    time = getToday("yyyy-MM-dd");
	    return time;
	  }

	  public static String getToday(String format)
	  {
	    return getDateStr(Calendar.getInstance().getTime(), format);
	  }

	  public static String getDateStr(Date date, String format)
	  {
	    SimpleDateFormat df = new SimpleDateFormat(format);
	    return df.format(date);
	  }

	  public static Date parseMills(long millis)
	  {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTimeInMillis(millis);
	    return calendar.getTime();
	  }

	  public static String getYearCode()
	  {
	    Calendar cal = Calendar.getInstance();
	    int year = cal.get(1);
	    String yearStr = new Integer(year).toString();
	    return yearStr.substring(2, 4);
	  }

}
