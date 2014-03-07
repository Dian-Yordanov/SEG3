package com.example.seg3;

import java.util.ArrayList;

import jsonReaderAndWriter.jsonReader;
import inflatedViews.*;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

public class SurveyActivity extends Activity {
	private static LinearLayout mainLayout;

	private static String questionText = "This is a question";
	private static String[] radioButtonQuestions = { "this is question 1",
			"this is question 2", "this is question 3", "this is a question 4" };
	private static String[] checkButtonQuestions = {
			"this is a check button 1", "this is a check button 2",
			"this is a check button 3" };
	private static String[] spinnerQuestions = {
		"this is a check button 1", "this is a check button 2",
		"this is a check button 3", "this is a check button 4",
		"this is a check button 5" };
	private static String[] questionWithSeekBarBoundaries = { "0", "1000" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questionary_layout);
		mainLayout = (LinearLayout) findViewById(R.id.LinearLayout1);

		setQuestions();

	}

	public void fillQuestions(String[] questionsData) {
		for (int i = 0; i < questionsData.length; i++) {

		}
	}
	public void setQuestions() {		
    	
		jsonReader jr = new jsonReader();
		
		setQuestionWithOpenTextView(jsonReader.questionText.get(0).toString());
		
		
	}

	public void setQuestionWithOpenTextView(String questionText) {
		questionWithOpenTextView questionTextView = new questionWithOpenTextView(
				getApplicationContext(), questionText);
		mainLayout.addView(questionTextView.inflator(questionText));
	}

	public void questionWithRadioButtons(String questionText, ArrayList<String> answerText) {
		questionWithRadioButtons questionRadioButtons = new questionWithRadioButtons(
				getApplicationContext(), questionText, answerText);
		mainLayout.addView(questionRadioButtons.inflator(
				getApplicationContext(), questionText, answerText));
		
	}

	public void questionWithCheckButtons(String questionText, ArrayList<String> answerText) {
		questionWithCheckButtons questionCheckButtons = new questionWithCheckButtons(
				getApplicationContext(), questionText, answerText);
		mainLayout.addView(questionCheckButtons.inflator(
				getApplicationContext(), questionText, answerText));
	}

	public void questionWithSpinner(String questionText, ArrayList<String> answerText) {
		questionWithSpinner questionWithSpinner = new questionWithSpinner(
				getApplicationContext(), questionText, answerText);
		mainLayout.addView(questionWithSpinner.inflator(
				getApplicationContext(), questionText, answerText));
	}

	public void questionWithSeekBar(String questionText, ArrayList<String> answerText) {
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