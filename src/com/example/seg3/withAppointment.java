package com.example.seg3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class withAppointment extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.with_appointment);
    }
    public void gotoQuestionaryView(View view) {
  		Intent i = new Intent(withAppointment.this,SurveyActivity.class);
  		startActivity(i);
  	}
    
}