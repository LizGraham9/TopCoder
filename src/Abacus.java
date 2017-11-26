/*
        Problem Statement (from [topcoder]TM ARENA)
        An abacus can be used to do arithmetic. The version that we have has 6 horizontal threads, each with nine beads on it. The beads on each thread are always arranged with just one gap, possibly at one of the ends. However many beads are adjacent and at the right end of the thread is the digit value of the thread. The value on the abacus is read by taking the digits in order from top thread to bottom thread and arranging them from left to right (so the top thread is the one that contains the most significant digit).
        Create a class Abacus that contains a method add that is given a String[] original and a number val and that returns a String[] showing the abacus after val has been added to the original abacus.

        Both in original and in the return, the String[] will contain exactly 6 elements representing the 6 threads in order from top thread to bottom thread. Each element will contain a lowercase 'o' to represent each bead and three consecutive hyphens '-' to indicate the empty part of the thread. Each element will thus contain exactly 12 characters.

        Definition
        Class: Abacus
        Method: add
        Parameters: String[], int
        Returns: String[]
        Method signature: String[] add(String[] original, int val)
        (be sure your method is public)
        Limits
        Time limit (s): 840.000
        Memory limit (MB): 64
        Constraints
        - original will contain exactly 6 elements.
        - Each element of original will contain exactly 12 characters, 9 lowercase 'o's and 3 consecutive '-'s.
        - val will be between 0 and 999,999 inclusive.
        - val added to the original abacus will result in a value that can be shown on the abacus.
        Examples
        0)
        {"ooo---oooooo", "---ooooooooo", "---ooooooooo", "---ooooooooo", "oo---ooooooo", "---ooooooooo"}
        5
        Returns: {"ooo---oooooo", "---ooooooooo", "---ooooooooo", "---ooooooooo", "o---oooooooo", "ooooo---oooo" }
        When we add 5 to the original, it is necessary to "carry" 1 to the next thread up. This shows the arithmetic 699979 + 5 = 699984
        1)
        {"ooo---oooooo", "---ooooooooo", "---ooooooooo", "---ooooooooo", "oo---ooooooo", "---ooooooooo"}
        21
        Returns: {"oo---ooooooo", "ooooooooo---", "ooooooooo---", "ooooooooo---", "ooooooooo---", "ooooooooo---" }
        This shows 699979 + 21 = 700000
        2)
        {"ooooooooo---", "---ooooooooo", "ooooooooo---", "---ooooooooo", "oo---ooooooo", "---ooooooooo"}
        100000
        Returns: {"oooooooo---o", "---ooooooooo", "ooooooooo---", "---ooooooooo", "oo---ooooooo", "---ooooooooo" }
        3)
        {"o---oooooooo", "---ooooooooo", "---ooooooooo", "---ooooooooo", "---ooooooooo", "---ooooooooo" }
        1
        Returns: {"---ooooooooo", "ooooooooo---", "ooooooooo---", "ooooooooo---", "ooooooooo---", "ooooooooo---" }
        This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
 */

import java.util.Arrays;

/**
 *
 * @author Elizabeth
 */
public class Abacus {

    private static final int NUM_THREDS = 6;
    private static final int THRED_LENGTH = 12;

    public String[] add(String[] original, int val) {
        int newAbacusValue = getAbacusValue(original) + val;
        return compileAbacus(newAbacusValue);
    }

    private int getAbacusValue(String[] original) {
        int originalVal = 0;
        for (int i = 0; i < NUM_THREDS; i++) {
            originalVal += getThredValue(original[i], i);
        }
        return originalVal;
    }

    private int getThredValue(String thred, int i) {
        int thredDigit = THRED_LENGTH - thred.lastIndexOf('-') - 1;
        int thredValue = thredDigit * getMultiplier(i);
        return thredValue;
    }

    private int getMultiplier(int i) {
        return (int) Math.pow(10, NUM_THREDS - i - 1);
    }
    
    private String[] compileAbacus(int sum) {
        String[] abacus = new String[NUM_THREDS];
        int[] thredDigits = deconstructSum(sum);
        for (int i = 0; i < NUM_THREDS; i++) {
            abacus[i] = compileThred(thredDigits[i]);
        }
        return abacus;
    }

    private int[] deconstructSum(int sum) {
        int[] thredDigits = new int[THRED_LENGTH];
        for (int i = 0; i < NUM_THREDS; i++) {
            int digit = (sum / getMultiplier(i));
            thredDigits[i] = digit;
            sum -= digit * getMultiplier(i);
        }
        //rework w/ modulo
//        String sumString = String.valueOf(sum);
//        int[] thredDigits = new int[sumString.length()];
//        for (int i = 0; i < sumString.length(); i++) {
//            thredDigits[i] = Character.getNumericValue(sumString.charAt(i));
//        }
        return thredDigits;
    }

    private String compileThred(int digit) {
        char[] thredArray = new char[THRED_LENGTH];
        Arrays.fill(thredArray, 'o');
        for (int i = THRED_LENGTH - digit - 3; i < THRED_LENGTH - digit; i++) {
            thredArray[i] = '-';
        }
        return new String(thredArray);
    }

}
