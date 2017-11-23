package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        FriendList friendList = new FriendList("/Users/brtjram/Documents/friends.txt", 12);
        List<List<Integer>> list = formGroupsDFS(friendList, friendList.getNumPeople());
        System.out.println(list);
        System.out.println(getMin(friendList, friendList.getNumPeople()));

    }
    public static List<List<Integer>> formGroups(FriendList fL, int n) {
        List<List<Integer>> list = new ArrayList<>();
        Map<Integer, List<Integer>> map = getMap(fL);
        boolean[] visited = new boolean[n+1];
        for (int num: map.keySet()) {
            if(!visited[num]) {
                List<Integer> sub = bfs(num, visited, map);
                list.add(sub);
            }
        }
        return list;
    }

    public static List<Integer> bfs(int num, boolean[] visited, Map<Integer, List<Integer>> map) {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> sub = new ArrayList<>();
        queue.offer(num);
        visited[num] = true;
        while (!queue.isEmpty()) {
            int i = queue.poll();
            sub.add(i);
            List<Integer> temp = map.get(i);
            for(int j: temp) {
                if(!visited[j]) {
                    queue.offer(j);
                    visited[j] = true;
                }
            }
        }
        return sub;
    }
    public static List<List<Integer>> formGroupsDFS(FriendList fL, int n) {
        Map<Integer, List<Integer>> map = getMap(fL);
        List<List<Integer>> list = new ArrayList<>();
        boolean[] visited = new boolean[n+1];
        for(int num: map.keySet()) {
            if(!visited[num]) {
                List<Integer> sub = new ArrayList<>();
                dfs(map, num, sub, visited);
                list.add(sub);
            }
        }
        return list;
    }

    public static void dfs(Map<Integer, List<Integer>> map, int i, List<Integer> sub, boolean[] visited) {
        if(visited[i])
            return;
        sub.add(i);
        visited[i] = true;
        for(int num: map.get(i)) {
            dfs(map, num, sub, visited);
        }
    }

    public static Map<Integer, List<Integer>> getMap(FriendList friendList) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (FriendPair fP : friendList.frList) {
            if(!map.containsKey(fP.one))
                map.put(fP.one, new ArrayList<>());
            map.get(fP.one).add(fP.two);
            if(!map.containsKey(fP.two))
                map.put(fP.two, new ArrayList<>());
            map.get(fP.two).add(fP.one);
        }
        return map;
    }

    public static int getMin(FriendList fL, int n) {
        Map<Integer, List<Integer>> map = getMap(fL);
        boolean[] visited = new boolean[n+1];
        int min = Integer.MAX_VALUE;
        for (int num: map.keySet()) {
            if(!visited[num]) {
                min = Math.min(min, bfs(num, visited, map).size());

            }
        }
        return min;
    }
}
