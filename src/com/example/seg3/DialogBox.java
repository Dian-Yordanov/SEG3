package com.example.seg3;

import barcodeScan.IntentIntegrator;
import barcodeScan.IntentResult;

import com.example.seg3.MainActivity.SimpleDialogFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DialogBox extends Activity{
	public static String scannedID;
	EditText name;
	Button scan;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_dialog);
		name = (EditText) findViewById(R.id.dialogName);
		scan = (Button) findViewById(R.id.scanButton);
		
	}
	
	
	 public void GoToScan(View view) {
			IntentIntegrator barcodeScanner = new IntentIntegrator(this);
			barcodeScanner.initiateScan(IntentIntegrator.TARGET_BARCODE_SCANNER_ONLY);
}
	 public void gotoSurvey(View view) {
			Intent i = new Intent(DialogBox.this, SurveyActivity.class);
			startActivity(i);
}

@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
	Log.v("heyhey","ff");
	switch (requestCode) {
	  case IntentIntegrator.REQUEST_CODE:
	     if (resultCode == Activity.RESULT_OK) {
	        IntentResult intentResult = 
	           IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
	        if (intentResult != null) {
	           String contents = intentResult.getContents();
	           String format = intentResult.getFormatName();
	           scannedID = contents;
	           Log.v("heyhey",contents);
	           
	           //this.resume = false;
	           Log.d("SEARCH_EAN", "OK, EAN: " + contents + ", FORMAT: " + format);
	        } else {
	           Log.e("SEARCH_EAN", "IntentResult je NULL!");
	        }
	     } else if (resultCode == Activity.RESULT_CANCELED) {
	        Log.e("SEARCH_EAN", "CANCEL");
	     }
	  }
	
	


}}
