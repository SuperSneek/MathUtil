package Math;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class EuclidAlgoRythm {

    //Alphabet 0123456789ABCDEF
    private static final char[] alphabet = new char[]{48,49,50,51,52,53,54,55,56,57,65,66,67,68,69,70};
    //Seperator ,
    private static final char seperator = 44;

    /**
     * @param number Number to convert
     * @param fromBase First base from 1 to 16
     * @param toBase
     * @return
     * @throws IOException
     */
    public static String convertNumberToBase(String number, int fromBase, int toBase) throws IOException {
        double decimalNumber = convertToDecimal(number, fromBase);
        return convertFromDecimal(decimalNumber, toBase);
    }

    private static double convertToDecimal(String number, int fromBase) throws IOException {
        char[] workingAlphabet = Arrays.copyOfRange(alphabet, 0, fromBase);
        char[] word = number.replace(",", "").toCharArray();
        int positivePartLength = getPositivePartLength(number.toCharArray());
        double sum = 0;
        for(int i = word.length - 1; i >= 0; i--) {
            int digitNumber = getDigitNumber(word[word.length - 1 - i], workingAlphabet);
            sum += digitNumber * Math.pow(fromBase, i - (word.length-positivePartLength));
        }
        return sum;
    }

    private static String convertFromDecimal(double decimal, int toBase) {
        char[] workingAlphabet = Arrays.copyOfRange(alphabet, 0, toBase);
        List<Character> word = new ArrayList<Character>();
        double overHead = 0;
        double remaining = decimal;
        int firstPower = findFirstPower(decimal, toBase);
        for(int i = firstPower; i >= -40; i--) {
            double currentFactor = Math.pow(toBase, i);
            int div = (int)(decimal/currentFactor);
            overHead = decimal - (div*currentFactor);
            word.add(workingAlphabet[div]);
            if(overHead == 0 && i < 0) {
                break;
            }
            if(i == 0) {
                word.add(seperator);
            }
            decimal = overHead;
        }
        char[] charWord = new char[word.size()];
        for(int j = 0; j < word.size(); j++) {
            if (word.get(j) != null) {
                charWord[j] = (char)word.get(j);
            }
        }
        return new String(charWord);
    }

    private static int getDigitNumber(char digit, char[] workingAlphabet) throws IOException {
        for (int i = workingAlphabet.length - 1; i >= 0; i--) {
            if(digit == workingAlphabet[i])
                return i;
        }
        throw new IOException("Word contains invalid digits");
    }

    private static int getPositivePartLength(char[] word) {
        for (int i = 0; i < word.length ; i++) {
            if(word[i] == seperator) {
                return (i);
            }
        }
        return word.length;
    }

    private static int findFirstPower(double number, int base) {
        int power = 0;
        while (Math.pow(base, power) <= number) {
            power++;
        }
        return power - 1;
    }

}
