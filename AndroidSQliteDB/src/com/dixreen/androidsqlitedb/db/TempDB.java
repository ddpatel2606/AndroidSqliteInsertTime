package com.dixreen.androidsqlitedb.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.dixreen.androidsqltedb.DBHelper.DBAdapter;

public class TempDB extends DBAdapter{
	

	public static final String	DATABASE_TABLE_NAME="temp";

	public static final String TABLE_ID = "_ID";
	public static final String VALUE_1 = "value_1";
	public static final String VALUE_2 = "value_2";
	public static final String VALUE_3 = "value_3";

	public static final String CREATE_DATABASE_TABLE_TEMP = "create table "
			+ DATABASE_TABLE_NAME + " (" + TABLE_ID + " integer PRIMARY KEY autoincrement,"
			+ VALUE_1 + " TEXT," + VALUE_2 + " TEXT," + VALUE_3 + " TEXT);";

	
	public SQLiteDatabase db;
	public DBAdapter mDbAdapter;
	
	
	public TempDB(Context con) {
		super(con);
	
		mDbAdapter =new DBAdapter(con);
		db= mDbAdapter.openDatabase();
	}

	
	
	public long InsertIntoTempTable(String val1,String Val2,String val3)
	{
		long id =0;
		try
		{
		  
		  ContentValues mValues= new ContentValues();
		  mValues.put(VALUE_1, val1);
		  mValues.put(VALUE_2, Val2);
		  mValues.put(VALUE_3, val3);
		  			
		   id =db.insert(DATABASE_TABLE_NAME, null, mValues);
		
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return id;		
	}

	 public void DeleteDatabase()
	 {		
		db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE_NAME+"");
	 }
	
}
