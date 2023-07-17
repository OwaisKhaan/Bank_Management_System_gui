package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class Withdraw extends JFrame implements ActionListener {
    JButton withdrawAmountBtn, backBtn;
    JTextField amountTextField;
    String username, password;

    Withdraw(String username, String password) {
        setLayout(null);

        this.username = username;
        this.password = password;

        // Background image of ATM
        ImageIcon atmInterface = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image scaleImage = atmInterface.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon scaledAtmInterfaceImage = new ImageIcon(scaleImage);

        JLabel backgroundImage = new JLabel(scaledAtmInterfaceImage);
        backgroundImage.setBounds(0, 0, 900, 900);
        add(backgroundImage);


        amountTextField = new JTextField();
        amountTextField.setFont(new Font("System", Font.BOLD, 16));
        amountTextField.setBounds(170, 300, 330, 20);
        backgroundImage.add(amountTextField);


        withdrawAmountBtn = new JButton("Withdraw");
        withdrawAmountBtn.setBounds(355, 484, 150, 25);
        withdrawAmountBtn.addActionListener(this);
        backgroundImage.add(withdrawAmountBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(355, 516, 150, 25);
        backBtn.addActionListener(this);
        backgroundImage.add(backBtn);


        setSize(900, 850);
        setLocation(300, 0);
        setUndecorated(true);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = username;
        String pass = password;

        if (e.getSource() == backBtn) {
            setVisible(false);
            new Transactions(user, pass).setVisible(true);
        } else if (e.getSource() == withdrawAmountBtn) {
            Date date = new Date();
            String type = "withdraw";
            String amountWithdraw = amountTextField.getText();
            try {
            long verifyAmount = CalculateBalance.checkRemainingAmount(user);
            if (verifyAmount > Integer.parseInt(amountWithdraw)){

                Conn c = new Conn();
                String query = "insert into bank values(default,?,?,?,?)";
                PreparedStatement statement = c.con.prepareStatement(query);
                statement.setString(1, user);
                statement.setString(2, date.toString());
                statement.setString(3, type);
                statement.setString(4, amountWithdraw);

                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Successfully withdraw " + amountWithdraw + " rupees from your account");
                    setVisible(false);
                    new Transactions(user, pass).setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Insufficient amount in your account for transaction");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }



}