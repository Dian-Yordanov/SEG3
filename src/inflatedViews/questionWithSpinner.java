package inflatedViews;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import com.example.seg3.R;

public class questionWithSpinner  extends LinearLayout {

	public static View inflatedViewSpinner;
	public static LayoutInflater questionWithSpinnerAnswer;
	TextView question;
	EditText answer;
	LinearLayout mainLayout;

	public questionWithSpinner(Context context, String questionString, ArrayList<String> spinnerQuestions) {
		super(context);

	}

	public View inflator(Context context, String questionText, ArrayList<String> spinnerQuestions) {

		questionWithSpinnerAnswer = LayoutInflater.from(getContext());

		inflatedViewSpinner = questionWithSpinnerAnswer.inflate(
				R.layout.question_with_spinner_layout, mainLayout,
				false);

		setQuestionTextView(questionText);
		setAnswerTextView(context, spinnerQuestions);

		return inflatedViewSpinner;

	}

	public void setQuestionTextView(String questionText) {
		TextView textView0 = (TextView) inflatedViewSpinner
				.findViewById(R.id.textViewForInflationSpinner);
		textView0.setText(questionText, BufferType.SPANNABLE);

		Spannable s = (Spannable) textView0.getText();
		ForegroundColorSpan fcs = new ForegroundColorSpan(Color.BLUE);

		AssetManager mngr = getContext().getAssets();
		Typeface face = Typeface.createFromAsset(mngr,
				"fonts/Roboto-Regular.ttf");
		textView0.setTypeface(face);

		s.setSpan(fcs, 0, questionText.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

	}

	public void setAnswerTextView(Context context, ArrayList<String> spinnerQuestions) {
		Spinner spinner0 = (Spinner) inflatedViewSpinner
				.findViewById(R.id.spinnerForInflation);
		inflatedAdaptor inflatedadaptorForSpinner = new inflatedAdaptor(
				context, R.layout.simple_list_item_1,spinnerQuestions);
		spinner0.setAdapter(inflatedadaptorForSpinner);
	}

}