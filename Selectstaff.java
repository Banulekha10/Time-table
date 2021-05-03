package com.example.time;

import java.util.Calendar;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class Selectstaff extends Activity implements OnItemSelectedListener {
	private SharedPreferences prefs;
	private String prefName = "report";
	SQLiteDatabase sq;
	Spinner sp1, sp2, sp3, sp4;
	Button sub, b2;
	String em = "";
	String ContactName = "";
	int i = 0;
	public static final CharSequence[] days = { "Monday", "Tuesday",
			"Wednesday", "Thursday", "Friday" };
	public static final CharSequence[] hour = { "first", "second", "third",
			"fourth", "fifth", "sixth" };
	// public String
	// staff={"Sunitha","Brindha","Muthu","Shakthi","Angai","Gowri","Vidya","Padmasini"};
	// public static final CharSequence[]
	// subject={"Phython","JAVA","P.d","Ds","SE","FIT","c","ALG","LIB"};
	String d = "", h = "", staf = "", subj = "", allot = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selectstaff);
		prefs = getSharedPreferences(prefName, MODE_PRIVATE);
		final String tname = prefs.getString("name", null);
		Toast.makeText(getBaseContext(), tname, Toast.LENGTH_SHORT).show();
		// final String tname="msc1";

		b2 = (Button) findViewById(R.id.button2);
		sp1 = (Spinner) findViewById(R.id.spinner1);
		sp2 = (Spinner) findViewById(R.id.spinner2);
		sp3 = (Spinner) findViewById(R.id.spinner3);
		sp4 = (Spinner) findViewById(R.id.spinner4);
		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
				this, android.R.layout.simple_spinner_item, days);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp1.setAdapter(adapter);
		sp1.setOnItemSelectedListener(this);

		ArrayAdapter<CharSequence> adapter1 = new ArrayAdapter<CharSequence>(
				this, android.R.layout.simple_spinner_item, hour);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp2.setAdapter(adapter1);
		sp2.setOnItemSelectedListener(this);
		sp3.setOnItemSelectedListener(this);

		sp4.setOnItemSelectedListener(this);
		sub = (Button) findViewById(R.id.button1);
		loadSpinnerData();

		sq = Selectstaff.this.openOrCreateDatabase("college", MODE_PRIVATE,
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
		Toast.makeText(getApplicationContext(), "Value inserted",
				Toast.LENGTH_LONG).show();

		Toast.makeText(getApplicationContext(), "Table created ",
				Toast.LENGTH_SHORT).show();
		/* sq.execSQL("drop table msc1"); */

		sub.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ContactName = h;
				allot = subj + "(" + staf + ")";

				Log.d("Allot", allot + ContactName);
				if (h.equals("first")) {
					sq.execSQL("UPDATE " + tname + " SET first='" + allot
							+ "' where ord='" + d + "';");

				} else if (h.equals("second")) {
					sq.execSQL("update " + tname + " set second='" + allot
							+ "' where ord='" + d + "';");
				} else if (h.equals("third")) {
					sq.execSQL("update " + tname + " set third='" + allot
							+ "' where ord='" + d + "';");
				} else if (h.equals("fourth")) {
					Toast.makeText(getApplicationContext(),
							"fourth" + allot + d, Toast.LENGTH_LONG).show();
					sq.execSQL("update " + tname + " set fourth='" + allot
							+ "' where ord='" + d + "';");
				} else if (h.equals("fifth")) {
					sq.execSQL("update " + tname + " set fifth='" + allot
							+ "' where ord='" + d + "';");
				} else if (h.equals("sixth")) {
					sq.execSQL("update " + tname + " set sixth='" + allot
							+ "' where ord='" + d + "';");
				}
				Toast.makeText(getApplicationContext(), d + h + staf + subj,
						Toast.LENGTH_LONG).show();

				insert(staf, subj, h, d);
			}

			private void insert(String table, String subject, String hour,
					String dd) {
				// TODO Auto-generated method stub
				String s = "update " + table + " set " + hour + "='" + subject
						+ "' where ord='" + d + "';";
				Log.d("insert", s);
				sq.execSQL(s);
				// "update msc1 set fifth='"+allot+"' where ord='"+d+"';"
			}
		});

		b2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent i = new Intent(Selectstaff.this, MainActivity1.class);
				startActivity(i);

			}
		});

	}

	private void loadSpinnerData() {
		// TODO Auto-generated method stub
		// database handler
		DatabaseHandler db = new DatabaseHandler(getApplicationContext());

		// Spinner Drop down elements
		int x = 1;
		List<String> lables = db.getAllLabels(x);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, lables);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp4.setAdapter(dataAdapter);

		x = 2;
		List<String> lables1 = db.getAllLabels(x);
		ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, lables1);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp3.setAdapter(dataAdapter1);
	}

	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {

		switch (parent.getId()) {
		case R.id.spinner1:
			d = (String) days[position];

			// Toast.makeText(getApplicationContext(),"Spinner1: position=" +
			// position + ", Value= " +
			// days[position],Toast.LENGTH_LONG).show();
			break;
		case R.id.spinner2:
			h = (String) hour[position];
			if (position == 0) {
				Toast.makeText(getApplicationContext(), "hour" + h,
						Toast.LENGTH_LONG).show();
			} else if (position == 1) {
				Toast.makeText(getApplicationContext(), "hour" + h,
						Toast.LENGTH_LONG).show();
			}

			break;

		case R.id.spinner3:
			String label1 = parent.getItemAtPosition(position).toString();

			// Showing selected spinner item
			Toast.makeText(parent.getContext(), "You selected: " + label1,
					Toast.LENGTH_LONG).show();
			subj = "" + label1;
			if (subj.equals("free")) {
				Toast.makeText(getApplicationContext(), "Your R free",
						Toast.LENGTH_LONG).show();

				Calendar calendar = Calendar.getInstance();
				int day = calendar.DAY_OF_WEEK;
				switch (day) {
				case Calendar.MONDAY:

					break;
				case Calendar.TUESDAY:

					break;
				case Calendar.WEDNESDAY:

					break;
				case Calendar.THURSDAY:

					break;
				case Calendar.FRIDAY:

					break;

				default:
					break;
				}
			}

			break;
		case R.id.spinner4:
			String label = parent.getItemAtPosition(position).toString();

			// Showing selected spinner item
			Toast.makeText(parent.getContext(), "You selected: " + label,
					Toast.LENGTH_LONG).show();
			staf = "" + label;
			// staf=(String) staff[position];
			// Toast.makeText(getApplicationContext(),"Spinner4: position=" +
			// position + ", Value= " +
			// staff[position],Toast.LENGTH_LONG).show();
			break;
		default:
			Log.d("DEBUG", "a different spinner was selected");
			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
