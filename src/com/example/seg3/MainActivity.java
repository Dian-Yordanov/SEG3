package com.example.seg3;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

@SuppressLint("ValidFragment")
public class MainActivity extends FragmentActivity {
	Button withAppointment;
	Button withoutAppointment;

	LinearLayout layoutForCentering;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		withAppointment = (Button) findViewById(R.id.withAppointment);
		withoutAppointment = (Button) findViewById(R.id.withoutAppointment);
	

	}

    public void gotoWithAppointmentView(View view) {
    	SimpleDialogFragment dialogFragment = SimpleDialogFragment.newInstance("Appointment");
        dialogFragment.show(getSupportFragmentManager(), "Appointment");
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
			final EditText dob = (EditText) view.findViewById(R.id.dialogDOB);
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
							i.putExtra("name", name.getText().toString());
							i.putExtra("dob", dob.getText().toString());
							dialog.dismiss();
							startActivity(i);
						}
					});
			return dialogBuilder.create();
		}
	}
}
