import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Calculator {
    static final String[] ROMAN1 = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    static final String[] ROMAN2 = new String[]{"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
    static final String[] ARAB = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    static final Map<String, Integer> MAP = new HashMap<>();
    static {
        MAP.put("I", 1);
        MAP.put("II", 2);
        MAP.put("III", 3);
        MAP.put("IV", 4);
        MAP.put("V", 5);
        MAP.put("VI", 6);
        MAP.put("VII", 7);
        MAP.put("VIII", 8);
        MAP.put("IX", 9);
        MAP.put("X", 10);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String expression = bufferedReader.readLine();
        String[] values = expression.split(" ");
        if (values.length != 3) {
            throw new MyException("Incorrect expression");
        }
        String symbol = values[1];
        int x = 0;
        int y = 0;

        if (MAP.containsKey(values[0]) && MAP.containsKey(values[2])) {
            x = MAP.get(values[0]);
            y = MAP.get(values[2]);
        }

        if (x == 0 && y == 0) {
            arabResult(values);
        } else {
            if (checkXY(x, y)) {
                throw new MyException("Incorrect data");
            }
            romanResult(exprResult(x, y, symbol));
        }
    }

    static boolean checkXY(int x, int y) {
        return x == 0 || y == 0 || x > 10 || y > 10;
    }

    static void arabResult(String[] values) throws MyException {
        int x = 0;
        int y = 0;
        for (int i = 0; i < 10; i++) {
            if (values[0].equals(ARAB[i])) {
                x = i + 1;
            }
            if (values[2].equals(ARAB[i])) {
                y = i + 1;
            }
        }
        if (checkXY(x, y)) {
            throw new MyException("Incorrect data");
        }
        System.out.println(exprResult(x, y, values[1]));
    }

    static void romanResult(int result) throws MyException {
        if (result < 1) {
            throw new MyException("Roman numerals cannot be lower than 1");
        }
        String roman;
        int a = result / 10;
        int b = result % 10;
        if (b == 0) {
            roman = ROMAN2[a - 1];
        } else if (a != 0) {
            roman = ROMAN2[a - 1] + ROMAN1[b - 1];
        } else {
            roman = ROMAN1[result - 1];
        }
        System.out.println(roman);
    }

    static Integer exprResult(int x, int y, String symbol) throws MyException {
        int result = 0;
        switch (symbol) {
            case "+":
                result = x + y;
                break;
            case "-":
                result = x - y;
                break;
            case "*":
                result = x * y;
                break;
            case "/":
                result = x / y;
                break;
            default:
                throw new MyException("Unidentified symbol");
        }
        return result;
    }
}
