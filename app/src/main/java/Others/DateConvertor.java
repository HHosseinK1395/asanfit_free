package Others;


import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by e.farajollahi on 7/9/2017.
 */

public class DateConvertor
{
	public String shamsiToMiladi(String shamsi)
	{
		String[] all = shamsi.split("/");
		int y = Integer.parseInt(all[0]);
		int m = Integer.parseInt(all[1]);
		int d = Integer.parseInt(all[2]);

		return jalali_to_gregorian(y, m, d);
	}

	public String miladiToShamsi(String miladi)
	{
		String all[] = miladi.split("/");
		int y = Integer.parseInt(all[0]);
		int m = Integer.parseInt(all[1]);
		int d = Integer.parseInt(all[2]);

		return gregorian_to_jalali(y, m, d);
	}

	public String gregorian_to_jalali(int g_y, int g_m, int g_d)
	{
		int g_days_in_month[] = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		int j_days_in_month[] = new int[]{31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29};
		int gy, gm, gd;
		int jy, jm, jd;
		long g_day_no, j_day_no;
		int j_np;
		int i;

		gy = g_y - 1600;
		gm = g_m - 1;
		gd = g_d - 1;

		g_day_no = 365 * gy + (gy + 3) / 4 - (gy + 99) / 100 + (gy + 399) / 400;
		for (i = 0; i < gm; ++i)
			g_day_no += g_days_in_month[i];
		if (gm > 1 && ((gy % 4 == 0 && gy % 100 != 0) || (gy % 400 == 0)))
							 /* leap and after Feb */
			++g_day_no;
		g_day_no += gd;

		j_day_no = g_day_no - 79;

		j_np = (int) (j_day_no / 12053);
		j_day_no %= 12053;

		jy = (int) (979 + 33 * j_np + 4 * (j_day_no / 1461));
		j_day_no %= 1461;

		if (j_day_no >= 366)
		{
			jy += (j_day_no - 1) / 365;
			j_day_no = (j_day_no - 1) % 365;
		}

		for (i = 0; i < 11 && j_day_no >= j_days_in_month[i]; ++i)
		{
			j_day_no -= j_days_in_month[i];
		}
		jm = i + 1;
		jd = (int) (j_day_no + 1);

		return "" + jy + "/" + String.format("%02d", jm) + "/" + String.format("%02d", jd);
	}

	public String jalali_to_gregorian(int j_y, int j_m, int j_d)
	{
		int g_days_in_month[] = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		int j_days_in_month[] = new int[]{31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29};
		int gy, gm, gd;
		int jy, jm, jd;
		long g_day_no, j_day_no;
		int leap;

		int i;

		jy = j_y - 979;
		jm = j_m - 1;
		jd = j_d - 1;

		j_day_no = 365 * jy + (jy / 33)*8 + (jy % 33 + 3) / 4;
		for (i = 0; i < jm; ++i)
			j_day_no += j_days_in_month[i];

		j_day_no += jd;

		g_day_no = j_day_no + 79;

		gy = (int) (1600 + 400 * (g_day_no / 146097)); /* 146097 = 365*400 + 400/4 - 400/100 + 400/400 */
		g_day_no = g_day_no % 146097;

		leap = 1;
		if (g_day_no >= 36525) /* 36525 = 365*100 + 100/4 */
		{
			g_day_no--;
			gy += 100 * (g_day_no / 36524); /* 36524 = 365*100 + 100/4 - 100/100 */
			g_day_no = g_day_no % 36524;

			if (g_day_no >= 365)
				g_day_no++;
			else
				leap = 0;
		}

		gy += 4 * (g_day_no / 1461); /* 1461 = 365*4 + 4/4 */
		g_day_no %= 1461;

		if (g_day_no >= 366)
		{
			leap = 0;

			g_day_no--;
			gy += g_day_no / 365;
			g_day_no = g_day_no % 365;
		}

		for (i = 0; g_day_no >= g_days_in_month[i] + ((i == 1 && leap == 1) ? 1:0); i++)
			g_day_no -= g_days_in_month[i] + ((i == 1 && leap == 1) ? 1:0);

		gm = i + 1;
		gd = (int) (g_day_no + 1);

		return "" + gy + "/" + String.format("%02d", gm) + "/" + String.format("%02d", gd);
	}

	public String getShamsiMonth(int month)
	{
		String strMonth = "";
		switch (month) {
			case 1:
				strMonth = "فروردين";
				break;
			case 2:
				strMonth = "ارديبهشت";
				break;
			case 3:
				strMonth = "خرداد";
				break;
			case 4:
				strMonth = "تير";
				break;
			case 5:
				strMonth = "مرداد";
				break;
			case 6:
				strMonth = "شهريور";
				break;
			case 7:
				strMonth = "مهر";
				break;
			case 8:
				strMonth = "آبان";
				break;
			case 9:
				strMonth = "آذر";
				break;
			case 10:
				strMonth = "دي";
				break;
			case 11:
				strMonth = "بهمن";
				break;
			case 12:
				strMonth = "اسفند";
				break;
		}
		return strMonth;
	}

	public String getDateFromTimeStamp(long timeStamp)
	{
		SimpleDateFormat onlyDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		return onlyDateFormat.format(timeStamp);
	}

	public String ChangeFormatYYYT(String timeString)
	{
		Date d = null;
		String miladi = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			d = sdf.parse(timeString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		//String miladi = this.miladiToShamsi(this.getDateFromTimeStamp(d.getTime()));

		miladi  = output.format(d);

		String dayOfTheWeek = (String) DateFormat.format("EEEE", d); // Thursday
		String day          = (String) DateFormat.format("dd",   d); // 20
		String monthString  = (String) DateFormat.format("MMM",  d); // Jun
		String monthNumber  = (String) DateFormat.format("MM",   d); // 06
		String year         = (String) DateFormat.format("yyyy", d); // 2013
		return year + "-" + monthNumber + "-" + day;
	}

	public String ConvertStringtoDate(String str)
	{
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd HH:mm:ss yyyy z");
		//sdf.applyPattern("EEE, MMM dd HH:mm:ss yyyy z");
		Date dt = null;
		try {
			dt = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(dt);
		return result;
	}

	public String milisecoundToYYYMMMDDD(long milisecound)
	{
		Calendar c = Calendar.getInstance();
//Set time in milliseconds
		c.setTimeInMillis(milisecound);
		int mYear = c.get(Calendar.YEAR);
		int mMonth = c.get(Calendar.MONTH);
		int mDay = c.get(Calendar.DAY_OF_MONTH);
		int hr = c.get(Calendar.HOUR);
		int min = c.get(Calendar.MINUTE);
		int sec = c.get(Calendar.SECOND);

		return mYear + "-" + mMonth + "-" + mDay + "T" + hr + ":" + min + ":" + sec;
	}
}
