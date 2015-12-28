package com.mfq.bean.user;

public enum SignIndex {

    RealName(1),
    Year(2),
    MailStatus(3),
    NewMobile(4),
    FirstPassword(5),
    AutoRegister(6);

    int value;

    SignIndex(int value) {
      this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public SignIndex fromValue(int value){
        for(SignIndex s : SignIndex.values()){
            if(s.getValue() == value){
                return s;
            }
        }
        return null;
    }
}
