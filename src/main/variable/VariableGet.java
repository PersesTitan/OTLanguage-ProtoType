package main.variable;

import Item.Check;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import main.Setting;

//변수에 값을 입력하는지 확인하기
public class VariableGet extends Setting implements Check {

    //:를 사용하였을때 변수나 상수를 값으로 치환
    public String setVariable (@NotNull String text) {
        //total -> 운 (:ㅇㅅㅇ + :ㅂㅅㅂ)
        String total = text;
        //saves -> [운, (:ㅇㅅㅇ, +, :ㅂㅅㅂ)]
        String[] saves = total.split(" ");
        for (String save : saves) {
            if (!save.isBlank() && check(save)) {
                //(:ㅇㅅㅇ, :ㅂㅅㅂ)
                int position = getPosition(total);
                //position -> 각각 3, 10
                String key = total.substring(position, position+4);
                total = total.replace(key, getVariable(key).toString());

            }
        }
        return total;
    }

    public Object getVariable (@NotNull String text) {
        text = text.trim();
        int position = getPosition(text);
        String key = text.substring(position+1, position+4);
        return map.get(key);
    }

    private int getPosition (@NotNull String text) {
        return text.indexOf(":");
    }

    @Override
    public boolean check(String text) {
        char c = text.trim().charAt(0);
        return c == ':';
    }
}
