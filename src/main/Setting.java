package main;

import Item.TextType;

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
     * @param ㅇㄱㅇ (글짜) char
     * @param ㅇㅅㅇ (실수) float
     * @param ㅇㅆㅇ (실수) double
     */

    String totalText;
    public static List<String> list = new ArrayList<>();
    public static Map<String, Object> map = new HashMap<>();

    public Setting() {

    }

    public Setting(String totalText) {
        this.totalText = totalText;
    }


//    public static Map<String, Integer> IntMap = new HashMap<>();
//    public static Map<String, Long> LongMap = new HashMap<>();
//    public static Map<String, Boolean> BooleanMap = new HashMap<>();
//    public static Map<String, String> StringMap = new HashMap<>();
//    public static Map<String, Character> CharMap = new HashMap<>();
//    public static Map<String, Float> FloatMap = new HashMap<>();
//    public static Map<String, Double> DoubleMap = new HashMap<>();

    public void setReset() {
        map.clear();
//        IntMap.clear();
//        LongMap.clear();
//        BooleanMap.clear();
//        StringMap.clear();
//        CharMap.clear();
//        FloatMap.clear();
//        DoubleMap.clear();
    }

    public void setTrim() {
        list.clear();
        String[] texts = totalText.split("\\n");
        for (String text : texts) {
            if (!text.trim().equals("")) list.add(text.trim());
        }
    }

}
