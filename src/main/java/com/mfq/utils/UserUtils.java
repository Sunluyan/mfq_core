package com.mfq.utils;

import javafx.collections.transformation.SortedList;
import org.apache.commons.collections.ListUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzhiguo1 on 16/1/13.
 */
public class UserUtils {
    private static final String[] keys = {"a","b","c","d","e","f","g","h","i","j","k","l",
            "m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    private static final int length = keys.length;

    /**
     * 生成邀请码 7位字符 分大小写
     * @param uid
     * @return
     */
    public static String makeInviteCode(long uid){
        StringBuffer inviteCode = new StringBuffer();
        for(int i = 0;i<6;i++){
            inviteCode.append(randomWord());
            int uidLastIndex = (int)uid%10/2;
            if(i == uidLastIndex){
                if(uidLastIndex/3 == 3){
                    inviteCode.append(keys[uidLastIndex]);
                }else{
                    inviteCode.append(keys[uidLastIndex].toUpperCase());
                }
            }
        }
        return inviteCode.toString();
    }
    public static String randomWord(){
        double random = Math.random();
        int index = (int)Math.floor(length*random);
        String word = keys[index];
        if(index%2 == 0){
            word = word.toUpperCase();
        }
        return word;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            String code = makeInviteCode(2798);
            System.out.println(code);
        }
    }
}
