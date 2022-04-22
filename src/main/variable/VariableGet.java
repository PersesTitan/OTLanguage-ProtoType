package main.variable;

import Item.Check;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import main.Setting;

import java.io.IOException;

//변수에 값을 입력하는지 확인하기
public class VariableGet extends Setting implements Check {

    //:를 사용하였을때 변수나 상수를 값으로 치환
    public String setVariable (@NotNull String text) throws IOException {
        //total -> 운 (:ㅇㅅㅇ + :ㅂㅅㅂ)
        String total = text;
        //saves -> [운, (:ㅇㅅㅇ, +, :ㅂㅅㅂ)]
        String[] saves = total.split(" ");
        for (String save : saves) {
            if (!save.isBlank() && check(save)) {

                //(:ㅇㅅㅇ, :ㅂㅅㅂ)
                save = save.trim();
                save = save.replaceAll(";", "")
                        .replaceAll("\\)", "").replaceAll("\\(", "");
//                int position = getPosition(total);
                int position = total.indexOf(":");
                //position -> 각각 3, 10
                String key = total.substring(position+1, position+4);
                total = total.replace(save, getVariable(key).toString());
            }
        }
        return total;
    }

    public Object getVariable (@NotNull String text) {
        text = text.trim().replaceAll(":", "").replaceAll(";", "");
//        int position = getPosition(text);
//        int position = text.indexOf(":");
//        String key = text.substring(position+1, position+4);
//        String key  = text.substring(position+1);
        return Setting.map.get(text);
    }

    private int getPosition (@NotNull String text) {
        return text.indexOf(":");
    }

    @Override
    public boolean check(String text) {
        if (text.isBlank()) return false;
//        return text.charAt(1) == ':';
        return text.contains(":");
    }
}
