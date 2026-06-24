package com.wugu.doublecamera.bean.dto;

public class CutoutDto {
    public CutoutData data;
    public String message;
    public int status;

    public static class CutoutData {
        public String completed_at;
        public String created_at;
        public String image;
        public String mask;
        public int output_type;
        public String processed_at;
        public int progress;
        public int state;
        public String task_id;
    }
}
