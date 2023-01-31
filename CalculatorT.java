import java.util.Scanner;
class CalculatorT{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Main result = new Main();
        System.out.println("Введите выражение:");
        String expression = input.nextLine();
        String answer = result.calc(expression);
        System.out.println("Результат:\n" + answer);
    }
}
class Main{
    public static String calc(String input){
        boolean roman = false;
        String exception = "Ошибка";
        String[] act = {"*", "+", "-", "/"};
        String[] regexAct = {"\\*", "\\+", "-", "/"};
        Main romanTranslate = new Main();
        Main arabTranslate = new Main();
        int result = 0;
        int actionI= -1;
        for(int i = 0;  i < act.length; i++) {
            if(input.contains(act[i])){
                actionI = i;
                break;
            }
        }
        if(actionI==-1){
            return exception;
        }
        String[] data = input.split(regexAct[actionI]);
        Integer firstNum = 0;
        Integer secondNum = 0;
        try {
            firstNum = Integer.parseInt(data[0]);
            secondNum = Integer.parseInt(data[1]);
        } catch (NumberFormatException e) {
            try {
                firstNum = romanTranslate.romanToArab(data[0]);
                secondNum = romanTranslate.romanToArab(data[1]);
                roman = true;
            } catch (NumberFormatException ex) {
                return exception;
            }
        }
        if ((firstNum < 1) || (firstNum > 10) || (secondNum < 1) || (secondNum > 10)){
            return exception;
        }
        switch (act[actionI]) {
            case "+" -> result = firstNum + secondNum;
            case "-" -> result = firstNum - secondNum;
            case "*" -> result = firstNum * secondNum;
            case "/" -> result = firstNum / secondNum;
            default -> {
                return exception;
            }
        }
        String output;
        if (roman){
            if(result < 1){
                return exception;
            } else {
                output = arabTranslate.arabInRome(result);
            }
        } else {
            output = Integer.toString(result);
        }

        return output;
    }
    Integer romanToArab(String romanInput){
        int result = 0;
        int[] arab = {10, 9, 5, 4, 1};
        String[] roman = {"X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arab.length; i++ ) {
            while (romanInput.indexOf(roman[i]) == 0) {
                result += arab[i];
                romanInput = romanInput.substring(roman[i].length());
            }
        }

        return result;
    }
    String arabInRome(int arabInput){
        String result = "";
        int value = 0;
        int[] arab = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arab.length; i++){
            value = arabInput / arab[i];
            for (int j = 0; j < value; j++){
                result = result.concat(roman[i]);
            }
            arabInput = arabInput % arab[i];
        }
        return result;
    }
}