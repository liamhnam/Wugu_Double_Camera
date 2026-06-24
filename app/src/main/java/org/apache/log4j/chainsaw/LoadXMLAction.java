package org.apache.log4j.chainsaw;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

class LoadXMLAction extends AbstractAction {
    private static final Logger LOG;
    static Class class$org$apache$log4j$chainsaw$LoadXMLAction;
    private final JFileChooser mChooser;
    private final XMLFileHandler mHandler;
    private final JFrame mParent;
    private final XMLReader mParser;

    static {
        Class clsClass$ = class$org$apache$log4j$chainsaw$LoadXMLAction;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.log4j.chainsaw.LoadXMLAction");
            class$org$apache$log4j$chainsaw$LoadXMLAction = clsClass$;
        }
        LOG = Logger.getLogger(clsClass$);
    }

    static Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    LoadXMLAction(JFrame jFrame, MyTableModel myTableModel) throws ParserConfigurationException, SAXException {
        JFileChooser jFileChooser = new JFileChooser();
        this.mChooser = jFileChooser;
        jFileChooser.setMultiSelectionEnabled(false);
        jFileChooser.setFileSelectionMode(0);
        this.mParent = jFrame;
        XMLFileHandler xMLFileHandler = new XMLFileHandler(myTableModel);
        this.mHandler = xMLFileHandler;
        XMLReader xMLReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
        this.mParser = xMLReader;
        xMLReader.setContentHandler(xMLFileHandler);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Logger logger = LOG;
        logger.info("load file called");
        if (this.mChooser.showOpenDialog(this.mParent) == 0) {
            logger.info("Need to load a file");
            File selectedFile = this.mChooser.getSelectedFile();
            logger.info(new StringBuffer("loading the contents of ").append(selectedFile.getAbsolutePath()).toString());
            try {
                JOptionPane.showMessageDialog(this.mParent, new StringBuffer("Loaded ").append(loadFile(selectedFile.getAbsolutePath())).append(" events.").toString(), "CHAINSAW", 1);
            } catch (Exception e) {
                LOG.warn("caught an exception loading the file", e);
                JOptionPane.showMessageDialog(this.mParent, new StringBuffer("Error parsing file - ").append(e.getMessage()).toString(), "CHAINSAW", 0);
            }
        }
    }

    private int loadFile(String str) throws SAXException, IOException {
        int numEvents;
        synchronized (this.mParser) {
            StringBuffer stringBuffer = new StringBuffer("<?xml version=\"1.0\" standalone=\"yes\"?>\n<!DOCTYPE log4j:eventSet [<!ENTITY data SYSTEM \"file:///");
            stringBuffer.append(str);
            stringBuffer.append("\">]>\n<log4j:eventSet xmlns:log4j=\"Claira\">\n&data;\n</log4j:eventSet>\n");
            this.mParser.parse(new InputSource(new StringReader(stringBuffer.toString())));
            numEvents = this.mHandler.getNumEvents();
        }
        return numEvents;
    }
}
