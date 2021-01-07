package ui;

import business.ContactBusiness;
import entity.ContactEntity;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MainForm extends JFrame {
    private JPanel rootPanel;
    private JButton buttonNewContact;
    private JButton buttonRemove;
    private JTable tableContacts;
    private JLabel labelContactCount;
    private JButton quitButton;

    private ContactBusiness mContactBusiness;
    private String mName = "";
    private String mPhone = "";


    public MainForm() {

        setContentPane(rootPanel);
        setSize(500, 250);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mContactBusiness = new ContactBusiness();

        loadContacts();
        setListeners();
    }

    private void loadContacts() {
        List<ContactEntity> contactList = mContactBusiness.getList();

        String[] columnNames = new String[2];
        columnNames[0] = "Name";
        columnNames[1] = "Telephone";

        DefaultTableModel model = new DefaultTableModel(new Object[0][0], columnNames);

        for (ContactEntity i : contactList) {
            Object[] o = new Object[2];

            o[0] = i.getName();
            o[1] = i.getPhone();

            model.addRow(o);
        }

        tableContacts.clearSelection();
        tableContacts.setModel(model);

        labelContactCount.setText(mContactBusiness.getContactCountDescription());
    }

    private void setListeners() {
        buttonNewContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ContactForm();
                dispose();
            }
        });

        buttonNewContact.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                buttonNewContact.setBackground(Color.CYAN);
            }
        });

        buttonNewContact.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                buttonNewContact.setBackground(null);
            }
        });

        quitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                quitButton.setBackground(Color.MAGENTA);
            }
        });

        quitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                quitButton.setBackground(null);
            }
        });

        buttonRemove.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                buttonRemove.setBackground(Color.YELLOW);
            }
        });

        buttonRemove.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                buttonRemove.setBackground(null);
            }
        });


        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuForm();
                dispose();
            }
        });

        tableContacts.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {

                    if (tableContacts.getSelectedRow() != -1) {

                        mName = tableContacts.getValueAt(tableContacts.getSelectedRow(), 0).toString();
                        mPhone = tableContacts.getValueAt(tableContacts.getSelectedRow(), 1).toString();
                    }

                }
            }
        });

        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mContactBusiness.delete(mName, mPhone);
                    loadContacts();

                    mName = "";
                    mPhone = "";
                } catch (Exception execp) {

                    JOptionPane.showMessageDialog(new JFrame(), execp.getMessage());

                }

            }
        });
    }

}
