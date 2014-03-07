package jsonReaderAndWriter;
/**
 * Created by Xelnect on 2/19/14.
 */

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class jsonWriter {

    public static JSONObject objMain = new JSONObject();
    public static int i=0;

    public static void main(String[] args) {

        String questionType1 = "radio button";
        String questionText1 = "do you have some problems with your hearth?";
        String[] answerText1 = {"yes", "no"};

        String questionType2 = "check boxes";
        String questionText2 = "which memebers of your family have heart problems?";
        String[] answerText2 = {"your mother", "your father", "your uncle", "your aunt"};


        writeJsonToFile(questionType1, questionText1, answerText1);
        writeJsonToFile(questionType2, questionText2, answerText2);
    }

    public static void questionDetailsJsonObj(String questionType, String questionText, String[] answerText) {
        i++;
        JSONArray list = new JSONArray();
        JSONObject obj = new JSONObject();
        writeQuestionType(obj, questionType);
        writeQuestionText(obj, questionText);
        writeAnswerText(obj, list, answerText);

        objMain.put("question number " + i, obj);

    }

    public static void writeJsonToFile(String questionType, String questionText, String[] answerText) {

        questionDetailsJsonObj(questionType, questionText, answerText);

        try {

            FileWriter file = new FileWriter("C:\\Users\\Xelnect\\Desktop\\JSonWritten.json");
            file.write(String.valueOf(objMain));
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public static void writeAnswerText(JSONObject obj, JSONArray list, String[] answerText) {
        for (int i = 0; i < answerText.length; i++) {
            list.add(answerText[i]);
        }
        obj.put("answerText", list);
    }
    public static void writeQuestionText(JSONObject obj, String questionText) {
        obj.put("questionText", questionText);
    }
    public static void writeQuestionType(JSONObject obj, String questionType) {
        obj.put("questionType", questionType);
    }

}