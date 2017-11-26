/*
Problem Statement (from [topcoder]TM ARENA)
You are working in an advertising agency. There are 100 billboards owned by your agency, numbered from 1 to 100.

You clients send you requests, one after another. Each request is the number of the billboard on which the client would like to place his advertisement.

Initially all billboards are empty. Each time you receive a request, you act as follows. If the corresponding billboard is empty, you satisfy the request and occupy the billboard with the client's advertisement. If the corresponding billboard is occupied, you reject the request.

You are given a requests containing the requests in the order you receive them. Return the number of rejected requests.

Definition
Class: AdvertisingAgency
Method: numberOfRejections
Parameters: int[]
Returns: int
Method signature: int numberOfRejections(int[] requests)
(be sure your method is public)
Limits
Time limit (s): 840.000
Memory limit (MB): 64
Constraints
- requests will contain between 1 and 50 elements, inclusive.
- Each element of requests will be between 1 and 100, inclusive.
Examples
0)
{1,2,3}
Returns: 0
All requests will be satisfied.
1)
{1,1,1}
Returns: 2
Only the first request will be satisfied.
2)
{1,2,1,2}
Returns: 2
3)
{100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100 }
Returns: 49
This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/

import java.util.HashSet;
import java.util.Set;

public class AdvertisingAgency {


    public int numberOfRejections(int[] requests) {

        Set<Integer> approved = new HashSet<>();

        for (int i = 0; i < requests.length; i++) {
            approved.add(requests[i]);
        }

        int duplicates = requests.length - approved.size();

        return duplicates;
    }
}



