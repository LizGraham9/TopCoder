/*
Problem Statement (from [topcoder]TM ARENA)
Alien Fred wants to destroy the Earth. But before he does that, he wants to eat some hamburgers.

You are given two s: type and taste. They describe all hamburgers available in the shop he found. Each hamburger has a type (some positive integer) and a taste (some, possibly negative, integer). For each i, type[i] represents the type of i-th hamburger (0-based index), and taste[i] represents the taste of i-th hamburger. It is possible that different hamburgers have the same type but a different taste.

Fred wants to eat some subset of those hamburgers (possibly none or all of them). Eating the chosen hamburgers will give him some amount of joy. This amount can be computed as Y * A, where Y is the number of different types of hamburgers eaten, and A is their total taste.

Return the largest possible amount of joy he can get.

Definition
Class: AlienAndHamburgers
Method: getNumber
Parameters: int[], int[]
Returns: int
Method signature: int getNumber(int[] type, int[] taste)
(be sure your method is public)
Limits
Time limit (s): 2.000
Memory limit (MB): 256
Constraints
- type will contain between 1 and 50 elements, inclusive.
- type and taste will contain the same number of elements.
- Each element of type will be between 1 and 100, inclusive.
- Each element of taste will be between -100,000 and 100,000, inclusive.
Examples
0)
{1, 2}
{4, 7}
Returns: 22
In this case, the best choice is to choose both hamburgers. The number of different types is 2, and the total taste is 11. Thus, the answer is 2*11 = 22.
1)
{1, 1}
{-1, -1}
Returns: 0
Note that sometimes the best choice is not to eat any hamburgers. In such a case the amount of joy is 0.
2)
{1, 2, 3}
{7, 4, -1}
Returns: 30
3)
{1, 2, 3, 2, 3, 1, 3, 2, 3, 1, 1, 1}
{1, 7, -2, 3, -4, -1, 3, 1, 3, -5, -1, 0}
Returns: 54
4)
{30, 20, 10}
{100000, -100000, 100000}
Returns: 400000
This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//solution still a work in progress

public class AlienAndHamburgers {

    public int getNumber(int[] type, int[] taste) {

        List<burgerGroup> burgers = new ArrayList<>();

        for (int i = 0; i < type.length; i++) {
            for (burgerGroup burger : burgers) {
                if (burger.type == type[i]) {
                    continue;
                }
            }

            List<Integer> tasteScores = new ArrayList<>();

            for (int j = i; j < type.length; i++) {
                if (type[j] == type[i]) {
                    tasteScores.add(taste[j]);
                }
            }

            burgers.add(new burgerGroup(type[i], tasteScores));
        }

        return 0;
    }


    private class burgerGroup {
        private int type;
        private List<Integer> rawTasteScores;
        private int sumOfTopScores;

        public burgerGroup(int type, List<Integer> rawTasteScores) {
            this.type = type;
            this.rawTasteScores = rawTasteScores;


        }

        private int cullTasteScores(List<Integer> tastes) {
            tastes.sort(Comparator.reverseOrder());
            int highestTaste = tastes.get(0);

            for (int i : tastes) {

            }

            if (highestTaste < 0) {
                tastes.removeIf(x -> x <= highestTaste);
            } else {
                tastes.removeIf(x -> x < 0);
            }

            return 0;

        }

//         burgersfdff.values().forEach(x -> cullTasteScores(x));


    }
}
