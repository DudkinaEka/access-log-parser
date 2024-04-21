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
            int max = 0;
            int min = 1024;

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
                    if (length > max) {
                        max = length;
                    }
                    if (length < min) {
                        min = length;
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
            System.out.println("Максимальная длина строки : " + max);
            System.out.println("Минимальная длина строки : " + min);
        }

    }
}

