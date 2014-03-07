package inflatedViews;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import com.example.seg3.R;

public class questionWithSeekBar extends LinearLayout {

	public static View inflatedViewSeekBar;
	public static LayoutInflater questionWithSeekBarAnswer;
	TextView question;
	LinearLayout mainLayout;
	public static SeekBar sb;

	public static int progressInt = 0;

	public questionWithSeekBar(Context context, String questionString,
			int minPosition, int maxPosition) {
		super(context);

	}

	public View inflator(Context context, String questionText, int minPosition,
			int maxPosition) {

		questionWithSeekBarAnswer = LayoutInflater.from(getContext());

		inflatedViewSeekBar = questionWithSeekBarAnswer.inflate(
				R.layout.question_with_seek_bar, mainLayout, false);

		setQuestionTextView(questionText);
		setAnswerTextView(context, minPosition, maxPosition);
		return inflatedViewSeekBar;

	}

	public void setQuestionTextView(String questionText) {
		TextView textView0 = (TextView) inflatedViewSeekBar
				.findViewById(R.id.textView1ForInflationSeekBar);
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

	public void setAnswerTextView(Context context, int minPosition,
			int maxPosition) {
		SeekBar sb = (SeekBar) inflatedViewSeekBar
				.findViewById(R.id.seekBarForInflation);
		final TextView tv = (TextView) inflatedViewSeekBar
				.findViewById(R.id.textViewToShowProgress);
		tv.setTextColor(Color.BLACK);
		sb.setMax(maxPosition);
		sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub				
				progressInt = progress;
				tv.setText(Integer.toString(progress));
			}
		});
	}
}
