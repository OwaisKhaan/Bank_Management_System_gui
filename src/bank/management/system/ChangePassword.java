package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class ChangePassword extends JFrame implements ActionListener {
    JTextField newPasswordTextField, confirmPasswordTextField;
    JButton backBtn, updatePasswordBtn;
    String username, password;

    ChangePassword(String username, String password){
        this.username = username;
        this.password = password;

        ImageIcon atmInterfaceImage = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image scaleImage = atmInterfaceImage.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon atmScaledInterfaceImage = new ImageIcon(scaleImage);

        JLabel image = new JLabel(atmScaledInterfaceImage);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel newPassword = new JLabel("Enter new password");
        newPassword.setBounds(170,300,150,24);
        newPassword.setFont(new Font("Raleway",Font.BOLD,18));
        newPassword.setForeground(Color.WHITE);
        image.add(newPassword);


        newPasswordTextField = new JTextField();
        newPasswordTextField.setFont(new Font("System", Font.BOLD, 16));
        newPasswordTextField.setBounds(170, 330, 330, 20);
        image.add(newPasswordTextField);



        JLabel confirmPassword = new JLabel("Enter new password");
        confirmPassword.setBounds(170,370,150,24);
        confirmPassword.setFont(new Font("Raleway",Font.BOLD,18));
        confirmPassword.setForeground(Color.WHITE);
        image.add(confirmPassword);

        confirmPasswordTextField = new JTextField();
        confirmPasswordTextField.setFont(new Font("System", Font.BOLD, 16));
        confirmPasswordTextField.setBounds(170, 400, 330, 20);
        image.add(confirmPasswordTextField);


        updatePasswordBtn = new JButton("Update password");
        updatePasswordBtn.setBounds(355,484,150,25);
        updatePasswordBtn.addActionListener(this);
        image.add(updatePasswordBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(355, 516, 150, 25);
        backBtn.addActionListener(this);
        image.add(backBtn);



        setSize(900,850);
        setLocation(300,0);
        setLayout(null);
        setUndecorated(true);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = username;
        String pass = password;
        String newPassword = newPasswordTextField.getText();
        String confirmPassword = confirmPasswordTextField.getText();

        if (e.getSource() == backBtn){
            setVisible(false);
            new Transactions(user,pass).setVisible(true);
        }

        else if (e.getSource() == updatePasswordBtn) {
            if(!newPassword.equals("") && !confirmPassword.equals("") && newPassword.equals(confirmPassword)){
                try {
                    Conn conn = new Conn();
                    String query = "Update login set password = ? where username = ?";
                    PreparedStatement statement = conn.con.prepareStatement(query);
                    statement.setString(1,newPassword);
                    statement.setString(2,user);
                    statement.executeUpdate();

                    JOptionPane.showMessageDialog(null,"Password changes successfully");


                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }else{
                JOptionPane.showMessageDialog(null, "Empty fields or mismatched passwords");
            }

        }
    }
}
