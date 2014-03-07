package inflatedViews;

import com.example.seg3.R;
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
import android.widget.TextView;
import android.widget.TextView.BufferType;

public class questionWithOpenTextView extends LinearLayout {

	public static View inflatedViewTextView;
	public static LayoutInflater questionWithOpenTextAnswer;
	TextView question;
	EditText answer;
	LinearLayout mainLayout;

	public questionWithOpenTextView(Context context, String questionString) {
		super(context);

	}

	public View inflator(String questionText) {

		questionWithOpenTextAnswer = LayoutInflater.from(getContext());

		inflatedViewTextView = questionWithOpenTextAnswer.inflate(
				R.layout.question_with_open_text_answer_layout, mainLayout,
				false);

		setQuestionTextView(questionText);
		setAnswerTextView();

		return inflatedViewTextView;

	}

	public void setQuestionTextView(String questionText) {
		TextView textView0 = (TextView) inflatedViewTextView
				.findViewById(R.id.textViewForInflation);
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

	public void setAnswerTextView() {
		EditText editText0 = (EditText) inflatedViewTextView
				.findViewById(R.id.editTextForInflation);

		AssetManager mngr = getContext().getAssets();
		Typeface face = Typeface.createFromAsset(mngr,
				"fonts/Roboto-Regular.ttf");
		editText0.setTypeface(face);

	}

}
