package lv.id.jc.converter;

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
        int a = input[0], b = a;
        var output = new StringBuilder().append(a);

        for (int i = 1; i < input.length; ++i) {
            int c = input[i];
            if (c - b == 1) {
                b = c;
            } else {
                output.append(printRangeEnd(a, b))
                        .append(',')
                        .append(c);
                a = b = c;
            }
        }
        return output.append(printRangeEnd(a, b)).toString();
    }

    private static String printRangeEnd(int a, int b) {
        if (a == b) return "";
        var isTwoNumberRange = b - a == 1;
        var delimiter = isTwoNumberRange ? "," : "-";
        return delimiter + b;
    }
}