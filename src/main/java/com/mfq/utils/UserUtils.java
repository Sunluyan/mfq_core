package com.mfq.utils;

/**
 * Created by liuzhiguo1 on 16/1/13.
 */
public class UserUtils {
    private static final String[] keys = {"a","b","c","d","e","f","g","h","i","j","k","l",
            "m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

    public static String makeInviteCode(long uid){

        return null;
    }
    public static int randomWord(){
        double random = Math.random();
        int length = keys.length;
        int index = (int)Math.floor(length*random);
        return index;
    }

    public static void main(String[] args) {
        int index = randomWord();
        System.out.println(index);
    }

}
