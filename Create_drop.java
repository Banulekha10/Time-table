package com.example.time;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class Create_drop extends Activity {
	Button creat, drop;
	private SharedPreferences prefs;
	private String prefName = "report";
	public static final CharSequence[] days = { "Monday", "Tuesday",
			"Wednesday", "Thursday", "Friday" };
	public static final CharSequence[] hour = { "first", "second", "third",
			"fourth", "fifth", "sixth" };

	SQLiteDatabase sq;
	String em = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_drop);
		creat = (Button) findViewById(R.id.cre);
		drop = (Button) findViewById(R.id.dr);
		prefs = getSharedPreferences(prefName, MODE_PRIVATE);
		final String tname = prefs.getString("name", null);
		Toast.makeText(getBaseContext(), tname, Toast.LENGTH_SHORT).show();

		sq = Create_drop.this.openOrCreateDatabase("college", MODE_PRIVATE,
				null);

		// sq.execSQL("create table if not exists msc2(ord varchar2(20),first varchar2(30),second varchar2(30),third varchar2(30),fourth varchar2(30),fifth varchar2(30),sixth varchar2(20));");
		sq.execSQL("create table if not exists "
				+ tname
				+ "(ord varchar2(20),first varchar2(30),second varchar2(30),third varchar2(30),fourth varchar2(30),fifth varchar2(30),sixth varchar2(20));");
		/*
		 * sq.execSQL("insert into msc1 values('"+days[0]
		 * +"','"+em+"','"+em+"','"+em+"','"+em+"','"+em+"','"+em+"');");
		 * sq.execSQL("insert into msc1 values('"+days[1]
		 * +"','"+em+"','"+em+"','"+em+"','"+em+"','"+em+"','"+em+"');");
		 * sq.execSQL("insert into msc1 values('"+days[2]
		 * +"','"+em+"','"+em+"','"+em+"','"+em+"','"+em+"','"+em+"');");
		 * sq.execSQL("insert into msc1 values('"+days[3]
		 * +"','"+em+"','"+em+"','"+em+"','"+em+"','"+em+"','"+em+"');");
		 * sq.execSQL("insert into msc1 values('"+days[4]
		 * +"','"+em+"','"+em+"','"+em+"','"+em+"','"+em+"','"+em+"');");
		 */
		// Toast.makeText(getApplicationContext(), "Value inserted",
		// Toast.LENGTH_LONG).show();

		// Toast.makeText(getApplicationContext(),
		// "Table created ",Toast.LENGTH_SHORT).show();
		/* sq.execSQL("drop table msc1"); */

		creat.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				sq.execSQL("drop table " + tname);
				sq = Create_drop.this.openOrCreateDatabase("college",
						MODE_PRIVATE, null);
				sq.execSQL("create table if not exists "
						+ tname
						+ "(ord varchar2(20),first varchar2(30),second varchar2(30),third varchar2(30),fourth varchar2(30),fifth varchar2(30),sixth varchar2(20));");
				String or = "ORDER";
				sq.execSQL("insert into " + tname + " values('" + or + "','"
						+ hour[0] + "','" + hour[1] + "','" + hour[2] + "','"
						+ hour[3] + "','" + hour[4] + "','" + hour[5] + "');");
				sq.execSQL("insert into " + tname + " values('" + days[0]
						+ "','" + em + "','" + em + "','" + em + "','" + em
						+ "','" + em + "','" + em + "');");
				sq.execSQL("insert into " + tname + " values('" + days[1]
						+ "','" + em + "','" + em + "','" + em + "','" + em
						+ "','" + em + "','" + em + "');");
				sq.execSQL("insert into " + tname + " values('" + days[2]
						+ "','" + em + "','" + em + "','" + em + "','" + em
						+ "','" + em + "','" + em + "');");
				sq.execSQL("insert into " + tname + " values('" + days[3]
						+ "','" + em + "','" + em + "','" + em + "','" + em
						+ "','" + em + "','" + em + "');");
				sq.execSQL("insert into " + tname + " values('" + days[4]
						+ "','" + em + "','" + em + "','" + em + "','" + em
						+ "','" + em + "','" + em + "');");
				Toast.makeText(arg0.getContext(), "new table created",
						Toast.LENGTH_LONG).show();
				Intent i = new Intent(Create_drop.this, Selectstaff.class);
				startActivity(i);
			}
		});
		drop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				sq.execSQL("drop table " + tname);
				Toast.makeText(arg0.getContext(),
						"old table is dropped.now click create table button",
						Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_create_drop, menu);
		return true;
	}

}
