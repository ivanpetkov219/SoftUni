package Telephony;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] numbers = scanner.nextLine().split("\\s+");
        String[] urls = scanner.nextLine().split("\\s+");

        Smartphone smartphone = new Smartphone(Arrays.asList(numbers), Arrays.asList(urls));

        System.out.print(smartphone.call());
        System.out.println(smartphone.browse());

    }
}
