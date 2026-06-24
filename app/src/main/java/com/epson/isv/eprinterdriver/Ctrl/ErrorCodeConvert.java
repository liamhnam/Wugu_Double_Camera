package com.epson.isv.eprinterdriver.Ctrl;

import com.epson.isv.eprinterdriver.Common.ErrorCode;

class ErrorCodeConvert {
    public static int getErrorCodeFromRuntimeError(int i) {
        if (i == -1750) {
            return ErrorCode.EPRINTSVR_ERR_PRINTER_NOT_FOUND;
        }
        if (i == -1650) {
            return ErrorCode.EPRINTSVR_ERR_CAN_NOT_RESET;
        }
        if (i == -1450) {
            return 131073;
        }
        if (i == -1306) {
            return ErrorCode.EPRINTSVR_ERR_PRINTER_NOT_USEFUL;
        }
        if (i == -1100) {
            return 65537;
        }
        if (i == -1015) {
            return 65543;
        }
        if (i == -1003) {
            return 65540;
        }
        if (i == 0) {
            return 0;
        }
        if (i == -1351 || i == -1350) {
            return 65541;
        }
        if (i == -1011) {
            return 65542;
        }
        if (i == -1010) {
            return 65538;
        }
        switch (i) {
            case EscprRunTimeError.EPS_ERR_INV_PRINT_QUALITY:
                return ErrorCode.EPRINTSVR_ERR_INV_PRINT_QUALITY;
            case EscprRunTimeError.EPS_ERR_INV_BORDER_MODE:
                return 131076;
            case EscprRunTimeError.EPS_ERR_INV_MEDIA_TYPE:
                return 131075;
            case EscprRunTimeError.EPS_ERR_INV_MEDIA_SIZE:
                return 131074;
            default:
                switch (i) {
                    case EscprRunTimeError.EPS_ERR_INV_ARG_PRINTER_ADDR:
                        return 262148;
                    case EscprRunTimeError.EPS_ERR_INV_ARG_PRINTER_ID:
                        return 262147;
                    case EscprRunTimeError.EPS_ERR_INV_ARG_UNK_METHOD:
                        return 262146;
                    case EscprRunTimeError.EPS_ERR_INV_ARG_PROBEINFO:
                        return 262145;
                    case EscprRunTimeError.EPS_ERR_PRINTER_NOT_FOUND:
                        return ErrorCode.EPRINTSVR_ERR_PRINTER_NOT_FOUND;
                    default:
                        switch (i) {
                            case 40:
                                return ErrorCode.EPRINTSVR_ERR_JOB_CANCELED;
                            case 41:
                                return ErrorCode.EPRINTSVR_ERR_OUT_OF_BOUNDS;
                            case 42:
                                return ErrorCode.EPRINTSVR_ERR_FIND_CANCELED;
                            default:
                                return 65539;
                        }
                }
        }
    }

    ErrorCodeConvert() {
    }
}
