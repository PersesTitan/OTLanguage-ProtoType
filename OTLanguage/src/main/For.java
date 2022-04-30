package main;

import Item.Check;
import Item.ForItem;

import java.io.IOException;
import java.util.*;

public class For implements Check {

    //없을때까지 루프
    public String loop(String total) throws IOException {
        String totalText = total;
        String start = null;
        String end = null;
        int startPosition;
        int endPosition;

        String[] lines = totalText.split("\\n");
        for (String line : lines) {
            if (!line.isBlank() && isCheck(line) != null) {
                start = line.strip();
                end = isCheck(line);
                break;
            }
        }

        assert start != null;
        assert end != null;
        startPosition = totalText.indexOf(start);
        endPosition = totalText.lastIndexOf(end) + start.length() - 1;
        totalText = totalText.substring(startPosition, endPosition);
        if (!check(start, end, totalText)) {
            total = total.replace(totalText, getNumber(start, totalText));
            totalText = totalText.replace(totalText, "");
        }

//        System.out.println("totalText = " + totalText);

        while (check(start, end, totalText)) {

            lines = totalText.split("\\n");
            for (String line : lines) {
                if (!line.isBlank() && isCheck(line) != null) {
                    start = line.strip();
                    end = isCheck(line);
                }
            }
            assert end != null;
            startPosition = totalText.indexOf(start);
            endPosition = totalText.lastIndexOf(end) + start.length() - 1;
            totalText = totalText.substring(startPosition, endPosition);
            if (!check(start, end, totalText)) {
                total = total.replace(totalText, getNumber(start, totalText));
                totalText = totalText.replace(totalText, "");
            }
        }
        return total;
    }

    //숫자 3개 가져옴
    //map 에 추가하기 이후에 아이디 반환
    private String getNumber(String start, String total) throws IOException {
        List<Integer> list = new ArrayList<>();
        String uuid = UUID.randomUUID().toString();
        String[] counts = start.split("\\^");
        if (counts.length != 3) throw new IOException("^^구문이 정확하지 않습니다.");

        for (String count : counts) {
            count = count.replaceAll("[^0-9]", "");
            if(count.isBlank()) throw new IOException("^^구문이 정확하지 않습니다.");
            else list.add(Integer.parseInt(count));
        }
        Setting.forMap.put(uuid, new ForItem(total, list.get(0), list.get(1), list.get(2)));
//        Setting.forList.add(new ForItem(uuid, total, list.get(0), list.get(1), list.get(2)));
        return uuid + "\n";
    }

    //코드에 ^^가 존재할때 true 반환
    private boolean check(String start, String end, String total) {
        total = total.replace(start, "").replace(end, "").strip();
        return total.contains("^^");
    }

    //line 이 ㅁ 0^2^1형태인지 확인
    //마지막이 ^^이 아니면 null 반환
    private String isCheck(String line) {
        if (line.strip().endsWith("^^")) return null;

        line = line.replaceAll("[0-9]", "").strip();
        if (line.endsWith("^^") && line.length()>3) return line; //아이디 + ^^ 출력
        else return null;
    }

    //리스트에 존재할때 ture
    public boolean checkText(String line) {
        return Setting.forMap.get(line.strip()) != null;
    }

    @Override
    public boolean check(String text) {
        return text.contains("^^");
    }

    //시작하기
    public void start(String key) {
        String value = Setting.forMap.get(key).getValue();
        int number1 = Setting.forMap.get(key).getNumber_1();
        int number2 = Setting.forMap.get(key).getNumber_2();
        int number3 = Setting.forMap.get(key).getNumber_3();

        String[] lines = value.split("\\n");
        List<String> list = Arrays.asList(lines);
        for (int i = number1; i<number2; i+=number3) {
            Setting.play(list);
        }
    }
}