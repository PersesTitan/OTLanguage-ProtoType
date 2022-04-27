package activity;

import Item.Check;
import Item.TextType;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import main.Setting;
import main.variable.VariableGet;
import main.variable.VariableSet;
import main.variable.VariableType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

        total = "";
        list.remove(0);
        list.forEach(object -> total += (object + " "));

        //변수나 상수를 숫자로 치환시킴
        if (variableGet.check(total)) total = variableGet.setVariable(total);
        //계산이 존재할 시 계산을 진행함
        if (extraction.check(total)) total = extraction.extractionNumber(total);

        System.out.println(total);
    }

    @Override
    public boolean check(String text) {
        boolean bool = text.trim().startsWith("ㅇㅜㄴ") || text.trim().startsWith("운");
        bool = bool || text.trim().toLowerCase(Locale.ROOT).startsWith("otl");
        return bool;
    }
}
