import main.Setting;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static main.Setting.play;
import static main.Setting.setForCount;

public class OTLanguage {

//    private static final ActivityPrint print = new ActivityPrint();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        boolean check = true;

        args = new String[1];
        args[0] = "start.otl";

        start(args);
        while (check)  {
            System.out.print("종료하겠습니까? (y/n) : ");
            String string = scanner.next();
            check = string.toLowerCase(Locale.ROOT).equals("n");
            if (check) start(args);
        }
    }

    private static void start(String[] args) throws IOException {
        String fileName;
        File file;

        Setting.totalString = "";
        //파일 읽기
        if (args.length <= 0) {
            System.out.print("파일 이름 입력: ");
            fileName = scanner.next();
            file = new File("./" + fileName);
            while (!file.canRead()) {
                System.out.println("\n파일을 읽을 수 없습니다. 다시 입력해주세요.");
                System.out.print("파일 이름 입력: ");
                fileName = scanner.next();
                file = new File("./" + fileName);
            }
            if (!file.getName().toLowerCase(Locale.ROOT).endsWith(".otl")) throw new IOException("확장자를 읽을 수 없습니다. 확장자는 .otl 이여야 합니다.");
            if (!file.canRead()) throw new IOException("파일을 읽을 수 없습니다.");
            Path path = Paths.get("./" + fileName);
            Charset cs = StandardCharsets.UTF_8;
            List<String> list = Files.readAllLines(path, cs);
            list.forEach(string -> Setting.totalString += (string + "\n"));
        } else {
            fileName = args[0];
            BufferedReader reader = new BufferedReader(new FileReader(fileName, StandardCharsets.UTF_8));
            String readerString;
            while ((readerString = reader.readLine()) != null) Setting.totalString += (readerString + "\n");
            reader.close();
        }

        // totalString
        Setting.map.clear();
        Setting.list.addAll(Setting.setTrim(Setting.totalString));

        System.out.println("===================출력===================");
        setForCount();
        play(Setting.totalString);
    }
}