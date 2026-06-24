package com.epson.isv.eprinterdriver.Ctrl;

import android.content.Context;
import android.content.Intent;
import com.epson.isv.eprinterdriver.Common.ServiceDefine;
import com.epson.isv.eprinterdriver.Common.ServiceIntent;

class ServiceJobStateNty {
    ServiceJobStateNty() {
    }

    public static void SearchJobNotify(Context context, SearchJobState searchJobState) {
        Intent intent = new Intent(ServiceIntent.TargetComponentName);
        intent.putExtra(ServiceDefine.TaskType, 2);
        int i = searchJobState.eventType;
        if (i == 0) {
            intent.putExtra(ServiceDefine.EventType, 131073);
        } else if (i == 1) {
            intent.putExtra(ServiceDefine.EventType, 131074);
            intent.putExtra(ServiceDefine.EpsPrinter, searchJobState.getRetEpsPrinter());
        } else if (i == 2) {
            intent.putExtra(ServiceDefine.EventType, 131075);
            int stopFactor = searchJobState.getStopFactor();
            if (stopFactor == 0) {
                intent.putExtra(ServiceDefine.SearchFinishReason, 262145);
            } else if (stopFactor == 1) {
                intent.putExtra(ServiceDefine.SearchFinishReason, 262146);
            } else if (stopFactor == 2) {
                intent.putExtra(ServiceDefine.SearchFinishReason, 262147);
                intent.putExtra(ServiceDefine.ErrorCode, ErrorCodeConvert.getErrorCodeFromRuntimeError(searchJobState.getErrorCode()));
            }
        }
        context.sendBroadcast(intent);
    }

    public static void PrintJobNotify(Context context, PrintJobState printJobState, int i) {
        Intent intent = new Intent(ServiceIntent.TargetComponentName);
        intent.putExtra(ServiceDefine.TaskType, 1);
        intent.putExtra(ServiceDefine.PrintJobId, i);
        switch (printJobState.getEventType()) {
            case 0:
                intent.putExtra(ServiceDefine.EventType, 65537);
                break;
            case 1:
                intent.putExtra(ServiceDefine.EventType, 65538);
                intent.putExtra(ServiceDefine.PageNumAll, printJobState.getAllPageNum());
                intent.putExtra(ServiceDefine.PageNumFinished, printJobState.getFinishedPageNum());
                break;
            case 2:
                intent.putExtra(ServiceDefine.EventType, 65539);
                intent.putExtra(ServiceDefine.PageNumAll, printJobState.getAllPageNum());
                intent.putExtra(ServiceDefine.PageNumCurrent, printJobState.getCurrentPageNum());
                if (printJobState.getPauseFactor() == 0) {
                    intent.putExtra(ServiceDefine.PauseReason, 65537);
                    intent.putExtra(ServiceDefine.ErrorCode, printJobState.getErrorCode());
                }
                break;
            case 3:
                intent.putExtra(ServiceDefine.EventType, 65540);
                intent.putExtra(ServiceDefine.PageNumAll, printJobState.getAllPageNum());
                intent.putExtra(ServiceDefine.PageNumFinished, printJobState.getFinishedPageNum());
                intent.putExtra(ServiceDefine.PageNumCurrent, printJobState.getCurrentPageNum());
                break;
            case 4:
                intent.putExtra(ServiceDefine.EventType, 65541);
                intent.putExtra(ServiceDefine.PageNumAll, printJobState.getAllPageNum());
                intent.putExtra(ServiceDefine.PageNumFinished, printJobState.getFinishedPageNum());
                intent.putExtra(ServiceDefine.PageNumCurrent, printJobState.getCurrentPageNum());
                int stopFactor = printJobState.getStopFactor();
                if (stopFactor == 0) {
                    intent.putExtra(ServiceDefine.PrintStopReason, 131073);
                } else if (stopFactor == 1) {
                    intent.putExtra(ServiceDefine.PrintStopReason, 131074);
                    intent.putExtra(ServiceDefine.ErrorCode, printJobState.getErrorCode());
                } else if (stopFactor == 2) {
                    intent.putExtra(ServiceDefine.PrintStopReason, 131075);
                } else if (stopFactor == 3) {
                    intent.putExtra(ServiceDefine.PrintStopReason, 131076);
                } else if (stopFactor == 5) {
                    intent.putExtra(ServiceDefine.PrintStopReason, 131078);
                    intent.putExtra(ServiceDefine.ErrorCode, ErrorCodeConvert.getErrorCodeFromRuntimeError(printJobState.getErrorCode()));
                }
                break;
            case 5:
                intent.putExtra(ServiceDefine.EventType, 65542);
                intent.putExtra(ServiceDefine.PageNumAll, printJobState.getAllPageNum());
                intent.putExtra(ServiceDefine.PageNumFinished, printJobState.getFinishedPageNum());
                intent.putExtra(ServiceDefine.PageNumCurrent, printJobState.getCurrentPageNum());
                break;
            case 6:
                intent.putExtra(ServiceDefine.EventType, 65543);
                intent.putExtra(ServiceDefine.CleaningTime, printJobState.getCleaningTime());
                break;
        }
        context.sendBroadcast(intent);
    }
}
