package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BalanceInquiry extends JFrame implements ActionListener {
    JButton backBtn;
    String user, pass;
    BalanceInquiry(String username, String password){
        user = username;
        pass = password;

        ImageIcon atmInterface = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image scaleImage = atmInterface.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon scaledAtmInterfaceImage = new ImageIcon(scaleImage);

        JLabel backgroundImage = new JLabel(scaledAtmInterfaceImage);
        backgroundImage.setBounds(0,0,900,900);
        add(backgroundImage);

        long balance = CalculateBalance.checkRemainingAmount(username);

        JLabel balanceCheck = new JLabel("Your current account balance is " + balance);
        balanceCheck.setFont(new Font("System",Font.BOLD,16));
        balanceCheck.setBounds(170,300,330,20);
        balanceCheck.setForeground(Color.WHITE);
        backgroundImage.add(balanceCheck);

        backBtn = new JButton("Back");
        backBtn.setBounds(355,516,150,25);
        backBtn.addActionListener(this);
        backgroundImage.add(backBtn);

        setSize(900,850);
        setLayout(null);
        setLocation(300,0);
        setUndecorated(true);
        setResizable(false);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backBtn){
            setVisible(false);
            new Transactions(user,pass).setVisible(true);
        }
    }
}
