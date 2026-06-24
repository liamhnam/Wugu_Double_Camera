package com.arthenica.ffmpegkit;

import java.util.List;
import java.util.concurrent.ExecutorService;
import org.eclipse.paho.android.service.MqttServiceConstants;

public class FFprobeKit {
    private FFprobeKit() {
    }

    private static String[] defaultGetMediaInformationCommandArguments(String str) {
        return new String[]{"-v", MqttServiceConstants.TRACE_ERROR, "-hide_banner", "-print_format", "json", "-show_format", "-show_streams", "-show_chapters", "-i", str};
    }

    public static FFprobeSession executeWithArguments(String[] strArr) {
        FFprobeSession fFprobeSessionCreate = FFprobeSession.create(strArr);
        FFmpegKitConfig.ffprobeExecute(fFprobeSessionCreate);
        return fFprobeSessionCreate;
    }

    public static FFprobeSession executeWithArgumentsAsync(String[] strArr, FFprobeSessionCompleteCallback fFprobeSessionCompleteCallback) {
        FFprobeSession fFprobeSessionCreate = FFprobeSession.create(strArr, fFprobeSessionCompleteCallback);
        FFmpegKitConfig.asyncFFprobeExecute(fFprobeSessionCreate);
        return fFprobeSessionCreate;
    }

    public static FFprobeSession executeWithArgumentsAsync(String[] strArr, FFprobeSessionCompleteCallback fFprobeSessionCompleteCallback, LogCallback logCallback) {
        FFprobeSession fFprobeSessionCreate = FFprobeSession.create(strArr, fFprobeSessionCompleteCallback, logCallback);
        FFmpegKitConfig.asyncFFprobeExecute(fFprobeSessionCreate);
        return fFprobeSessionCreate;
    }

    public static FFprobeSession executeWithArgumentsAsync(String[] strArr, FFprobeSessionCompleteCallback fFprobeSessionCompleteCallback, ExecutorService executorService) {
        FFprobeSession fFprobeSessionCreate = FFprobeSession.create(strArr, fFprobeSessionCompleteCallback);
        FFmpegKitConfig.asyncFFprobeExecute(fFprobeSessionCreate, executorService);
        return fFprobeSessionCreate;
    }

    public static FFprobeSession executeWithArgumentsAsync(String[] strArr, FFprobeSessionCompleteCallback fFprobeSessionCompleteCallback, LogCallback logCallback, ExecutorService executorService) {
        FFprobeSession fFprobeSessionCreate = FFprobeSession.create(strArr, fFprobeSessionCompleteCallback, logCallback);
        FFmpegKitConfig.asyncFFprobeExecute(fFprobeSessionCreate, executorService);
        return fFprobeSessionCreate;
    }

    public static FFprobeSession execute(String str) {
        return executeWithArguments(FFmpegKitConfig.parseArguments(str));
    }

    public static FFprobeSession executeAsync(String str, FFprobeSessionCompleteCallback fFprobeSessionCompleteCallback) {
        return executeWithArgumentsAsync(FFmpegKitConfig.parseArguments(str), fFprobeSessionCompleteCallback);
    }

    public static FFprobeSession executeAsync(String str, FFprobeSessionCompleteCallback fFprobeSessionCompleteCallback, LogCallback logCallback) {
        return executeWithArgumentsAsync(FFmpegKitConfig.parseArguments(str), fFprobeSessionCompleteCallback, logCallback);
    }

    public static FFprobeSession executeAsync(String str, FFprobeSessionCompleteCallback fFprobeSessionCompleteCallback, ExecutorService executorService) {
        FFprobeSession fFprobeSessionCreate = FFprobeSession.create(FFmpegKitConfig.parseArguments(str), fFprobeSessionCompleteCallback);
        FFmpegKitConfig.asyncFFprobeExecute(fFprobeSessionCreate, executorService);
        return fFprobeSessionCreate;
    }

    public static FFprobeSession executeAsync(String str, FFprobeSessionCompleteCallback fFprobeSessionCompleteCallback, LogCallback logCallback, ExecutorService executorService) {
        FFprobeSession fFprobeSessionCreate = FFprobeSession.create(FFmpegKitConfig.parseArguments(str), fFprobeSessionCompleteCallback, logCallback);
        FFmpegKitConfig.asyncFFprobeExecute(fFprobeSessionCreate, executorService);
        return fFprobeSessionCreate;
    }

    public static MediaInformationSession getMediaInformation(String str) {
        MediaInformationSession mediaInformationSessionCreate = MediaInformationSession.create(defaultGetMediaInformationCommandArguments(str));
        FFmpegKitConfig.getMediaInformationExecute(mediaInformationSessionCreate, 5000);
        return mediaInformationSessionCreate;
    }

    public static MediaInformationSession getMediaInformation(String str, int i) {
        MediaInformationSession mediaInformationSessionCreate = MediaInformationSession.create(defaultGetMediaInformationCommandArguments(str));
        FFmpegKitConfig.getMediaInformationExecute(mediaInformationSessionCreate, i);
        return mediaInformationSessionCreate;
    }

    public static MediaInformationSession getMediaInformationAsync(String str, MediaInformationSessionCompleteCallback mediaInformationSessionCompleteCallback) {
        MediaInformationSession mediaInformationSessionCreate = MediaInformationSession.create(defaultGetMediaInformationCommandArguments(str), mediaInformationSessionCompleteCallback);
        FFmpegKitConfig.asyncGetMediaInformationExecute(mediaInformationSessionCreate, 5000);
        return mediaInformationSessionCreate;
    }

    public static MediaInformationSession getMediaInformationAsync(String str, MediaInformationSessionCompleteCallback mediaInformationSessionCompleteCallback, LogCallback logCallback, int i) {
        MediaInformationSession mediaInformationSessionCreate = MediaInformationSession.create(defaultGetMediaInformationCommandArguments(str), mediaInformationSessionCompleteCallback, logCallback);
        FFmpegKitConfig.asyncGetMediaInformationExecute(mediaInformationSessionCreate, i);
        return mediaInformationSessionCreate;
    }

    public static MediaInformationSession getMediaInformationAsync(String str, MediaInformationSessionCompleteCallback mediaInformationSessionCompleteCallback, ExecutorService executorService) {
        MediaInformationSession mediaInformationSessionCreate = MediaInformationSession.create(defaultGetMediaInformationCommandArguments(str), mediaInformationSessionCompleteCallback);
        FFmpegKitConfig.asyncGetMediaInformationExecute(mediaInformationSessionCreate, executorService, 5000);
        return mediaInformationSessionCreate;
    }

    public static MediaInformationSession getMediaInformationAsync(String str, MediaInformationSessionCompleteCallback mediaInformationSessionCompleteCallback, LogCallback logCallback, ExecutorService executorService, int i) {
        MediaInformationSession mediaInformationSessionCreate = MediaInformationSession.create(defaultGetMediaInformationCommandArguments(str), mediaInformationSessionCompleteCallback, logCallback);
        FFmpegKitConfig.asyncGetMediaInformationExecute(mediaInformationSessionCreate, executorService, i);
        return mediaInformationSessionCreate;
    }

    public static MediaInformationSession getMediaInformationFromCommand(String str) {
        MediaInformationSession mediaInformationSessionCreate = MediaInformationSession.create(FFmpegKitConfig.parseArguments(str));
        FFmpegKitConfig.getMediaInformationExecute(mediaInformationSessionCreate, 5000);
        return mediaInformationSessionCreate;
    }

    public static MediaInformationSession getMediaInformationFromCommandAsync(String str, MediaInformationSessionCompleteCallback mediaInformationSessionCompleteCallback, LogCallback logCallback, int i) {
        return getMediaInformationFromCommandArgumentsAsync(FFmpegKitConfig.parseArguments(str), mediaInformationSessionCompleteCallback, logCallback, i);
    }

    private static MediaInformationSession getMediaInformationFromCommandArgumentsAsync(String[] strArr, MediaInformationSessionCompleteCallback mediaInformationSessionCompleteCallback, LogCallback logCallback, int i) {
        MediaInformationSession mediaInformationSessionCreate = MediaInformationSession.create(strArr, mediaInformationSessionCompleteCallback, logCallback);
        FFmpegKitConfig.asyncGetMediaInformationExecute(mediaInformationSessionCreate, i);
        return mediaInformationSessionCreate;
    }

    public static List<FFprobeSession> listFFprobeSessions() {
        return FFmpegKitConfig.getFFprobeSessions();
    }

    public static List<MediaInformationSession> listMediaInformationSessions() {
        return FFmpegKitConfig.getMediaInformationSessions();
    }
}
