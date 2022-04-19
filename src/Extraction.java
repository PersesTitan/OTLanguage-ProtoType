import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Extraction {

    public String extractionNumber(String text) {
        String total = text;
        while (total.contains("(") || total.contains(")")) {
            if (total.lastIndexOf("(") != -1 && !total.contains(")")) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.err.println("문법 오류");
                    System.err.print("괄호 갯수가 맞지 않습니다.");
                    break;
                }
            } else if (total.lastIndexOf("(") == -1 && total.contains(")")) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.err.println("문법 오류");
                    System.err.print("괄호 갯수가 맞지 않습니다.");
                    break;
                }
            }

            System.out.println("text = " + total);
            int start = total.lastIndexOf("(");
            int end = total.indexOf(")") + 1;
            String someText = total.substring(start, end);

            if (someText.trim().split(" ").length <= 1) {
                total = total.replace(someText, calculation(someText));
                System.out.println("a : " +calculation(someText));
                System.out.println("b : " +total);
                return total;
            } else {
                total = total.replace(someText, calculation(someText));
                System.out.println("text = " + total);

                System.out.println("a" +calculation(someText));
                System.out.println("b" +total);
            }

         System.out.println(total);
        }

        return total;
    }

    public String calculation (String text) {
        text = text.replaceAll("\\)", "");
        text = text.replaceAll("\\(", "");

        String[] texts = text.replaceAll("[0-9]", " ").split(" ");
        String[] numbers = text.split("\\+|-|\\*|\\/");

        List<String> number = Arrays.asList(numbers);
        List<String> textList = Arrays
                .stream(texts).filter(t -> !t.trim().equals(""))
                .collect(Collectors.toList());

        System.out.println("=====================");
        textList.forEach(System.out::println);
        number.forEach(System.out::println);
        System.out.println("=====================");

        return calculation(number, textList);
    }

    /**
     * 숫자가 음수가 되면서 생기는 문제 해결
     * @TODO (+) : +-, ++, +/, +*
     * @TODO (-) : -+, --, -/, -*
     */

    public String calculation (List<String> number, List<String> text) {
        assert number.size()+1 == text.size();
        double d = Double.parseDouble(number.get(0));
        for (int i=0; i<text.size(); i++) {
            String sign = text.get(i).trim();
            double signValue = Double.parseDouble(number.get(i + 1));

            switch (sign) {
                case "*": case "/+": d *= signValue; break;
                case "/": d /= signValue; break;
                case "+": case "--": case "++": d += signValue; break;
                case "-": case "+-": case "-+": d -= signValue; break;
                case "/-": d = d / -signValue; break;
                case "*-": d = d * -signValue; break;
                default:
                    try {
                        throw new Exception();
                    } catch (Exception e) {
                        System.err.println("연산자 오류 발생!!");
                    }
                    break;
            }
        }
        return Double.toString(d);
    }
}
