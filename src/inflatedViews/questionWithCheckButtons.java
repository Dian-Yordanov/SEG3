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
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.BufferType;

public class questionWithCheckButtons extends LinearLayout {
	public static View inflatedViewCheckBox;
	public static LayoutInflater questionWithCheckBoxAnswers;
	TextView question;
	public static LinearLayout mainLayout;

	public questionWithCheckButtons(Context context, String questionString,
			ArrayList<String> checkBoxQuestions) {
		super(context);
	}

	public View inflator(Context context, String questionText,
			ArrayList<String> checkBoxQuestions) {

		questionWithCheckBoxAnswers = LayoutInflater.from(getContext());

		
		inflatedViewCheckBox = questionWithCheckBoxAnswers.inflate(
				R.layout.question_with_open_check_buttons_layout, mainLayout,
				false);
		
		

		setQuestionCheckBox(questionText);
		
		setAnswerCheckButtons(context, checkBoxQuestions);

		

		return inflatedViewCheckBox;

	}

	public void setQuestionCheckBox(String questionText) {
		TextView textView1 = (TextView) inflatedViewCheckBox
				.findViewById(R.id.textView2ForInflation);
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

	public void setAnswerCheckButtons(Context context,
			ArrayList<String> checkButtonQuestions) {
	
		AssetManager mngr = getContext().getAssets();
		Typeface face = Typeface.createFromAsset(mngr,
				"fonts/Roboto-Regular.ttf");
		
		LinearLayout layoutForCheckBoxInflation = 
				(LinearLayout) inflatedViewCheckBox
				.findViewById(R.id.LinearLayoutForCheckBoxes);
		

		CheckBox[] cb = new CheckBox[checkButtonQuestions.size()];
		
		
		for (int i = 0; i < checkButtonQuestions.size(); i++) {
			cb[i] = new CheckBox(context);
			cb[i].setText("this is checkBox" + i, BufferType.SPANNABLE);
			
			Spannable s = (Spannable) cb[i].getText();
			ForegroundColorSpan fcs = new ForegroundColorSpan(Color.BLACK);
			cb[i].setTypeface(face);

			s.setSpan(fcs, 0, cb[i].getText().length(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			
			layoutForCheckBoxInflation.addView(cb[i]);
		}

	}

}
