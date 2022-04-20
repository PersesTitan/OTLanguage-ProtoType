import activity.ActivityPrint;
import main.Setting;
import main.variable.VariableGet;
import main.variable.VariableSet;

import java.io.File;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class OTLanguage {

    private static String totalString;

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("파일 이름 입력: ");
        String fileName = scanner.next();

//        if (args.length <= 0) throw new IOException("파일 이름을 입력히주세요.");
        File file = new File("../" + fileName);
        if (!file.getName().toLowerCase(Locale.ROOT).endsWith(".otl")) {
            throw new IOException("확장자를 읽을 수 없습니다.");
        }

        if (!file.canRead()) throw new IOException("파일을 읽을 수 없습니다.");

//        String text =
//                "1+(123-300*342)+123\n" +
//                "ㅇㅅㅇ 100\n" +
//                "운 :ㅇㅅㅇ 출력 하기\n" +
//                "otl otl\n" +
//                "OTL OTL\n";

//        Setting setting = new Setting(args[0]);

        totalString = "";
        Path path = Paths.get("../" + fileName);
        Charset cs = StandardCharsets.UTF_8;
        List<String> list = new ArrayList<>();
        list = Files.readAllLines(path, cs);
        list.forEach(string -> totalString += (string + "\n"));

        ActivityPrint print = new ActivityPrint();
        VariableGet variableGet = new VariableGet();
        VariableSet variableSet = new VariableSet();
        Setting setting = new Setting(totalString);

        Setting.map.clear();
        setting.setTrim();

        System.out.println("===================출력===================");
        Setting.list.forEach(object -> {
            if (variableSet.check(object)) {
                variableSet.setVariable(object);
            } else if (print.check(object)) {
                print.print(object);
            }
        });

//        Extraction extraction = new Extraction();
//        System.out.println(extraction.extractionNumber(text));
    }
}