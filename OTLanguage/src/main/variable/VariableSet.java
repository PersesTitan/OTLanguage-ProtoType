package main.variable;

import Item.Check;
import Item.TextType;
import activity.Extraction;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import main.Setting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class VariableSet extends Setting implements Check {

    //타입이 변수일때 변수에 값 저장하기
    public void setVariable(String text) throws IOException {
        String[] texts = text.split(" ");

        for (String t : texts) {
            if (!t.isBlank() && t.trim().endsWith(";") && check(t)) {
                String key = getKey(text);
                map.put(key, getValue(text));
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

//        if (check(text)) setVariable(text);
//        if (variableGet.check(text)) text = variableGet.setVariable(text);
        if (extraction.check(text)) text = extraction.extractionNumber(text);

        if (text.trim().equals("")) return null;
        return text.trim();
    }

    //타입 반환
    private TextType getType(String text) throws IOException {
        char c = text.replaceAll(" ", "").charAt(1);
        if (c == 'ㅈ') return TextType.ㅈ;
        else if (c == 'ㅉ') return TextType.ㅉ;
        else if (c == 'ㅂ') return TextType.ㅂ;
        else if (c == 'ㅁ') return TextType.ㅁ;
        else if (c == 'ㄱ') return TextType.ㄱ;
        else if (c == 'ㅅ') return TextType.ㅅ;
        else if (c == 'ㅆ') return TextType.ㅆ;
        else throw new IOException("타입 오류 발생");
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
