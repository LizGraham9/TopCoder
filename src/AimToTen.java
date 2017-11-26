/*
Problem Statement (from [topcoder]TM ARENA)
You are given a int[] marks containing the grades you have received so far in a class. Each grade is between 0 and 10, inclusive. Assuming that you will receive a 10 on all future assignments, determine the minimum number of future assignments that are needed for you to receive a final grade of 10. You will receive a final grade of 10 if your average grade is 9.5 or higher.
Definition
Class: AimToTen
Method: need
Parameters: int[]
Returns: int
Method signature: int need(int[] marks)
(be sure your method is public)
Limits
Time limit (s): 840.000
Memory limit (MB): 64
Constraints
- marks has between 1 and 50 elements, inclusive.
- Each element of marks is between 0 and 10, inclusive.
Examples
0)
{9, 10, 10, 9}
Returns: 0
Your average is already 9.5, so no future assignments are needed.
1)
{8, 9}
Returns: 4
In this case you need 4 more assignments. With each completed assignment, your average could increase to 9, 9.25, 9.4 and 9.5, respectively.
2)
{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
Returns: 950
3)
{10, 10, 10, 10}
Returns: 0
This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
 */

import java.util.Arrays;

/**
 *
 * @author Elizabeth
 */

public class AimToTen {

    public int need(int[] marks) {
        int count = 19 * marks.length - 2 * startSum(marks);
        return Math.max(0, count);
    }

    private int startSum(int[] marks) {
        return Arrays.stream(marks).sum();
    }
}
