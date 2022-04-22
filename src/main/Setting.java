package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Setting {
    /**
     * @param ㅇㅈㅇ (정수) int
     * @param ㅇㅉㅇ (정수) long
     * @param ㅇㅂㅇ (블린) boolean
     * @param ㅇㅁㅇ (문자) String
     * @param ㅇㄱㅇ (글자) char
     * @param ㅇㅅㅇ (실수) float
     * @param ㅇㅆㅇ (실수) double
     */

    String totalText;
    public static List<String> list = new ArrayList<>();
    public static volatile Map<String, Object> map = new HashMap<>();

    public Setting() {}

    public Setting(String totalText) throws IOException {
        this.totalText = totalText;
    }

    public void setReset() {
        map.clear();
    }

    public void setTrim() {
        list.clear();
        String[] texts = totalText.split("\\n");
        for (String text : texts) {
            if (!text.trim().equals("")) list.add(text.trim());
        }
    }

}
