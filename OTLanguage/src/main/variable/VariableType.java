package main.variable;


import Item.Check;
import Item.TextType;
import Item.VarItem;
import main.Setting;

import java.io.IOException;

public class VariableType extends Setting implements Check {

    public String get(String key) throws IOException {
        if (map.get(key).getTextType().equals(TextType.ㅈ)) return String.valueOf(map.get(key).getI());
        else if (map.get(key).getTextType().equals(TextType.ㅉ)) return String.valueOf(map.get(key).getL());
        else if (map.get(key).getTextType().equals(TextType.ㅂ)) return String.valueOf(map.get(key).isB());
        else if (map.get(key).getTextType().equals(TextType.ㅁ)) return String.valueOf(map.get(key).getStr());
        else if (map.get(key).getTextType().equals(TextType.ㄱ)) return String.valueOf(map.get(key).getC());
        else if (map.get(key).getTextType().equals(TextType.ㅅ)) return String.valueOf(map.get(key).getF());
        else if (map.get(key).getTextType().equals(TextType.ㅆ)) return String.valueOf(map.get(key).getD());
        else throw new IOException("타입 오류");
    }

    //비교 메소드
    VariableGet variableGet = new VariableGet();
    public VarItem put(String type, Object value) throws IOException {
        TextType textType = getType(type);
        String val = value.toString().trim();
        if (variableGet.check(val)) val = variableGet.setVariable(val);
        if (textType.equals(TextType.ㅈ)) return new VarItem(getType(type), Integer.parseInt(val));
        else if (textType.equals(TextType.ㅉ)) return new VarItem(getType(type), Long.parseLong(val));
        else if (textType.equals(TextType.ㅂ)) return new VarItem(getType(type), Boolean.parseBoolean(val));
        else if (textType.equals(TextType.ㅁ)) return new VarItem(getType(type), val);
        else if (textType.equals(TextType.ㄱ)) return new VarItem(getType(type), val.charAt(0));
        else if (textType.equals(TextType.ㅅ)) return new VarItem(getType(type), Float.parseFloat(val));
        else if (textType.equals(TextType.ㅆ)) return new VarItem(getType(type), Double.parseDouble(val));
        else throw new IOException("타입 오류 발생");
    }

    //타입 반환
    public TextType getType(String text) throws IOException {

        text = text.trim().replaceAll(":", "").replaceAll(";", "");
        char c = text.charAt(1);

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
        VariableSet variableSet = new VariableSet();
        return variableSet.check(text);
    }
}