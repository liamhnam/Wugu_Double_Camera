package com.wugu.doublecamera.database.dbSheet;

public class FileMd5Entity {
    private Long _id;
    private String fileMD5;
    private String filePath;
    private int fileType;

    public FileMd5Entity(Long l, String str, String str2, int i) {
        this._id = l;
        this.filePath = str;
        this.fileMD5 = str2;
        this.fileType = i;
    }

    public FileMd5Entity() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long l) {
        this._id = l;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String str) {
        this.filePath = str;
    }

    public String getFileMD5() {
        return this.fileMD5;
    }

    public void setFileMD5(String str) {
        this.fileMD5 = str;
    }

    public int getFileType() {
        return this.fileType;
    }

    public void setFileType(int i) {
        this.fileType = i;
    }
}
