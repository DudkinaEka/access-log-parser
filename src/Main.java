import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Введите текст и нажмите <Enter>");
        //String text = new Scanner(System.in) .nextLine();
        //System.out.println("Длина текста: " + text.length());
        int count = 0;
        while (count >= 0) {
            String path = new Scanner(System.in).nextLine(); //Запрос пути в консоли

            File file = new File(path);
            boolean fileExists = file.exists(); //Если файл существует - true
            boolean isDirectory = file.isDirectory(); //Если это путь к папке, то true
            if (fileExists == isDirectory) {
                System.out.println("Файл не существует или указанный путь является путем к папке");
                continue;
            } else {
                System.out.println("Путь указан верно");
                count++;
                System.out.println("Это файл номер " + count);
            }
        }

    }
}
