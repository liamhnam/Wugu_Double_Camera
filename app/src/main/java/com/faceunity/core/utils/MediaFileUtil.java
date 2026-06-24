package com.faceunity.core.utils;

import com.google.android.exoplayer2.util.MimeTypes;
import java.util.HashMap;
import java.util.Iterator;

public class MediaFileUtil {
    public static final int FILE_TYPE_3GPP = 23;
    public static final int FILE_TYPE_3GPP2 = 24;
    public static final int FILE_TYPE_AMR = 4;
    public static final int FILE_TYPE_AWB = 5;
    public static final int FILE_TYPE_BMP = 34;
    public static final int FILE_TYPE_GIF = 32;
    public static final int FILE_TYPE_IMY = 13;
    public static final int FILE_TYPE_JPEG = 31;
    public static final int FILE_TYPE_M3U = 41;
    public static final int FILE_TYPE_M4A = 2;
    public static final int FILE_TYPE_M4V = 22;
    public static final int FILE_TYPE_MID = 11;
    public static final int FILE_TYPE_MOV = 26;
    public static final int FILE_TYPE_MP3 = 1;
    public static final int FILE_TYPE_MP4 = 21;
    public static final int FILE_TYPE_OGG = 7;
    public static final int FILE_TYPE_PLS = 42;
    public static final int FILE_TYPE_PNG = 33;
    public static final int FILE_TYPE_SMF = 12;
    public static final int FILE_TYPE_WAV = 3;
    public static final int FILE_TYPE_WBMP = 35;
    public static final int FILE_TYPE_WMA = 6;
    public static final int FILE_TYPE_WMV = 25;
    public static final int FILE_TYPE_WPL = 43;
    private static final int FIRST_AUDIO_FILE_TYPE = 1;
    private static final int FIRST_IMAGE_FILE_TYPE = 31;
    private static final int FIRST_MIDI_FILE_TYPE = 11;
    private static final int FIRST_PLAYLIST_FILE_TYPE = 41;
    private static final int FIRST_VIDEO_FILE_TYPE = 21;
    private static final int LAST_AUDIO_FILE_TYPE = 7;
    private static final int LAST_IMAGE_FILE_TYPE = 35;
    private static final int LAST_MIDI_FILE_TYPE = 13;
    private static final int LAST_PLAYLIST_FILE_TYPE = 43;
    private static final int LAST_VIDEO_FILE_TYPE = 26;
    public static final String UNKNOWN_STRING = "<unknown>";
    public static String sFileExtensions;
    private static HashMap<String, MediaFileType> sFileTypeMap = new HashMap<>();
    private static HashMap<String, Integer> sMimeTypeMap = new HashMap<>();

    public static boolean isAudioFileType(int i) {
        if (i < 1 || i > 7) {
            return i >= 11 && i <= 13;
        }
        return true;
    }

    public static boolean isImageFileType(int i) {
        return i >= 31 && i <= 35;
    }

    public static boolean isPlayListFileType(int i) {
        return i >= 41 && i <= 43;
    }

    public static boolean isVideoFileType(int i) {
        return i >= 21 && i <= 26;
    }

    static class MediaFileType {
        int fileType;
        String mimeType;

        MediaFileType(int i, String str) {
            this.fileType = i;
            this.mimeType = str;
        }
    }

    static {
        addFileType("MP3", 1, MimeTypes.AUDIO_MPEG);
        addFileType("M4A", 2, MimeTypes.AUDIO_MP4);
        addFileType("WAV", 3, "audio/x-wav");
        addFileType("AMR", 4, "audio/amr");
        addFileType("AWB", 5, MimeTypes.AUDIO_AMR_WB);
        addFileType("WMA", 6, "audio/x-ms-wma");
        addFileType("OGG", 7, "application/ogg");
        addFileType("MID", 11, "audio/midi");
        addFileType("XMF", 11, "audio/midi");
        addFileType("RTTTL", 11, "audio/midi");
        addFileType("SMF", 12, "audio/sp-midi");
        addFileType("IMY", 13, "audio/imelody");
        addFileType("MP4", 21, MimeTypes.VIDEO_MP4);
        addFileType("M4V", 22, MimeTypes.VIDEO_MP4);
        addFileType("3GP", 23, MimeTypes.VIDEO_H263);
        addFileType("3GPP", 23, MimeTypes.VIDEO_H263);
        addFileType("3G2", 24, "video/3gpp2");
        addFileType("3GPP2", 24, "video/3gpp2");
        addFileType("WMV", 25, "video/x-ms-wmv");
        addFileType("MOV", 26, "video/mov");
        addFileType("JPG", 31, "image/jpeg");
        addFileType("JPEG", 31, "image/jpeg");
        addFileType("GIF", 32, "image/gif");
        addFileType("PNG", 33, "image/png");
        addFileType("BMP", 34, "image/x-ms-bmp");
        addFileType("WBMP", 35, "image/vnd.wap.wbmp");
        addFileType("M3U", 41, "audio/x-mpegurl");
        addFileType("PLS", 42, "audio/x-scpls");
        addFileType("WPL", 43, "application/vnd.ms-wpl");
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = sFileTypeMap.keySet().iterator();
        while (it.hasNext()) {
            if (sb.length() > 0) {
                sb.append(',');
            }
            sb.append(it.next());
        }
        sFileExtensions = sb.toString();
    }

    static void addFileType(String str, int i, String str2) {
        sFileTypeMap.put(str, new MediaFileType(i, str2));
        sMimeTypeMap.put(str2, new Integer(i));
    }

    public static MediaFileType getFileType(String str) {
        int iLastIndexOf = str.lastIndexOf(".");
        if (iLastIndexOf < 0) {
            return null;
        }
        return sFileTypeMap.get(str.substring(iLastIndexOf + 1).toUpperCase());
    }

    public static boolean isVideoFileType(String str) {
        MediaFileType fileType = getFileType(str);
        if (fileType != null) {
            return isVideoFileType(fileType.fileType);
        }
        return false;
    }

    public static boolean isAudioFileType(String str) {
        MediaFileType fileType = getFileType(str);
        if (fileType != null) {
            return isAudioFileType(fileType.fileType);
        }
        return false;
    }

    public static int getFileTypeForMimeType(String str) {
        Integer num = sMimeTypeMap.get(str);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public static boolean isImageFileType(String str) {
        MediaFileType fileType = getFileType(str);
        if (fileType != null) {
            return isImageFileType(fileType.fileType);
        }
        return false;
    }
}
