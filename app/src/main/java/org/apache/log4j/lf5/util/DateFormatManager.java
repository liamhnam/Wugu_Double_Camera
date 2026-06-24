package org.apache.log4j.lf5.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateFormatManager {
    private DateFormat _dateFormat;
    private Locale _locale;
    private String _pattern;
    private TimeZone _timeZone;

    public DateFormatManager() {
        this._timeZone = null;
        this._locale = null;
        this._pattern = null;
        this._dateFormat = null;
        configure();
    }

    public DateFormatManager(TimeZone timeZone) {
        this._locale = null;
        this._pattern = null;
        this._dateFormat = null;
        this._timeZone = timeZone;
        configure();
    }

    public DateFormatManager(Locale locale) {
        this._timeZone = null;
        this._pattern = null;
        this._dateFormat = null;
        this._locale = locale;
        configure();
    }

    public DateFormatManager(String str) {
        this._timeZone = null;
        this._locale = null;
        this._dateFormat = null;
        this._pattern = str;
        configure();
    }

    public DateFormatManager(TimeZone timeZone, Locale locale) {
        this._pattern = null;
        this._dateFormat = null;
        this._timeZone = timeZone;
        this._locale = locale;
        configure();
    }

    public DateFormatManager(TimeZone timeZone, String str) {
        this._locale = null;
        this._dateFormat = null;
        this._timeZone = timeZone;
        this._pattern = str;
        configure();
    }

    public DateFormatManager(Locale locale, String str) {
        this._timeZone = null;
        this._dateFormat = null;
        this._locale = locale;
        this._pattern = str;
        configure();
    }

    public DateFormatManager(TimeZone timeZone, Locale locale, String str) {
        this._dateFormat = null;
        this._timeZone = timeZone;
        this._locale = locale;
        this._pattern = str;
        configure();
    }

    public synchronized TimeZone getTimeZone() {
        TimeZone timeZone = this._timeZone;
        if (timeZone != null) {
            return timeZone;
        }
        return TimeZone.getDefault();
    }

    public synchronized void setTimeZone(TimeZone timeZone) {
        this._timeZone = timeZone;
        configure();
    }

    public synchronized Locale getLocale() {
        Locale locale = this._locale;
        if (locale != null) {
            return locale;
        }
        return Locale.getDefault();
    }

    public synchronized void setLocale(Locale locale) {
        this._locale = locale;
        configure();
    }

    public synchronized String getPattern() {
        return this._pattern;
    }

    public synchronized void setPattern(String str) {
        this._pattern = str;
        configure();
    }

    public synchronized String getOutputFormat() {
        return this._pattern;
    }

    public synchronized void setOutputFormat(String str) {
        this._pattern = str;
        configure();
    }

    public synchronized DateFormat getDateFormatInstance() {
        return this._dateFormat;
    }

    public synchronized void setDateFormatInstance(DateFormat dateFormat) {
        this._dateFormat = dateFormat;
    }

    public String format(Date date) {
        return getDateFormatInstance().format(date);
    }

    public String format(Date date, String str) {
        DateFormat dateFormatInstance = getDateFormatInstance();
        boolean z = dateFormatInstance instanceof SimpleDateFormat;
        DateFormat dateFormat = dateFormatInstance;
        if (z) {
            SimpleDateFormat simpleDateFormat = (SimpleDateFormat) dateFormatInstance.clone();
            simpleDateFormat.applyPattern(str);
            dateFormat = simpleDateFormat;
        }
        return dateFormat.format(date);
    }

    public Date parse(String str) throws ParseException {
        return getDateFormatInstance().parse(str);
    }

    public Date parse(String str, String str2) throws ParseException {
        DateFormat dateFormatInstance = getDateFormatInstance();
        boolean z = dateFormatInstance instanceof SimpleDateFormat;
        DateFormat dateFormat = dateFormatInstance;
        if (z) {
            SimpleDateFormat simpleDateFormat = (SimpleDateFormat) dateFormatInstance.clone();
            simpleDateFormat.applyPattern(str2);
            dateFormat = simpleDateFormat;
        }
        return dateFormat.parse(str);
    }

    private synchronized void configure() {
        DateFormat dateTimeInstance = SimpleDateFormat.getDateTimeInstance(0, 0, getLocale());
        this._dateFormat = dateTimeInstance;
        dateTimeInstance.setTimeZone(getTimeZone());
        String str = this._pattern;
        if (str != null) {
            ((SimpleDateFormat) this._dateFormat).applyPattern(str);
        }
    }
}
