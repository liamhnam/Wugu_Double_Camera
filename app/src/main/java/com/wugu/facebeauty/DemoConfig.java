package com.wugu.facebeauty;

import android.os.Environment;
import java.io.File;

public class DemoConfig {
    public static final int BG_GREEN_FILTER_FRAME = 1;
    public static final boolean FACE_DELAY_LEAVE_ENABLE = false;
    public static final int HAND_DETECT_WHEN_NO_HAND_NUM = 3;
    public static final boolean IS_SHOW_RESET_BUTTON = false;
    public static String BUNDLE_AI_FACE = "model" + File.separator + "ai_face_processor.bundle";
    public static String BUNDLE_AI_HAND = "model" + File.separator + "ai_hand_processor.bundle";
    public static String BUNDLE_AI_HUMAN = "model" + File.separator + "ai_human_processor.bundle";
    public static String BUNDLE_AI_TONGUE = "graphics" + File.separator + "tongue.bundle";
    public static String BUNDLE_FACE_BEAUTIFICATION = "graphics" + File.separator + "face_beautification.bundle";
    public static String BUNDLE_FACE_MAKEUP = "graphics" + File.separator + "face_makeup.bundle";
    private static String MAKEUP_RESOURCE_DIR = "makeup" + File.separator;
    public static String MAKEUP_RESOURCE_COLOR_SETUP_JSON = MAKEUP_RESOURCE_DIR + "color_setup.json";
    public static String MAKEUP_RESOURCE_JSON_DIR = MAKEUP_RESOURCE_DIR + "config_json" + File.separator;
    public static String MAKEUP_RESOURCE_COMBINATION_BUNDLE_DIR = MAKEUP_RESOURCE_DIR + "combination_bundle" + File.separator;
    public static String MAKEUP_RESOURCE_ITEM_BUNDLE_DIR = MAKEUP_RESOURCE_DIR + "item_bundle" + File.separator;
    public static String BUNDLE_BODY_BEAUTY = "graphics" + File.separator + "body_slim.bundle";
    public static String BUNDLE_ANIMATION_FILTER = "graphics" + File.separator + "fuzzytoonfilter.bundle";
    public static String BUNDLE_HAIR_NORMAL = "hair_seg" + File.separator + "hair_normal.bundle";
    public static String BUNDLE_HAIR_GRADIENT = "hair_seg" + File.separator + "hair_gradient.bundle";
    public static String BUNDLE_LIGHT_MAKEUP = "light_makeup" + File.separator + "light_makeup.bundle";
    public static String BUNDLE_POSTER_CHANGE_FACE = "change_face" + File.separator + "change_face.bundle";
    public static String BUNDLE_BG_SEG_GREEN = "green_screen.bundle";
    public static String BUNDLE_ANTI_ALIASING = "graphics" + File.separator + "fxaa.bundle";
    public static String BUNDLE_BG_SEG_CUSTOM = "bg_segment.bundle";
    public static String BUNDLE_LANDMARKS = "effect" + File.separator + "landmarks.bundle";
    public static int DEVICE_LEVEL = 2;
    public static String BLACK_LIST = "others" + File.separator + "blackList.json";
    public static float FACE_CONFIDENCE_SCORE = 0.95f;
    public static boolean IS_OPEN_LAND_MARK = false;
    public static String DEVICE_NAME = "";
    public static boolean OPEN_FILE_LOG = false;
    public static final String APP_NAME = "KotlinFaceUnityDemo";
    public static String OPEN_FILE_PATH = Environment.getExternalStoragePublicDirectory("") + File.separator + "FaceUnity" + File.separator + APP_NAME + File.separator;
    public static String OPEN_FILE_NAME = "openFile.txt";
    public static int OPEN_FILE_MAX_SIZE = 104857600;
    public static int OPEN_FILES = 100;
    public static boolean OPEN_TIME_PROFILE_LOG = false;
    public static String OPEN_TIME_PROFILE_PATH = Environment.getExternalStoragePublicDirectory("") + File.separator + "FaceUnity" + File.separator + APP_NAME + File.separator;
    public static boolean OPEN_FACE_BEAUTY_TO_FILE = true;
}
