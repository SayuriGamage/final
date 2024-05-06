package lk.ijse.util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Regex {
    public static boolean isTextFieldValid(TextField textField, String text){
        String filed = "";

        switch (textField){
            case ID:
                filed = "r'^COND\\d{3}$'";
                break;
            case NAME:
                filed = "^[A-z|\\\\s]{4,}$";
                break;
            case CONTACT:
                filed="^[A-z|\\\\s]{4,}$";
        }

        Pattern pattern = Pattern.compile(filed);

        if (text != null){
            if (text.trim().isEmpty()){
                return false;
            }
        }else {
            return false;
        }

        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()){
            return true;
        }
        return false;
    }

    public static boolean setTextColor(TextField location, javafx.scene.control.TextField textField){
        if (Regex.isTextFieldValid(location, textField.getText())){
            textField.setStyle("-fx-text-box-border: green;");


            return true;
        }else {
            textField.setStyle("-fx-text-box-border: red;");

            return false;
        }
    }
}