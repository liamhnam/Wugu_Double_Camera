package p000a.p001a.p002a.p007e.p008a;

import com.hiti.usb.utility.FileUtility;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class C0019b {

    private static FTPClient f102a;

    private static FileOutputStream f103b;

    public static EnumC0018a m89a(String str, String str2, String str3, String str4, String str5, String str6) {
        f102a = new FTPClient();
        EnumC0018a enumC0018a = EnumC0018a.NON;
        try {
            try {
                try {
                    EnumC0018a enumC0018a2 = EnumC0018a.ERROR_OPEN_CONNECTION;
                    f102a.connect(str, Integer.valueOf(str2).intValue());
                    EnumC0018a enumC0018a3 = EnumC0018a.NON;
                    EnumC0018a enumC0018a4 = EnumC0018a.ERROR_USER;
                    f102a.login(str3, str4);
                    if (!FTPReply.isPositiveCompletion(f102a.getReplyCode())) {
                        f102a.disconnect();
                        try {
                            FileOutputStream fileOutputStream = f103b;
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                                f103b = null;
                            }
                            EnumC0018a enumC0018a5 = EnumC0018a.ERROR_CLOSE_CONNECTION;
                            f102a.logout();
                            f102a.disconnect();
                            f102a = null;
                            EnumC0018a enumC0018a6 = EnumC0018a.NON;
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        return enumC0018a4;
                    }
                    EnumC0018a enumC0018a7 = EnumC0018a.NON;
                    EnumC0018a enumC0018a8 = EnumC0018a.ERROR_SAVE_FILE;
                    f102a.setFileType(2);
                    f102a.enterLocalPassiveMode();
                    String strGetFileName = FileUtility.GetFileName(str5);
                    String strGetFolderFullPath = FileUtility.GetFolderFullPath(str5.replace("ftp://" + str + MqttTopic.TOPIC_LEVEL_SEPARATOR, ""));
                    for (FTPFile fTPFile : f102a.listFiles(strGetFolderFullPath)) {
                        if (fTPFile.getName().equals(strGetFileName)) {
                            f103b = new FileOutputStream(new File(str6));
                            f102a.retrieveFile(strGetFolderFullPath + MqttTopic.TOPIC_LEVEL_SEPARATOR + fTPFile.getName(), f103b);
                        }
                    }
                    EnumC0018a enumC0018a9 = EnumC0018a.NON;
                    try {
                        FileOutputStream fileOutputStream2 = f103b;
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                            f103b = null;
                        }
                        EnumC0018a enumC0018a10 = EnumC0018a.ERROR_CLOSE_CONNECTION;
                        f102a.logout();
                        f102a.disconnect();
                        f102a = null;
                        return EnumC0018a.NON;
                    } catch (IOException e3) {
                        e3.printStackTrace();
                        return enumC0018a9;
                    } catch (Exception e4) {
                        e4.printStackTrace();
                        return enumC0018a9;
                    }
                } catch (Throwable th) {
                    try {
                        FileOutputStream fileOutputStream3 = f103b;
                        if (fileOutputStream3 != null) {
                            fileOutputStream3.close();
                            f103b = null;
                        }
                        EnumC0018a enumC0018a11 = EnumC0018a.ERROR_CLOSE_CONNECTION;
                        f102a.logout();
                        f102a.disconnect();
                        f102a = null;
                        EnumC0018a enumC0018a12 = EnumC0018a.NON;
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    } catch (Exception e6) {
                        e6.printStackTrace();
                    }
                    throw th;
                }
            } catch (SocketException e7) {
                e7.printStackTrace();
                try {
                    FileOutputStream fileOutputStream4 = f103b;
                    if (fileOutputStream4 != null) {
                        fileOutputStream4.close();
                        f103b = null;
                    }
                    EnumC0018a enumC0018a13 = EnumC0018a.ERROR_CLOSE_CONNECTION;
                    f102a.logout();
                    f102a.disconnect();
                    f102a = null;
                    EnumC0018a enumC0018a14 = EnumC0018a.NON;
                } catch (IOException e8) {
                    e8.printStackTrace();
                } catch (Exception e9) {
                    e9.printStackTrace();
                }
                return enumC0018a;
            } catch (Exception e10) {
                e10.printStackTrace();
                try {
                    FileOutputStream fileOutputStream5 = f103b;
                    if (fileOutputStream5 != null) {
                        fileOutputStream5.close();
                        f103b = null;
                    }
                    EnumC0018a enumC0018a15 = EnumC0018a.ERROR_CLOSE_CONNECTION;
                    f102a.logout();
                    f102a.disconnect();
                    f102a = null;
                    EnumC0018a enumC0018a16 = EnumC0018a.NON;
                } catch (IOException e11) {
                    e11.printStackTrace();
                } catch (Exception e12) {
                    e12.printStackTrace();
                }
                return enumC0018a;
            }
        } catch (FileNotFoundException e13) {
            e13.printStackTrace();
            try {
                FileOutputStream fileOutputStream6 = f103b;
                if (fileOutputStream6 != null) {
                    fileOutputStream6.close();
                    f103b = null;
                }
                EnumC0018a enumC0018a17 = EnumC0018a.ERROR_CLOSE_CONNECTION;
                f102a.logout();
                f102a.disconnect();
                f102a = null;
                EnumC0018a enumC0018a18 = EnumC0018a.NON;
            } catch (IOException e14) {
                e14.printStackTrace();
            } catch (Exception e15) {
                e15.printStackTrace();
            }
            return enumC0018a;
        } catch (IOException e16) {
            e16.printStackTrace();
            try {
                FileOutputStream fileOutputStream7 = f103b;
                if (fileOutputStream7 != null) {
                    fileOutputStream7.close();
                    f103b = null;
                }
                EnumC0018a enumC0018a19 = EnumC0018a.ERROR_CLOSE_CONNECTION;
                f102a.logout();
                f102a.disconnect();
                f102a = null;
                EnumC0018a enumC0018a20 = EnumC0018a.NON;
            } catch (IOException e17) {
                e17.printStackTrace();
            } catch (Exception e18) {
                e18.printStackTrace();
            }
            return enumC0018a;
        }
    }

    public static void m90a() {
        try {
            FileOutputStream fileOutputStream = f103b;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            FTPClient fTPClient = f102a;
            if (fTPClient == null || !fTPClient.isConnected()) {
                return;
            }
            f102a.logout();
            f102a.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
