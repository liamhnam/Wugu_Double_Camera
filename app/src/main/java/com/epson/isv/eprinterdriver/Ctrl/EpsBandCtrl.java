package com.epson.isv.eprinterdriver.Ctrl;

class EpsBandCtrl {
    private int bandCmd;

    public static final class Command {
        public static final int BAND_CMD_ENDPAGE = 65538;
        public static final int BAND_CMD_STARTPAGE = 65537;
    }

    public EpsBandCtrl(int i) {
        this.bandCmd = i;
    }

    public int getBandCmd() {
        return this.bandCmd;
    }
}
