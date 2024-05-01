package platevalidator;

public class PlateValidator {

    public static boolean validate(String s){
        int state = 0;
        int input = 0;

        int[][] FSA = {
                {2, 1},
                {15, 3},
                {5, 4},
                {15, 6},
                {15, 7},
                {8, 15},
                {10, 15},
                {15, 10},
                {15, 9},
                {15, 11},
                {12, 15},
                {15, 13},
                {14, 15},
                {15, 14},
                {15, 15},
                {15, 15},
        };

        for (int i = 0; i < s.length(); i++){
            char currChar = Character.toUpperCase(s.charAt(i));
            // For ignoring spaces in input
            if (currChar == ' ') {
                continue;
            }

            if (Character.isLetter(currChar)){
                input = 0;
            } else if (Character.isDigit(currChar)) {
                input = 1;
            } else {
                input = 15;
            }

            state = FSA[state][input];
        }

        return state == 14;
    }

    public static String getRegion(String s){
        s = s.replaceAll("\\s+", "").toUpperCase();

        if (validate(s)){
            char firstLetter;
            if (s.length() == 7) {
                firstLetter = Character.toUpperCase(s.charAt(0));
            } else if (s.length() == 6 && Character.isLetter(s.charAt(0))) {
                firstLetter = Character.toUpperCase(s.charAt(0));
            } else if (s.length() == 6 && Character.isDigit(s.charAt(1))) {
                firstLetter = Character.toUpperCase(s.charAt(3));
            } else {
                return "Unknown Region";
            }

            switch (firstLetter){
                case 'N':
                case 'P':
                case 'Q':
                case 'T':
                case 'U':
                case 'X':
                    return "National Capital Region (NCR)";
                case 'A':
                case 'I':
                    return "Region 1";
                case 'B':
                    return "Region 2";
                case 'C':
                case 'R':
                case 'W':
                    return "Region 3";
                case 'D':
                case 'O':
                    return "Region 4A";
                case 'V':
                    return "Region 4B";
                case 'E':
                    return "Region 5";
                case 'F':
                    return "Region 6";
                case 'G':
                    return "Region 7";
                case 'H':
                    return "Region 8";
                case 'J':
                    return "Region 9";
                case 'K':
                    return "Region 10";
                case 'L':
                    return "Region 11";
                case 'M':
                    return "Region 12";
                case 'Y':
                    return "Cordillera Administrative Region (CAR)";
                case 'Z':
                    return "CARAGA";
                case 'S':
                    return "Government Vehicle";
                default:
                    return "Unknown Region";
            }
        } else {
            return "Invalid";
        }
    }


    public static String getType(String s) {
        s = s.replaceAll("\\s+", "").toUpperCase();

        if (validate(s)) {
            if (s.length() == 7) {
                return "Four Wheel Vehicle";
            } else if (s.length() == 6 && Character.isLetter(s.charAt(1))) {
                return "Motor Vehicle";
            } else if (s.length() == 6 && Character.isDigit(s.charAt(1))) {
                return "Motor Vehicle";
            }
        }
        return "Unknown";
    }
}

