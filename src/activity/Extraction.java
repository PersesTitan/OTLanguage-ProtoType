package activity;

import Item.Check;
import main.Setting;
import main.variable.VariableGet;
import main.variable.VariableSet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Extraction implements Check {

    public String getNumber(String text) throws IOException {


        //ex - input : 운 ((100 + 100) * 20) 출력하기
        String total = text;
        int firstPosition = total.indexOf("(");
        int endPosition = total.lastIndexOf(")") + 1;
        total = total.substring(firstPosition, endPosition);
        //ex - output : ((100 + 100) * 20)

        //괄호가 존재할때만 돌리기
        while (total.contains("(") && total.contains(")")) {
            int start = total.lastIndexOf("(");
            int end = total.indexOf(")") + 1;
            String someText = total.substring(start, end);
            //ex - someText Output : (100 + 100)


        }

        return null;
    }

    public String extractionNumber(String text) throws IOException {
        String total = text;
        while (total.contains("(") && total.contains(")")) {
            int start = total.lastIndexOf("(");
            int end = total.indexOf(")") + 1;
            String someText = total.substring(start, end);

            total = total.replace(someText, calculation(someText));
        }

        if (total.contains("(") && !total.contains(")")) throw new IOException("소괄호의 짝이 맞지 않습니다.");
        else if (!total.contains("(") && total.contains(")")) throw new IOException("소괄호의 짝이 맞지 않습니다.");

        return total;
    }

    VariableGet variableGet = new VariableGet();
    private String calculation (String text) throws IOException {
        text = text.replaceAll("\\)", "");
        text = text.replaceAll("\\(", "");

        if (variableGet.check(text)) text = variableGet.setVariable(text);

        String[] texts = text.replaceAll("[0-9|.]",",").split(",");
        String[] numbers = text.replaceAll("[^0-9|.]", ",").split(",");

        List<String> number = new ArrayList<>(Arrays.asList(numbers));
        List<String> textList = new ArrayList<>(Arrays.asList(texts));

        number.removeAll(Collections.singletonList(""));
        number.removeAll(Collections.singletonList(" "));
        number.removeAll(Collections.singletonList(null));
        textList.removeAll(Collections.singletonList(""));
        textList.removeAll(Collections.singletonList(" "));
        textList.removeAll(Collections.singletonList(null));

        return calculation(number, textList);
    }

    /**
     * 숫자가 음수가 되면서 생기는 문제 해결
     * @TODO (+) : +-, ++, +/, +*
     * @TODO (-) : -+, --, -/, -*
     */

    private String calculation (List<String> number, List<String> text) throws IOException {
        assert number.size()+1 == text.size();

        double d = Double.parseDouble(number.get(0));
        for (int i=0; i<text.size(); i++) {
            String sign = text.get(i).trim();
            double signValue = Double.parseDouble(number.get(i + 1));

            switch (sign) {
                case "*": d *= signValue; break;
                case "/": case "/+": d /= signValue; break;
                case "+": case "--": case "++": d += signValue; break;
                case "-": case "+-": case "-+": d -= signValue; break;
                case "/-": d = d / -signValue; break;
                case "*-": d = d * -signValue; break;
                default: throw new IOException("연산자 오류 발생" + sign);
            }
        }
        return Double.toString(d);
    }

    @Override
    public boolean check(String text) {
        return text.contains("(") && text.contains(")");
    }
}