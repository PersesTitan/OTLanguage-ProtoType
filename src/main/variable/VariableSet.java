package main.variable;

import Item.Check;
import Item.TextType;
import activity.Extraction;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import main.Setting;

import java.io.IOException;

public class VariableSet extends Setting implements Check {

    VariableGet variableGet = new VariableGet();

    //타입이 변수일때 변수에 값 저장하기
    public void setVariable(String text) throws IOException {

        String key = getKey(text);
        Object value = getValue(text);
        map.put(key, value);
    }

    private String getKey(@NotNull String text) {
        text = text.trim();
        return text.substring(0, 3);
    }

    //기본 값 확인하기
    private Object getValue(@NotNull String text) throws IOException {
        text = text.trim().substring(3);
        if (text.endsWith(";")) text = text.substring(0, text.length()-1);

        Extraction extraction = new Extraction();
        if (extraction.check(text)) text = extraction.extractionNumber(text);

        if (text.trim().equals("")) return null;
        return text;
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
        text = text.trim();
        boolean bool = (text.charAt(1) == 'ㅈ') || (text.charAt(1) == 'ㅉ') || (text.charAt(1) == 'ㅂ');
        bool = bool || (text.charAt(1) == 'ㅁ') || (text.charAt(1) == 'ㄱ') || (text.charAt(1) == 'ㅅ');
        return bool || (text.charAt(1) == 'ㅆ');
    }
}
