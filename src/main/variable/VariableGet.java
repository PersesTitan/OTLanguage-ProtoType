package main.variable;

import Item.Check;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import main.Setting;

//변수에 값을 입력하는지 확인하기
public class VariableGet extends Setting implements Check {

    public Object getVariable (@NotNull String text) {
        text = text.trim();
        int position = getPosition(text);
        String key = text.substring(position-1, position+1);
        return map.get(key);
    }

    private int getPosition (@NotNull String text) {
        text = text.trim();
        return text.indexOf(text.indexOf(":"));
    }

    @Override
    public boolean check(String text) {
        char c = text.trim().charAt(0);
        return c == ':';
    }
}
