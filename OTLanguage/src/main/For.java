package main;

import Item.Check;
import Item.ForItem;

import java.io.IOException;
import java.util.*;

public class For implements Check {

//    private void go(String total) {
//        String[] lines = total.split("\\n");
//        for (String line : lines) {
//            if (!line.isBlank() && isCheack!=null){
//                String startText = line.trim();
//                String endText = isCheack(like);
//                int startPosition = total.indexOf(startText);
//                int endPosition = total.lastIndexOf(endText)+endText.length;
//
//                String total = total.subString(startPosition, endPosition);
//
//                //처음과 끝제거함
//                //제거하고도 없을시 while 로 없을때까지 루프
//                if (total.replace(startText).replace(endText).contains("^^")) {
//
//                }
//
//            }
//        }
//    }


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
                start = line.trim();
                end = isCheck(line);
                break;
//                System.out.println("start = " + start);
//                System.out.println("end = " + end);
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
                    start = line.trim();
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

//            System.out.println("totalText = " + totalText);
//            System.out.println("start = " + start);
//            System.out.println("end = " + end);
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
        total = total.replace(start, "").replace(end, "").trim();
        return total.contains("^^");
    }

    //line 이 ㅁ 0^2^1형태인지 확인
    //마지막이 ^^이 아니면 null 반환
    private String isCheck(String line) {
        if (line.trim().endsWith("^^")) return null;

        line = line.replaceAll("[0-9]", "").trim();
        if (line.endsWith("^^") && line.length()>3) return line; //아이디 + ^^ 출력
        else return null;
    }

    //리스트에 존재할때 ture
    public boolean checkText(String line) {
        return Setting.forMap.get(line.trim()) != null;
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

//    List<StartEndItem> startEndItemList = new ArrayList<>();
//
//    private void addStart(String TotalText) throws IOException {
//
//        String[] TotalTexts = TotalText.split("\\n");
//        //리스트 반전
////        List<String> list1 = Arrays.asList(TotalTexts);
////        Collections.reverse(list1);
////        String[] TotalTextsReverse = list1.toArray(TotalTexts);
//
//        for (String totalText : TotalTexts) {
//            if (!totalText.isBlank() && totalText.contains("^^")) {
//                totalText = totalText.trim();
//                //for 문 아이디 추출
////                String text1 =  totalText.substring(0, totalText.lastIndexOf("ㅇㅍㅇ")+3).trim();
////                String text2 =  totalText.substring(0, text1.length()).trim();
//
//                String text1 =  totalText.substring(0, totalText.indexOf("^^")+2).trim();
//                String text2 =  totalText.substring(0, text1.length()).trim();
//
//                int firstTextPosition = TotalText.indexOf(text1);
//                int secondTextPosition = TotalText.lastIndexOf(text2) + text1.length();
//                if (firstTextPosition == secondTextPosition) throw new IOException("ㅇㅍㅇ의 짝이 일치하지 않습니다.");
//
//                startEndItemList.add(new StartEndItem(firstTextPosition, secondTextPosition));
//            }
//        }
//    }
//
//    String totalText = "";
////    startEndItemList.clear();
//    public void saveFor(String TotalText) throws IOException {
//        go(TotalText);
//
//        addStart(TotalText);
//        for (StartEndItem startEndItem : startEndItemList) {
//            int start = startEndItem.getFirst();
//            int end = startEndItem.getSecond();
//            //for 문 아이디 생성
//            String uuid = UUID.randomUUID().toString();
//            //for 문 동작 후 삭제할 문장
//            String texts = TotalText.substring(start, end);
//            List<String> list = Setting.setTrim(texts);
//
//            if (check(list.get(0)) && list.get(0).contains("^")) {
//                String[] firstText = list.get(0).trim().split(",");
//                if (firstText.length != 3) throw new IOException("ㅇㅍㅇ의 갯수가 틀림니다.");
//                int number_1 = getNumber(firstText[0]);
//                int number_2 = getNumber(firstText[1]);
//                int number_3 = getNumber(firstText[2]);
//                totalText = "";
//                //ㅇㅍㅇ 삭제
//                list.remove(0);
//                list.remove(list.size()-1);
//                list.forEach(o -> totalText += (o + "\n"));
//                //추가 및 저장후 아이디로 변경
//                Setting.forList.add(new ForItem(uuid, totalText, number_1, number_2, number_3));
//                Setting.totalString = Setting.totalString.replace(texts, uuid);
//
//            }
//        }
//    }
//
//    public void playFor(String uuid) throws IOException {
//        List<ForItem> list = new ArrayList<>(Setting.forList);
//        for (ForItem forItem : list) {
//            if (uuid.contains(forItem.getId())) {
//                try {
//                    if (check(forItem.getValue())) saveFor(forItem.getValue());
//                    int number_1 = forItem.getNumber_1();
//                    int number_2 = forItem.getNumber_2();
//                    int number_3 = forItem.getNumber_3();
//                    for (int i = number_1; i <number_2; i+=number_3)
//                        Setting.play(Setting.setTrim(forItem.getValue()), forItem.getValue());
//                } catch (IOException ignored) {}
//            }
//        }
//    }
//
//    int count = 0;
//    private int count(String totalText) throws IOException {
//        count = 0;
//        String[] split = totalText.split("\\n");
//        List<String> list = new ArrayList<>(Arrays.asList(split));
//        list.removeAll(Collections.singletonList(""));
//        list.removeAll(Collections.singletonList(null));
//        list.forEach(text -> {if (check(text)) ++count;});
//        if (count%2==1) throw new IOException("ㅇㅍㅇ의 갯수가 맞지 않습니다.");
//        return count/2;
//    }
//
//    private int getNumber(String texts) {
//        return Integer.parseInt(texts.replaceAll("[^0-9]", ""));
//    }
//
//    //
//    public void setTotal() {
//        String total = totalString;
//        String[] lines = total.split("\\n");
//        for (String line : lines) {
//            // 공백 제거, ^^가 없으면 제외
//            if (!line.isBlank() && changeText(line).contains("^^")) {
//                // 0^5^1 형태 가져오기
//                if (!line.trim().contains("^^")) {
//                    String[] texts = line.trim().split(" ");
//                    int startPosition = totalString.indexOf(texts[0]+" "+texts[1]); //a 0^5^1
//                    int endPosition = totalString.lastIndexOf(texts[0]); //a ^^
//                    //a 0^5^1 ~ a ^^
//                    String inText = totalString.substring(startPosition, endPosition + texts[0].length());
//                }
//            }
//        }
//    }
//
//    //ㅁ 0^5^1 -> ㅁ ^^
//    private String changeText(String total) {
//        return total.replaceAll("[0-9]", "").trim();
//    }
//
//    //시작하기
//    public void go(String totalText) throws IOException {
//        //처음 저장되는 장소
//        //^^가 없을때까지 돌리기
//        while (checked(totalText)) totalText = getInText(totalText);
//
//        //아이디 생성
//        String uuid = UUID.randomUUID().toString();
//        //줄바꿈 나누기
//        List<String> list = Setting.setTrim(totalText);
//
//        System.out.println("list = " + list);
//        String[] firstText = list.get(0).trim().split("\\^");
//        if (firstText.length != 3) throw new IOException("ㅇㅍㅇ의 갯수가 틀림니다.");
//        int number_1 = getNumber(firstText[0]);
//        int number_2 = getNumber(firstText[1]);
//        int number_3 = getNumber(firstText[2]);
//
//        this.totalText = "";
//        //ㅇㅍㅇ 삭제
//        list.remove(0);
//        list.remove(list.size()-1);
//        list.forEach(o -> this.totalText += (o + "\n"));
//        //추가 및 저장후 아이디로 변경
//        Setting.forList.add(new ForItem(uuid, totalText, number_1, number_2, number_3));
//        Setting.totalString = Setting.totalString.replace(totalText, uuid);
//    }
//
//
//    //^^안에 있는 값 리턴
//    private String getInText(String TotalText) throws IOException {
//        String[] TotalTexts = TotalText.split("\\n");
//        for (String totalText : TotalTexts) {
//            if (!totalText.isBlank() && totalText.contains("^^")) {
//                totalText = totalText.trim().replaceAll("[0-9]", "");
//                String text1 =  totalText.substring(0, totalText.indexOf("^^")+2).trim();
//                String text2 =  totalText.substring(0, text1.length()).trim();
//
//                int firstTextPosition = TotalText.indexOf(text1);
//                int secondTextPosition = TotalText.lastIndexOf(text2) + text1.length();
//                if (firstTextPosition == secondTextPosition) throw new IOException("ㅇㅍㅇ의 짝이 일치하지 않습니다.");
//                return TotalText.substring(firstTextPosition, secondTextPosition);
//            }
//        } throw new IOException("오류가 발생하였습니다.");
//    }
//
//    //^^가 존재시 true, 아니면 false
//    String total = "";
//    private boolean checked(String texts) {
//        String[] text = texts.split("\\n");
//        List<String> list = new ArrayList<>(Arrays.asList(text));
//        if (list.size()<=1) return false;
//        list.remove(0);
//        list.remove(list.size()-1);
//        total = "";
//        list.forEach(string -> total += string);
//        return total.contains("^^");
//    }
//
//    boolean bool = false;
//    public boolean uuidCheck(String text) {
//        if (Setting.forList.size()<=0) return false;
//        bool = false;
//        List<ForItem> list = new ArrayList<>(Setting.forList);
//        list.forEach(item -> bool = bool || item.getId().contains(text));
//        return bool;
//    }
//
//    @Override
//    public boolean check(String text) {
//        return text.contains("^^");
//    }
}