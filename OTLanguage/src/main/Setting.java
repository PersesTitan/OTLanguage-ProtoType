package main;

import Item.ForItem;
import Item.VarItem;
import activity.ActivityPrint;
import main.variable.VariableGet;
import main.variable.VariableSet;

import java.io.IOException;
import java.util.*;

public class Setting {
    /**
     * @param ㅇㅈㅇ (정수) int
     * @param ㅇㅉㅇ (정수) long
     * @param ㅇㅂㅇ (블린) boolean
     * @param ㅇㅁㅇ (문자) String
     * @param ㅇㄱㅇ (글자) char
     * @param ㅇㅅㅇ (실수) float
     * @param ㅇㅆㅇ (실수) double
     *
     * @param ㅇㅍㅇ (폴문) for
     */

    String totalText;
    public static String totalString;
    public static List<String> list = new ArrayList<>();
//    public static List<ForItem> forList = new ArrayList<>();
    public static Map<String, ForItem> forMap = new HashMap<>();
    public static volatile Map<String, VarItem> map = new HashMap<>();

    public Setting() {}

    public static void play() {
        play(Setting.list);
    }

//    //입력된 값이 실행됨
    public static void play(List<String> list) {
        ActivityPrint print = new ActivityPrint();
        VariableSet variableSet = new VariableSet();
        For fore = new For();

        list.forEach(object -> {
//            System.out.println("object = " + object.trim());
            if (variableSet.check(object)) {
                try {
                    variableSet.setVariable(object);
                } catch (IOException ignored) {}
            } else if (print.check(object)) {
                try {
                    print.print(object);
                } catch (IOException ignored) {}
            } else if (fore.checkText(object)) {
                fore.start(object.strip());
            }
        });
    }

    public static void setForCount() throws IOException {
        int count = 0;
        for (int i = 0; i<Setting.list.size(); i++) {
            if (Setting.list.get(i).strip().startsWith("ㅇㅍㅇ")) count ++;
        } if (count%2 == 1) throw new IOException("for 문의 짝이 맞지 않습니다.");
    }


    public static List<String> setTrim(String totalText) throws IOException {
        VariableGet variableGet = new VariableGet();
        VariableSet variableSet = new VariableSet();

        List<String> stringList = new ArrayList<>();

        String[] texts = totalText.split("\\n");
        for (String text : texts) {
            text = text.strip();
            if (!text.isBlank()) {
                variableSet.setVariable(text);
                if (variableGet.check(text)) stringList.add(variableGet.setVariable(text));
                else stringList.add(text);
            }
        } return stringList;
    }
}
