package com.brother.sdk.common.socket.scan;

import com.brother.sdk.common.device.MediaSize;
import com.brother.sdk.common.socket.scan.scancommand.ScanCommandParameters;

public class ScanImage {
    public ImageCornerInfo CornerInfo;
    public byte[] Data;
    public ScanCommandParameters.ScanImageDataFormat Format;
    public int Height;
    public int LineCount;
    public int PageIndex;
    public boolean ReverseOrder;
    public int Stride;
    public int Width;
    public MediaSize documentSize;

    public static class ImageCornerInfo {
        public int LeftTopX = 0;
        public int LeftTopY = 0;
        public int LeftBottomX = 0;
        public int LeftBottomY = 0;
        public int RightTopX = 0;
        public int RightTopY = 0;
        public int RightBottomX = 0;
        public int RightBottomY = 0;
    }

    public interface ScanImageListener {
        void onScanImageRead(ScanImage scanImage);
    }
}
