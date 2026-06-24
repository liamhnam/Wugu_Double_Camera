package com.tom_roush.pdfbox.exceptions;

public class WrappedException extends Exception {
    public WrappedException(Exception exc) {
        super(exc);
    }
}
