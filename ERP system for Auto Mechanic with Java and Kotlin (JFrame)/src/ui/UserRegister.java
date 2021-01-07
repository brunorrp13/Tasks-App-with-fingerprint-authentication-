package ui;

import business.CreateBusiness;
import entity.UserEntity;
import repository.RegisterRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserRegister extends JFrame  {
    private JPanel rootPanel;
    private JButton returnButton;
    private JButton createButton;
    private JTextField textLogin;
    private JPasswordField passwordField1;
    private JTextField textPassword;
    private CreateBusiness mCreateBusiness;

    public UserRegister() {
        setContentPane(rootPanel);
        setSize(500,250);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mCreateBusiness = new CreateBusiness();
        setListeners();
        System.out.println(RegisterRepository.Companion.getUsers());
    }

    private void setListeners() {

      returnButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              new LoginForm();
              dispose();
          }
      });

        createButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                createButton.setBackground(Color.CYAN);
            }
        });

        createButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                createButton.setBackground(null);
            }
        });

        returnButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                returnButton.setBackground(Color.MAGENTA);
            }
        });

        returnButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                returnButton.setBackground(null);
            }
        });

       createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String login = textLogin.getText();
                    String password = String.valueOf(passwordField1.getPassword());
                    UserEntity user = new UserEntity(login, password);
                    mCreateBusiness.createUser(user);
                    new LoginForm();
                    dispose();
                } catch (Exception execp) {
                    JOptionPane.showMessageDialog(new JFrame(), execp.getMessage());
                }

            }
        });

    }

}
