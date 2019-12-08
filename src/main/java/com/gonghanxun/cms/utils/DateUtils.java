package com.gonghanxun.cms.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
	static final int millSecondsPerDay =  1000*60*60*24; 
	
	public static int getAge(Date birthday) {
		//2018 12 4 // 5 // 6
		Calendar calendar = Calendar.getInstance();
		//璁＄畻鍑虹敓鐨勫勾銆佹湀銆佹棩
		calendar.setTime(birthday);
		int birthYear = calendar.get(Calendar.YEAR);
		int birthMonth = calendar.get(Calendar.MONTH);
		int birthDate = calendar.get(Calendar.DATE);
		
		
		//璁＄畻褰撳墠鐨勫勾銆佹湀銆佹棩
		calendar.setTime(new Date());
		int currentYear = calendar.get(Calendar.YEAR);
		int currentMonth = calendar.get(Calendar.MONTH);
		int currentDate = calendar.get(Calendar.DATE);
		
		int age = currentYear - birthYear;
		// 濡傛灉褰撳墠鐨勬湀浠藉皬 鍒欏勾榫勫噺鍘�1
		if(currentMonth<birthMonth) {
			age--;
		}else if(currentMonth==birthMonth && currentDate<birthDate) {
			// 濡傛灉鏈堜唤鐩稿悓 褰撳墠鐨勬棩鏈熷皬 鍒欏勾榫勫噺鍘�1
			age--;
		}
		return age;	
	}
	
	/**
	 * 璁＄畻杩樺墿浣欏灏戝ぉ
	 * @param future
	 */
	public static int getRemainDays(Date future) {
		
		return (int )((future.getTime()- new Date().getTime())/millSecondsPerDay);
		
	}
	
	/**
	 *  鍒ゆ柇鏄惁涓哄綋澶�
	 * @param date
	 * @return
	 */
	public static boolean isToday(Date date) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		
		String formatSrc = dateFormat.format(date);// 鍙傛暟鏍煎紡鍖栨垚涓�涓瓧绗︿覆
		
		String formatToday = dateFormat.format(new Date());// 鎶婂綋鍓嶆棩鏈熸牸寮忓寲鎴愬瓧绗︿覆
		
		return formatSrc.equals(formatToday);
		
	}
	
	/**
	 * 鑾峰彇褰撴湀鐨勬湀鍒�
	 */
	public static Date getBeginOfMonth() {
		
		// 鑾峰彇鏃ュ巻鐨勫疄渚�
		Calendar instance = Calendar.getInstance();
		// 璁剧疆鎴愬綋鍓嶇殑鏃堕棿
		instance.setTime(new Date());
		instance.set(Calendar.SECOND, 0);// 璁剧疆0绉�
		instance.set(Calendar.MINUTE, 0);// 璁剧疆0鍒�
		instance.set(Calendar.HOUR, 0);// 璁剧疆0灏忔椂
		instance.set(Calendar.AM_PM, Calendar.AM);// 璁剧疆涓婂崍
		instance.set(Calendar.DATE, 1);// 璁剧疆1鏃�
		
		return instance.getTime();
	}
	
	/**
	 * 鑾峰彇褰撳墠鏈堢殑鏈堟湯
	 * @return
	 */
	public static Date getEndOfMonth() {
		// 鑾峰彇鏃ュ巻鐨勫疄渚�
		Calendar instance = Calendar.getInstance();
		// 璁剧疆鎴愬綋鍓嶇殑鏃堕棿
		instance.setTime(new Date());
		instance.add(Calendar.MONTH, 1);// 鏈堜唤鍔�1
		
		// 涓嬪垪浠ｇ爜璁剧疆鎴愭湀鍒�
		instance.set(Calendar.SECOND, 0);// 璁剧疆0绉�
		instance.set(Calendar.MINUTE, 0);// 璁剧疆0鍒�
		instance.set(Calendar.HOUR, 0);// 璁剧疆0灏忔椂
		instance.set(Calendar.AM_PM, Calendar.AM);// 璁剧疆涓婂崍
		instance.set(Calendar.DATE, 1);// 璁剧疆1鏃�
		
		// 鍑忓幓涓�绉� 鍙樻垚褰撴湀鐨勬湀鏈�
		instance.add(Calendar.SECOND, -1);// 绉掑噺鍘�1
		return instance.getTime();
		
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static boolean  isThisWeek(Date date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

		Calendar firstDayOfWeek = Calendar.getInstance(Locale.getDefault());

		firstDayOfWeek.setFirstDayOfWeek(Calendar.MONDAY);

		int day = firstDayOfWeek.get(Calendar.DAY_OF_WEEK);

		firstDayOfWeek.add(Calendar.DATE, -day+1+1);// 鍚庨潰鐨�+1鏄洜涓轰粠鍛ㄦ棩寮�濮�

		// 鏈懆涓�鐨勬棩鏈�

		System.out.println(format.format(firstDayOfWeek.getTime()));

		Calendar lastDayOfWeek = Calendar.getInstance(Locale.getDefault());

		lastDayOfWeek.setFirstDayOfWeek(Calendar.MONDAY);

		day = lastDayOfWeek.get(Calendar.DAY_OF_WEEK);

		lastDayOfWeek.add(Calendar.DATE, 7-day+1);

		// 鏈懆鏄熸湡澶╃殑鏃ユ湡

		System.out.println(format.format(lastDayOfWeek.getTime()));
		
		return (date.getTime()<lastDayOfWeek.getTime().getTime() &&
				date.getTime()>firstDayOfWeek.getTime().getTime() );

	}
}
