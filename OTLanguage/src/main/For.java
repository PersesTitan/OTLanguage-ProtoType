package main;

import Item.Check;
import Item.ForItem;
import Item.StartEndItem;

import java.io.IOException;
import java.util.*;

import static main.Setting.totalString;

public class For implements Check {

    List<StartEndItem> startEndItemList = new ArrayList<>();

    private void addStart(String TotalText) throws IOException {

        String[] TotalTexts = TotalText.split("\\n");
        //리스트 반전
        List<String> list1 = Arrays.asList(TotalTexts);
        Collections.reverse(list1);
        String[] TotalTextsReverse = list1.toArray(TotalTexts);

        for (String totalText : TotalTextsReverse) {
            if (!totalText.isBlank() && totalText.contains("ㅇㅍㅇ")) {
                totalText = totalText.trim();
                //for 문 아이디 추출
                String text1 =  totalText.substring(0, totalText.lastIndexOf("ㅇㅍㅇ")+3).trim();
                String text2 =  totalText.substring(0, text1.length()).trim();

                int firstTextPosition = TotalText.indexOf(text1);
                int secondTextPosition = TotalText.lastIndexOf(text2) + text1.length();
                if (firstTextPosition == secondTextPosition) throw new IOException("ㅇㅍㅇ의 짝이 일치하지 않습니다.");

                startEndItemList.add(new StartEndItem(firstTextPosition, secondTextPosition));
            }
        }
    }

    String totalText = "";
//    startEndItemList.clear();
    public void saveFor(String TotalText) throws IOException {
        addStart(TotalText);
        for (StartEndItem startEndItem : startEndItemList) {
            int start = startEndItem.getFirst();
            int end = startEndItem.getSecond();
            //for 문 아이디 생성
            String uuid = UUID.randomUUID().toString();
            //for 문 동작 후 삭제할 문장
            String texts = TotalText.substring(start, end);
            List<String> list = Setting.setTrim(texts);

            if (check(list.get(0)) && list.get(0).contains(",")) {
                String[] firstText = list.get(0).trim().split(",");
                if (firstText.length != 3) throw new IOException("ㅇㅍㅇ의 갯수가 틀림니다.");
                int number_1 = getNumber(firstText[0]);
                int number_2 = getNumber(firstText[1]);
                int number_3 = getNumber(firstText[2]);
                totalText = "";
                //ㅇㅍㅇ 삭제
                list.remove(0);
                list.remove(list.size()-1);
                list.forEach(o -> totalText += (o + "\n"));
                //추가 및 저장후 아이디로 변경
                Setting.forList.add(new ForItem(uuid, totalText, number_1, number_2, number_3));
                Setting.totalString = Setting.totalString.replace(texts, uuid);
//                if (check(totalText)) {
//                    System.out.println(totalText);
//                }
            }
        }
    }

    public void playFor(String uuid) throws IOException {
        List<ForItem> list = new ArrayList<>(Setting.forList);
        for (ForItem forItem : list) {
            if (uuid.contains(forItem.getId())) {
                try {
                    if (check(forItem.getValue())) saveFor(forItem.getValue());
                    int number_1 = forItem.getNumber_1();
                    int number_2 = forItem.getNumber_2();
                    int number_3 = forItem.getNumber_3();
                    for (int i = number_1; i <number_2; i+=number_3)
                        Setting.play(Setting.setTrim(forItem.getValue()), forItem.getValue());
                } catch (IOException ignored) {}
            }
        }
    }

    int count = 0;
    private int count(String totalText) throws IOException {
        count = 0;
        String[] split = totalText.split("\\n");
        List<String> list = new ArrayList<>(Arrays.asList(split));
        list.removeAll(Collections.singletonList(""));
        list.removeAll(Collections.singletonList(null));
        list.forEach(text -> {if (check(text)) ++count;});
        if (count%2==1) throw new IOException("ㅇㅍㅇ의 갯수가 맞지 않습니다.");
        return count/2;
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

    boolean bool = false;
    public boolean uuidCheck(String text) {
        if (Setting.forList.size()<=0) return false;
        bool = false;
        List<ForItem> list = new ArrayList<>(Setting.forList);
        list.forEach(item -> bool = bool || item.getId().contains(text));
        return bool;
    }

    @Override
    public boolean check(String text) {
        return text.contains("ㅇㅍㅇ");
    }
}