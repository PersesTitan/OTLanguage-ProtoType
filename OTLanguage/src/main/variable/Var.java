package main.variable;

import Item.TextType;
import Item.VarItem;
import main.Setting;

import java.io.IOException;


public class Var {

    //변수 값 넣기
    public void setVar(String line, int count) throws IOException {
        String[] texts = line.split(" ");

        for (String text : texts) {
            if (!text.isBlank() && text.length() > 3 && text.contains(";")) {
                int end = text.indexOf(";");    // ; 위치 출력
                int start = end - 2;    // 예시 출력) ㅅ
                if (end>2 && start>=0 && check(text.charAt(start))) {

                    String key = text.substring(start-1, end);    //출력) ㅇㅅㅇ
                    String value = line.substring(end+2).strip();    //== line.replaceAll(key+";") , 값 출력
                    //넣을 값이 변수 일 경우 치환시키기
                    Setting.map.put(key, new VarItem(getTextType(key.strip().charAt(1)), getVar(value)));
                    printError(key, value, count+1);       //타입이 다를경우 경고함
//                    System.out.println("==============");
//                    System.out.println("key = " + key);
//                    System.out.println("value = " + value);
//                    System.out.println("==============");
                }
            }
        }
    }

    //key 에 값이 존재할 시 치환함
    public String getVar(String line) throws IOException {
        String[] texts = line.split(" ");
        for (String text : texts) {
            if (!text.isBlank() && text.length()>3 && text.contains(":")) {
                int start = text.indexOf(":");
                int end = start + 2;     //위치 반환 : ㅅ
                if (check(text.charAt(end)) && start<end) {
                    String key = text.substring(start+1, end+2);    //위치 반환 = ㅇㅅㅇ
                    String k = text.substring(start, end+2);    //위치 반환 = :ㅇㅅㅇ
                    String value = Setting.map.get(key).getValue().toString();
                    if (value.isBlank()) throw new IOException("변수가 존재하지 않습니다.");
                    //만약 값이 없을때 에러 발생
                    else line = line.replace(k, value);
                }
            }
        } return line;
    }

    //변수에 값 넣을때 타입 불일치 할때 에러 출력
    public void printError(String key, Object value, int line) {
        char c = key.strip().charAt(1);
        if (value instanceof Integer && c=='ㅈ') System.err.println(line + "번 째 줄 타입 경고");
        if (value instanceof Long && c=='ㅉ') System.err.println(line + "번 째 줄 타입 경고");
        if (value instanceof Boolean && c=='ㅂ') System.err.println(line + "번 째 줄 타입 경고");
        if (value instanceof String && c=='ㅁ') System.err.println(line + "번 째 줄 타입 경고");
        if (value instanceof Character && c=='ㄱ') System.err.println(line + "번 째 줄 타입 경고");
        if (value instanceof Float && c=='ㅅ') System.err.println(line + "번 째 줄 타입 경고");
        if (value instanceof Double && c=='ㅆ') System.err.println(line + "번 째 줄 타입 경고");
    }

    //: 가 존재하는지 확인하기
    public boolean getVarCheck(String line) {
        String[] texts = line.split(" ");
        for (String text : texts) {
            return !text.isBlank() && text.length() > 3 && text.contains(":");
        } return false;
    }

    private TextType getTextType(char c) throws IOException {
        if (c == 'ㅂ') return TextType.ㅂ;
        else if (c == 'ㅉ') return TextType.ㅉ;
        else if (c == 'ㅈ') return TextType.ㅈ;
        else if (c == 'ㅁ') return TextType.ㅁ;
        else if (c == 'ㄱ') return TextType.ㄱ;
        else if (c == 'ㅅ') return TextType.ㅅ;
        else if (c == 'ㅆ') return TextType.ㅆ;
        else throw new IOException("타입 오류 발생");
    }

    private boolean check(char c) {
        String text = Character.toString(c);
        if (text.isBlank()) return false;
        boolean bool = text.contains("ㅈ") || text.contains("ㅉ") || text.contains("ㅂ");
        bool = bool || text.contains("ㅁ") || text.contains("ㄱ") || text.contains("ㅅ");
        return bool || text.contains("ㅆ");
    }
}
