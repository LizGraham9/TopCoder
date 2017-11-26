/*
Problem Statement (from [topcoder]TM ARENA)
You are given an electrical circuit for a home, with a number of nodes possibly connected by wires. Any pair of nodes may be connected by at most one wire, and a node can't be connected to itself. Each node on the circuit is either an electrical outlet for the house or a connection to the main electrical grid. The wires tells you the wires that are already in place; the xth character of the yth element is '1' (one) if nodes x and y have a wire between them, '0' (zero) otherwise. The gridConnections lists the indices of the nodes that are connections to the main electrical grid.

You'd like to make the circuit safer and more redundant by adding as many extra wires to it as possible. The one complication is that no two main grid connections are currently wired together (directly or indirectly), and you must preserve this, or else disaster will result. Determine the maximum number of new wires you can add to the circuit.
Definition
Class: AddElectricalWires
Method: maxNewWires
Parameters:
Returns: int
Method signature:
(be sure your method is public)
Limits
Time limit (s): 840.000
Memory limit (MB): 64
Constraints
- wires will contain between 1 and 50 elements, inclusive.
- Each element of wires will have the same length as wires.
- Each element of wires will contain only the characters '0' and '1'.
- Character i of element i of wires will be a '0'.
- Character i of element j of wires will be the same as character j of element i.
- gridConnections will contain between 1 and 50 elements, inclusive.
- Each element of gridConnections will be an integer between 0 and length(wires)-1, inclusive.
- Each element of gridConnections will be distinct.
- Each pair of elements of gridConnections will not index nodes connected by a path of '1's in wires.
Examples
0)
{"000","000","000"}
{0}
Returns: 3
Every valid wire can be added.
1)
{"000","000","000"}
{0,1}
Returns: 1
0 and 1 can't be connected, but 0 and 2 (or 1 and 2) still can be.
2)
{"01","10"}
{0}
Returns: 0
This circuit is already complete.
3)
{"00000","00000","00000","00000","00000"}
{0,1,2,3,4}
Returns: 0
Any connections would be disastrous.
4)
{"01000","10100","01010","00100","00000"}
{2,4}
Returns: 3
This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
 */

import java.util.*;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author Elizabeth
 */
public class AddElectricalWires {

    public int maxNewWires(String[] nodes, int[] gridNodes) {
        List<List<Integer>> gridClusters = getGridClusters(nodes, gridNodes);
        List<Integer> freeNodes = getFreeNodes(nodes, gridClusters);
        getLargestCluster(gridClusters).addAll(freeNodes);
        return getTotalPossibleConnections(gridClusters) - getNumberOfExistingConnections(nodes);
    }

    private List<List<Integer>> getGridClusters(String[] nodes, int[] gridNodes) {
        return Arrays.stream(gridNodes).mapToObj(x -> getGridCluster(nodes, x)).collect(toList());
    }

    private List<Integer> getGridCluster(String[] nodes, int nodeIndex) {
        return getGridCluster(new ArrayList<>(), nodes, nodeIndex);
    }

    private List<Integer> getGridCluster(List<Integer> gridCluster, String[] nodes, int nodeIndex) {
        gridCluster.add(nodeIndex);
        for (int i = 0; i < nodes[nodeIndex].length(); i++) {
            if (isConnected(nodeIndex, i, nodes) && !gridCluster.contains(i)) {
                getGridCluster(gridCluster, nodes, i);
            }
        }
        return gridCluster;
    }

    private boolean isConnected(int node1Index, int node2Index, String[] nodes) {
        return nodes[node1Index].charAt(node2Index) == '1';
    }

    private List<Integer> getFreeNodes(String[] nodes, List<List<Integer>> gridClusters) {
        List<Integer> freeNodes = IntStream.range(0, nodes.length).boxed().collect(toList());
        gridClusters.stream().forEach(freeNodes::removeAll);
        return freeNodes;
    }

    private List<Integer> getLargestCluster(List<List<Integer>> gridClusters) {
        return gridClusters.stream().max(Comparator.comparing(Collection::size)).orElse(Collections.emptyList());
    }

    private int getTotalPossibleConnections(List<List<Integer>> gridClusters) {
        return gridClusters.stream().mapToInt(x -> possibleConnections(x.size())).sum();
    }

    private int possibleConnections(int n) {
        return (n + (n - 1)) / 2;
    }

    private int getNumberOfExistingConnections(String[] nodes) {
        int connections = 0;
        for (int i = 0; i < nodes.length; i++) {
            for (int j = i; j < nodes[i].length(); j++) {
                if (isConnected(i, j, nodes)) {
                    connections++;
                }
            }
        }
        return connections;
    }
}
