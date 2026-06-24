package com.wugu.doublecamera.bean.dto;

import java.util.List;

public class MqttInfoDto {
    public String password;
    public List<MqttSubscribe> subscribeTypeDtos;
    public String userName;

    public static class MqttSubscribe {
        public int flag;
        public String topic;
        public int type;
    }
}
