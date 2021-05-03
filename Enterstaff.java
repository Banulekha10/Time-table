package com.example.time;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Enterstaff extends Activity implements OnItemSelectedListener {
	public static final CharSequence[] days = { "Monday", "Tuesday",
			"Wednesday", "Thursday", "Friday" };
	public static final CharSequence[] hour = { "first", "second", "third",
			"fourth", "fifth", "sixth" };
	// Spinner element
	Spinner spinner;
	SQLiteDatabase sq;
	// Add button
	Button btnAdd, ne;
	// Input text
	EditText inputLabel;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enterstaff);
		sq = Enterstaff.this
				.openOrCreateDatabase("college", MODE_PRIVATE, null);
		ne = (Button) findViewById(R.id.buu);

		// Spinner element
		spinner = (Spinner) findViewById(R.id.spinner);

		// add button
		btnAdd = (Button) findViewById(R.id.btn_add);

		// new label input field
		inputLabel = (EditText) findViewById(R.id.input_label);

		// Spinner click listener
		spinner.setOnItemSelectedListener(this);

		// Loading spinner data from database
		loadSpinnerData();

		btnAdd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String label = inputLabel.getText().toString();

				if (label.trim().length() > 0) {
					// database handler
					DatabaseHandler db = new DatabaseHandler(
							getApplicationContext());
					int a = 1;
					// inserting new label into database
					db.insertLabel(label, a);

					// making input filed text to blank
					inputLabel.setText("");
					table(label);
					// Hiding the keyboard
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(inputLabel.getWindowToken(), 0);

					// loading spinner with newly added data
					loadSpinnerData();
				} else {
					Toast.makeText(getApplicationContext(),
							"Please enter label name", Toast.LENGTH_SHORT)
							.show();
				}

			}

			private void table(String lab) {
				// TODO Auto-generated method stub
				Log.d("hi", "msg");
				Toast.makeText(getApplicationContext(), "table success" + lab,
						Toast.LENGTH_LONG).show();

				String s = "create table if not exists "
						+ lab
						+ "(ord varchar2(20),first varchar2(30),second varchar2(30),third varchar2(30),fourth varchar2(30),fifth varchar2(30),sixth varchar2(20))";
				String em = "free";
				String order = "ORDER";
				sq.execSQL(s);
				sq.execSQL("insert into " + lab + " values('" + order + "','"
						+ hour[0] + "','" + hour[1] + "','" + hour[2] + "','"
						+ hour[3] + "','" + hour[4] + "','" + hour[5] + "');");
				sq.execSQL("insert into " + lab + " values('" + days[0] + "','"
						+ em + "','" + em + "','" + em + "','" + em + "','"
						+ em + "','" + em + "');");
				sq.execSQL("insert into " + lab + " values('" + days[1] + "','"
						+ em + "','" + em + "','" + em + "','" + em + "','"
						+ em + "','" + em + "');");
				sq.execSQL("insert into " + lab + " values('" + days[2] + "','"
						+ em + "','" + em + "','" + em + "','" + em + "','"
						+ em + "','" + em + "');");
				sq.execSQL("insert into " + lab + " values('" + days[3] + "','"
						+ em + "','" + em + "','" + em + "','" + em + "','"
						+ em + "','" + em + "');");
				sq.execSQL("insert into " + lab + " values('" + days[4] + "','"
						+ em + "','" + em + "','" + em + "','" + em + "','"
						+ em + "','" + em + "');");

			}

		});

		ne.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Enterstaff.this, Create_drop.class);
				startActivity(i);
			}
		});
	}

	/**
	 * Function to load the spinner data from SQLite database
	 * */
	private void loadSpinnerData() {
		// database handler
		DatabaseHandler db = new DatabaseHandler(getApplicationContext());

		// Spinner Drop down elements
		int x = 1;
		List<String> lables = db.getAllLabels(x);

		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, lables);

		// Drop down layout style - list view with radio button
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		spinner.setAdapter(dataAdapter);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// On selecting a spinner item
		String label = parent.getItemAtPosition(position).toString();

		// Showing selected spinner item
		Toast.makeText(parent.getContext(), "You selected: " + label,
				Toast.LENGTH_LONG).show();

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}
}