package com.example.time;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class MainActivity1 extends Activity {
	TableLayout table_layout;
	SQLController sqlcon;
	ProgressDialog PD;
	public static String TABLE_MEMBER;
	private SharedPreferences prefs;
	private String prefName = "report";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main1);
		prefs = getSharedPreferences(prefName, MODE_PRIVATE);

		TABLE_MEMBER = prefs.getString("name", null);
		sqlcon = new SQLController(this);
		table_layout = (TableLayout) findViewById(R.id.tableLayout1);
		BuildTable();
	}

	private void BuildTable() {
		sqlcon.open();
		Cursor c = sqlcon.readEntry();
		int rows = c.getCount();
		int cols = c.getColumnCount();
		c.moveToFirst();
		Log.d("Rows", String.valueOf(rows));
		Log.d("Cols", String.valueOf(cols));
		// outer for loop
		for (int i = 0; i < rows; i++) {
			TableRow row = new TableRow(this);
			row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT));
			// inner for loop
			for (int j = 0; j < cols; j++) {
				TextView tv = new TextView(this);
				tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				tv.setBackgroundResource(R.drawable.cell_shape);
				tv.setGravity(Gravity.CENTER);
				tv.setTextSize(18);
				tv.setPadding(0, 5, 0, 5);
				tv.setTextColor(Color.GREEN);
				tv.setText(c.getString(j));
				row.addView(tv);
			}

			c.moveToNext();
			table_layout.addView(row);
		}
		sqlcon.close();
	}
}
