package activity;

import Item.Check;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import main.Setting;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class ActivityPrint extends Setting implements Check {

    String total = "";

    public void print(@NotNull String text) {

        String[] texts = text.split(" ");
        List<Object> list = new ArrayList<>();

        for (String str : texts) {
            if (!str.equals("")) {
                if (str.startsWith(":")) {
                    String s = str.trim().substring(1);
                    list.add(getValue(s));
                } else list.add(str);
            }
        }

        total = "";
        list.remove(0);
        list.forEach(object -> total += (object + " "));

        System.out.println(total);

    }

    private Object getValue(String key) {
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
