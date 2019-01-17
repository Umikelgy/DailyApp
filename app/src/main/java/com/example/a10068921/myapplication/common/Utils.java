package com.example.a10068921.myapplication.common;


import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 10068921(LgyTT)
 * @description:
 * @date 2019/1/17 15:36
 **/
public class Utils {
    private final static String ALLKEYS="~·！@#￥%……&*（）—+《》？，。、；：’‘“”【】{}`~!$%^&*()_+-={}[];:''\",./<>?";
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<String> getList(String msg){
        List<String> result=new ArrayList<>();
        Set<Integer> indexSet=new HashSet<>();
        int t;
        for(char c:ALLKEYS.toCharArray()){
            t=msg.indexOf(c);
            while(t!=-1&&t!=msg.length()){
                indexSet.add(t);
                t=msg.indexOf(c,t+1);
            }
        }
        /**排序*/
        List<Integer> endIndex=indexSet.stream().sorted().collect(Collectors.toList());
        /**
         * 截取字符串开始索引，因为要删掉符号，方便遍历就使用-1+1的方式
         * */
        indexSet.add(-1);
        List<Integer> startIndex=indexSet.stream().sorted().collect(Collectors.toList());
        int i;
        for(i=0;i<endIndex.size();i++){
            result.add(msg.substring(startIndex.get(i)+1,endIndex.get(i)));
        }
        result.add(msg.substring(i));
        return result;
    }
 }
