package org.apache.log4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.log4j.config.PropertySetter;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.p044or.RendererMap;
import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.OptionHandler;
import org.apache.log4j.spi.RendererSupport;
import org.apache.log4j.spi.ThrowableRenderer;
import org.apache.log4j.spi.ThrowableRendererSupport;

public class PropertyConfigurator implements Configurator {
    static final String ADDITIVITY_PREFIX = "log4j.additivity.";
    static final String APPENDER_PREFIX = "log4j.appender.";
    private static final String APPENDER_REF_TAG = "appender-ref";
    static final String CATEGORY_PREFIX = "log4j.category.";
    static final String FACTORY_PREFIX = "log4j.factory";
    private static final String INTERNAL_ROOT_NAME = "root";
    public static final String LOGGER_FACTORY_KEY = "log4j.loggerFactory";
    static final String LOGGER_PREFIX = "log4j.logger.";
    private static final String LOGGER_REF = "logger-ref";
    static final String RENDERER_PREFIX = "log4j.renderer.";
    private static final String RESET_KEY = "log4j.reset";
    static final String ROOT_CATEGORY_PREFIX = "log4j.rootCategory";
    static final String ROOT_LOGGER_PREFIX = "log4j.rootLogger";
    private static final String ROOT_REF = "root-ref";
    static final String THRESHOLD_PREFIX = "log4j.threshold";
    private static final String THROWABLE_RENDERER_PREFIX = "log4j.throwableRenderer";
    static Class class$org$apache$log4j$Appender;
    static Class class$org$apache$log4j$Layout;
    static Class class$org$apache$log4j$spi$ErrorHandler;
    static Class class$org$apache$log4j$spi$Filter;
    static Class class$org$apache$log4j$spi$LoggerFactory;
    static Class class$org$apache$log4j$spi$ThrowableRenderer;
    private LoggerRepository repository;
    protected Hashtable registry = new Hashtable(11);
    protected LoggerFactory loggerFactory = new DefaultCategoryFactory();

    public void doConfigure(String str, LoggerRepository loggerRepository) throws Throwable {
        FileInputStream fileInputStream;
        Properties properties = new Properties();
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(str);
            } catch (Exception e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            properties.load(fileInputStream);
            fileInputStream.close();
            try {
                fileInputStream.close();
            } catch (InterruptedIOException unused) {
                Thread.currentThread().interrupt();
            } catch (Throwable unused2) {
            }
            doConfigure(properties, loggerRepository);
        } catch (Exception e2) {
            e = e2;
            fileInputStream2 = fileInputStream;
            if ((e instanceof InterruptedIOException) || (e instanceof InterruptedException)) {
                Thread.currentThread().interrupt();
            }
            LogLog.error(new StringBuffer("Could not read configuration file [").append(str).append("].").toString(), e);
            LogLog.error(new StringBuffer("Ignoring configuration file [").append(str).append("].").toString());
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (InterruptedIOException unused3) {
                    Thread.currentThread().interrupt();
                } catch (Throwable unused4) {
                }
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (InterruptedIOException unused5) {
                    Thread.currentThread().interrupt();
                } catch (Throwable unused6) {
                }
            }
            throw th;
        }
    }

    public static void configure(String str) throws Throwable {
        new PropertyConfigurator().doConfigure(str, LogManager.getLoggerRepository());
    }

    public static void configure(URL url) throws Throwable {
        new PropertyConfigurator().doConfigure(url, LogManager.getLoggerRepository());
    }

    public static void configure(InputStream inputStream) throws Throwable {
        new PropertyConfigurator().doConfigure(inputStream, LogManager.getLoggerRepository());
    }

    public static void configure(Properties properties) throws Throwable {
        new PropertyConfigurator().doConfigure(properties, LogManager.getLoggerRepository());
    }

    public static void configureAndWatch(String str) {
        configureAndWatch(str, 60000L);
    }

    public static void configureAndWatch(String str, long j) {
        PropertyWatchdog propertyWatchdog = new PropertyWatchdog(str);
        propertyWatchdog.setDelay(j);
        propertyWatchdog.start();
    }

    public void doConfigure(Properties properties, LoggerRepository loggerRepository) throws Throwable {
        this.repository = loggerRepository;
        String property = properties.getProperty(LogLog.DEBUG_KEY);
        if (property == null && (property = properties.getProperty(LogLog.CONFIG_DEBUG_KEY)) != null) {
            LogLog.warn("[log4j.configDebug] is deprecated. Use [log4j.debug] instead.");
        }
        if (property != null) {
            LogLog.setInternalDebugging(OptionConverter.toBoolean(property, true));
        }
        String property2 = properties.getProperty(RESET_KEY);
        if (property2 != null && OptionConverter.toBoolean(property2, false)) {
            loggerRepository.resetConfiguration();
        }
        String strFindAndSubst = OptionConverter.findAndSubst(THRESHOLD_PREFIX, properties);
        if (strFindAndSubst != null) {
            loggerRepository.setThreshold(OptionConverter.toLevel(strFindAndSubst, Level.ALL));
            LogLog.debug(new StringBuffer("Hierarchy threshold set to [").append(loggerRepository.getThreshold()).append("].").toString());
        }
        configureRootCategory(properties, loggerRepository);
        configureLoggerFactory(properties);
        parseCatsAndRenderers(properties, loggerRepository);
        LogLog.debug("Finished configuring.");
        this.registry.clear();
    }

    @Override
    public void doConfigure(InputStream inputStream, LoggerRepository loggerRepository) throws Throwable {
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            doConfigure(properties, loggerRepository);
        } catch (IOException e) {
            if (e instanceof InterruptedIOException) {
                Thread.currentThread().interrupt();
            }
            LogLog.error(new StringBuffer("Could not read configuration file from InputStream [").append(inputStream).append("].").toString(), e);
            LogLog.error(new StringBuffer("Ignoring configuration InputStream [").append(inputStream).append("].").toString());
        }
    }

    @Override
    public void doConfigure(URL url, LoggerRepository loggerRepository) throws Throwable {
        Properties properties = new Properties();
        LogLog.debug(new StringBuffer("Reading configuration from URL ").append(url).toString());
        InputStream inputStream = null;
        try {
            try {
                URLConnection uRLConnectionOpenConnection = url.openConnection();
                uRLConnectionOpenConnection.setUseCaches(false);
                inputStream = uRLConnectionOpenConnection.getInputStream();
                properties.load(inputStream);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (InterruptedIOException unused) {
                        Thread.currentThread().interrupt();
                    } catch (IOException | RuntimeException unused2) {
                    }
                }
                doConfigure(properties, loggerRepository);
            } catch (Exception e) {
                if ((e instanceof InterruptedIOException) || (e instanceof InterruptedException)) {
                    Thread.currentThread().interrupt();
                }
                LogLog.error(new StringBuffer("Could not read configuration file from URL [").append(url).append("].").toString(), e);
                LogLog.error(new StringBuffer("Ignoring configuration file [").append(url).append("].").toString());
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (InterruptedIOException unused3) {
                        Thread.currentThread().interrupt();
                    } catch (IOException | RuntimeException unused4) {
                    }
                }
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (InterruptedIOException unused5) {
                    Thread.currentThread().interrupt();
                } catch (IOException | RuntimeException unused6) {
                }
            }
            throw th;
        }
    }

    protected void configureLoggerFactory(Properties properties) throws Throwable {
        String strFindAndSubst = OptionConverter.findAndSubst(LOGGER_FACTORY_KEY, properties);
        if (strFindAndSubst != null) {
            LogLog.debug(new StringBuffer("Setting category factory to [").append(strFindAndSubst).append("].").toString());
            Class clsClass$ = class$org$apache$log4j$spi$LoggerFactory;
            if (clsClass$ == null) {
                clsClass$ = class$("org.apache.log4j.spi.LoggerFactory");
                class$org$apache$log4j$spi$LoggerFactory = clsClass$;
            }
            LoggerFactory loggerFactory = (LoggerFactory) OptionConverter.instantiateByClassName(strFindAndSubst, clsClass$, this.loggerFactory);
            this.loggerFactory = loggerFactory;
            PropertySetter.setProperties(loggerFactory, properties, "log4j.factory.");
        }
    }

    static Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    void configureRootCategory(Properties properties, LoggerRepository loggerRepository) {
        String str = ROOT_LOGGER_PREFIX;
        String strFindAndSubst = OptionConverter.findAndSubst(ROOT_LOGGER_PREFIX, properties);
        if (strFindAndSubst == null) {
            strFindAndSubst = OptionConverter.findAndSubst(ROOT_CATEGORY_PREFIX, properties);
            str = ROOT_CATEGORY_PREFIX;
        }
        String str2 = str;
        String str3 = strFindAndSubst;
        if (str3 == null) {
            LogLog.debug("Could not find root logger information. Is this OK?");
            return;
        }
        Logger rootLogger = loggerRepository.getRootLogger();
        synchronized (rootLogger) {
            parseCategory(properties, rootLogger, str2, INTERNAL_ROOT_NAME, str3);
        }
    }

    protected void parseCatsAndRenderers(Properties properties, LoggerRepository loggerRepository) throws Throwable {
        Enumeration<?> enumerationPropertyNames = properties.propertyNames();
        while (enumerationPropertyNames.hasMoreElements()) {
            String str = (String) enumerationPropertyNames.nextElement();
            String strSubstring = null;
            if (str.startsWith(CATEGORY_PREFIX) || str.startsWith(LOGGER_PREFIX)) {
                if (str.startsWith(CATEGORY_PREFIX)) {
                    strSubstring = str.substring(15);
                } else if (str.startsWith(LOGGER_PREFIX)) {
                    strSubstring = str.substring(13);
                }
                String str2 = strSubstring;
                String strFindAndSubst = OptionConverter.findAndSubst(str, properties);
                Logger logger = loggerRepository.getLogger(str2, this.loggerFactory);
                synchronized (logger) {
                    parseCategory(properties, logger, str, str2, strFindAndSubst);
                    parseAdditivityForLogger(properties, logger, str2);
                }
            } else if (str.startsWith(RENDERER_PREFIX)) {
                String strSubstring2 = str.substring(15);
                String strFindAndSubst2 = OptionConverter.findAndSubst(str, properties);
                if (loggerRepository instanceof RendererSupport) {
                    RendererMap.addRenderer((RendererSupport) loggerRepository, strSubstring2, strFindAndSubst2);
                }
            } else if (str.equals(THROWABLE_RENDERER_PREFIX) && (loggerRepository instanceof ThrowableRendererSupport)) {
                Class clsClass$ = class$org$apache$log4j$spi$ThrowableRenderer;
                if (clsClass$ == null) {
                    clsClass$ = class$("org.apache.log4j.spi.ThrowableRenderer");
                    class$org$apache$log4j$spi$ThrowableRenderer = clsClass$;
                }
                ThrowableRenderer throwableRenderer = (ThrowableRenderer) OptionConverter.instantiateByKey(properties, THROWABLE_RENDERER_PREFIX, clsClass$, null);
                if (throwableRenderer == null) {
                    LogLog.error("Could not instantiate throwableRenderer.");
                } else {
                    new PropertySetter(throwableRenderer).setProperties(properties, "log4j.throwableRenderer.");
                    ((ThrowableRendererSupport) loggerRepository).setThrowableRenderer(throwableRenderer);
                }
            }
        }
    }

    void parseAdditivityForLogger(Properties properties, Logger logger, String str) {
        String strFindAndSubst = OptionConverter.findAndSubst(new StringBuffer(ADDITIVITY_PREFIX).append(str).toString(), properties);
        LogLog.debug(new StringBuffer("Handling log4j.additivity.").append(str).append("=[").append(strFindAndSubst).append("]").toString());
        if (strFindAndSubst == null || strFindAndSubst.equals("")) {
            return;
        }
        boolean z = OptionConverter.toBoolean(strFindAndSubst, true);
        LogLog.debug(new StringBuffer("Setting additivity for \"").append(str).append("\" to ").append(z).toString());
        logger.setAdditivity(z);
    }

    void parseCategory(Properties properties, Logger logger, String str, String str2, String str3) throws Throwable {
        LogLog.debug(new StringBuffer("Parsing for [").append(str2).append("] with value=[").append(str3).append("].").toString());
        StringTokenizer stringTokenizer = new StringTokenizer(str3, ",");
        if (!str3.startsWith(",") && !str3.equals("")) {
            if (!stringTokenizer.hasMoreTokens()) {
                return;
            }
            String strNextToken = stringTokenizer.nextToken();
            LogLog.debug(new StringBuffer("Level token is [").append(strNextToken).append("].").toString());
            if (Configurator.INHERITED.equalsIgnoreCase(strNextToken) || Configurator.NULL.equalsIgnoreCase(strNextToken)) {
                if (str2.equals(INTERNAL_ROOT_NAME)) {
                    LogLog.warn("The root logger cannot be set to null.");
                } else {
                    logger.setLevel(null);
                }
            } else {
                logger.setLevel(OptionConverter.toLevel(strNextToken, Level.DEBUG));
            }
            LogLog.debug(new StringBuffer("Category ").append(str2).append(" set to ").append(logger.getLevel()).toString());
        }
        logger.removeAllAppenders();
        while (stringTokenizer.hasMoreTokens()) {
            String strTrim = stringTokenizer.nextToken().trim();
            if (strTrim != null && !strTrim.equals(",")) {
                LogLog.debug(new StringBuffer("Parsing appender named \"").append(strTrim).append("\".").toString());
                Appender appender = parseAppender(properties, strTrim);
                if (appender != null) {
                    logger.addAppender(appender);
                }
            }
        }
    }

    Appender parseAppender(Properties properties, String str) throws Throwable {
        int i;
        Appender appenderRegistryGet = registryGet(str);
        if (appenderRegistryGet != null) {
            LogLog.debug(new StringBuffer("Appender \"").append(str).append("\" was already parsed.").toString());
            return appenderRegistryGet;
        }
        String string = new StringBuffer(APPENDER_PREFIX).append(str).toString();
        String string2 = new StringBuffer().append(string).append(".layout").toString();
        Class clsClass$ = class$org$apache$log4j$Appender;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.log4j.Appender");
            class$org$apache$log4j$Appender = clsClass$;
        }
        Appender appender = (Appender) OptionConverter.instantiateByKey(properties, string, clsClass$, null);
        if (appender == null) {
            LogLog.error(new StringBuffer("Could not instantiate appender named \"").append(str).append("\".").toString());
            return null;
        }
        appender.setName(str);
        if (appender instanceof OptionHandler) {
            if (appender.requiresLayout()) {
                Class clsClass$2 = class$org$apache$log4j$Layout;
                if (clsClass$2 == null) {
                    clsClass$2 = class$("org.apache.log4j.Layout");
                    class$org$apache$log4j$Layout = clsClass$2;
                }
                Layout layout = (Layout) OptionConverter.instantiateByKey(properties, string2, clsClass$2, null);
                if (layout != null) {
                    appender.setLayout(layout);
                    LogLog.debug(new StringBuffer("Parsing layout options for \"").append(str).append("\".").toString());
                    PropertySetter.setProperties(layout, properties, new StringBuffer().append(string2).append(".").toString());
                    LogLog.debug(new StringBuffer("End of parsing for \"").append(str).append("\".").toString());
                }
            }
            String string3 = new StringBuffer().append(string).append(".errorhandler").toString();
            if (OptionConverter.findAndSubst(string3, properties) != null) {
                Class clsClass$3 = class$org$apache$log4j$spi$ErrorHandler;
                if (clsClass$3 == null) {
                    clsClass$3 = class$("org.apache.log4j.spi.ErrorHandler");
                    class$org$apache$log4j$spi$ErrorHandler = clsClass$3;
                }
                ErrorHandler errorHandler = (ErrorHandler) OptionConverter.instantiateByKey(properties, string3, clsClass$3, null);
                if (errorHandler != null) {
                    appender.setErrorHandler(errorHandler);
                    LogLog.debug(new StringBuffer("Parsing errorhandler options for \"").append(str).append("\".").toString());
                    parseErrorHandler(errorHandler, string3, properties, this.repository);
                    Properties properties2 = new Properties();
                    String[] strArr = {new StringBuffer().append(string3).append(".root-ref").toString(), new StringBuffer().append(string3).append(".logger-ref").toString(), new StringBuffer().append(string3).append(".appender-ref").toString()};
                    for (Map.Entry entry : properties.entrySet()) {
                        int i2 = 0;
                        while (true) {
                            i = 3;
                            if (i2 >= 3) {
                                break;
                            }
                            if (strArr[i2].equals(entry.getKey())) {
                                i = 3;
                                break;
                            }
                            i2++;
                        }
                        if (i2 == i) {
                            properties2.put(entry.getKey(), entry.getValue());
                        }
                    }
                    PropertySetter.setProperties(errorHandler, properties2, new StringBuffer().append(string3).append(".").toString());
                    LogLog.debug(new StringBuffer("End of errorhandler parsing for \"").append(str).append("\".").toString());
                }
            }
            PropertySetter.setProperties(appender, properties, new StringBuffer().append(string).append(".").toString());
            LogLog.debug(new StringBuffer("Parsed \"").append(str).append("\" options.").toString());
        }
        parseAppenderFilters(properties, str, appender);
        registryPut(appender);
        return appender;
    }

    private void parseErrorHandler(ErrorHandler errorHandler, String str, Properties properties, LoggerRepository loggerRepository) {
        Appender appender;
        if (OptionConverter.toBoolean(OptionConverter.findAndSubst(new StringBuffer().append(str).append(ROOT_REF).toString(), properties), false)) {
            errorHandler.setLogger(loggerRepository.getRootLogger());
        }
        String strFindAndSubst = OptionConverter.findAndSubst(new StringBuffer().append(str).append(LOGGER_REF).toString(), properties);
        if (strFindAndSubst != null) {
            LoggerFactory loggerFactory = this.loggerFactory;
            errorHandler.setLogger(loggerFactory == null ? loggerRepository.getLogger(strFindAndSubst) : loggerRepository.getLogger(strFindAndSubst, loggerFactory));
        }
        String strFindAndSubst2 = OptionConverter.findAndSubst(new StringBuffer().append(str).append(APPENDER_REF_TAG).toString(), properties);
        if (strFindAndSubst2 == null || (appender = parseAppender(properties, strFindAndSubst2)) == null) {
            return;
        }
        errorHandler.setBackupAppender(appender);
    }

    void parseAppenderFilters(Properties properties, String str, Appender appender) throws Throwable {
        String strSubstring;
        String strSubstring2;
        String string = new StringBuffer(APPENDER_PREFIX).append(str).append(".filter.").toString();
        int length = string.length();
        Hashtable hashtable = new Hashtable();
        Enumeration enumerationKeys = properties.keys();
        String str2 = "";
        while (enumerationKeys.hasMoreElements()) {
            String str3 = (String) enumerationKeys.nextElement();
            if (str3.startsWith(string)) {
                int iIndexOf = str3.indexOf(46, length);
                if (iIndexOf != -1) {
                    strSubstring2 = str3.substring(0, iIndexOf);
                    strSubstring = str3.substring(iIndexOf + 1);
                } else {
                    strSubstring = str2;
                    strSubstring2 = str3;
                }
                Vector vector = (Vector) hashtable.get(strSubstring2);
                if (vector == null) {
                    vector = new Vector();
                    hashtable.put(strSubstring2, vector);
                }
                if (iIndexOf != -1) {
                    vector.add(new NameValue(strSubstring, OptionConverter.findAndSubst(str3, properties)));
                }
                str2 = strSubstring;
            }
        }
        SortedKeyEnumeration sortedKeyEnumeration = new SortedKeyEnumeration(hashtable);
        while (sortedKeyEnumeration.hasMoreElements()) {
            String str4 = (String) sortedKeyEnumeration.nextElement();
            String property = properties.getProperty(str4);
            if (property != null) {
                LogLog.debug(new StringBuffer("Filter key: [").append(str4).append("] class: [").append(properties.getProperty(str4)).append("] props: ").append(hashtable.get(str4)).toString());
                Class clsClass$ = class$org$apache$log4j$spi$Filter;
                if (clsClass$ == null) {
                    clsClass$ = class$("org.apache.log4j.spi.Filter");
                    class$org$apache$log4j$spi$Filter = clsClass$;
                }
                Filter filter = (Filter) OptionConverter.instantiateByClassName(property, clsClass$, null);
                if (filter != null) {
                    PropertySetter propertySetter = new PropertySetter(filter);
                    Enumeration enumerationElements = ((Vector) hashtable.get(str4)).elements();
                    while (enumerationElements.hasMoreElements()) {
                        NameValue nameValue = (NameValue) enumerationElements.nextElement();
                        propertySetter.setProperty(nameValue.key, nameValue.value);
                    }
                    propertySetter.activate();
                    LogLog.debug(new StringBuffer("Adding filter of type [").append(filter.getClass()).append("] to appender named [").append(appender.getName()).append("].").toString());
                    appender.addFilter(filter);
                }
            } else {
                LogLog.warn(new StringBuffer("Missing class definition for filter: [").append(str4).append("]").toString());
            }
        }
    }

    void registryPut(Appender appender) {
        this.registry.put(appender.getName(), appender);
    }

    Appender registryGet(String str) {
        return (Appender) this.registry.get(str);
    }
}
