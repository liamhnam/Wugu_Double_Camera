package com.brother.sdk.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileUtil {
    public static void copyFile(File file, File file2) throws Throwable {
        FileChannel fileChannel;
        FileChannel channel = null;
        try {
            FileChannel channel2 = new FileInputStream(file2).getChannel();
            try {
                channel = new FileOutputStream(file).getChannel();
                channel2.transferTo(0L, channel2.size(), channel);
                closeFileChannel(channel2);
                closeFileChannel(channel);
            } catch (IOException e) {
                e = e;
                FileChannel fileChannel2 = channel;
                channel = channel2;
                fileChannel = fileChannel2;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    closeFileChannel(channel);
                    closeFileChannel(fileChannel);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                FileChannel fileChannel3 = channel;
                channel = channel2;
                fileChannel = fileChannel3;
                closeFileChannel(channel);
                closeFileChannel(fileChannel);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            fileChannel = null;
        } catch (Throwable th3) {
            th = th3;
            fileChannel = null;
        }
    }

    private static void closeFileChannel(FileChannel fileChannel) {
        if (fileChannel != null) {
            try {
                fileChannel.close();
            } catch (IOException unused) {
            }
        }
    }
}
