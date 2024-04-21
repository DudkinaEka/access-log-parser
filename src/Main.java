import java.io.*;
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
            //построчное чтение файла
            int cnt_line = 0;
            int cnt_y = 0;
            int cnt_g = 0;

            try {
                FileReader fileReader = new FileReader(path);
                BufferedReader reader = new BufferedReader(fileReader);
                String line;


                while ((line = reader.readLine()) != null) {
                    int length = line.length();
                    cnt_line += 1;
                    if (length > 1024) {
                        throw new CustomException("Длина строки номер " + cnt_line + " больше, чем 1024 символа.");
                    }
                    if (line.indexOf("(") > 0 && line.indexOf(")") > 0) {

                        //Нахождение User-Agent
                        String firstBrackets = line.substring(line.lastIndexOf("\"", line.indexOf("(")), line.indexOf("\"", line.indexOf("(")) + 1);

                        String[] parts = firstBrackets.split(";");
                        if (parts.length >= 2) {
                            String fragment = parts[1];
                            if (fragment.indexOf("/") > 0) {
                                String bot = (fragment.substring(0, fragment.indexOf("/")));
                                //очистка от пробелов и нахождение ботов
                                if (bot.replace(" ", "").equals("YandexBot")) {
                                    cnt_y++;
                                }
                                if (bot.replace(" ", "").equals("Googlebot")) {
                                    cnt_g++;
                                }
                            }
                        }
                    }
                }
            } catch (FileNotFoundException ex1) {
                System.out.println("Файл не существует");
                ex1.printStackTrace();
            } catch (IOException ex2) {
                System.out.println("Ошибка при вводе данных");
                ex2.printStackTrace();
            }

            System.out.println("Количество строк в файле: " + cnt_line);
            System.out.println("Доля запросов от YandexBot: " + (double) cnt_y / (double) cnt_line);
            System.out.println("Доля запросов от Googlebot: " + (double) cnt_g / (double) cnt_line);
        }

    }
}

