package activity;

import Item.Check;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import main.Setting;
import main.variable.VariableGet;
import main.variable.VariableSet;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class ActivityPrint extends Setting implements Check {

    Extraction extraction = new Extraction();
    VariableGet variableGet = new VariableGet();
    VariableSet variableSet = new VariableSet();
    String total = "";

    public void print(@NotNull String text) throws IOException {

        //ex - input : ((100 + 200)*30)
        text = text.replaceAll(";", "");
        String[] texts = text.split(" ");
        List<Object> list = new ArrayList<>();

        for (String str : texts) {
            if (!str.isBlank()) {
                if (str.contains("(") && str.contains(")")) {
                    if (variableGet.check(str)) list.add(variableGet.getVariable(str));
                } else list.add(str);
            }
        }

//        System.out.println("text = " + list);

        total = "";
        list.remove(0);
        list.forEach(object -> total += (object + " "));

//        if (variableSet.check(total)) variableSet.setVariable(total);
        //변수나 상수를 숫자로 치환시킴
        if (variableGet.check(total)) total = variableGet.setVariable(total);
//        System.out.println("object = " + total);
        //계산이 존재할 시 계산을 진행함
        if (extraction.check(total)) total = extraction.extractionNumber(total);

        System.out.println(total);
    }

    private Object getValue(String key) throws IOException {
        if (extraction.check(key)) {
            key = key.replaceAll(";", "");
            if (key.contains(")")) return map.get(key.replaceAll("\\)", "")).toString().trim()+")";
            else if (key.contains("(")) return "(" + map.get(key.replaceAll("\\(", "")).toString().trim();
        }

        if (map.get(key) == null) throw new IOException("함수가 존재하지 않습니다.");
        return map.get(key).toString().trim();
    }

    @Override
    public boolean check(String text) {
        String[] texts = text.trim().split(" ");
        boolean bool = texts[0].equals("ㅇㅜㄴ") || texts[0].equals("운");
        bool = bool || texts[0].toLowerCase(Locale.ROOT).equals("otl");
        return bool;
    }
}
