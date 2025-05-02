package com.minsik.calculator;

import java.util.List;
import java.util.Scanner;

public class AppLv2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator calculator = new Calculator();
        while (true){
            System.out.println("첫 수");
            int a = sc.nextInt();
            System.out.println("두번째 수");
            int b = sc.nextInt();
            System.out.println("연산기호 + - * /");
            char way = sc.next().charAt(0);

            int res = calculator.calculate(a, b, way);
            System.out.println("연산 결과: "+ res);

            List<Integer> lst = calculator.getResults();
            System.out.println("리스트: " + lst);

            System.out.println("첫 번째 요소 삭제?");
            if (sc.next().equalsIgnoreCase("y")) {
                try {
                    int removed = calculator.removeOldest();
                    System.out.println("삭제된 값: " + removed);
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
            }

        }
    }
}
