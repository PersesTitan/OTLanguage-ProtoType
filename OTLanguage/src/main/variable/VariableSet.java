package main.variable;

import Item.Check;
import Item.VarItem;
import activity.Extraction;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import main.Setting;

import java.io.IOException;
import java.util.Objects;

public class VariableSet extends Setting implements Check {

    VariableType variableType = new VariableType();

    //타입이 변수일때 변수에 값 저장하기
    public void setVariable(String text) throws IOException {
        String[] texts = text.split(" ");
        for (String t : texts) {
            if (!t.isBlank() && t.trim().endsWith(";") && check(t)) {
                String key = getKey(text);
                VarItem varItem = variableType.put(key, Objects.requireNonNull(getValue(text)));
                map.put(key, varItem);
            }
        }
    }

    private String getKey(@NotNull String text) {
        text = text.trim();
        return text.substring(0, 3);
    }

    //기본 값 확인하기
    Extraction extraction = new Extraction();
    private Object getValue(@NotNull String text) throws IOException {
        text = text.replaceAll(";", "");
        text = text.trim();
        text = text.substring(3);

        if (extraction.check(text)) text = extraction.extractionNumber(text);

        if (text.trim().equals("")) return null;
        return text.trim();
    }

    @Override
    public boolean check(String text) {
        if (text.length() < 2 || text.isBlank()) return false;

        if (text.contains(";")) {
            int position = text.indexOf(";");
            text = text.substring(position-2, position-1);
            text = text.trim();
        } else text = String.valueOf(text.trim().indexOf(1));

        boolean bool = text.equals("ㅈ") || text.equals("ㅉ") || text.equals("ㅂ");
        bool = bool || text.equals("ㅁ") || text.equals("ㄱ") || text.equals("ㅅ");
        return bool || text.equals("ㅆ");
    }
}
