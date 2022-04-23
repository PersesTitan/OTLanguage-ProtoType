package main;

import Item.Check;

import java.io.IOException;
import java.util.List;

public class For implements Check {

    String totalText = "";
    public void startFor(String TotalText) throws IOException {
        int start = TotalText.indexOf("ㅇㅍㅇ");
        int end = TotalText.lastIndexOf("ㅇㅍㅇ");

        String texts = TotalText.substring(start, end);
        List<String> list = Setting.setTrim(texts);


        if (check(list.get(0)) && list.get(0).contains(",")) {
            String[] firstText = list.get(0).trim().split(",");
            if (firstText.length!=3) throw new IOException("ㅇㅍㅇ의 갯수가 틀림니다.");
            int number_1 = getNumber(firstText[0]);
            int number_2 = getNumber(firstText[1]);
            int number_3 = getNumber(firstText[2]);
            totalText = "";
            list.remove(0);
            list.forEach(o -> totalText += o);

            for (int i = number_1; i <number_2; i+=number_3) {
                Setting.play(list, totalText);
            }
        } else throw new IOException("예기치 못한 에러가 발생하였습니다.");
    }

    private int getNumber(String texts) {
        return Integer.parseInt(texts.replaceAll("[^0-9]", ""));
    }

   private int checkPosition() {
        for (int i = 0; i<Setting.forCountCheck.length; i++) {
            if (!Setting.forCountCheck[i]) return i;
        } return 0;
    }

    private void finish() {
        Setting.forCountCheck[checkPosition()] = false;
    }

    @Override
    public boolean check(String text) {
        return text.contains("ㅇㅍㅇ");
    }
}