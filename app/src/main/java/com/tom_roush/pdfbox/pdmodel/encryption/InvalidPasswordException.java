package com.tom_roush.pdfbox.pdmodel.encryption;

import java.io.IOException;

public class InvalidPasswordException extends IOException {
    InvalidPasswordException(String str) {
        super(str);
    }
}
