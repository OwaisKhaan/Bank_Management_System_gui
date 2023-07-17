package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transactions extends JFrame implements ActionListener {
    JButton depositBtn, withdrawBtn, fastCashBtn, passwordChangeBtn, miniStatementBtn, balanceEnquiryBtn, exitBtn;
    String username, password;


    Transactions(String username, String password){
        setLayout(null);


        this.username = username;
        this.password = password;

        ImageIcon atmInterface = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image scaledImage = atmInterface.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon scaledAtmInterface = new ImageIcon(scaledImage);

        JLabel atmImage = new JLabel(scaledAtmInterface);
        atmImage.setBounds(0,0,900,900);
        add(atmImage);

        JLabel title = new JLabel("Please select your Transactions");
        title.setFont(new Font("System",Font.BOLD,16));
        title.setBounds(210,300,700,35);
        title.setForeground(Color.WHITE);
        atmImage.add(title);

        depositBtn = new JButton("Deposit Amount");
        depositBtn.setBounds(170,420,150,25);
        depositBtn.addActionListener(this);
        atmImage.add(depositBtn);

        withdrawBtn = new JButton("Cash Withdraw");
        withdrawBtn.setBounds(355,420,150,25);
        withdrawBtn.addActionListener(this);
        atmImage.add(withdrawBtn);

        fastCashBtn = new JButton("Fast Cash");
        fastCashBtn.setBounds(170,452,150,25);
        fastCashBtn.addActionListener(this);
        atmImage.add(fastCashBtn);

        miniStatementBtn = new JButton("Mini Statement");
        miniStatementBtn.setBounds(355,452,150,25);
        miniStatementBtn.addActionListener(this);
        atmImage.add(miniStatementBtn);

        passwordChangeBtn = new JButton("Pin Change");
        passwordChangeBtn.setBounds(170,484,150,25);
        passwordChangeBtn.addActionListener(this);
        atmImage.add(passwordChangeBtn);

        balanceEnquiryBtn = new JButton("Balance Enquiry");
        balanceEnquiryBtn.setBounds(355,484,150,25);
        balanceEnquiryBtn.addActionListener(this);
        atmImage.add(balanceEnquiryBtn);

        exitBtn = new JButton("Exit");
        exitBtn.setBounds(355,516,150,25);
        exitBtn.addActionListener(this);
        atmImage.add(exitBtn);






        setSize(900,850);
        setLocation(300,0);
        setUndecorated(true);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // to exit from system
        if(e.getSource() == exitBtn){
            System.exit(0);
            new Login().setVisible(true);
        }

        else if (e.getSource() == depositBtn) {
            setVisible(false);
            new Deposit(username,password).setVisible(true);

        }

        else if (e.getSource() == withdrawBtn) {
            setVisible(false);
            new Withdraw(username,password).setVisible(true);

        }

        else if (e.getSource() == balanceEnquiryBtn) {
            setVisible(false);
            new BalanceInquiry(username, password).setVisible(true);
        }
        else if (e.getSource() == miniStatementBtn) {
            new MiniStatement(username,password).setVisible(true);
        }

        else if (e.getSource() == passwordChangeBtn){
            setVisible(false);
            new ChangePassword(username, password).setVisible(true);
        } else if (e.getSource() == fastCashBtn) {
            setVisible(false);
            new FastCash(username,password).setVisible(true);
        }


    }
}
