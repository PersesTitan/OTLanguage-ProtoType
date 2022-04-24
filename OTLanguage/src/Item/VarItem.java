package Item;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VarItem {
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
    TextType textType;
    int i;
    long l;
    boolean b;
    String str;
    char c;
    float f;
    double d;

    public VarItem(TextType textType, int i) {
        this.textType = textType;
        this.i = i;
    }

    public VarItem(TextType textType, long l) {
        this.textType = textType;
        this.l = l;
    }

    public VarItem(TextType textType, boolean b) {
        this.textType = textType;
        this.b = b;
    }

    public VarItem(TextType textType, String str) {
        this.textType = textType;
        this.str = str;
    }

    public VarItem(TextType textType, char c) {
        this.textType = textType;
        this.c = c;
    }

    public VarItem(TextType textType, float f) {
        this.textType = textType;
        this.f = f;
    }

    public VarItem(TextType textType, double d) {
        this.textType = textType;
        this.d = d;
    }
}