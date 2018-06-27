package com.mz.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class BallUtil {

    /**
     * 随机产生红球
     * @return
     */
    public static List<String> randomRed(){
        // 04,05,07,11,12,25  33个红球中随机产生 6个红球1个蓝球
        // set 集合 : 不能重复  并且有序
        Set<Integer> sets = new TreeSet();

        while(sets.size() < 6){
            int i = new Random().nextInt(33) + 1;
            sets.add(i);
        }

        List<String> balls = new ArrayList<>();
        for (Integer i : sets){
            balls.add(String.format("%02d", i));
        }

        return balls;
    }

    /**
     * 随机产生蓝球
     * @return
     */
    public static String randomBlue(){
        int j = new Random().nextInt(16) + 1;
        String blue = String.format("%02d", j);
        return blue;
    }
}
