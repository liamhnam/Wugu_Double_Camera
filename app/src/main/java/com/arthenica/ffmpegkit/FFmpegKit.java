package com.arthenica.ffmpegkit;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class FFmpegKit {
    private FFmpegKit() {
    }

    public static FFmpegSession executeWithArguments(String[] strArr) {
        FFmpegSession fFmpegSessionCreate = FFmpegSession.create(strArr);
        FFmpegKitConfig.ffmpegExecute(fFmpegSessionCreate);
        return fFmpegSessionCreate;
    }

    public static FFmpegSession executeWithArgumentsAsync(String[] strArr, FFmpegSessionCompleteCallback fFmpegSessionCompleteCallback) {
        FFmpegSession fFmpegSessionCreate = FFmpegSession.create(strArr, fFmpegSessionCompleteCallback);
        FFmpegKitConfig.asyncFFmpegExecute(fFmpegSessionCreate);
        return fFmpegSessionCreate;
    }

    public static FFmpegSession executeWithArgumentsAsync(String[] strArr, FFmpegSessionCompleteCallback fFmpegSessionCompleteCallback, LogCallback logCallback, StatisticsCallback statisticsCallback) {
        FFmpegSession fFmpegSessionCreate = FFmpegSession.create(strArr, fFmpegSessionCompleteCallback, logCallback, statisticsCallback);
        FFmpegKitConfig.asyncFFmpegExecute(fFmpegSessionCreate);
        return fFmpegSessionCreate;
    }

    public static FFmpegSession executeWithArgumentsAsync(String[] strArr, FFmpegSessionCompleteCallback fFmpegSessionCompleteCallback, ExecutorService executorService) {
        FFmpegSession fFmpegSessionCreate = FFmpegSession.create(strArr, fFmpegSessionCompleteCallback);
        FFmpegKitConfig.asyncFFmpegExecute(fFmpegSessionCreate, executorService);
        return fFmpegSessionCreate;
    }

    public static FFmpegSession executeWithArgumentsAsync(String[] strArr, FFmpegSessionCompleteCallback fFmpegSessionCompleteCallback, LogCallback logCallback, StatisticsCallback statisticsCallback, ExecutorService executorService) {
        FFmpegSession fFmpegSessionCreate = FFmpegSession.create(strArr, fFmpegSessionCompleteCallback, logCallback, statisticsCallback);
        FFmpegKitConfig.asyncFFmpegExecute(fFmpegSessionCreate, executorService);
        return fFmpegSessionCreate;
    }

    public static FFmpegSession execute(String str) {
        return executeWithArguments(FFmpegKitConfig.parseArguments(str));
    }

    public static FFmpegSession executeAsync(String str, FFmpegSessionCompleteCallback fFmpegSessionCompleteCallback) {
        return executeWithArgumentsAsync(FFmpegKitConfig.parseArguments(str), fFmpegSessionCompleteCallback);
    }

    public static FFmpegSession executeAsync(String str, FFmpegSessionCompleteCallback fFmpegSessionCompleteCallback, LogCallback logCallback, StatisticsCallback statisticsCallback) {
        return executeWithArgumentsAsync(FFmpegKitConfig.parseArguments(str), fFmpegSessionCompleteCallback, logCallback, statisticsCallback);
    }

    public static FFmpegSession executeAsync(String str, FFmpegSessionCompleteCallback fFmpegSessionCompleteCallback, ExecutorService executorService) {
        FFmpegSession fFmpegSessionCreate = FFmpegSession.create(FFmpegKitConfig.parseArguments(str), fFmpegSessionCompleteCallback);
        FFmpegKitConfig.asyncFFmpegExecute(fFmpegSessionCreate, executorService);
        return fFmpegSessionCreate;
    }

    public static FFmpegSession executeAsync(String str, FFmpegSessionCompleteCallback fFmpegSessionCompleteCallback, LogCallback logCallback, StatisticsCallback statisticsCallback, ExecutorService executorService) {
        FFmpegSession fFmpegSessionCreate = FFmpegSession.create(FFmpegKitConfig.parseArguments(str), fFmpegSessionCompleteCallback, logCallback, statisticsCallback);
        FFmpegKitConfig.asyncFFmpegExecute(fFmpegSessionCreate, executorService);
        return fFmpegSessionCreate;
    }

    public static void cancel() {
        FFmpegKitConfig.nativeFFmpegCancel(0L);
    }

    public static void cancel(long j) {
        FFmpegKitConfig.nativeFFmpegCancel(j);
    }

    public static List<FFmpegSession> listSessions() {
        return FFmpegKitConfig.getFFmpegSessions();
    }
}
