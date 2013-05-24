package com.dixreen.androidsqlitedb;

import android.app.Activity;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dixreen.androidsqlitedb.db.TempDB;

public class MainActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button StatementWithTransactionInsertBtn =(Button)findViewById(R.id.StatementWithTransactionInsertBtn);
		StatementWithTransactionInsertBtn.setOnClickListener(this);
		
		Button ResetBtn =(Button)findViewById(R.id.ResetBtn);
		ResetBtn.setOnClickListener(this);
		
		Button StatementInsertBtn =(Button)findViewById(R.id.StatementInsertBtn);
		StatementInsertBtn.setOnClickListener(this);
		Button simpleInsertBtn =(Button)findViewById(R.id.simpleInsertBtn);
		simpleInsertBtn.setOnClickListener(this);
		Button transactionInsertBtn =(Button)findViewById(R.id.transactionInsertBtn);
		transactionInsertBtn.setOnClickListener(this);
		
		EditText mrecordValue=(EditText)findViewById(R.id.recordValue);
		mrecordValue.setText("0");
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		TempDB mDb = new TempDB(getApplicationContext());

		EditText mrecordValue=(EditText)findViewById(R.id.recordValue);
		TextView StatementWithTransactionInsertTime =(TextView) findViewById(R.id.StatementWithTransactionInsertTime);
		TextView StatementInsertTime =(TextView) findViewById(R.id.StatementInsertTime);
		TextView simpleInsertTime =(TextView) findViewById(R.id.simpleInsertTime);
		TextView transactionInsertTime =(TextView) findViewById(R.id.transactionInsertTime);
						
		long startnow=0;
		long endnow=0;
		
		if (v.getId() == R.id.StatementWithTransactionInsertBtn) {

			startnow = android.os.SystemClock.uptimeMillis();
			try {

				mDb.mDbAdapter.db.beginTransaction();
				String sql = "Insert into "
						+ TempDB.DATABASE_TABLE_NAME + " (" + TempDB.VALUE_1
						+ ", " + TempDB.VALUE_2 + "," + TempDB.VALUE_3
						+ ") values(?,?,?)";

				SQLiteStatement insert = mDb.mDbAdapter.db
						.compileStatement(sql);

				for (int i = 0; i < Integer.valueOf(mrecordValue.getText().toString()); i++) {

					insert.bindString(1, "HEllo " + i);
					insert.bindString(2, "value " + i);
					insert.bindString(3, "meet  " + i);

					insert.execute();
					
					Log.i("StatementWithTransactionInsert", " InsertId : "+i);
					
					insert.clearBindings();
				}
				mDb.mDbAdapter.db.setTransactionSuccessful();
				Log.d("This Activity", "StatementWithTransactionInsert");
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				mDb.mDbAdapter.db.endTransaction();
			}
			
			endnow = android.os.SystemClock.uptimeMillis();
			
			StatementWithTransactionInsertTime.setText(""+(endnow-startnow)/1000+" s");

		} else if (v.getId() == R.id.StatementInsertBtn) {

			startnow = android.os.SystemClock.uptimeMillis();
			try {
						
				String sql = "Insert into "
						+ TempDB.DATABASE_TABLE_NAME + " (" + TempDB.VALUE_1
						+ ", " + TempDB.VALUE_2 + "," + TempDB.VALUE_3
						+ ") values(?,?,?)";

				SQLiteStatement insert = mDb.mDbAdapter.db
						.compileStatement(sql);

				for (int i = 0; i < Integer.valueOf(mrecordValue.getText().toString()); i++) {

					insert.bindString(1, "HEllo " + i);
					insert.bindString(2, "value " + i);
					insert.bindString(3, "meet  " + i);

					insert.execute();
					
					Log.i("StatementInsert", " InsertId :  "+i);
					insert.clearBindings();
					
					
				}

				Log.d("This Activity", "StatementInsert");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			endnow = android.os.SystemClock.uptimeMillis();
			StatementInsertTime.setText(""+(endnow-startnow)/1000+" s");

		} else if (v.getId() == R.id.simpleInsertBtn) {
			
			startnow = android.os.SystemClock.uptimeMillis();
			
			try {

				for (int i = 0; i < Integer.valueOf(mrecordValue.getText().toString()); i++) {

				long id= mDb.InsertIntoTempTable("HEllosm " + i, "valuesm " + i,
							"meetsm  " + i);
					
					Log.i("simpleInsert", " InsertId : "+id+" & i : "+i);
				}
			
				Log.d("This Activity", "simpleInsert");
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			endnow = android.os.SystemClock.uptimeMillis();
			simpleInsertTime.setText(""+(endnow-startnow)/1000+" s");
			
		} else if (v.getId() == R.id.transactionInsertBtn) {

			startnow = android.os.SystemClock.uptimeMillis();
			
			try {

				mDb.mDbAdapter.db.beginTransaction();

				for (int i = 0; i < Integer.valueOf(mrecordValue.getText().toString()); i++) {

					long id =mDb.InsertIntoTempTable("HEllomn " + i, "valuemn " + i,
							"meetmn  " + i);
					
					Log.i("transactionInsert", " InsertId : "+id+" & i : "+i);
					
				}
				mDb.mDbAdapter.db.setTransactionSuccessful();
				Log.d("This Activity", "transactionInsert");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				mDb.mDbAdapter.db.endTransaction();
			}
			
			endnow = android.os.SystemClock.uptimeMillis();
			transactionInsertTime.setText(""+(endnow-startnow)/1000+" s");
		}
		else if(v.getId()==R.id.ResetBtn)
		{
			
			transactionInsertTime.setText("");
			simpleInsertTime.setText("");
			StatementInsertTime.setText("");
			StatementWithTransactionInsertTime.setText("");
			mrecordValue.setText("0");
			startnow=0;
		    endnow=0;
		  		  
		}

	}
	

}
