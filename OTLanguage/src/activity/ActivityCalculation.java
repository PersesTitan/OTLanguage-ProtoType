package activity;

import Item.Check;

import javax.print.DocFlavor;
import java.io.IOException;

//계산하는 클래스
public class ActivityCalculation implements Check {

    //변수가 존재하면 숫자로 변환 후 리턴
    public void varCal(String line) {

    }
    /**
     * @param ㅇㅈㅇ (정수) int
     * @param ㅇㅉㅇ (정수) long
     * @param ㅇㅂㅇ (블린) boolean
     * @param ㅇㅁㅇ (문자) String
     * @param ㅇㄱㅇ (글자) char
     * @param ㅇㅅㅇ (실수) float
     * @param ㅇㅆㅇ (실수) double
     */
    private String numberType(String line) {

    }

    //계산하는 메소드
    private int calculation(String line) {
        //(100 + (1234 * 22))
        line = line.trim();
        int startPosition = line.indexOf(")") + 1;
        int endPosition = line.lastIndexOf("(");
        //(1234 * 22) 출력
        String numbers = line.substring(startPosition, endPosition);


    }

    //숫자를 제외한 나머지 문자가 존재하는지 확인함
    private boolean checked(String numbers) throws IOException {
        //(100 + (1234 * 22))
        numbers = numbers.replaceAll("\\*", "");
        numbers = numbers.replaceAll("\\+", "");
        numbers = numbers.replaceAll("\\.", "");
        numbers = numbers.replaceAll("\\(", "");
        numbers = numbers.replaceAll("\\)", "");
        numbers = numbers.replaceAll("-", "");
        numbers = numbers.replaceAll("/", "");
        numbers = numbers.replaceAll(" ", "");
        //100123422
        for (int i = 0; i<numbers.length(); i++) {
            if (Character.isDigit(numbers.charAt(i))) throw new IOException("계산식이 일치하지 않습니다.");
        } return true;
    }

    @Override
    public boolean check(String text) {
        if (text.isBlank()) return false;
        return text.contains(")") && text.contains("(");
    }
}
