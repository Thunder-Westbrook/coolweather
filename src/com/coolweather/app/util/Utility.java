package com.coolweather.app.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import com.coolweather.app.db.CoolWeatherDB;
import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

/**
 * xml��ʽ���н����ʹ���
 * @author �ֵ�
 * @time 2016.12.28
 *
 */

public class Utility {
	
	static String TAG = "Utility";
	
	/**
	 * /**
	 * �����ʹ�����������ص�ʡ������
	 * @param coolWeatherDB
	 * @param response
	 * @return
	 */
	public synchronized static boolean handleProvincesResponse(CoolWeatherDB
			coolWeatherDB, String response) {
		if (!TextUtils.isEmpty(response)) {
			Log.d("utility response", response);
			String[] allProvinces = response.split(",");
			if (allProvinces != null && allProvinces.length > 0) {
				for (String p : allProvinces) {
					String[] array = p.split("\\|");
					Province province = new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					// ���������������ݴ洢��Province��
					coolWeatherDB.saveProvince(province);
				}
				return true;
			}
		}
		return false;
	}

	/**
	 *  �����ʹ�����������ص��м�����
	 * @param coolWeatherDB
	 * @param response
	 * @param provinceId
	 * @return
	 */
	public synchronized static boolean handleCitiesResponse(CoolWeatherDB
			coolWeatherDB, String response, int provinceId) {
		if (!TextUtils.isEmpty(response)) {
			Log.d("utility response", response);
			String[] allcities = response.split(",");
			if (allcities != null && allcities.length > 0) {
				for (String c : allcities) {
					String[] array = c.split("\\|");
					City city = new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinceId);
					// ���������������ݴ洢��City��
					coolWeatherDB.saveCity(city);
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * �����ʹ�����������ص��ؼ�����
	 * @param coolWeatherDB
	 * @param response
	 * @param cityId
	 * @return
	 */
	public synchronized static boolean handleCountiesResponse(CoolWeatherDB
			coolWeatherDB, String response, int cityId) {
		if (!TextUtils.isEmpty(response)) {
			Log.d("utility response", response);
			String[] allcounties = response.split(",");
			if (allcounties != null && allcounties.length > 0) {
				for (String c : allcounties) {
					String[] array = c.split("\\|");
					County county = new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityId);
					// ���������������ݴ洢��County��
					coolWeatherDB.saveCounty(county);
				}
				return true;
			}
		}
		return false;
	}
	
    /**
     * �������������ص�JSON���ݣ����������������ݴ洢�����ء�
     * @param context
     * @param response 
     */
	public static void handleWeatherResponse(Context context,String response) {
		try {
			JSONObject jsonObject = new JSONObject(response);
			JSONObject weatherInfo = jsonObject.getJSONObject("weatherinfo");
			String cityName = weatherInfo.getString("city");
			String weatherCode = weatherInfo.getString("cityid");
			String temp1 = weatherInfo.getString("temp1");
			String temp2 = weatherInfo.getString("temp2");
			String weatherDesp = weatherInfo.getString("weather");
			String publishTime = weatherInfo.getString("ptime");
			saveWeatherInfo(context, cityName, 
					weatherCode, temp1, temp2, weatherDesp, publishTime);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * �����������ص�����������Ϣ�洢��SharedPreferences�ļ��С�
     * @param context
     * @param cityName
     * @param weatherCode
     * @param temp1
     * @param tremp2
     * @param weatherDesp
     * @param pulishTime 
     */
	public static void saveWeatherInfo(Context context, String cityName, 
			String weatherCode, String temp1, String temp2, String weatherDesp, String publishTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��M��d��",Locale.CHINA);
		SharedPreferences.Editor editor = PreferenceManager
				.getDefaultSharedPreferences(context).edit();
		editor.putBoolean("city_selected", true);
		editor.putString("city_name", cityName);
		editor.putString("weather_code", weatherCode);
		editor.putString("temp1", temp1);
		editor.putString("temp2", temp2);
		editor.putString("weather_desp", weatherDesp);
		editor.putString("publish_time", publishTime);
		editor.putString("current_date", sdf.format(new Date()));
		editor.commit();		
	}
	
}
