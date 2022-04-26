import main.For;
import main.Setting;
import main.variable.Var;
import main.variable.VariableGet;

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

import static main.Setting.*;

public class OTLanguage {

    private static final Scanner scanner = new Scanner(System.in);
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        boolean check = true;

        args = new String[1];
        args[0] = "start.otl";
//        args[0] = "main.otl";

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
            count = 0;
            //1줄씩 읽어오기
            //읽은뒤 변수 값 설정 및 변수 가져오기
            list.forEach(OTLanguage::setDefault);
        } else {
            fileName = args[0];
            BufferedReader reader = new BufferedReader(new FileReader(fileName, StandardCharsets.UTF_8));
            String readerString;

            //1줄 씩 읽어오기
            //읽은뒤 변수 값 설정 및 변수 가져오기
            count = 0;
            while ((readerString = reader.readLine()) != null) setDefault(readerString);
            reader.close();
        }

        For fore = new For();
        // totalString
        Setting.map.clear();
        Setting.forList.clear();
        fore.saveFor(totalString);

        System.out.println("===================출력===================");
        Setting.list.addAll(Setting.setTrim(Setting.totalString));
        setForCount();
        play(Setting.totalString);
    }

    private static final Var var = new Var();
    private static void setDefault(String line) {
        try {
            line = var.getVar(line);
            var.setVar(line, count);
            count ++;
        } catch (IOException ignored) {}
        Setting.totalString += (line + "\n");
    }
}