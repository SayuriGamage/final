package lk.ijse.util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Regex {
    public static boolean isTextFieldValid(TextField textField, String text){
        String field = "";

        switch (textField) {
            case  ID:
                field="";
            case NAME:
                field = "^[A-Za-z\\s]{4,}$";
                break;
            case CONTACT:
                field = "^(\\+94|0)?[1-9][0-9]{8}$";
                break;
            case COST:
                field = "^([0-9]){1,}[.]([0-9]){1,}$";
                break;
            case DATE:
                field = "^\\d{4}-\\d{2}-\\d{2}$";
                break;
            case TEXT:
                field="^[a-zA-Z0-9]{0,30}$";
            case QTY:
                field="^\\d+$";
        }


        Pattern pattern = Pattern.compile(field);

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