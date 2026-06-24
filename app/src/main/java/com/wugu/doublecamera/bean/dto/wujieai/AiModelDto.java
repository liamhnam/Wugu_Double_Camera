package com.wugu.doublecamera.bean.dto.wujieai;

import java.util.List;

public class AiModelDto {
    public List<ModelDetail> lists;
    public int total;

    public static class ModelDetail {
        public List<String> example;
        public String model_id;
        public String name;
        public List<TypeStyles> styles;
        public int type;
    }

    public static class TypeStyles {

        public String f2447id;
        public String name;
    }
}
