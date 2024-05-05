import javax.swing.event.MenuDragMouseListener;
import java.io.*;
import java.time.Duration;
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
            //Statistics test = new Statistics();
            Statistics test2 = new Statistics();

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

                        //System.out.println(line);
                        //System.out.println(new LogEntry(line).toString());
                        //System.out.println(new UserAgent(new LogEntry(line).userAgent));

                        //test.addEntry(new LogEntry(line));
                        test2.addEntry(new LogEntry(line));
                        //System.out.println(test2.isBot(new LogEntry(line).userAgent));

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

            //System.out.println("Количество строк в файле: " + cnt_line);
            //System.out.println("Доля запросов от YandexBot: " + (double) cnt_y / (double) cnt_line);
            //System.out.println("Доля запросов от Googlebot: " + (double) cnt_g / (double) cnt_line);
            //System.out.println(test.getTrafficRate());
            //System.out.println(test.minTime);
            //System.out.println(test.maxTime);
            //Duration duration = Duration.between(test.minTime, test.maxTime);
            //double hour = duration.toHours();
            //System.out.println(hour);
            //System.out.println(test2.nonExistPages.size());
            //System.out.println(test2.getBrowserCount());
            //System.out.println(test2.getAvgTotalVisitPerHour());
            //System.out.println(test2.getAvgErrorPerHour());
            //System.out.println(test2.userVisit);
            //System.out.println(test2.getAvgVisitUniqUser());
            //Метод для расчета пиковой посещаемости
            System.out.println(test2.visitPerSecExclBot);
            System.out.println(test2.getPeakVisitPerSec());
            //Метод возвращающий список сайтов
            System.out.println(test2.getReferringDomains());
            //Метод расчета максимальной посещаемости одним пользователем
            System.out.println(test2.maxVisitForUser());
            }
        }

    }


