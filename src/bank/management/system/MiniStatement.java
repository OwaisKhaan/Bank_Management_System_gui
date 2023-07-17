package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MiniStatement extends JFrame implements ActionListener {
    JButton exitBtn;
    MiniStatement(String username, String password)  {
        setSize(400,700);
        setLayout(null);
        setTitle("Mini Statement");
        getContentPane().setBackground(Color.WHITE);
        setResizable(false);
        setVisible(true);

        JLabel bankName = new JLabel("Habib Bank Limited");
        bankName.setBounds(100,50,200,30);
        bankName.setBackground(Color.WHITE);
        bankName.setFont(new Font("Raleway",Font.BOLD,20));
        add(bankName);

        int count = 1;
        JLabel transactionDetail = new JLabel();
        transactionDetail.setBounds(35, 100, 300, 200);
        try {
            ResultSet rs = retrieveInfo(username);
            while (rs.next()) {
                if(count == 10) break;
                String date = rs.getString("date");
                String type =  rs.getString("type");
                String amount = rs.getString("amount");
               transactionDetail.setText(transactionDetail.getText() + "<html>" + rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + " &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; " + rs.getString("amount") + "<br><br><html>");
                count++;
            }

            add(transactionDetail);

        }catch (Exception ex){
            ex.printStackTrace();
        }


        JLabel currentBalance = new JLabel("Current Amount : " + CalculateBalance.checkRemainingAmount(username));
        currentBalance.setBounds(35,480,200,30);
        currentBalance.setBackground(Color.WHITE);
        currentBalance.setFont(new Font("Raleway",Font.BOLD,16));
        add(currentBalance);

        exitBtn = new JButton("Exit");
        exitBtn.setBounds(100,550,200,30);
        exitBtn.addActionListener(this);
        add(exitBtn);


    }

    private static ResultSet retrieveInfo(String username){
        ResultSet resultSet = null;
        try {
            Conn c = new Conn();
            String query = "select * from bank where username = ? ORDER BY tid DESC";
            PreparedStatement statement = c.con.prepareStatement(query);
            statement.setString(1,username);
            resultSet = statement.executeQuery();

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return resultSet;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitBtn){
            setVisible(false);
        }
    }
}
