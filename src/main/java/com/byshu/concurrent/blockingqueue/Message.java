package com.byshu.concurrent.blockingqueue;

public class Message {
    private String msg;

    public Message(String str) {
        this.msg = str;
    }

    public String getMsg() {
        return msg;
    }

}
