package com.example.time;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLController {

	private MyDbHelper dbhelper;
	private Context ourcontext;
	private SQLiteDatabase database;

	public SQLController(Context c) {
		ourcontext = c;
	}

	public SQLController open() throws SQLException {
		dbhelper = new MyDbHelper(ourcontext);
		database = dbhelper.getWritableDatabase();
		return this;
	}

	public void close() {
		dbhelper.close();
	}

	public void insertData(String name, String lname) {
		/*
		 * // TODO Auto-generated method stub ContentValues cv = new
		 * ContentValues(); cv.put(MyDbHelper.MEMBER_FIRSTNAME, name);
		 * cv.put(MyDbHelper.MEMBER_LASTNAME, lname);
		 * database.insert(MyDbHelper.TABLE_MEMBER, null, cv);
		 */
	}

	public Cursor readEntry() {
		// TODO Auto-generated method stub
		String[] allColumns = new String[] 
				{
				MyDbHelper.Day, MyDbHelper.First,
				MyDbHelper.Second, MyDbHelper.Third, MyDbHelper.Fourth,
				MyDbHelper.Fifth, MyDbHelper.sixth };

		Cursor c = database.query(MainActivity1.TABLE_MEMBER, allColumns, null,
				null, null, null, null);

		if (c != null) {

			c.moveToFirst();
		}
		return c;

	}

}
