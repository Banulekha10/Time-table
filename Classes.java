package com.example.time;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;

public class Classes extends Activity 
{
	private RadioGroup radioClassGroup;
	private RadioButton radioClassButton;  
	private Button btnDisplay;
	private SharedPreferences prefs;
	private String prefName = "report";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_classes);

		addListenerOnButton();

	}

public void addListenerOnButton() {

		radioClassGroup = (RadioGroup) findViewById(R.id.radioClass);
		btnDisplay = (Button) findViewById(R.id.btnDisplay);

		btnDisplay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {		
				int selectedId = radioClassGroup.getCheckedRadioButtonId();
				radioClassButton = (RadioButton) findViewById(selectedId);
				Toast.makeText(getApplicationContext(),radioClassButton.getText(), Toast.LENGTH_SHORT).show();
				if(radioClassButton.getText().equals("M.Sc.IT_I year"))
				{
					prefs = getSharedPreferences(prefName, MODE_PRIVATE);
	        		SharedPreferences.Editor editor = prefs.edit();	        
	        		editor.putString("name","msc1");
	                editor.commit();
					Intent i=new Intent(Classes.this,Viewtables.class);
					startActivity(i);
				}
				else
				{
					prefs = getSharedPreferences(prefName, MODE_PRIVATE);
	        		SharedPreferences.Editor editor = prefs.edit();	        
	        		editor.putString("name","msc2");
	                editor.commit();
	                Intent i=new Intent(Classes.this,Viewtables.class);
					startActivity(i);			
				}

			}

		});

	}

}
