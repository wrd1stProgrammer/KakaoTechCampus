// Calculator.java
package com.minsik.calculator;

import java.util.LinkedList;
import java.util.List;

public class Calculator {
    private List<Integer> results;
    int result;

    public Calculator() {
        this.results = new LinkedList<>();
    }
    public int calculate(int a, int b, char operator) {
        switch (operator) {
            case '+': result = a + b; break;
            case '-': result = a - b; break;
            case '*': result = a * b; break;
            case '/':
                if (b == 0) {
                    System.out.println("나눗셈에서 분모는 0이 될 수 없습니다.");
                }
                result = a / b;
                break;
            default:
                System.out.println("유효하지 않은 연산 기호입니다.");
        }
        results.add(result);
        return result;
    }

    public List<Integer> getResults() {
        return new LinkedList<>(results);
    }

    public void setResults(List<Integer> results) {
        this.results = results;
    }

    public int removeOldest() {
        if (results.isEmpty()) {
            System.out.println("삭제할 결과가 없습니다.");
        }
        return results.remove(0);
    }
}
