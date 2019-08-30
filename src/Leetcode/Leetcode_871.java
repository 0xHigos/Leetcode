package Leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leetcode_871 {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int[] nums = new int[rooms.size()];
        LinkedList<Integer> queue =new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int num =queue.poll();
            if(nums[num] ==1)
                continue;
            else
                nums[num] =1;
            List<Integer> list = rooms.get(num);
            for (int i = 0; i < list.size(); i++) {
                if (!queue.contains(list.get(i)))
                    queue.offer(list.get(i));
            }
        }
        for (int num : nums)
            if(num == 1)
                return false;
        return true;
    }

    public boolean canVisitAllRooms2(List<List<Integer>> rooms) {
        boolean[] visit = new boolean[rooms.size()];
        DFS(rooms, 0, visit);
        for (boolean b : visit) {
            if(!b)
                return false;
        }
        return true;
    }

    private void DFS(List<List<Integer>> rooms, int i, boolean[] visit) {
        visit[i] =true;
        List<Integer> list = rooms.get(i);
        for (Integer integer : list) {
            if(!visit[integer])
                DFS(rooms, integer, visit);
        }
    }

}
