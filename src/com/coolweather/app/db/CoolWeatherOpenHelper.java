package com.coolweather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CoolWeatherOpenHelper extends SQLiteOpenHelper {
	
    /**
     * Province表建表语句
     */
	public static final String CREATE_PROVINCE="create table Province ("
            + "id integer primary key autoincrement, "
            + "province_name text, "
            + "province_code text)";
	/**
	 * City表建表语句
	 */
	public static final String CREATE_CITY="create table City ("
			+ "id integer prtimary key autoincrement, "
			+ "city_name text, "
			+ "city_code text, "
			+ "province_Id integer)";
	/**
	 * County表建表语句
	 */
	public static final String CREATE_COUNTY="create table County( "
			+ "id integer primary key autoincrement, "
			+ "county_name text, "
			+ "county_code text, "
			+ "city_Id integer)";
	public CoolWeatherOpenHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_PROVINCE); // 创建Province表
		db.execSQL(CREATE_CITY); // 创建City表
		db.execSQL(CREATE_COUNTY); // 创建County表
	}

	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

}
