package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

    JButton fc100, fc200, fc500, fc1000, fc5000, fc10000, backBtn;
    String username, password;

    FastCash(String username, String password) {
        setLayout(null);


        this.username = username;
        this.password = password;

        ImageIcon atmInterface = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image scaledImage = atmInterface.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon scaledAtmInterface = new ImageIcon(scaledImage);

        JLabel atmImage = new JLabel(scaledAtmInterface);
        atmImage.setBounds(0, 0, 900, 900);
        add(atmImage);

        JLabel title = new JLabel("Please select your Withdraw amount");
        title.setFont(new Font("System", Font.BOLD, 16));
        title.setBounds(210, 300, 700, 35);
        title.setForeground(Color.WHITE);
        atmImage.add(title);

        fc100 = new JButton("100");
        fc100.setBounds(170, 420, 150, 25);
        fc100.addActionListener(this);
        atmImage.add(fc100);

        fc200 = new JButton("200");
        fc200.setBounds(355, 420, 150, 25);
        fc200.addActionListener(this);
        atmImage.add(fc200);

        fc500 = new JButton("500");
        fc500.setBounds(170, 452, 150, 25);
        fc500.addActionListener(this);
        atmImage.add(fc500);

        fc1000 = new JButton("1000");
        fc1000.setBounds(355, 452, 150, 25);
        fc1000.addActionListener(this);
        atmImage.add(fc1000);

        fc5000 = new JButton("5000");
        fc5000.setBounds(170, 484, 150, 25);
        fc5000.addActionListener(this);
        atmImage.add(fc5000);

        fc10000 = new JButton("10000");
        fc10000.setBounds(355, 484, 150, 25);
        fc10000.addActionListener(this);
        atmImage.add(fc10000);

        backBtn = new JButton("Exit");
        backBtn.setBounds(355, 516, 150, 25);
        backBtn.addActionListener(this);
        atmImage.add(backBtn);


        setSize(900, 850);
        setLocation(300, 0);
        setUndecorated(true);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int verifyAmount = CalculateBalance.checkRemainingAmount(username);

        if (e.getSource() == backBtn) {
            setVisible(false);
            new Transactions(username, password).setVisible(true);
        }
        else if (e.getSource() == fc100) {
            if (verifyAmount > 100) {
                withdrawProcess(username, "100");
            } else {
                JOptionPane.showMessageDialog(null, "Insufficient balance for this transaction");
            }

        } else if (e.getSource() == fc200) {
            if (verifyAmount > 200) {
                withdrawProcess(username, "200");
            } else {
                JOptionPane.showMessageDialog(null, "Insufficient balance for this transaction");
            }


        } else if (e.getSource() == fc500) {
            if (verifyAmount > 500) {
                withdrawProcess(username, "500");
            } else {
                JOptionPane.showMessageDialog(null, "Insufficient balance for this transaction");
            }


        } else if (e.getSource() == fc1000) {
            if (verifyAmount > 1000) {
                withdrawProcess(username, "1000");
            } else {
                JOptionPane.showMessageDialog(null, "Insufficient balance for this transaction");
            }


        } else if (e.getSource() == fc5000) {
            if (verifyAmount > 5000) {
                withdrawProcess(username, "5000");
            } else {
                JOptionPane.showMessageDialog(null, "Insufficient balance for this transaction");
            }


        } else if (e.getSource() == fc10000) {
            if (verifyAmount > 10000) {
                withdrawProcess(username, "10000");
            } else {
                JOptionPane.showMessageDialog(null, "Insufficient balance for this transaction");
            }


        }
    }

    private static void withdrawProcess(String username, String amount) {
        Date date = new Date();
        String type = "withdraw";
        String amountWithdraw = amount;
        try {
            Conn c = new Conn();
            String query = "insert into bank values(default,?,?,?,?)";
            PreparedStatement statement = c.con.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, date.toString());
            statement.setString(3, type);
            statement.setString(4, amountWithdraw);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfully withdraw " + amountWithdraw + " rupees from your account");

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}