package Leetcode;

import java.util.*;
import java.util.stream.IntStream;

public class Leetcode_451 {
    public static String frequencySort(String s) {
        Map<Character, Integer> frequencyForNum = new HashMap<>();
        List<Character>[] buckets = new ArrayList[s.length() + 1];
        for (char c : s.toCharArray()) {
            frequencyForNum.put(c, frequencyForNum.getOrDefault(c, 0) + 1);
        }
        for (Character character : frequencyForNum.keySet()) {
            Integer frequency = frequencyForNum.get(character);
            if (buckets[frequency] == null) {
                buckets[frequency] = new ArrayList<>();
            }
            buckets[frequency].add(character);
        }
        StringBuffer sb=new StringBuffer();
        for (int pos = buckets.length - 1; pos >= 0; pos--) {
            if (buckets[pos] != null) {
                for (Character bucket : buckets[pos]) {
                    for (int i = 0; i < pos; i++) {
                        sb.append(bucket);
                    }
                }
            }
        }
        return sb.toString();
    }

    public static String frequencySortTwo(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        pq.addAll(map.entrySet());
        StringBuilder sb =new StringBuilder();
        while (!pq.isEmpty()) {
        Map.Entry e =pq.poll();
        for (int i = 0; i < (int)e.getValue(); i++) {
            sb.append(e.getKey());
        }
    }
        return sb.toString();
}

    public static String frequencySortThree(String s) {
        Map<Character, Integer> map = new HashMap<>();
        IntStream.range(0, s.length()).forEach(i -> map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1));
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        pq.addAll(map.entrySet());
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Map.Entry e = pq.poll();
            IntStream.range(0, (int) e.getValue()).forEach(i -> sb.append(e.getKey()));
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        System.out.println(frequencySortThree("agagGgfaogaldgfietrovanltjkqwoeidfjqwljladsfqpadskmvajfqo33"));
    }
}
