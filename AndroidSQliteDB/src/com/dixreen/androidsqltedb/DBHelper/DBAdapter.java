package com.dixreen.androidsqltedb.DBHelper;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.dixreen.androidsqlitedb.Consts;
import com.dixreen.androidsqlitedb.db.TempDB;

public class DBAdapter {

	public static final String	TAG = DBAdapter.class.getName();

	public static final String DATABASE_NAME="TempDatabase";
	public static final int  DATABASE_VERSION = 1; 


	
	public SQLiteDatabase db;
	public static DBHelper mDBHelper;
	public Context mContext;


	public DBAdapter(Context con) {

		mContext = con;

		// Create DBHelper Class SingleTon for created Database Multithreaded.

		if(mDBHelper == null)
		{
			mDBHelper = new DBHelper(con);
		}


	}



	public static class DBHelper extends  SQLiteOpenHelper{



		public DBHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);

			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub

			db.execSQL(TempDB.CREATE_DATABASE_TABLE_TEMP);


			if(Consts.IsDeBug)
			{			
				Log.i(TAG, "Database Created");
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub

			try {

				Log.i(TAG, "Upgrading database from version "
						+ oldVersion + " to " + newVersion);

				// we want both updates, so no break statement here...
				switch (oldVersion) {
				case 1:

				case 2:

				}

			} catch (SQLiteException se) {
				// TODO: handle exception
				if (Consts.IsDeBug) {

					se.printStackTrace();
				}
			} catch (Exception ex) {
				if (Consts.IsDeBug) {

					ex.printStackTrace();
				}
			}
		}


		@Override
		public synchronized void close() {
			// TODO Auto-generated method stub
			super.close();

			if(Consts.IsDeBug)
			{
				Log.i(TAG, "Database close");
			}

		}

		@Override
		public void onOpen(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			super.onOpen(db);

			if(Consts.IsDeBug)
			{
				Log.i(TAG, "Database Open");
			}
		}
	}

	public synchronized SQLiteDatabase openDatabase() throws SQLException {

		try {

			if (db == null) {
				db = mDBHelper.getWritableDatabase();
			}

		} catch (Exception ex) {
			if (Consts.IsDeBug) {
				ex.printStackTrace();
			}
		}
		return db;
	}

	public synchronized SQLiteDatabase openReadDatabase() throws SQLException {

		try {
			if (db == null) {
				db = mDBHelper.getReadableDatabase();
			}

		} catch (Exception ex) {
			if (Consts.IsDeBug) {
				ex.printStackTrace();
			}
		}

		return db;
	}

	public synchronized void closeDatabase() {

		// If Another Application is Not Access Your Database Then Don't Close Your Database
		//  this Will never give Errors. 

		// db.setTransactionSuccessful();
		// db.endTransaction();
		// DBHelper.close();
	}

	public synchronized void exit() {
		mDBHelper = null;

		Log.i(TAG, "dbhelper : " + mDBHelper);

	}

}
