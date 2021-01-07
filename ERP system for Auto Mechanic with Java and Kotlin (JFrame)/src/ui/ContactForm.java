package ui;

import business.ContactBusiness;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

public class ContactForm extends JFrame {

    private JPanel rootPanel;
    private JTextField textName;
    private JFormattedTextField textPhone;
    private JButton buttonCancel;
    private JButton buttonSave;
    private JTextField textEmail;
    private JTextField textField6;
    private JTextField textSocialSecurity;
    private JTextField textField9;
    private JButton saveAndQuitButton;
    private MaskFormatter ftmPhone;

    private ContactBusiness mContactBusiness;


    public ContactForm() {

        setContentPane(rootPanel);
        setSize(700,350);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mContactBusiness = new ContactBusiness();
        setListeners();
        try {
            TextFieldWithMask();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

        public void TextFieldWithMask() throws ParseException {

            ftmPhone = new MaskFormatter("###-###-####");
            ftmPhone.install(textPhone);

            ftmPhone.setValidCharacters("0123456789");
            textPhone.setColumns(6);
        }

        private void save() {

                String name = textName.getText();
                String phone = textPhone.getText();
                mContactBusiness.save(name, phone);
    }


    private void setListeners() {

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                save();
                new MainForm();
                dispose();
                } catch (Exception execp) {

                    JOptionPane.showMessageDialog(new JFrame(), execp.getMessage());

                }
            }
        });

        buttonSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                buttonSave.setBackground(Color.CYAN);
            }
        });

        buttonSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                buttonSave.setBackground(null);
            }
        });

        buttonCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                buttonCancel.setBackground(Color.MAGENTA);
            }
        });

        buttonCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                buttonCancel.setBackground(null);
            }
        });

        saveAndQuitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                saveAndQuitButton.setBackground(Color.YELLOW);
            }
        });

        saveAndQuitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                saveAndQuitButton.setBackground(null);
            }
        });

        saveAndQuitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                saveAndQuitButton.setBackground(null);
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuForm();
                dispose();
            }
        });

        saveAndQuitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                save();
                new MenuForm();
                dispose();
                } catch (Exception execp) {

                    JOptionPane.showMessageDialog(new JFrame(), execp.getMessage());

                }

            }
        });
    }

}
