import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       System.out.println("Введите число");
       int firstNumber =new Scanner(System.in) .nextInt();
       int secondNumber =new Scanner(System.in) .nextInt();
       System.out.println("Сумма: " + (firstNumber + secondNumber));
       System.out.println("Разность: " + (firstNumber - secondNumber));
       System.out.println("Пороизведение: " + (firstNumber * secondNumber));
       System.out.println("Частное: " + ((double) firstNumber / secondNumber));


    }
}
