package com.example.time;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {

	// TABLE INFORMATTION
	// public static String TABLE_MEMBER = "msc1";
	public static String TABLE_MEMBER;

	public static final String Day = "ord";
	public static final String First = "first";
	public static final String Second = "second";
	public static final String Third = "third";
	public static final String Fourth = "fourth";
	public static final String Fifth = "fifth";
	public static final String sixth = "sixth";

	// DATABASE INFORMATION
	static final String DB_NAME = "college";
	static final int DB_VERSION = 1;

	// TABLE CREATION STATEMENT

	/*
	 * private static final String CREATE_TABLE = "create table " + TABLE_MEMBER
	 * + "(" + MEMBER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
	 * MEMBER_FIRSTNAME + " TEXT NOT NULL ," + MEMBER_LASTNAME +
	 * " TEXT NOT NULL,MemberM varchar(20));";
	 */

	public MyDbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		// db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBER);
		onCreate(db);

	}

}