package com.ordrit.database;

import java.util.ArrayList;
import java.util.List;

import com.ordrit.model.Store;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class OrdrItdataBaseHelper {
	private SQLiteDatabase sqLiteDatabase;
	private OrdrItDataBase ordrItDataBase;

	public OrdrItdataBaseHelper(Context context) {
		ordrItDataBase = new OrdrItDataBase(context);
	}

	public void open() throws SQLException {
		sqLiteDatabase = ordrItDataBase.getWritableDatabase();
	}

	public void close() {
		ordrItDataBase.close();
	}
	
	public boolean insertStore(Store store) {
		boolean isAdded=false;
		open();
		String whereClause = OrdrItDataBase.COLUMN_STORE_ID + "=\""
				+ store.getId() + "\"";
		String selectQuery = "SELECT  * FROM " + OrdrItDataBase.TABLE_STORE + " WHERE "
				+ whereClause;

		Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {

		}else {
			ContentValues contentValues =new ContentValues();
			contentValues.put(OrdrItDataBase.COLUMN_STORE_NAME, store.getStoreName());
			contentValues.put(OrdrItDataBase.COLUMN_STORE_LOCATION_LATLONG, store.getLocationLatLong());
			contentValues.put(OrdrItDataBase.COLUMN_STORE_ID, store.getId());
			contentValues.put(OrdrItDataBase.COLUMN_STORE_URL, store.getUrl());
			contentValues.put(OrdrItDataBase.COLUMN_STORE_PHONE_NUMBER_1, store.getPhoneNumber1());
			contentValues.put(OrdrItDataBase.COLUMN_STORE_PHONE_NUMBER_2, store.getPhoneNumber2());
			contentValues.put(OrdrItDataBase.COLUMN_STORE_OPEN_AT, store.getOpenAt());
			contentValues.put(OrdrItDataBase.COLUMN_STORE_CLOSE_AT, store.getCloseAt());
			contentValues.put(OrdrItDataBase.COLUMN_STORE_MINIMUM_ORDER, store.getMinimumOrder());
			contentValues.put(OrdrItDataBase.COLUMN_STORE_USER_ID, "");
			contentValues.put(OrdrItDataBase.COLUMN_STORE__ADDRESS_ID,"");
			sqLiteDatabase.insert(OrdrItDataBase.TABLE_STORE, null, contentValues);
			isAdded=true;
		}
		cursor.close();
		close();
		return isAdded;
		
		
	}
	public List<Store> getAllAddedStore() {
		List<Store> list= new ArrayList<Store>();
		open();

	    String selectQuery = "SELECT  * FROM "+ OrdrItDataBase.TABLE_STORE;
	   	Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
	   	
	   	if (cursor.moveToFirst()) {
			do {
				Store store=new Store();
				store.setStoreName(cursor.getString(cursor.getColumnIndex(OrdrItDataBase.COLUMN_STORE_NAME)));
				store.setId(cursor.getString(cursor.getColumnIndex(OrdrItDataBase.COLUMN_STORE_ID)));
				list.add(store);

			} while (cursor.moveToNext());
		}
		close();
		return list;
	}
	public String getStoreName(String id) {
		String storeName= null;
			open();
			String whereClause = OrdrItDataBase.COLUMN_STORE_ID + "=\""
					+ id + "\"";
			String selectQuery = "SELECT  * FROM " + OrdrItDataBase.TABLE_STORE + " WHERE "
					+ whereClause;

			Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

			if (cursor.moveToFirst()) {
             storeName=cursor.getString(cursor.getColumnIndex(OrdrItDataBase.COLUMN_STORE_NAME));
			}
			close();
	  
		return storeName;
	}
	public String getStoreSchedule(String id) {
		String schedule= null;
			open();
			String whereClause = OrdrItDataBase.COLUMN_STORE_ID + "=\""
					+ id + "\"";
			String selectQuery = "SELECT  * FROM " + OrdrItDataBase.TABLE_STORE + " WHERE "
					+ whereClause;

			Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			schedule = "The next available delivery slot at "+cursor.getString(cursor
					.getColumnIndex(OrdrItDataBase.COLUMN_STORE_OPEN_AT))
					+ " to "
					+ cursor.getString(cursor
							.getColumnIndex(OrdrItDataBase.COLUMN_STORE_CLOSE_AT));
		}
			close();
	  
		return schedule;
	}
	public String getStoreMinimumOrder(String id) {
		String minimumOrder= null;
			open();
			String whereClause = OrdrItDataBase.COLUMN_STORE_ID + "=\""
					+ id + "\"";
			String selectQuery = "SELECT  * FROM " + OrdrItDataBase.TABLE_STORE + " WHERE "
					+ whereClause;

			Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			minimumOrder = cursor.getString(cursor
					.getColumnIndex(OrdrItDataBase.COLUMN_STORE_MINIMUM_ORDER));
		}
			close();
	  
		return minimumOrder;
	}
	
	public void deleteTable(String tableName) {
	
		sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tableName);
		sqLiteDatabase.execSQL(OrdrItDataBase.CREATE_TABLE_STORE);
		
		  
	}
}
