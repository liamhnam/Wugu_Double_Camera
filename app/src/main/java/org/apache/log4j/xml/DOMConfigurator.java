package org.apache.log4j.xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Hashtable;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import org.apache.log4j.Appender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.config.PropertySetter;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.p044or.RendererMap;
import org.apache.log4j.spi.AppenderAttachable;
import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.RendererSupport;
import org.apache.log4j.spi.ThrowableRenderer;
import org.apache.log4j.spi.ThrowableRendererSupport;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DOMConfigurator implements Configurator {
    static final String ADDITIVITY_ATTR = "additivity";
    static final String APPENDER_REF_TAG = "appender-ref";
    static final String APPENDER_TAG = "appender";
    static final String CATEGORY = "category";
    static final String CATEGORY_FACTORY_TAG = "categoryFactory";
    static final String CLASS_ATTR = "class";
    static final String CONFIGURATION_TAG = "log4j:configuration";
    static final String CONFIG_DEBUG_ATTR = "configDebug";
    static final String EMPTY_STR = "";
    static final String ERROR_HANDLER_TAG = "errorHandler";
    static final String FILTER_TAG = "filter";
    static final String INTERNAL_DEBUG_ATTR = "debug";
    static final String LAYOUT_TAG = "layout";
    static final String LEVEL_TAG = "level";
    static final String LOGGER = "logger";
    static final String LOGGER_FACTORY_TAG = "loggerFactory";
    static final String LOGGER_REF = "logger-ref";
    static final String NAME_ATTR = "name";
    static final String OLD_CONFIGURATION_TAG = "configuration";
    static final Class[] ONE_STRING_PARAM;
    static final String PARAM_TAG = "param";
    static final String PRIORITY_TAG = "priority";
    static final String REF_ATTR = "ref";
    static final String RENDERED_CLASS_ATTR = "renderedClass";
    static final String RENDERER_TAG = "renderer";
    static final String RENDERING_CLASS_ATTR = "renderingClass";
    private static final String RESET_ATTR = "reset";
    static final String ROOT_REF = "root-ref";
    static final String ROOT_TAG = "root";
    static final String THRESHOLD_ATTR = "threshold";
    private static final String THROWABLE_RENDERER_TAG = "throwableRenderer";
    static final String VALUE_ATTR = "value";
    static Class class$java$lang$String = null;
    static Class class$org$apache$log4j$spi$ErrorHandler = null;
    static Class class$org$apache$log4j$spi$Filter = null;
    static Class class$org$apache$log4j$spi$LoggerFactory = null;
    static final String dbfKey = "javax.xml.parsers.DocumentBuilderFactory";
    Properties props;
    LoggerRepository repository;
    protected LoggerFactory catFactory = null;
    Hashtable appenderBag = new Hashtable();

    private interface ParseAction {
        Document parse(DocumentBuilder documentBuilder) throws SAXException, IOException;
    }

    static {
        Class[] clsArr = new Class[1];
        Class clsClass$ = class$java$lang$String;
        if (clsClass$ == null) {
            clsClass$ = class$("java.lang.String");
            class$java$lang$String = clsClass$;
        }
        clsArr[0] = clsClass$;
        ONE_STRING_PARAM = clsArr;
    }

    static Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    protected Appender findAppenderByName(Document document, String str) throws Throwable {
        Element element;
        Appender appender = (Appender) this.appenderBag.get(str);
        if (appender != null) {
            return appender;
        }
        NodeList elementsByTagName = document.getElementsByTagName(APPENDER_TAG);
        int i = 0;
        while (true) {
            if (i >= elementsByTagName.getLength()) {
                element = null;
                break;
            }
            Node nodeItem = elementsByTagName.item(i);
            if (str.equals(nodeItem.getAttributes().getNamedItem("name").getNodeValue())) {
                element = (Element) nodeItem;
                break;
            }
            i++;
        }
        if (element == null) {
            LogLog.error(new StringBuffer("No appender named [").append(str).append("] could be found.").toString());
            return null;
        }
        Appender appender2 = parseAppender(element);
        if (appender2 != null) {
            this.appenderBag.put(str, appender2);
        }
        return appender2;
    }

    protected Appender findAppenderByReference(Element element) {
        return findAppenderByName(element.getOwnerDocument(), subst(element.getAttribute(REF_ATTR)));
    }

    private static void parseUnrecognizedElement(Object obj, Element element, Properties properties) throws Exception {
        if (obj instanceof UnrecognizedElementHandler ? ((UnrecognizedElementHandler) obj).parseUnrecognizedElement(element, properties) : false) {
            return;
        }
        LogLog.warn(new StringBuffer("Unrecognized element ").append(element.getNodeName()).toString());
    }

    private static void quietParseUnrecognizedElement(Object obj, Element element, Properties properties) {
        try {
            parseUnrecognizedElement(obj, element, properties);
        } catch (Exception e) {
            if ((e instanceof InterruptedException) || (e instanceof InterruptedIOException)) {
                Thread.currentThread().interrupt();
            }
            LogLog.error("Error in extension content: ", e);
        }
    }

    protected Appender parseAppender(Element element) throws Throwable {
        String strSubst = subst(element.getAttribute("class"));
        LogLog.debug(new StringBuffer("Class name: [").append(strSubst).append(']').toString());
        try {
            Object objNewInstance = Loader.loadClass(strSubst).newInstance();
            Appender appender = (Appender) objNewInstance;
            PropertySetter propertySetter = new PropertySetter(appender);
            appender.setName(subst(element.getAttribute("name")));
            NodeList childNodes = element.getChildNodes();
            int length = childNodes.getLength();
            for (int i = 0; i < length; i++) {
                Node nodeItem = childNodes.item(i);
                if (nodeItem.getNodeType() == 1) {
                    Element element2 = (Element) nodeItem;
                    if (element2.getTagName().equals(PARAM_TAG)) {
                        setParameter(element2, propertySetter);
                    } else if (element2.getTagName().equals("layout")) {
                        appender.setLayout(parseLayout(element2));
                    } else if (element2.getTagName().equals(FILTER_TAG)) {
                        parseFilters(element2, appender);
                    } else if (element2.getTagName().equals(ERROR_HANDLER_TAG)) {
                        parseErrorHandler(element2, appender);
                    } else if (element2.getTagName().equals(APPENDER_REF_TAG)) {
                        String strSubst2 = subst(element2.getAttribute(REF_ATTR));
                        if (appender instanceof AppenderAttachable) {
                            LogLog.debug(new StringBuffer().append("Attaching appender named [").append(strSubst2).append("] to appender named [").append(appender.getName()).append("].").toString());
                            ((AppenderAttachable) appender).addAppender(findAppenderByReference(element2));
                        } else {
                            LogLog.error(new StringBuffer().append("Requesting attachment of appender named [").append(strSubst2).append("] to appender named [").append(appender.getName()).append("] which does not implement org.apache.log4j.spi.AppenderAttachable.").toString());
                        }
                    } else {
                        parseUnrecognizedElement(objNewInstance, element2, this.props);
                    }
                }
            }
            propertySetter.activate();
            return appender;
        } catch (Exception e) {
            if ((e instanceof InterruptedException) || (e instanceof InterruptedIOException)) {
                Thread.currentThread().interrupt();
            }
            LogLog.error("Could not create an Appender. Reported error follows.", e);
            return null;
        }
    }

    protected void parseErrorHandler(Element element, Appender appender) throws Throwable {
        String strSubst = subst(element.getAttribute("class"));
        Class clsClass$ = class$org$apache$log4j$spi$ErrorHandler;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.log4j.spi.ErrorHandler");
            class$org$apache$log4j$spi$ErrorHandler = clsClass$;
        }
        ErrorHandler errorHandler = (ErrorHandler) OptionConverter.instantiateByClassName(strSubst, clsClass$, null);
        if (errorHandler != null) {
            errorHandler.setAppender(appender);
            PropertySetter propertySetter = new PropertySetter(errorHandler);
            NodeList childNodes = element.getChildNodes();
            int length = childNodes.getLength();
            for (int i = 0; i < length; i++) {
                Node nodeItem = childNodes.item(i);
                if (nodeItem.getNodeType() == 1) {
                    Element element2 = (Element) nodeItem;
                    String tagName = element2.getTagName();
                    if (tagName.equals(PARAM_TAG)) {
                        setParameter(element2, propertySetter);
                    } else if (tagName.equals(APPENDER_REF_TAG)) {
                        errorHandler.setBackupAppender(findAppenderByReference(element2));
                    } else if (tagName.equals(LOGGER_REF)) {
                        String attribute = element2.getAttribute(REF_ATTR);
                        LoggerFactory loggerFactory = this.catFactory;
                        errorHandler.setLogger(loggerFactory == null ? this.repository.getLogger(attribute) : this.repository.getLogger(attribute, loggerFactory));
                    } else if (tagName.equals(ROOT_REF)) {
                        errorHandler.setLogger(this.repository.getRootLogger());
                    } else {
                        quietParseUnrecognizedElement(errorHandler, element2, this.props);
                    }
                }
            }
            propertySetter.activate();
            appender.setErrorHandler(errorHandler);
        }
    }

    protected void parseFilters(Element element, Appender appender) throws Throwable {
        String strSubst = subst(element.getAttribute("class"));
        Class clsClass$ = class$org$apache$log4j$spi$Filter;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.log4j.spi.Filter");
            class$org$apache$log4j$spi$Filter = clsClass$;
        }
        Filter filter = (Filter) OptionConverter.instantiateByClassName(strSubst, clsClass$, null);
        if (filter != null) {
            PropertySetter propertySetter = new PropertySetter(filter);
            NodeList childNodes = element.getChildNodes();
            int length = childNodes.getLength();
            for (int i = 0; i < length; i++) {
                Node nodeItem = childNodes.item(i);
                if (nodeItem.getNodeType() == 1) {
                    Element element2 = (Element) nodeItem;
                    if (element2.getTagName().equals(PARAM_TAG)) {
                        setParameter(element2, propertySetter);
                    } else {
                        quietParseUnrecognizedElement(filter, element2, this.props);
                    }
                }
            }
            propertySetter.activate();
            LogLog.debug(new StringBuffer("Adding filter of type [").append(filter.getClass()).append("] to appender named [").append(appender.getName()).append("].").toString());
            appender.addFilter(filter);
        }
    }

    protected void parseCategory(Element element) {
        Logger logger;
        String strSubst = subst(element.getAttribute("name"));
        String strSubst2 = subst(element.getAttribute("class"));
        if ("".equals(strSubst2)) {
            LogLog.debug("Retreiving an instance of org.apache.log4j.Logger.");
            LoggerFactory loggerFactory = this.catFactory;
            logger = loggerFactory == null ? this.repository.getLogger(strSubst) : this.repository.getLogger(strSubst, loggerFactory);
        } else {
            LogLog.debug(new StringBuffer("Desired logger sub-class: [").append(strSubst2).append(']').toString());
            try {
                logger = (Logger) Loader.loadClass(strSubst2).getMethod("getLogger", ONE_STRING_PARAM).invoke(null, strSubst);
            } catch (InvocationTargetException e) {
                if ((e.getTargetException() instanceof InterruptedException) || (e.getTargetException() instanceof InterruptedIOException)) {
                    Thread.currentThread().interrupt();
                }
                LogLog.error(new StringBuffer("Could not retrieve category [").append(strSubst).append("]. Reported error follows.").toString(), e);
                return;
            } catch (Exception e2) {
                LogLog.error(new StringBuffer("Could not retrieve category [").append(strSubst).append("]. Reported error follows.").toString(), e2);
                return;
            }
        }
        synchronized (logger) {
            boolean z = OptionConverter.toBoolean(subst(element.getAttribute(ADDITIVITY_ATTR)), true);
            LogLog.debug(new StringBuffer("Setting [").append(logger.getName()).append("] additivity to [").append(z).append("].").toString());
            logger.setAdditivity(z);
            parseChildrenOfLoggerElement(element, logger, false);
        }
    }

    protected void parseCategoryFactory(Element element) throws Throwable {
        String strSubst = subst(element.getAttribute("class"));
        if ("".equals(strSubst)) {
            LogLog.error("Category Factory tag class attribute not found.");
            LogLog.debug("No Category Factory configured.");
            return;
        }
        LogLog.debug(new StringBuffer("Desired category factory: [").append(strSubst).append(']').toString());
        Class clsClass$ = class$org$apache$log4j$spi$LoggerFactory;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.log4j.spi.LoggerFactory");
            class$org$apache$log4j$spi$LoggerFactory = clsClass$;
        }
        Object objInstantiateByClassName = OptionConverter.instantiateByClassName(strSubst, clsClass$, null);
        if (objInstantiateByClassName instanceof LoggerFactory) {
            this.catFactory = (LoggerFactory) objInstantiateByClassName;
        } else {
            LogLog.error(new StringBuffer("Category Factory class ").append(strSubst).append(" does not implement org.apache.log4j.LoggerFactory").toString());
        }
        PropertySetter propertySetter = new PropertySetter(objInstantiateByClassName);
        NodeList childNodes = element.getChildNodes();
        int length = childNodes.getLength();
        for (int i = 0; i < length; i++) {
            Node nodeItem = childNodes.item(i);
            if (nodeItem.getNodeType() == 1) {
                Element element2 = (Element) nodeItem;
                if (element2.getTagName().equals(PARAM_TAG)) {
                    setParameter(element2, propertySetter);
                } else {
                    quietParseUnrecognizedElement(objInstantiateByClassName, element2, this.props);
                }
            }
        }
    }

    protected void parseRoot(Element element) {
        Logger rootLogger = this.repository.getRootLogger();
        synchronized (rootLogger) {
            parseChildrenOfLoggerElement(element, rootLogger, true);
        }
    }

    protected void parseChildrenOfLoggerElement(Element element, Logger logger, boolean z) {
        PropertySetter propertySetter = new PropertySetter(logger);
        logger.removeAllAppenders();
        NodeList childNodes = element.getChildNodes();
        int length = childNodes.getLength();
        for (int i = 0; i < length; i++) {
            Node nodeItem = childNodes.item(i);
            if (nodeItem.getNodeType() == 1) {
                Element element2 = (Element) nodeItem;
                String tagName = element2.getTagName();
                if (tagName.equals(APPENDER_REF_TAG)) {
                    Appender appenderFindAppenderByReference = findAppenderByReference(element2);
                    String strSubst = subst(element2.getAttribute(REF_ATTR));
                    if (appenderFindAppenderByReference != null) {
                        LogLog.debug(new StringBuffer("Adding appender named [").append(strSubst).append("] to category [").append(logger.getName()).append("].").toString());
                    } else {
                        LogLog.debug(new StringBuffer("Appender named [").append(strSubst).append("] not found.").toString());
                    }
                    logger.addAppender(appenderFindAppenderByReference);
                } else if (tagName.equals(LEVEL_TAG)) {
                    parseLevel(element2, logger, z);
                } else if (tagName.equals(PRIORITY_TAG)) {
                    parseLevel(element2, logger, z);
                } else if (tagName.equals(PARAM_TAG)) {
                    setParameter(element2, propertySetter);
                } else {
                    quietParseUnrecognizedElement(logger, element2, this.props);
                }
            }
        }
        propertySetter.activate();
    }

    protected Layout parseLayout(Element element) {
        String strSubst = subst(element.getAttribute("class"));
        LogLog.debug(new StringBuffer("Parsing layout of class: \"").append(strSubst).append("\"").toString());
        try {
            Object objNewInstance = Loader.loadClass(strSubst).newInstance();
            Layout layout = (Layout) objNewInstance;
            PropertySetter propertySetter = new PropertySetter(layout);
            NodeList childNodes = element.getChildNodes();
            int length = childNodes.getLength();
            for (int i = 0; i < length; i++) {
                Node nodeItem = childNodes.item(i);
                if (nodeItem.getNodeType() == 1) {
                    Element element2 = (Element) nodeItem;
                    if (element2.getTagName().equals(PARAM_TAG)) {
                        setParameter(element2, propertySetter);
                    } else {
                        parseUnrecognizedElement(objNewInstance, element2, this.props);
                    }
                }
            }
            propertySetter.activate();
            return layout;
        } catch (Exception e) {
            if ((e instanceof InterruptedException) || (e instanceof InterruptedIOException)) {
                Thread.currentThread().interrupt();
            }
            LogLog.error("Could not create the Layout. Reported error follows.", e);
            return null;
        }
    }

    protected void parseRenderer(Element element) throws Throwable {
        String strSubst = subst(element.getAttribute(RENDERING_CLASS_ATTR));
        String strSubst2 = subst(element.getAttribute(RENDERED_CLASS_ATTR));
        LoggerRepository loggerRepository = this.repository;
        if (loggerRepository instanceof RendererSupport) {
            RendererMap.addRenderer((RendererSupport) loggerRepository, strSubst2, strSubst);
        }
    }

    protected ThrowableRenderer parseThrowableRenderer(Element element) {
        String strSubst = subst(element.getAttribute("class"));
        LogLog.debug(new StringBuffer("Parsing throwableRenderer of class: \"").append(strSubst).append("\"").toString());
        try {
            Object objNewInstance = Loader.loadClass(strSubst).newInstance();
            ThrowableRenderer throwableRenderer = (ThrowableRenderer) objNewInstance;
            PropertySetter propertySetter = new PropertySetter(throwableRenderer);
            NodeList childNodes = element.getChildNodes();
            int length = childNodes.getLength();
            for (int i = 0; i < length; i++) {
                Node nodeItem = childNodes.item(i);
                if (nodeItem.getNodeType() == 1) {
                    Element element2 = (Element) nodeItem;
                    if (element2.getTagName().equals(PARAM_TAG)) {
                        setParameter(element2, propertySetter);
                    } else {
                        parseUnrecognizedElement(objNewInstance, element2, this.props);
                    }
                }
            }
            propertySetter.activate();
            return throwableRenderer;
        } catch (Exception e) {
            if ((e instanceof InterruptedException) || (e instanceof InterruptedIOException)) {
                Thread.currentThread().interrupt();
            }
            LogLog.error("Could not create the ThrowableRenderer. Reported error follows.", e);
            return null;
        }
    }

    protected void parseLevel(Element element, Logger logger, boolean z) {
        String name = logger.getName();
        if (z) {
            name = ROOT_TAG;
        }
        String strSubst = subst(element.getAttribute(VALUE_ATTR));
        LogLog.debug(new StringBuffer("Level value for ").append(name).append(" is  [").append(strSubst).append("].").toString());
        if (!Configurator.INHERITED.equalsIgnoreCase(strSubst) && !Configurator.NULL.equalsIgnoreCase(strSubst)) {
            String strSubst2 = subst(element.getAttribute("class"));
            if ("".equals(strSubst2)) {
                logger.setLevel(OptionConverter.toLevel(strSubst, Level.DEBUG));
            } else {
                LogLog.debug(new StringBuffer("Desired Level sub-class: [").append(strSubst2).append(']').toString());
                try {
                    logger.setLevel((Level) Loader.loadClass(strSubst2).getMethod("toLevel", ONE_STRING_PARAM).invoke(null, strSubst));
                } catch (Exception e) {
                    if ((e instanceof InterruptedException) || (e instanceof InterruptedIOException)) {
                        Thread.currentThread().interrupt();
                    }
                    LogLog.error(new StringBuffer("Could not create level [").append(strSubst).append("]. Reported error follows.").toString(), e);
                    return;
                }
            }
        } else if (z) {
            LogLog.error("Root level cannot be inherited. Ignoring directive.");
        } else {
            logger.setLevel(null);
        }
        LogLog.debug(new StringBuffer().append(name).append(" level set to ").append(logger.getLevel()).toString());
    }

    protected void setParameter(Element element, PropertySetter propertySetter) {
        propertySetter.setProperty(subst(element.getAttribute("name")), subst(OptionConverter.convertSpecialChars(element.getAttribute(VALUE_ATTR))));
    }

    public static void configure(Element element) throws Throwable {
        new DOMConfigurator().doConfigure(element, LogManager.getLoggerRepository());
    }

    public static void configureAndWatch(String str) {
        configureAndWatch(str, 60000L);
    }

    public static void configureAndWatch(String str, long j) {
        XMLWatchdog xMLWatchdog = new XMLWatchdog(str);
        xMLWatchdog.setDelay(j);
        xMLWatchdog.start();
    }

    public void doConfigure(final String str, LoggerRepository loggerRepository) {
        doConfigure(new ParseAction() {
            @Override
            public Document parse(DocumentBuilder documentBuilder) throws SAXException, IOException {
                return documentBuilder.parse(new File(str));
            }

            public String toString() {
                return new StringBuffer("file [").append(str).append("]").toString();
            }
        }, loggerRepository);
    }

    @Override
    public void doConfigure(final URL url, LoggerRepository loggerRepository) throws Throwable {
        doConfigure(new ParseAction() {
            @Override
            public Document parse(DocumentBuilder documentBuilder) throws SAXException, IOException {
                URLConnection uRLConnectionOpenConnection = url.openConnection();
                uRLConnectionOpenConnection.setUseCaches(false);
                InputStream inputStream = uRLConnectionOpenConnection.getInputStream();
                try {
                    InputSource inputSource = new InputSource(inputStream);
                    inputSource.setSystemId(url.toString());
                    return documentBuilder.parse(inputSource);
                } finally {
                    inputStream.close();
                }
            }

            public String toString() {
                return new StringBuffer("url [").append(url.toString()).append("]").toString();
            }
        }, loggerRepository);
    }

    @Override
    public void doConfigure(final InputStream inputStream, LoggerRepository loggerRepository) throws Throwable {
        doConfigure(new ParseAction() {
            @Override
            public Document parse(DocumentBuilder documentBuilder) throws SAXException, IOException {
                InputSource inputSource = new InputSource(inputStream);
                inputSource.setSystemId("dummy://log4j.dtd");
                return documentBuilder.parse(inputSource);
            }

            public String toString() {
                return new StringBuffer("input stream [").append(inputStream.toString()).append("]").toString();
            }
        }, loggerRepository);
    }

    public void doConfigure(final Reader reader, LoggerRepository loggerRepository) throws Throwable {
        doConfigure(new ParseAction() {
            @Override
            public Document parse(DocumentBuilder documentBuilder) throws SAXException, IOException {
                InputSource inputSource = new InputSource(reader);
                inputSource.setSystemId("dummy://log4j.dtd");
                return documentBuilder.parse(inputSource);
            }

            public String toString() {
                return new StringBuffer("reader [").append(reader.toString()).append("]").toString();
            }
        }, loggerRepository);
    }

    protected void doConfigure(final InputSource inputSource, LoggerRepository loggerRepository) throws Throwable {
        if (inputSource.getSystemId() == null) {
            inputSource.setSystemId("dummy://log4j.dtd");
        }
        doConfigure(new ParseAction() {
            @Override
            public Document parse(DocumentBuilder documentBuilder) throws SAXException, IOException {
                return documentBuilder.parse(inputSource);
            }

            public String toString() {
                return new StringBuffer("input source [").append(inputSource.toString()).append("]").toString();
            }
        }, loggerRepository);
    }

    private final void doConfigure(ParseAction parseAction, LoggerRepository loggerRepository) throws Throwable {
        this.repository = loggerRepository;
        try {
            LogLog.debug(new StringBuffer("System property is :").append(OptionConverter.getSystemProperty(dbfKey, null)).toString());
            DocumentBuilderFactory documentBuilderFactoryNewInstance = DocumentBuilderFactory.newInstance();
            LogLog.debug("Standard DocumentBuilderFactory search succeded.");
            LogLog.debug(new StringBuffer("DocumentBuilderFactory is: ").append(documentBuilderFactoryNewInstance.getClass().getName()).toString());
            try {
                documentBuilderFactoryNewInstance.setValidating(true);
                DocumentBuilder documentBuilderNewDocumentBuilder = documentBuilderFactoryNewInstance.newDocumentBuilder();
                documentBuilderNewDocumentBuilder.setErrorHandler(new SAXErrorHandler());
                documentBuilderNewDocumentBuilder.setEntityResolver(new Log4jEntityResolver());
                parse(parseAction.parse(documentBuilderNewDocumentBuilder).getDocumentElement());
            } catch (Exception e) {
                if ((e instanceof InterruptedException) || (e instanceof InterruptedIOException)) {
                    Thread.currentThread().interrupt();
                }
                LogLog.error(new StringBuffer("Could not parse ").append(parseAction.toString()).append(".").toString(), e);
            }
        } catch (FactoryConfigurationError e2) {
            LogLog.debug("Could not instantiate a DocumentBuilderFactory.", e2.getException());
            throw e2;
        }
    }

    public void doConfigure(Element element, LoggerRepository loggerRepository) throws Throwable {
        this.repository = loggerRepository;
        parse(element);
    }

    public static void configure(String str) throws FactoryConfigurationError {
        new DOMConfigurator().doConfigure(str, LogManager.getLoggerRepository());
    }

    public static void configure(URL url) throws Throwable {
        new DOMConfigurator().doConfigure(url, LogManager.getLoggerRepository());
    }

    protected void parse(Element element) throws Throwable {
        ThrowableRenderer throwableRenderer;
        String tagName = element.getTagName();
        if (!tagName.equals(CONFIGURATION_TAG)) {
            if (tagName.equals(OLD_CONFIGURATION_TAG)) {
                LogLog.warn("The <configuration> element has been deprecated.");
                LogLog.warn("Use the <log4j:configuration> element instead.");
            } else {
                LogLog.error("DOM element is - not a <log4j:configuration> element.");
                return;
            }
        }
        String strSubst = subst(element.getAttribute("debug"));
        LogLog.debug(new StringBuffer("debug attribute= \"").append(strSubst).append("\".").toString());
        if (!strSubst.equals("") && !strSubst.equals(Configurator.NULL)) {
            LogLog.setInternalDebugging(OptionConverter.toBoolean(strSubst, true));
        } else {
            LogLog.debug("Ignoring debug attribute.");
        }
        String strSubst2 = subst(element.getAttribute(RESET_ATTR));
        LogLog.debug(new StringBuffer("reset attribute= \"").append(strSubst2).append("\".").toString());
        if (!"".equals(strSubst2) && OptionConverter.toBoolean(strSubst2, false)) {
            this.repository.resetConfiguration();
        }
        String strSubst3 = subst(element.getAttribute(CONFIG_DEBUG_ATTR));
        if (!strSubst3.equals("") && !strSubst3.equals(Configurator.NULL)) {
            LogLog.warn("The \"configDebug\" attribute is deprecated.");
            LogLog.warn("Use the \"debug\" attribute instead.");
            LogLog.setInternalDebugging(OptionConverter.toBoolean(strSubst3, true));
        }
        String strSubst4 = subst(element.getAttribute(THRESHOLD_ATTR));
        LogLog.debug(new StringBuffer("Threshold =\"").append(strSubst4).append("\".").toString());
        if (!"".equals(strSubst4) && !Configurator.NULL.equals(strSubst4)) {
            this.repository.setThreshold(strSubst4);
        }
        NodeList childNodes = element.getChildNodes();
        int length = childNodes.getLength();
        for (int i = 0; i < length; i++) {
            Node nodeItem = childNodes.item(i);
            if (nodeItem.getNodeType() == 1) {
                Element element2 = (Element) nodeItem;
                String tagName2 = element2.getTagName();
                if (tagName2.equals(CATEGORY_FACTORY_TAG) || tagName2.equals(LOGGER_FACTORY_TAG)) {
                    parseCategoryFactory(element2);
                }
            }
        }
        for (int i2 = 0; i2 < length; i2++) {
            Node nodeItem2 = childNodes.item(i2);
            if (nodeItem2.getNodeType() == 1) {
                Element element3 = (Element) nodeItem2;
                String tagName3 = element3.getTagName();
                if (tagName3.equals(CATEGORY) || tagName3.equals(LOGGER)) {
                    parseCategory(element3);
                } else if (tagName3.equals(ROOT_TAG)) {
                    parseRoot(element3);
                } else if (tagName3.equals(RENDERER_TAG)) {
                    parseRenderer(element3);
                } else if (tagName3.equals(THROWABLE_RENDERER_TAG)) {
                    if ((this.repository instanceof ThrowableRendererSupport) && (throwableRenderer = parseThrowableRenderer(element3)) != null) {
                        ((ThrowableRendererSupport) this.repository).setThrowableRenderer(throwableRenderer);
                    }
                } else if (!tagName3.equals(APPENDER_TAG) && !tagName3.equals(CATEGORY_FACTORY_TAG) && !tagName3.equals(LOGGER_FACTORY_TAG)) {
                    quietParseUnrecognizedElement(this.repository, element3, this.props);
                }
            }
        }
    }

    protected String subst(String str) {
        return subst(str, this.props);
    }

    public static String subst(String str, Properties properties) {
        try {
            return OptionConverter.substVars(str, properties);
        } catch (IllegalArgumentException e) {
            LogLog.warn("Could not perform variable substitution.", e);
            return str;
        }
    }

    public static void setParameter(Element element, PropertySetter propertySetter, Properties properties) {
        propertySetter.setProperty(subst(element.getAttribute("name"), properties), subst(OptionConverter.convertSpecialChars(element.getAttribute(VALUE_ATTR)), properties));
    }

    public static Object parseElement(Element element, Properties properties, Class cls) throws Exception {
        Object objInstantiateByClassName = OptionConverter.instantiateByClassName(subst(element.getAttribute("class"), properties), cls, null);
        if (objInstantiateByClassName == null) {
            return null;
        }
        PropertySetter propertySetter = new PropertySetter(objInstantiateByClassName);
        NodeList childNodes = element.getChildNodes();
        int length = childNodes.getLength();
        for (int i = 0; i < length; i++) {
            Node nodeItem = childNodes.item(i);
            if (nodeItem.getNodeType() == 1) {
                Element element2 = (Element) nodeItem;
                if (element2.getTagName().equals(PARAM_TAG)) {
                    setParameter(element2, propertySetter, properties);
                } else {
                    parseUnrecognizedElement(objInstantiateByClassName, element2, properties);
                }
            }
        }
        return objInstantiateByClassName;
    }
}
