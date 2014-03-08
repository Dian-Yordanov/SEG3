package com.example.seg3;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import jsonReaderAndWriter.jsonReader;
import inflatedViews.*;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

public class SurveyActivity extends Activity {
	private static LinearLayout mainLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questionary_layout);
		mainLayout = (LinearLayout) findViewById(R.id.LinearLayout1);
		setQuestions();
	}
	
	public void setQuestions() {
		jsonReader jr = new jsonReader();
		for (int i = 0; i < jsonReader.questionType.size(); i++) {
			Log.v("",jsonReader.questionAnswer.get(i).toString());
			
			if (jsonReader.questionType.get(i).equals("text view")) {
				setQuestionWithOpenTextView(jsonReader.questionText.get(i));
			}
			if (jsonReader.questionType.get(i).equals("radio button")) {
				questionWithRadioButtons(jsonReader.questionText.get(i),
						jsonReader.questionAnswer.get(i));
			}
			if (jsonReader.questionType.get(i).equals("check boxes")) {
				questionWithCheckButtons(jsonReader.questionText.get(i),
						jsonReader.questionAnswer.get(i));
			}
			if (jsonReader.questionType.get(i).equals("spinner")) {
				questionWithSpinner(jsonReader.questionText.get(i),
						jsonReader.questionAnswer.get(i));
			}
			if (jsonReader.questionType.get(i).equals("seek bar")) {
				questionWithSeekBar(jsonReader.questionText.get(i),
						jsonReader.questionAnswer.get(i));
			}
		}
	}

	public void setQuestionWithOpenTextView(String questionText) {
		questionWithOpenTextView questionTextView = new questionWithOpenTextView(
				getApplicationContext(), questionText);
		mainLayout.addView(questionTextView.inflator(questionText));
	}

	public void questionWithRadioButtons(String questionText,
			ArrayList<String> answerText) {
		questionWithRadioButtons questionRadioButtons = new questionWithRadioButtons(
				getApplicationContext(), questionText, answerText);
		mainLayout.addView(questionRadioButtons.inflator(
				getApplicationContext(), questionText, answerText));

	}

	public void questionWithCheckButtons(String questionText,
			ArrayList<String> answerText) {
		questionWithCheckButtons questionCheckButtons = new questionWithCheckButtons(
				getApplicationContext(), questionText, answerText);
		mainLayout.addView(questionCheckButtons.inflator(
				getApplicationContext(), questionText, answerText));
	}

	public void questionWithSpinner(String questionText,
			ArrayList<String> answerText) {
		questionWithSpinner questionWithSpinner = new questionWithSpinner(
				getApplicationContext(), questionText, answerText);
		mainLayout.addView(questionWithSpinner.inflator(
				getApplicationContext(), questionText, answerText));
	}

	public void questionWithSeekBar(String questionText,
			ArrayList<String> answerText) {
		questionWithSeekBar questionWithSeekBar = new questionWithSeekBar(
				getApplicationContext(), questionText,
				Integer.parseInt(answerText.get(0)),
				Integer.parseInt(answerText.get(1)));
		mainLayout.addView(questionWithSeekBar.inflator(
				getApplicationContext(), questionText,
				Integer.parseInt(answerText.get(0)),
				Integer.parseInt(answerText.get(1))));
	}
}