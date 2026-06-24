package com.epson.isv.eprinterdriver.Ctrl;

class SearchJob {
    int searchJobStatus = 0;

    public static final class SearchJobStatus {
        public static final int SearchJob_Init = 0;
        public static final int SearchJob_Search = 1;
        public static final int SearchJob_UserCancel = 2;
        public static final int SearchJob_finish = 3;
    }

    public void resetSearchJob() {
        this.searchJobStatus = 0;
    }

    public int getSearchJobStatus() {
        return this.searchJobStatus;
    }

    public void setSearchJobStatus(int i) {
        this.searchJobStatus = i;
    }
}
