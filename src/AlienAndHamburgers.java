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

import java.util.*;


public class AlienAndHamburgers {

    public int getNumber(int[] type, int[] taste) {
        List<TypeGroup> typeGroups = sortIntoTypeGroups(type, taste);
        return findMaxJoy(typeGroups);
    }

    private List<TypeGroup> sortIntoTypeGroups(int[] type, int[] taste) {
        List<TypeGroup> typeGroups = new ArrayList<>();
        for (int i = 0; i < type.length; i++) {  //iterate through type list
            int j = i;
            if (typeGroups.stream().noneMatch(x -> x.type == type[j])) {
                List<Integer> tasteScores = new ArrayList<>(); //if the type hasn't been added yet, create a list of all of it's taste scores
                for (int k = i; k < type.length; k++) { //run through type list (starts at i, rather than beginning) and look for other occurrences of same type. Add the corresponding taste score to that type's score list
                    if (type[k] == type[i]) {
                        tasteScores.add(taste[k]);
                    }
                }
                typeGroups.add(new TypeGroup(type[i], tasteScores)); //add given type and its taste scores to list of type groups
            }
        }
        return typeGroups;
    }

    private int findMaxJoy(List<TypeGroup> typeGroups) {
        Collections.sort(typeGroups);  //puts groups in ascending order based on value
        int maxJoy = 0;
        for (int i = 0; i < typeGroups.size(); i++) {  //iterates through sorted groups to find max joy
            int groupsRemaining = typeGroups.size() - i;
            int sumOfRemainingTopScores = typeGroups.stream().skip(i).mapToInt(x -> x.value).sum();
            int currentJoy = (groupsRemaining) * sumOfRemainingTopScores;
            if (currentJoy > maxJoy) {
                maxJoy = currentJoy;
            }
        }
        return maxJoy;
    }

    private class TypeGroup implements Comparable<TypeGroup> {
        private int type;
        private int value;

        public TypeGroup(int type, List<Integer> tasteScores) {
            this.type = type;
            this.value = sumTopScores(tasteScores);
        }

        private int sumTopScores(List<Integer> tasteScores) {
            tasteScores.sort(Comparator.reverseOrder());
            int sumOfTopScores = tasteScores.get(0);  //adds highest value to sum. this value could be negative.

            for (int i = 1; i < tasteScores.size(); i++) { //runs through rest of reverse-sorted list and adds positive values (if any are present)
                if (tasteScores.get(i) > 0) {
                    sumOfTopScores += tasteScores.get(i);
                } else {
                    break;
                }
            }
            return sumOfTopScores;
        }

        @Override
        public int compareTo(TypeGroup group) {
            return this.value - group.value;
        }

        @Override
        public String toString() {
            return "type group: " + type + " | top scores: " + value;
        }
    }
}
