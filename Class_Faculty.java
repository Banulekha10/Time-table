package com.example.time;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Class_Faculty extends Activity {
Button c,f;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_class__faculty);
		f=(Button)findViewById(R.id.button1);
		c=(Button)findViewById(R.id.button2);
		f.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Intent i=new Intent(Class_Faculty.this,StaffList.class);
				startActivity(i);
			}
		});
		c.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i=new Intent(Class_Faculty.this,Classes.class);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_class__faculty, menu);
		return true;
	}

}
