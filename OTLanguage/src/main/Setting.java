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
    public static List<ForItem> forList = new ArrayList<>();

    public static volatile Map<String, VarItem> map = new HashMap<>();

    public Setting() {}

    public Setting(String totalText) throws IOException {
        this.totalText = totalText;
    }

    public void setReset() {
        map.clear();
    }

    public static boolean[] forCountCheck;
    public static String[] forCountText;
    public static void play(String totalText) throws IOException {
        play(Setting.list, totalText);
    }

    //입력된 값이 실행됨
    public static void play(List<String> list, String totalText) throws IOException {
        ActivityPrint print = new ActivityPrint();
        VariableSet variableSet = new VariableSet();
        For fore = new For();

        if (fore.check(totalText)) fore.saveFor(totalText);
        list.forEach(object -> {
            if (variableSet.check(object)) {
                try {
                    variableSet.setVariable(object);
                } catch (IOException ignored) {}
            } else if (print.check(object)) {
                try {
                    print.print(object);
                } catch (IOException ignored) {}
            } else if (fore.uuidCheck(object)) {
                try {
                    fore.playFor(object);
                } catch (IOException ignored) {}
            }
        });
    }

    public static void setForCount() throws IOException {
        int count = 0;
        for (int i = 0; i<Setting.list.size(); i++) {
            if (Setting.list.get(i).trim().startsWith("ㅇㅍㅇ")) count ++;
        }
        if (count%2 == 1) throw new IOException("for 문의 짝이 맞지 않습니다.");
        forCountCheck = new boolean[count/2];
        forCountText = new String[count/2];
        Arrays.fill(forCountCheck, false);
        Arrays.fill(forCountText, "");
    }


    public static List<String> setTrim(String totalText) throws IOException {
        VariableGet variableGet = new VariableGet();
        VariableSet variableSet = new VariableSet();

        List<String> stringList = new ArrayList<>();
        String[] texts = totalText.split("\\n");
        for (String text : texts) {
            text = text.trim();
            if (!text.isBlank()) {
                variableSet.setVariable(text);
                if (variableGet.check(text)) stringList.add(variableGet.setVariable(text));
                else stringList.add(text);
            }
        }
        return stringList;
    }
}
