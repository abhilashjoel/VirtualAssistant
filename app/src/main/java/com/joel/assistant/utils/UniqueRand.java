package com.joel.assistant.utils;

import java.util.ArrayList;

/**
 * Created by Joel on 6/9/2016.
 */
public class UniqueRand {
    ArrayList<Integer> list;
    int def = 0;

    public UniqueRand(int size, int def){
        int i;
        this.def = def;
        list = new ArrayList<Integer>();
        for(i = 0 ; i < size; i++){
            list.add(i);
        }
    }

    public int getNext(){
        if(list.size() <= 0)
            return -1;
        int r = RandomGenerator.get(list.size());
        int n = list.get(r);
        list.remove(r);
        return n;
    }
}
