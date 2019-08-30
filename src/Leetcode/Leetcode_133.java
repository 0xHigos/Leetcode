package Leetcode;

import Leetcode.base.Node;

import java.util.ArrayList;
import java.util.HashMap;

public class Leetcode_133 {
    public Node cloneGraph(Node node) {
        return DFS(node, new HashMap<Node, Node>());
    }

    private Node DFS(Node node, HashMap<Node, Node> map) {
        if (map.containsKey(node)) {
            return map.get(node);
        }
        Node newNode = new Node(node.val, new ArrayList<>());
        map.put(node, newNode);
        for (Node neighbor : node.neighbors) {
            newNode.neighbors.add(DFS(neighbor, map));
        }
        return newNode;
    }
}
