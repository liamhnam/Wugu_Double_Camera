package org.apache.log4j.chainsaw;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.apache.log4j.Logger;

class ExitAction extends AbstractAction {
    public static final ExitAction INSTANCE;
    private static final Logger LOG;
    static Class class$org$apache$log4j$chainsaw$ExitAction;

    static {
        Class clsClass$ = class$org$apache$log4j$chainsaw$ExitAction;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.log4j.chainsaw.ExitAction");
            class$org$apache$log4j$chainsaw$ExitAction = clsClass$;
        }
        LOG = Logger.getLogger(clsClass$);
        INSTANCE = new ExitAction();
    }

    static Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    private ExitAction() {
    }

    public void actionPerformed(ActionEvent actionEvent) {
        LOG.info("shutting down");
        System.exit(0);
    }
}
