package jsonReaderAndWriter;

/**
 * Created by Xelnect on 2/19/14.
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.example.seg3.SurveyActivity;

import android.util.Log;

public class jsonReader {

	public static ArrayList<String> questionType = new ArrayList<String>();
	public static ArrayList<String> questionText = new ArrayList<String>();
	public static ArrayList<ArrayList<String>> questionAnswer = new ArrayList<ArrayList<String>>();

	public static String jsonString = "{\"question number 6\":{\"answerText\":[\"Fine\",\"Good\",\"Not Bad\",\"Bad\"],\"questionText\":\"Is Frona leaving?\",\"questionType\":\"check boxes\"},\"question number 5\":{\"answerText\":[\"Fine\",\"Good\",\"Not Bad\",\"Bad\"],\"questionText\":\"How is your girlfriend doing?\",\"questionType\":\"check boxes\"},\"question number 4\":{\"answerText\":[\"RB,Fuck off\",\"Good\",\"Not Bad\",\"Bad\"],\"questionText\":\"How is your mom doing?\",\"questionType\":\"radio button\"},\"question number 3\":{\"answerText\":[\"I'm screwed\",\"Good\",\"Not Bad%Bad\"],\"questionText\":\"How are you doing?,\",\"questionType\":\"radio button\"},\"question number 2\":{\"answerText\":[\"Fine\",\"Good\",\"Not Bad\",\"Bad\"],\"questionText\":\"Is everything ok?\",\"questionType\":\"check boxes\"},\"question number 1\":{\"answerText\":[\"Fine\",\"Good\",\"Not Bad\",\"Bad\"],\"questionText\":\"How are you doing?\",\"questionType\":\"radio button\"}}";

	public jsonReader() {
		jsonObjectExtractor(jsonFileReader());
	}

	public static JSONObject jsonFileReader() {
		JSONParser parser = new JSONParser();
		Object objToBeParsedFromFile = null;
		JSONObject jsonObjectFromObj = null;

		try {
			objToBeParsedFromFile = parser.parse(jsonString);
			jsonObjectFromObj = (JSONObject) objToBeParsedFromFile;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return jsonObjectFromObj;
	}

	public static void jsonObjectExtractor(JSONObject objToBeParsedFromFile) {

		JSONObject questionNumber = null;
		for (int i = 0; i < objToBeParsedFromFile.size(); i++) {
			String questionNumberIndex = "question number ".concat(Integer
					.toString(i + 1));

			questionNumber = (JSONObject) objToBeParsedFromFile
					.get(questionNumberIndex);

			questionAnswer.add(readAnswerText(questionNumber));
			questionText.add(readQuestionText(questionNumber));
			questionType.add(readQuestionType(questionNumber));

		}

	}

	public static ArrayList<String> readAnswerText(JSONObject obj) {
		JSONArray answerTextJsonArray = (JSONArray) obj.get("answerText");
		ArrayList<String> answerTextArray = new ArrayList();
		Iterator<String> iterator = answerTextJsonArray.iterator();
		while (iterator.hasNext()) {
			answerTextArray.add(iterator.next());
		}
		return answerTextArray;
	}

	public static String readQuestionText(JSONObject obj) {
		String questionText = (String) obj.get("questionText");
		return questionText;
	}

	public static String readQuestionType(JSONObject obj) {
		String questionType = (String) obj.get("questionType");
		return questionType;
	}
}