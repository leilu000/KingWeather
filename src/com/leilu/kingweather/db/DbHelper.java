package com.leilu.kingweather.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库创建工具
 * 
 * @author Administrator
 * 
 */
public class DbHelper extends SQLiteOpenHelper {
	/**
	 * 省份建表语句
	 */
	private static final String CREATE_PROVINCE = "create table province ("
			+ "id integer primary key autoincrement,"
			+ "province_name varchar(255),province_code(255))";
	/**
	 * 城市建表语句
	 */
	private static final String CREATE_CITY = "create table city ("
			+ "id integer primary key autoincrement,"
			+ "city_name varchar(255),city_code(255),privince_id interger)";

	/**
	 * 县份建表语句
	 */
	private static final String CREATE_COUNTRY = "create table country ("
			+ "id integer primary key autoincrement,"
			+ "country_name varchar(255),country_code(255),city_id interger)";

	public DbHelper(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
	}

	public DbHelper(Context context) {
		this(context, "weather.db", null, 1);
	}

	public DbHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_PROVINCE);
		db.execSQL(CREATE_CITY);
		db.execSQL(CREATE_COUNTRY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
