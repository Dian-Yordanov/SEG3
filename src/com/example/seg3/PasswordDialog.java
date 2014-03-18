package com.example.seg3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PasswordDialog extends Activity {
	EditText passwordEditText;
	EditText ipEditText;
	TextView passwordTextView;
	TextView ipTextView;
	Button okButton;
	//add
	SharedPreferences sharedPref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.password_dialog);
		passwordEditText = (EditText) findViewById(R.id.passordEditText);
		ipEditText = (EditText) findViewById(R.id.ServerIPAddressEditView);
		passwordTextView = (TextView) findViewById(R.id.passwordTextView);
		ipTextView = (TextView) findViewById(R.id.ServerIPAddressTextView);
		okButton = (Button) findViewById(R.id.passwordButton);
		System.out.println("pula mea" +R.id.button1);
		//add
		sharedPref = MainActivity.getSharedPreferences();
		

	}

	public void onClick(View view) {

		switch (passwordEditText.getVisibility()) {

		case View.VISIBLE:

			if (passwordEditText.getText().toString().equals("1234")) {

				passwordEditText.setVisibility(View.GONE);
				passwordTextView.setVisibility(View.GONE);
				ipEditText.setVisibility(View.VISIBLE);
				ipTextView.setVisibility(View.VISIBLE);

			}
			break;

		case View.GONE:
		
			
			SharedPreferences.Editor editor = sharedPref.edit();
			System.out.println(sharedPref.getString("ip", "not yet"));
			editor.putString("ip", ipEditText.getText().toString());
			
			editor.commit();
			System.out.println(sharedPref.getString("ip", "not yet"));
			finish();
			break;

		}
	}

}
