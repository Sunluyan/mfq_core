package com.mfq.bean.user;


public enum Gender {
    Unset(0),
    Male(1),
    Female(2);

    int value;

    Gender(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Gender fromValue(int value) {
        for(Gender gender : Gender.values()){
            if(value == gender.getValue()){
                return gender;
            }
        }
        return null;
    }
}
