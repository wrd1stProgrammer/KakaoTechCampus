package com.minsik.calculator;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true){
            int a = sc.nextInt();
            int b = sc.nextInt();
            char way = sc.next().charAt(0);
            int res = 0;
            switch (way) {
                case '+':
                    res = a+b;
                    break;
                case '-':
                    res = a-b;
                    break;
                case '*':
                    res = a*b;
                    break;
                case '/':
                    if(b == 0){
                        System.out.println("나눗셈 연산에서 분모에 0이 입력될 수ㅇ없습니다.");
                        break;
                    }else {
                        res = a / b;
                        break;
                    }
                default:
                    System.out.println("유효하지 않은 계산");

            }
            System.out.println("계산 결과 :"+ res);
            System.out.println("더 계산하십니가? (exit 입력시 계산 종료");
            String isExit = sc.next();
            if (isExit.equalsIgnoreCase("exit")) {
                System.out.println("계산을 종료합니다!");
                break;
            }

        }
        sc.close();

    }
}
