package com.leilu.kingweather.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.leilu.kingweather.model.City;
import com.leilu.kingweather.model.Country;
import com.leilu.kingweather.model.Province;

/**
 * ��ʡ�ݡ����С��ط���ص����ݿ⹤��
 * 
 * @author Administrator
 * 
 */
public class CityDB {
	private DbHelper helper;
	private static CityDB cityDB;

	private CityDB(Context context) {
		helper = new DbHelper(context);
	}

	/**
	 * �õ�����ʵ��
	 * 
	 * @param context
	 * @return
	 */
	public static CityDB getInstance(Context context) {
		if (cityDB == null) {
			synchronized (CityDB.class) {
				if (cityDB == null) {
					cityDB = new CityDB(context);
				}
			}
		}
		return cityDB;
	}

	/**
	 * ����ʡ������
	 * 
	 * @param province
	 */
	public void saveProvince(Province province) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("province_name", province.getProvinceName());
		values.put("province_code", province.getProvinceCode());
		db.insert("province", null, values);
	}

	/**
	 * ��ѯ���е�ʡ��
	 * 
	 * @return
	 */
	public List<Province> loadProvinces() {
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db
				.query("province", null, null, null, null, null, null);
		List<Province> list = new ArrayList<Province>();
		Province province = null;
		while (cursor.moveToNext()) {
			province = new Province();
			province.setId(cursor.getInt(cursor.getColumnIndex("province_id")));
			province.setProvinceName(cursor.getString(cursor
					.getColumnIndex("province_name")));
			province.setProvinceCode(cursor.getString(cursor
					.getColumnIndex("province_code")));
			list.add(province);
		}
		cursor.close();
		return list;
	}

	/**
	 * �����������
	 * 
	 * @param province
	 */
	public void saveCity(City city) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("city_name", city.getCityName());
		values.put("city_code", city.getCityCode());
		values.put("province_id", city.getProvinceId());
		db.insert("city", null, values);
	}

	/**
	 * ����ʡ��id��ѯ��Ӧ�ĳ�������
	 * 
	 * @param provinceId
	 *            ʡ��id
	 * @return
	 */
	public List<City> loadCityByProvinceId(int provinceId) {
		SQLiteDatabase db = helper.getReadableDatabase();
		List<City> list = new ArrayList<City>();
		Cursor cursor = db.query("city", null, "province_id = ?",
				new String[] { String.valueOf(provinceId) }, null, null, null);
		City city = null;
		while (cursor.moveToNext()) {
			city = new City();
			city.setId(cursor.getInt(cursor.getColumnIndex("city_id")));
			city.setCityCode(cursor.getString(cursor
					.getColumnIndex("city_code")));
			city.setCityName(cursor.getString(cursor
					.getColumnIndex("city_name")));
			city.setProvinceId(provinceId);
			list.add(city);
		}
		cursor.close();
		return list;
	}

	/**
	 * �����ط�
	 * 
	 * @param country
	 */
	public void saveCountry(Country country) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("country_name", country.getCountryName());
		values.put("country_code", country.getCountryCode());
		values.put("city_id", country.getCityId());
		db.insert("country", null, values);
	}

	/**
	 * ���ݳ���id��ѯ��Ӧ���ط�
	 * 
	 * @param cityId
	 * @return
	 */
	public List<Country> loadCountryByCityId(int cityId) {
		SQLiteDatabase db = helper.getReadableDatabase();
		List<Country> list = new ArrayList<Country>();
		Cursor cursor = db.query("country", null, "city_id = ?",
				new String[] { String.valueOf(cityId) }, null, null, null);
		Country country = null;
		while (cursor.moveToNext()) {
			country = new Country();
			country.setId(cursor.getInt(cursor.getColumnIndex("country_id")));
			country.setCountryCode(cursor.getString(cursor
					.getColumnIndex("country_code")));
			country.setCountryName(cursor.getString(cursor
					.getColumnIndex("country_name")));
			country.setCityId(cityId);
			list.add(country);
		}
		cursor.close();
		return list;
	}
}
