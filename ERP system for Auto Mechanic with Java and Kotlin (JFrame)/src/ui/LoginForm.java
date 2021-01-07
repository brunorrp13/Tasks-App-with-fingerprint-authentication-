package ui;

import business.CreateBusiness;
import business.LoginBusiness;
import entity.UserEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginForm extends JFrame {
    private JButton sairButton;
    private JButton loginButton;
    private JPanel rootPanel;
    private JTextField textLogin;
    private JPasswordField textPassword;
    private JButton registerButton;
    private JButton button3;
    private LoginBusiness mLoginBusiness;

    public LoginForm() {

        setContentPane(rootPanel);
        setSize(500,250);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mLoginBusiness = new LoginBusiness();

        setListeners();

    }


    private void login() {
        String login = textLogin.getText();
        String password = String.valueOf(textPassword.getPassword());
        UserEntity user = new UserEntity(login, password);
        mLoginBusiness.validation(user);
    }


    private void setListeners() {

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    login();
                    new MenuForm();
                    dispose();
                } catch (Exception execp) {
                   JOptionPane.showMessageDialog(new JFrame(), execp.getMessage());
                }

            }
        });

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                loginButton.setBackground(Color.CYAN);
            }
        });

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                loginButton.setBackground(null);
            }
        });

        sairButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                sairButton.setBackground(Color.MAGENTA);
            }
        });

        sairButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                sairButton.setBackground(null);
            }
        });

        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                registerButton.setBackground(Color.YELLOW);
            }
        });

        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                registerButton.setBackground(null);
            }
        });


        sairButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        dispose();
        }
    });

       registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserRegister();
                dispose();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
