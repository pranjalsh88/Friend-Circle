package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FriendList {
    List<FriendPair> frList;
    int numPeople;

    public int getNumPeople() {
        return numPeople;
    }

    public FriendList(String url, int num) {
        this.frList = new ArrayList<>();
        this.numPeople = num;
        File file = new File(url);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] a = line.split(",");
                FriendPair fp = new FriendPair(Integer.parseInt(a[0]), Integer.parseInt(a[1]));
                frList.add(fp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
