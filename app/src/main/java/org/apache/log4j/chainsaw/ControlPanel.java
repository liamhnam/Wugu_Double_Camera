package org.apache.log4j.chainsaw;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

class ControlPanel extends JPanel {
    private static final Logger LOG;
    static Class class$org$apache$log4j$chainsaw$ControlPanel;

    static {
        Class clsClass$ = class$org$apache$log4j$chainsaw$ControlPanel;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.log4j.chainsaw.ControlPanel");
            class$org$apache$log4j$chainsaw$ControlPanel = clsClass$;
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

    ControlPanel(final MyTableModel myTableModel) {
        setBorder(BorderFactory.createTitledBorder("Controls: "));
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        setLayout(gridBagLayout);
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridy = 0;
        JLabel jLabel = new JLabel("Filter Level:");
        gridBagLayout.setConstraints(jLabel, gridBagConstraints);
        add(jLabel);
        gridBagConstraints.gridy++;
        JLabel jLabel2 = new JLabel("Filter Thread:");
        gridBagLayout.setConstraints(jLabel2, gridBagConstraints);
        add(jLabel2);
        gridBagConstraints.gridy++;
        JLabel jLabel3 = new JLabel("Filter Logger:");
        gridBagLayout.setConstraints(jLabel3, gridBagConstraints);
        add(jLabel3);
        gridBagConstraints.gridy++;
        JLabel jLabel4 = new JLabel("Filter NDC:");
        gridBagLayout.setConstraints(jLabel4, gridBagConstraints);
        add(jLabel4);
        gridBagConstraints.gridy++;
        JLabel jLabel5 = new JLabel("Filter Message:");
        gridBagLayout.setConstraints(jLabel5, gridBagConstraints);
        add(jLabel5);
        gridBagConstraints.weightx = 1.0d;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridy = 0;
        Level[] levelArr = {Level.FATAL, Level.ERROR, Level.WARN, Level.INFO, Level.DEBUG, Level.TRACE};
        final JComboBox jComboBox = new JComboBox(levelArr);
        Level level = levelArr[5];
        jComboBox.setSelectedItem(level);
        myTableModel.setPriorityFilter(level);
        gridBagLayout.setConstraints(jComboBox, gridBagConstraints);
        add(jComboBox);
        jComboBox.setEditable(false);
        jComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                myTableModel.setPriorityFilter((Priority) jComboBox.getSelectedItem());
            }
        });
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridy++;
        final JTextField jTextField = new JTextField("");
        jTextField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent documentEvent) {
                myTableModel.setThreadFilter(jTextField.getText());
            }

            public void removeUpdate(DocumentEvent documentEvent) {
                myTableModel.setThreadFilter(jTextField.getText());
            }

            public void changedUpdate(DocumentEvent documentEvent) {
                myTableModel.setThreadFilter(jTextField.getText());
            }
        });
        gridBagLayout.setConstraints(jTextField, gridBagConstraints);
        add(jTextField);
        gridBagConstraints.gridy++;
        final JTextField jTextField2 = new JTextField("");
        jTextField2.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent documentEvent) {
                myTableModel.setCategoryFilter(jTextField2.getText());
            }

            public void removeUpdate(DocumentEvent documentEvent) {
                myTableModel.setCategoryFilter(jTextField2.getText());
            }

            public void changedUpdate(DocumentEvent documentEvent) {
                myTableModel.setCategoryFilter(jTextField2.getText());
            }
        });
        gridBagLayout.setConstraints(jTextField2, gridBagConstraints);
        add(jTextField2);
        gridBagConstraints.gridy++;
        final JTextField jTextField3 = new JTextField("");
        jTextField3.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent documentEvent) {
                myTableModel.setNDCFilter(jTextField3.getText());
            }

            public void removeUpdate(DocumentEvent documentEvent) {
                myTableModel.setNDCFilter(jTextField3.getText());
            }

            public void changedUpdate(DocumentEvent documentEvent) {
                myTableModel.setNDCFilter(jTextField3.getText());
            }
        });
        gridBagLayout.setConstraints(jTextField3, gridBagConstraints);
        add(jTextField3);
        gridBagConstraints.gridy++;
        final JTextField jTextField4 = new JTextField("");
        jTextField4.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent documentEvent) {
                myTableModel.setMessageFilter(jTextField4.getText());
            }

            public void removeUpdate(DocumentEvent documentEvent) {
                myTableModel.setMessageFilter(jTextField4.getText());
            }

            public void changedUpdate(DocumentEvent documentEvent) {
                myTableModel.setMessageFilter(jTextField4.getText());
            }
        });
        gridBagLayout.setConstraints(jTextField4, gridBagConstraints);
        add(jTextField4);
        gridBagConstraints.weightx = 0.0d;
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        JButton jButton = new JButton("Exit");
        jButton.setMnemonic('x');
        jButton.addActionListener(ExitAction.INSTANCE);
        gridBagLayout.setConstraints(jButton, gridBagConstraints);
        add(jButton);
        gridBagConstraints.gridy++;
        JButton jButton2 = new JButton("Clear");
        jButton2.setMnemonic('c');
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                myTableModel.clear();
            }
        });
        gridBagLayout.setConstraints(jButton2, gridBagConstraints);
        add(jButton2);
        gridBagConstraints.gridy++;
        final JButton jButton3 = new JButton("Pause");
        jButton3.setMnemonic('p');
        jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                myTableModel.toggle();
                jButton3.setText(myTableModel.isPaused() ? "Resume" : "Pause");
            }
        });
        gridBagLayout.setConstraints(jButton3, gridBagConstraints);
        add(jButton3);
    }
}
