package lv.id.jc.converter;

import java.util.StringJoiner;

public class Main {
    private static final String INTERVALS_DELIMITER = ",";
    private static final String TWO_NUMBERS_DELIMITER = ",";
    private static final String INTERVAL_SYMBOL = "-";

    private final static Integer INPUTS[][] = {{-6, -3, -2, -1, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18, 19, 20},
            {-6, -3, -2, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18, 19, 20},
            {-4, -3, -2, -1, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18, 19, 20},
            {-6, -5, -4, -3, -2, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18, 19, 20, 100, 101, 102, 103, 105},
            {1, 3, 4, 6, 7, 9, 20, 21}
    };

    private final static String[] RESULTS = {"-6,-3-1,3-5,7-11,14,15,17-20",
            "-6,-3,-2,0,1,3-5,7-11,14,15,17-20",
            "-4-1,3-5,7-11,14,15,17-20",
            "-6--2,0,1,3-5,7-11,14,15,17-20,100-103,105",
            "1,3,4,6,7,9,20,21"
    };

    public static void main(String[] args) {

        for (int i = 0; i < INPUTS.length; i++) {
            var result = convertToIntervals(INPUTS[i]);
            var expectedResult = RESULTS[i];
            boolean correct = result.equalsIgnoreCase(expectedResult);
            System.out.println("The result is " + (correct ? "correct. " : "incorrect. ") + "Result:" + result + " ExpectedResult:" + expectedResult);
        }
    }

    private static String convertToIntervals(Integer[] input) {
        if (input == null || input.length == 0) {
            return "";
        }
        var stringJoiner = new StringJoiner(INTERVALS_DELIMITER);
        int firstNumber = input[0];
        int lastNumber = firstNumber;

        for (int i = 1; i < input.length; ++i) {
            int currentNumber = input[i];

            if (isIntervalEnd(currentNumber, lastNumber)) {
                stringJoiner.add(printInterval(firstNumber, lastNumber));
                firstNumber = currentNumber;
            }

            lastNumber = currentNumber;
        }
        stringJoiner.add(printInterval(firstNumber, lastNumber));

        return stringJoiner.toString();
    }

    private static boolean isIntervalEnd(int currentNumber, int lastNumber) {
        return currentNumber - lastNumber != 1;
    }

    private static String printInterval(int firstNumber, int lastNumber) {
        int numbersInInterval = lastNumber - firstNumber + 1;
        switch (numbersInInterval) {
            case 1:
                return String.valueOf(firstNumber);
            case 2:
                return firstNumber + TWO_NUMBERS_DELIMITER + lastNumber;
            default:
                return firstNumber + INTERVAL_SYMBOL + lastNumber;
        }
    }

}