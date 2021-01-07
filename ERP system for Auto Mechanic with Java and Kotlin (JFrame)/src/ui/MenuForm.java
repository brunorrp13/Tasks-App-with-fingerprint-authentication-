package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MenuForm extends JFrame {
    private JButton clientesButton;
    private JButton financeAndAccountingButton;
    private JButton partsButton;
    private JButton quotationsButton;
    private JButton usersButton;
    private JButton vehiclesButton;
    private JButton suppliersButton;
    private JButton quitButton;
    private JButton reportsButton;
    private JPanel rootPanel;

    public MenuForm() {

        setContentPane(rootPanel);
        setSize(600,250);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setListeners();
    }
    private void setListeners() {

        clientesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                clientesButton.setBackground(Color.CYAN);
            }
        });

        clientesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                clientesButton.setBackground(null);
            }
        });

        quitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                quitButton.setBackground(Color.magenta);
            }
        });

        quitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                quitButton.setBackground(null);
            }
        });

     quitButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             new LoginForm();
             dispose();
         }
     });

     clientesButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             new MainForm();
             dispose();
         }
     });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginForm();
                dispose();
            }
        });

    }
}