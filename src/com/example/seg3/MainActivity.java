package com.example.seg3;

import barcodeScan.IntentIntegrator;
import barcodeScan.IntentResult;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

@SuppressLint("ValidFragment")
public class MainActivity extends FragmentActivity {
	Button withAppointment;
	Button withoutAppointment;
	public static String scannedID;
	LinearLayout layoutForCentering;
	static SharedPreferences sharedPref ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		withAppointment = (Button) findViewById(R.id.withAppointment);
		withoutAppointment = (Button) findViewById(R.id.withoutAppointment);
		sharedPref = getPreferences(Context.MODE_PRIVATE);

	}
	

    public void gotoWithAppointmentView(View view) {
    	//SimpleDialogFragment dialogFragment = SimpleDialogFragment.newInstance("Appointment");
        //dialogFragment.show(getSupportFragmentManager(), "Appointment");
    	Intent i = new Intent(MainActivity.this, DialogBox.class);
		startActivity(i);
	}

	public void gotoWithoutAppointmentView(View view) {
		Intent i = new Intent(MainActivity.this, withoutAppointment.class);
		startActivity(i);
	}

	public static class SimpleDialogFragment extends DialogFragment {
		String title;

		/**
		 * This method creates a new dialogue fragment from the title and
		 * content with a simple dialog box
		 * 
		 * @param String
		 *            title int in value resources, containing title String
		 * @param String
		 *            content the int of the layout xml file
		 * @return a dialog fragment with title content and an ok button;
		 */
		public static SimpleDialogFragment newInstance(String title) {
			SimpleDialogFragment newDialogFragment = new SimpleDialogFragment();

			Bundle args = new Bundle();
			args.putString("title", title);
			newDialogFragment.setArguments(args);
			return newDialogFragment;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			title = getArguments().getString("title");
			return createDialog(title);
		}

		/**
		 * This method creates a new dialogue from the title and content (from
		 * resources) of the dialogue
		 * 
		 * @param String
		 *            title int in value resources, containing title String
		 * @param String
		 *            content the int of the layout xml file
		 */
		public Dialog createDialog(String title) {

			
			AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(
					getActivity());
			dialogBuilder.setTitle(title);
			LayoutInflater inflater = getActivity().getLayoutInflater();
			View view = inflater.inflate(R.layout.fragment_dialog, null);
			final EditText name = (EditText) view.findViewById(R.id.dialogName);
			final Button scan = (Button) view.findViewById(R.id.scanButton);
			
			dialogBuilder.setView(view);
			dialogBuilder.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// if this button is clicked, just close
							// the dialog box and do nothing
							dialog.dismiss();
						}
					});
			dialogBuilder.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// if this button is clicked, open survey of the
							// patient

							Intent i = new Intent(getActivity(),
									SurveyActivity.class);
							i.putExtra("Hospital Number", name.getText().toString());
							//dialog.dismiss();
							
							Server sr = new Server(name.getText().toString());
							sr.start();
							Log.v("",Server.hospitalId);
							
							//startActivity(i);
							
							
						}
					});
			return dialogBuilder.create();
		}
	    public void GoToScan() {
					IntentIntegrator barcodeScanner = new IntentIntegrator(getActivity());
					barcodeScanner.initiateScan(IntentIntegrator.TARGET_BARCODE_SCANNER_ONLY);
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
		}
	}
	public static SharedPreferences getSharedPreferences(){
		return sharedPref;
	}
	public static String getIP(){
	
		
		System.out.println(sharedPref.getString("ip", "not assigned yet"));
		return sharedPref.getString("ip", "not assigned yet");
		
	}
}
