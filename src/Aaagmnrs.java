/*
Problem Statement (from [topcoder]TM ARENA)
Two phrases are anagrams if they are permutations of each other, ignoring spaces and capitalization. For example, "Aaagmnrs" is an anagram of "anagrams", and "TopCoder" is an anagram of "Drop Cote". Given a phrases, remove each phrase that is an anagram of an earlier phrase, and return the remaining phrases in their original order.

Definition
Class: Aaagmnrs
Method: anagrams
Parameters: String[]
Returns: String[]
Method signature: String[] anagrams(String[] phrases)
(be sure your method is public)
Limits
Time limit (s): 840.000
Memory limit (MB): 64
Constraints
- phrases contains between 2 and 50 elements, inclusive.
- Each element of phrases contains between 1 and 50 characters, inclusive.
- Each element of phrases contains letters ('a'-'z' and 'A'-'Z') and spaces (' ') only.
- Each element of phrases contains at least one letter.
Examples
0)
{ "Aaagmnrs", "TopCoder", "anagrams", "Drop Cote" }
Returns: { "Aaagmnrs", "TopCoder" }
The examples above.
1)
{ "SnapDragon vs tomek", "savants groped monk", "Adam vents prongs ok" }
Returns: { "SnapDragon vs tomek" }
2)
{ "Radar ghost jilts Kim", "patched hers first", "DEPTH FIRST SEARCH", "DIJKSTRAS ALGORITHM" }
Returns: { "Radar ghost jilts Kim", "patched hers first" }
This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
 */

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author Elizabeth
 */
public class Aaagmnrs {

    public String[] anagrams(String[] phrases) {
        return Arrays.stream(phrases).map(Anagram::new).distinct().map(Anagram::toString).toArray(String[]::new);
    }

    private class Anagram {

        private final String text;
        private final String scrubbedText;

        private Anagram(String text) {
            this.text = text;
            this.scrubbedText = scrubAndSortString(text);
        }

        private String scrubAndSortString(String input) {
            char[] charArray = input.toLowerCase().replaceAll(" ", "").toCharArray();
            Arrays.sort(charArray);
            return new String(charArray);
//            return input.toLowerCase().replaceAll(" ", "").chars().sorted().mapToObj(x -> Character.toString((char) x)).collect(Collectors.joining(""));
        }

        @Override
        public String toString() {
            return text;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 29 * hash + Objects.hashCode(this.scrubbedText);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Anagram other = (Anagram) obj;
            return this.scrubbedText.equals(other.scrubbedText);
        }
    }
}
