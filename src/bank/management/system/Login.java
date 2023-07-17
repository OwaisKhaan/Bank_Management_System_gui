package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame implements ActionListener {
    JButton loginBtn, clearBtn, signUpBtn;
    JTextField usernameTextField;
    JPasswordField passwordTextField;
    Login(){
        setTitle("AUTOMATED TELLER MACHINE");

        //there are different layout in Jframe default is border layout
        setLayout(null);

        //to place image in jfame we use classloader
        ImageIcon logoImage = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));

        //this line is used to scale the image
        Image scaleLogoImage = logoImage.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon convertedImage = new ImageIcon(scaleLogoImage);

        //we use jlabel to put  ilage inside it and display on the frame
        JLabel label = new JLabel(convertedImage);

        //in this line we set the location of image (lable)
        label.setBounds(70,10,100,100);

        //add is use to add any component to frame
        add(label);

        //write text on frame
        JLabel bankName = new JLabel("Welcome to HBL");
        bankName.setFont(new Font("Osward",Font.BOLD,36));
        bankName.setBounds(250, 40, 400, 40);
        add(bankName);

        // card number
        JLabel cardNumber = new JLabel("Username :");
        cardNumber.setFont(new Font("Raleway",Font.BOLD,24));
        cardNumber.setBounds(200, 160, 400, 30);
        add(cardNumber);

        // card number textfield
        usernameTextField = new JTextField();
        usernameTextField.setBounds( 350, 160,250,30);
        usernameTextField.setFont(new Font("Arial",Font.CENTER_BASELINE,18));
        add(usernameTextField);

        // pin number
        JLabel pinNumber = new JLabel("Password :");
        pinNumber.setFont(new Font("Raleway",Font.BOLD,24));
        pinNumber.setBounds(200, 220, 200, 30);
        add(pinNumber);

        // pin number passwordField
        passwordTextField = new JPasswordField();
        passwordTextField.setBounds( 350, 220,250,30);
        passwordTextField.setFont(new Font("Arial",Font.CENTER_BASELINE,18));
        add(passwordTextField);

        loginBtn = new JButton("SIGN IN");
        loginBtn.setBounds(350, 300, 100,30);
        loginBtn.addActionListener(this);
        add(loginBtn);

        clearBtn = new JButton("CLEAR");
        clearBtn.setBounds(500, 300, 100,30);
        clearBtn.addActionListener(this);
        add(clearBtn);

        signUpBtn = new JButton("SIGN UP");
        signUpBtn.setBounds(350, 350, 250,30);
        signUpBtn.addActionListener(this);
        add(signUpBtn);


        setSize(800,480);
        setVisible(true);
        setLocation(350,200);

        getContentPane().setBackground(Color.white);



    }
    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // clearn button

        if(e.getSource() == clearBtn){
            usernameTextField.setText("");
            passwordTextField.setText("");
        }

        // login button configuration

        else if (e.getSource() == loginBtn) {
            String username = usernameTextField.getText();
            String password = String.valueOf(passwordTextField.getPassword());
            try {
                boolean isUserExists = validation(username,password);
                if(isUserExists){
                    setVisible(false);
                    new Transactions(username,password).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null,"Invalid username and password");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

        // open sign up button

        else if (e.getSource() == signUpBtn) {
            setVisible(false);
            new SignUpOne().setVisible(true);
        }

    }


    // method to find existing user with username and password
    private boolean validation(String username, String password) throws SQLException {
        Conn c = new Conn();
        String query = "Select * from login where username = ? and password = ?";
        PreparedStatement statement = c.con.prepareStatement(query);
        statement.setString(1,username);
        statement.setString(2,password);

        ResultSet rs = statement.executeQuery();
        if (rs.next()){
            return true;
        }

        return false;
    }
}
