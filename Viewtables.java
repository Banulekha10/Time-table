package com.example.time;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Viewtables extends Activity {
	private SQLiteOpenHelper helper;
	private SQLiteDatabase database;
	private TableLayout tableList;
	private Context context;
	private SharedPreferences prefs;
	private String prefName = "report";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewtables);

		prefs = getSharedPreferences(prefName, MODE_PRIVATE);
		final String table = prefs.getString("name", null);
		Toast.makeText(getBaseContext(),
				String.valueOf(prefs.getString("name", null)),
				Toast.LENGTH_SHORT).show();
		Loadtable(table);
	}

	public void Loadtable(String lab) {
		helper = new SQLiteOpenHelper(this, "college", null, 1) {
			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion,
					int newVersion) {

			}

			@Override
			public void onCreate(SQLiteDatabase db) {
				database = db;
			}
		};
		if (database == null) {
			database = helper.getWritableDatabase();
		}
		context = this;

		Cursor cursor = database.rawQuery("select * from " + lab, null);

		tableList = (TableLayout) findViewById(R.id.tableList);
		tableList.removeAllViews();
		while (cursor.moveToNext()) {
			TableRow tableRow1 = new TableRow(context);
			TextView txtView1 = new TextView(context);
			txtView1.setBackgroundResource(R.drawable.cell_shape);
			txtView1.setGravity(Gravity.CENTER);
			txtView1.setTextSize(18);
			txtView1.setPadding(10, 5, 10, 5);
			txtView1.setTextColor(Color.GREEN);
			txtView1.setText(cursor.getString(0));

			TextView txtView2 = new TextView(context);
			txtView2.setBackgroundResource(R.drawable.cell_shape);
			txtView2.setGravity(Gravity.CENTER);
			txtView2.setTextSize(18);
			txtView2.setPadding(10, 5, 10, 5);
			txtView2.setTextColor(Color.GREEN);
			txtView2.setText(cursor.getString(1));

			TextView txtView3 = new TextView(context);
			txtView3.setBackgroundResource(R.drawable.cell_shape);
			txtView3.setGravity(Gravity.CENTER);
			txtView3.setTextSize(18);
			txtView3.setPadding(10, 5, 10, 5);
			txtView3.setTextColor(Color.GREEN);
			txtView3.setText(cursor.getString(2));

			TextView txtView4 = new TextView(context);
			txtView4.setBackgroundResource(R.drawable.cell_shape);
			txtView4.setGravity(Gravity.CENTER);
			txtView4.setTextSize(18);
			txtView4.setPadding(10, 5, 10, 5);
			txtView4.setTextColor(Color.GREEN);
			txtView4.setText(cursor.getString(3));

			TextView txtView5 = new TextView(context);
			txtView5.setBackgroundResource(R.drawable.cell_shape);
			txtView5.setGravity(Gravity.CENTER);
			txtView5.setTextSize(18);
			txtView5.setPadding(10, 5, 10, 5);
			txtView5.setTextColor(Color.GREEN);
			txtView5.setText(cursor.getString(4));

			TextView txtView6 = new TextView(context);
			txtView6.setBackgroundResource(R.drawable.cell_shape);
			txtView6.setGravity(Gravity.CENTER);
			txtView6.setTextSize(18);
			txtView6.setPadding(10, 5, 10, 5);
			txtView6.setTextColor(Color.GREEN);
			txtView6.setText(cursor.getString(5));

			TextView txtView7 = new TextView(context);
			txtView7.setBackgroundResource(R.drawable.cell_shape);
			txtView7.setGravity(Gravity.CENTER);
			txtView7.setTextSize(18);
			txtView7.setPadding(10, 5, 10, 5);
			// txtView7.setPadding(left, top, right, bottom)
			txtView7.setTextColor(Color.GREEN);
			txtView7.setText(cursor.getString(6));

			tableRow1.addView(txtView1);
			tableRow1.addView(txtView2);
			tableRow1.addView(txtView3);
			tableRow1.addView(txtView4);
			tableRow1.addView(txtView5);
			tableRow1.addView(txtView6);
			tableRow1.addView(txtView7);
			tableList.addView(tableRow1);

		}
		// tableList.invalidate();
	}
}
