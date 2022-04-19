import java.util.*;

public class OTLanguage {
    /**
     * @param ㅇㅈㅇ (정수) int
     * @param ㅇㅉㅇ (정수) long
     * @param ㅇㅂㅇ (블린) boolean
     * @param ㅇㅁㅇ (문자) String
     * @param ㅇㄱㅇ (글짜) char
     * @param ㅇㅅㅇ (실수) float
     * @param ㅇㅆㅇ (실수) double
     */

    String totalText = null;
    List<String> list = new ArrayList<>();

    Map<String, Integer> IntMap = new HashMap<>();
    Map<String, Long> LongMap = new HashMap<>();
    Map<String, Boolean> BooleanMap = new HashMap<>();
    Map<String, String> StringMap = new HashMap<>();
    Map<String, Character> CharMap = new HashMap<>();
    Map<String, Float> FloatMap = new HashMap<>();
    Map<String, Double> DoubleMap = new HashMap<>();

    public static void main(String[] args) {

        String text = "1+(123-300*342)+123";
        Extraction extraction = new Extraction();
        System.out.println(extraction.extractionNumber(text));
    }

    public void setTrim() {
        list.clear();
        String[] texts = totalText.split("\\n");
        for (String text : texts) {
            if (!text.trim().equals("")) list.add(text.trim());
        }
    }

    public void setType(String text) {
        char c = text.trim().charAt(1);
        String key = text.trim().substring(0, 2);
        if (c == 'ㅈ') {

        } else if (c == 'ㅉ') {

        } else if (c == 'ㅂ') {

        } else if (c == 'ㅁ') {

        } else if (c == 'ㄱ') {

        } else if (c == 'ㅅ') {

        } else if (c == 'ㅆ') {

        }

    }
}
