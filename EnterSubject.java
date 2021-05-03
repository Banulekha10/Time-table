package com.example.time;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
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

public class EnterSubject extends Activity implements OnItemSelectedListener {
	Button nextbtn;
	Spinner sp1;
	// SQLiteDatabase sq;
	Button addbtn;
	EditText ed;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_subject);
		nextbtn = (Button) findViewById(R.id.next);
		sp1 = (Spinner) findViewById(R.id.spin);
		addbtn = (Button) findViewById(R.id.add);
		ed = (EditText) findViewById(R.id.input);
		sp1.setOnItemSelectedListener(this);
		loadSpinnerData();

		addbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				String label = ed.getText().toString();

				if (label.trim().length() > 0) {
					// database handler
					DatabaseHandler db = new DatabaseHandler(
							getApplicationContext());
					int a = 2;
					// inserting new label into database
					db.insertLabel(label, a);

					// making input filed text to blank
					ed.setText("");

					// Hiding the keyboard
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(ed.getWindowToken(), 0);

					// loading spinner with newly added data
					loadSpinnerData();
				} else {
					Toast.makeText(getApplicationContext(),
							"Please enter label name", Toast.LENGTH_SHORT)
							.show();
				}
			}
		});

		nextbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(EnterSubject.this, Enterstaff.class);
				startActivity(i);
			}
		});
	}

	private void loadSpinnerData() {
		// TODO Auto-generated method stub

		DatabaseHandler db = new DatabaseHandler(getApplicationContext());

		// Spinner Drop down elements
		int x = 2;
		List<String> lables = db.getAllLabels(x);

		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, lables);

		// Drop down layout style - list view with radio button
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		sp1.setAdapter(dataAdapter);

	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		String label = arg0.getItemAtPosition(arg2).toString();

		// Showing selected spinner item
		Toast.makeText(arg0.getContext(), "You selected: " + label,
				Toast.LENGTH_LONG).show();

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
