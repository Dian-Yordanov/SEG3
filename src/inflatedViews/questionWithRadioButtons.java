package inflatedViews;

import java.util.ArrayList;

import com.example.seg3.R;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TextView.BufferType;

public class questionWithRadioButtons extends LinearLayout {
	public static View inflatedViewRadioButtons;
	public static LayoutInflater questionWithRadioButtonsAnswers;
	TextView question;
	public static LinearLayout mainLayout;

	public questionWithRadioButtons(Context context, String questionString,
			ArrayList<String> radioButtonQuestions) {
		super(context);
	}

	public View inflator(Context context, String questionText,
			ArrayList<String> radioButtonQuestions) {

		questionWithRadioButtonsAnswers = LayoutInflater.from(getContext());

		
		inflatedViewRadioButtons = questionWithRadioButtonsAnswers.inflate(
				R.layout.question_with_open_radio_buttons_layout, mainLayout,
				false);
		
		RadioGroup rg1 = (RadioGroup) inflatedViewRadioButtons
				.findViewById(R.id.radioGroup1);
		
		setQuestionRadioButtons(questionText);
		
		rg1.addView(setAnswerRadioButtons(context, radioButtonQuestions));

		

		return inflatedViewRadioButtons;

	}

	public void setQuestionRadioButtons(String questionText) {
		TextView textView1 = (TextView) inflatedViewRadioButtons
				.findViewById(R.id.textView1ForInflation);
		textView1.setText(questionText, BufferType.SPANNABLE);

		Spannable s = (Spannable) textView1.getText();
		ForegroundColorSpan fcs = new ForegroundColorSpan(Color.BLUE);

		AssetManager mngr = getContext().getAssets();
		Typeface face = Typeface.createFromAsset(mngr,
				"fonts/Roboto-Regular.ttf");
		textView1.setTypeface(face);

		s.setSpan(fcs, 0, questionText.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

	}

	public RadioGroup setAnswerRadioButtons(Context context,
			ArrayList<String> radioButtonQuestions) {
		
		RadioGroup rg = new RadioGroup(context);

		AssetManager mngr = getContext().getAssets();
		Typeface face = Typeface.createFromAsset(mngr,
				"fonts/Roboto-Regular.ttf");
		
		

		RadioButton[] rb = new RadioButton[radioButtonQuestions.size()];
		
		
		for (int i = 0; i < radioButtonQuestions.size(); i++) {
			rb[i] = new RadioButton(context);
			rb[i].setText("this is radioButton" + i, BufferType.SPANNABLE);
			
			Spannable s = (Spannable) rb[i].getText();
			ForegroundColorSpan fcs = new ForegroundColorSpan(Color.BLACK);
			rb[i].setTypeface(face);

			s.setSpan(fcs, 0, rb[i].getText().length(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			
			rg.addView(rb[i]);
		}
		return rg;

	}

}
