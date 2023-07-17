package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class SignUpThree extends JFrame implements ActionListener {
     private String formNumber;
    JTextField usernameTextField;
    JPasswordField  passwordField, confirmPasswordField;

    JCheckBox termsAndConditions;

    JButton submitButton;
    SignUpThree(String formNumber){
        this.formNumber = formNumber;

        setLayout(null);
        setTitle("New Account Registration Form - Page 3");

        setSize(850,800);
        setLocation(350,10);
        setVisible(true);
        getContentPane().setBackground(Color.WHITE);

        setResizable(false);

        JLabel securityDetails = new JLabel("Security Details");
        securityDetails.setFont(new Font("Raleway",Font.BOLD,22));
        securityDetails.setBounds(330,80,350,30);
        add(securityDetails);

        JLabel username = new JLabel("Username :");
        username.setFont(new Font("Raleway",Font.BOLD,20));
        username.setBounds(100,150,250,30);
        add(username);
        usernameTextField = new JTextField();
        usernameTextField.setFont(new Font("Raleway",Font.BOLD,14));
        usernameTextField.setBounds(300,150,350,30);
        add(usernameTextField);


        JLabel password = new JLabel("Password :");
        password.setFont(new Font("Raleway",Font.BOLD,20));
        password.setBounds(100,200,250,30);
        add(password);
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Raleway",Font.BOLD,14));
        passwordField.setBounds(300,200,350,30);
        add(passwordField);

        JLabel confirmPassword = new JLabel("Confirm Password :");
        confirmPassword.setFont(new Font("Raleway",Font.BOLD,20));
        confirmPassword.setBounds(100,250,250,30);
        add(confirmPassword);
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(new Font("Raleway",Font.BOLD,14));
        confirmPasswordField.setBounds(300,250,350,30);
        add(confirmPasswordField);

        termsAndConditions = new JCheckBox("Accept terms and conditions");
        termsAndConditions.setFont(new Font("Raleway",Font.BOLD,20));
        termsAndConditions.setBounds(100,350,400,30);
        termsAndConditions.setBackground(Color.WHITE);
        add(termsAndConditions);

        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Raleway",Font.BOLD,16));
        submitButton.setBounds(550,650,100,30);
        submitButton.addActionListener(this);
        add(submitButton);



    }




    @Override
    public void actionPerformed(ActionEvent e) {
        String formNo = formNumber;
        String username = usernameTextField.getText();
        String password = String.valueOf(passwordField.getPassword());
        String confirmPassword = String.valueOf(confirmPasswordField.getPassword());


        try {
            if (username.equals("") || username.length() < 8){
                JOptionPane.showMessageDialog(null, "Username must have atleast 8 characters");
            } else if (!password.equals(confirmPassword) ) {
                JOptionPane.showMessageDialog(null, "Mismatched password");
            } else if (password.length() < 8 || confirmPassword.length() < 8) {
                JOptionPane.showMessageDialog(null,"Password must be 8 characters");
            } else if (!termsAndConditions.isSelected()) {
                JOptionPane.showMessageDialog(null,"Accept terms and conditions");
            } else {
                Conn c = new Conn();
                String query = "insert into login values(?,?,?)";
                PreparedStatement statement = c.con.prepareStatement(query);
                statement.setString(1,formNo);
                statement.setString(2,username);
                statement.setString(3,password);

                statement.executeUpdate();
                setVisible(false);
                new Login().setVisible(true);
                JOptionPane.showMessageDialog(null,"New user created successfully");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
