package lv.id.jc.converter;

import java.util.Objects;
import java.util.StringJoiner;

public class Main {
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
        var sequenceBuilder = new SequenceBuilder();

        sequenceBuilder.startRange(input[0]);
        for (int i = 1; i < input.length; ++i) {
            if (input[i] - input[i - 1] == 1) {
                continue;
            }
            sequenceBuilder.endRange(input[i - 1]);
            sequenceBuilder.startRange(input[i]);
        }
        sequenceBuilder.endRange(input[input.length - 1]);

        return sequenceBuilder.build();
    }

    static class SequenceBuilder {
        private final StringJoiner stringJoiner = new StringJoiner(",");
        private Integer firstNumber;

        public void startRange(Integer number) {
            firstNumber = number;
        }

        public void endRange(Integer lastNumber) {
            if (Objects.equals(lastNumber, firstNumber)) {
                stringJoiner.add(firstNumber.toString());
            } else if (lastNumber - firstNumber == 1) {
                stringJoiner.add(firstNumber + "," + lastNumber);
            } else {
                stringJoiner.add(firstNumber + "-" + lastNumber);
            }
        }

        public String build() {
            return stringJoiner.toString();
        }
    }
}