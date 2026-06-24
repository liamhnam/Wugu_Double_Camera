package org.apache.log4j.config;

import com.tom_roush.fontbox.ttf.NamingTable;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import org.apache.log4j.Appender;
import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.config.PropertyGetter;

public class PropertyPrinter implements PropertyGetter.PropertyCallback {
    protected Hashtable appenderNames;
    protected boolean doCapitalize;
    protected Hashtable layoutNames;
    protected int numAppenders;
    protected PrintWriter out;

    public PropertyPrinter(PrintWriter printWriter) {
        this(printWriter, false);
    }

    public PropertyPrinter(PrintWriter printWriter, boolean z) {
        this.numAppenders = 0;
        this.appenderNames = new Hashtable();
        this.layoutNames = new Hashtable();
        this.out = printWriter;
        this.doCapitalize = z;
        print(printWriter);
        printWriter.flush();
    }

    protected String genAppName() {
        StringBuffer stringBuffer = new StringBuffer("A");
        int i = this.numAppenders;
        this.numAppenders = i + 1;
        return stringBuffer.append(i).toString();
    }

    protected boolean isGenAppName(String str) {
        if (str.length() < 2 || str.charAt(0) != 'A') {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

    public void print(PrintWriter printWriter) {
        printOptions(printWriter, Logger.getRootLogger());
        Enumeration currentLoggers = LogManager.getCurrentLoggers();
        while (currentLoggers.hasMoreElements()) {
            printOptions(printWriter, (Logger) currentLoggers.nextElement());
        }
    }

    protected void printOptions(PrintWriter printWriter, Category category) {
        Enumeration allAppenders = category.getAllAppenders();
        Level level = category.getLevel();
        String string = level == null ? "" : level.toString();
        while (allAppenders.hasMoreElements()) {
            Appender appender = (Appender) allAppenders.nextElement();
            String name = (String) this.appenderNames.get(appender);
            if (name == null) {
                name = appender.getName();
                if (name == null || isGenAppName(name)) {
                    name = genAppName();
                }
                this.appenderNames.put(appender, name);
                printOptions(printWriter, appender, new StringBuffer("log4j.appender.").append(name).toString());
                if (appender.getLayout() != null) {
                    printOptions(printWriter, appender.getLayout(), new StringBuffer("log4j.appender.").append(name).append(".layout").toString());
                }
            }
            string = new StringBuffer().append(string).append(", ").append(name).toString();
        }
        String string2 = category == Logger.getRootLogger() ? "log4j.rootLogger" : new StringBuffer("log4j.logger.").append(category.getName()).toString();
        if (string != "") {
            printWriter.println(new StringBuffer().append(string2).append("=").append(string).toString());
        }
        if (category.getAdditivity() || category == Logger.getRootLogger()) {
            return;
        }
        printWriter.println(new StringBuffer("log4j.additivity.").append(category.getName()).append("=false").toString());
    }

    protected void printOptions(PrintWriter printWriter, Logger logger) {
        printOptions(printWriter, (Category) logger);
    }

    protected void printOptions(PrintWriter printWriter, Object obj, String str) {
        printWriter.println(new StringBuffer().append(str).append("=").append(obj.getClass().getName()).toString());
        PropertyGetter.getProperties(obj, this, new StringBuffer().append(str).append(".").toString());
    }

    @Override
    public void foundProperty(Object obj, String str, String str2, Object obj2) {
        if ((obj instanceof Appender) && NamingTable.TAG.equals(str2)) {
            return;
        }
        if (this.doCapitalize) {
            str2 = capitalize(str2);
        }
        this.out.println(new StringBuffer().append(str).append(str2).append("=").append(obj2.toString()).toString());
    }

    public static String capitalize(String str) {
        if (!Character.isLowerCase(str.charAt(0))) {
            return str;
        }
        if (str.length() != 1 && !Character.isLowerCase(str.charAt(1))) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(str);
        stringBuffer.setCharAt(0, Character.toUpperCase(str.charAt(0)));
        return stringBuffer.toString();
    }

    public static void main(String[] strArr) {
        new PropertyPrinter(new PrintWriter(System.out));
    }
}
