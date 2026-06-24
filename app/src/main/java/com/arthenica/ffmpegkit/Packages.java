package com.arthenica.ffmpegkit;

import com.google.android.exoplayer2.util.MimeTypes;
import com.p020hp.jipp.model.TrimmingType;
import io.reactivex.annotations.SchedulerSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Packages {
    private static final List<String> supportedExternalLibraries;

    static {
        ArrayList arrayList = new ArrayList();
        supportedExternalLibraries = arrayList;
        arrayList.add("dav1d");
        arrayList.add("fontconfig");
        arrayList.add("freetype");
        arrayList.add("fribidi");
        arrayList.add("gmp");
        arrayList.add("gnutls");
        arrayList.add("kvazaar");
        arrayList.add("mp3lame");
        arrayList.add("libass");
        arrayList.add("iconv");
        arrayList.add("libilbc");
        arrayList.add("libtheora");
        arrayList.add("libvidstab");
        arrayList.add("libvorbis");
        arrayList.add("libvpx");
        arrayList.add("libwebp");
        arrayList.add("libxml2");
        arrayList.add("opencore-amr");
        arrayList.add("openh264");
        arrayList.add("openssl");
        arrayList.add("opus");
        arrayList.add("rubberband");
        arrayList.add("sdl2");
        arrayList.add("shine");
        arrayList.add("snappy");
        arrayList.add("soxr");
        arrayList.add("speex");
        arrayList.add("srt");
        arrayList.add("tesseract");
        arrayList.add("twolame");
        arrayList.add("x264");
        arrayList.add("x265");
        arrayList.add("xvid");
        arrayList.add("zimg");
    }

    public static String getPackageName() {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        List<String> externalLibraries = getExternalLibraries();
        boolean zContains = externalLibraries.contains("speex");
        boolean zContains2 = externalLibraries.contains("fribidi");
        boolean zContains3 = externalLibraries.contains("gnutls");
        boolean zContains4 = externalLibraries.contains("xvid");
        boolean z6 = true;
        boolean z7 = false;
        if (!zContains || !zContains2) {
            if (zContains) {
                z2 = true;
                z = false;
                z4 = false;
            } else {
                if (zContains2) {
                    z = true;
                    z2 = false;
                } else if (zContains4) {
                    if (zContains3) {
                        z4 = true;
                        z = false;
                        z2 = false;
                        z3 = false;
                        z6 = z3;
                        z5 = z6;
                    } else {
                        z5 = true;
                        z = false;
                        z2 = false;
                        z4 = false;
                        z3 = false;
                        z6 = false;
                    }
                } else if (zContains3) {
                    z3 = true;
                    z = false;
                    z2 = false;
                    z4 = false;
                    z6 = false;
                    z5 = z6;
                } else {
                    z = false;
                    z2 = false;
                }
                z4 = z2;
            }
            z3 = z4;
            z6 = z3;
            z5 = z6;
        } else if (zContains4) {
            z = false;
            z2 = false;
            z4 = false;
            z3 = false;
            z5 = false;
        } else {
            z = false;
            z2 = false;
            z4 = false;
            z3 = false;
            z5 = false;
            z7 = true;
            z6 = false;
        }
        return z6 ? (externalLibraries.contains("dav1d") && externalLibraries.contains("fontconfig") && externalLibraries.contains("freetype") && externalLibraries.contains("fribidi") && externalLibraries.contains("gmp") && externalLibraries.contains("gnutls") && externalLibraries.contains("kvazaar") && externalLibraries.contains("mp3lame") && externalLibraries.contains("libass") && externalLibraries.contains("iconv") && externalLibraries.contains("libilbc") && externalLibraries.contains("libtheora") && externalLibraries.contains("libvidstab") && externalLibraries.contains("libvorbis") && externalLibraries.contains("libvpx") && externalLibraries.contains("libwebp") && externalLibraries.contains("libxml2") && externalLibraries.contains("opencore-amr") && externalLibraries.contains("opus") && externalLibraries.contains("shine") && externalLibraries.contains("snappy") && externalLibraries.contains("soxr") && externalLibraries.contains("speex") && externalLibraries.contains("twolame") && externalLibraries.contains("x264") && externalLibraries.contains("x265") && externalLibraries.contains("xvid") && externalLibraries.contains("zimg")) ? "full-gpl" : SchedulerSupport.CUSTOM : z7 ? (externalLibraries.contains("dav1d") && externalLibraries.contains("fontconfig") && externalLibraries.contains("freetype") && externalLibraries.contains("fribidi") && externalLibraries.contains("gmp") && externalLibraries.contains("gnutls") && externalLibraries.contains("kvazaar") && externalLibraries.contains("mp3lame") && externalLibraries.contains("libass") && externalLibraries.contains("iconv") && externalLibraries.contains("libilbc") && externalLibraries.contains("libtheora") && externalLibraries.contains("libvorbis") && externalLibraries.contains("libvpx") && externalLibraries.contains("libwebp") && externalLibraries.contains("libxml2") && externalLibraries.contains("opencore-amr") && externalLibraries.contains("opus") && externalLibraries.contains("shine") && externalLibraries.contains("snappy") && externalLibraries.contains("soxr") && externalLibraries.contains("speex") && externalLibraries.contains("twolame") && externalLibraries.contains("zimg")) ? TrimmingType.full : SchedulerSupport.CUSTOM : z ? (externalLibraries.contains("dav1d") && externalLibraries.contains("fontconfig") && externalLibraries.contains("freetype") && externalLibraries.contains("fribidi") && externalLibraries.contains("kvazaar") && externalLibraries.contains("libass") && externalLibraries.contains("iconv") && externalLibraries.contains("libtheora") && externalLibraries.contains("libvpx") && externalLibraries.contains("libwebp") && externalLibraries.contains("snappy") && externalLibraries.contains("zimg")) ? MimeTypes.BASE_TYPE_VIDEO : SchedulerSupport.CUSTOM : z2 ? (externalLibraries.contains("mp3lame") && externalLibraries.contains("libilbc") && externalLibraries.contains("libvorbis") && externalLibraries.contains("opencore-amr") && externalLibraries.contains("opus") && externalLibraries.contains("shine") && externalLibraries.contains("soxr") && externalLibraries.contains("speex") && externalLibraries.contains("twolame")) ? MimeTypes.BASE_TYPE_AUDIO : SchedulerSupport.CUSTOM : z4 ? (externalLibraries.contains("gmp") && externalLibraries.contains("gnutls") && externalLibraries.contains("libvidstab") && externalLibraries.contains("x264") && externalLibraries.contains("x265") && externalLibraries.contains("xvid")) ? "https-gpl" : SchedulerSupport.CUSTOM : z3 ? (externalLibraries.contains("gmp") && externalLibraries.contains("gnutls")) ? "https" : SchedulerSupport.CUSTOM : z5 ? (externalLibraries.contains("libvidstab") && externalLibraries.contains("x264") && externalLibraries.contains("x265") && externalLibraries.contains("xvid")) ? "min-gpl" : SchedulerSupport.CUSTOM : externalLibraries.size() == 0 ? "min" : SchedulerSupport.CUSTOM;
    }

    public static List<String> getExternalLibraries() {
        String nativeBuildConf = AbiDetect.getNativeBuildConf();
        ArrayList arrayList = new ArrayList();
        for (String str : supportedExternalLibraries) {
            if (nativeBuildConf.contains("enable-" + str) || nativeBuildConf.contains("enable-lib" + str)) {
                arrayList.add(str);
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }
}
