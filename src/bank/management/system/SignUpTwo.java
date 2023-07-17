package bank.management.system;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpTwo extends JFrame implements ActionListener {
    String formNumber;
    JTextField incomeTextField,cnicTextField;
    JComboBox religionBox, accountCategoryBox, qualificationBox, occupationBox;
    JRadioButton seniorCitizenY, seniorCitizenN, existingAccountY, existingAccountN;

    JButton nextButton;
    SignUpTwo(String formNumber){
        setTitle("New Account Registration Form - Page 2");

        //set layout
        setLayout(null);
        setResizable(false);

        this.formNumber = formNumber;


        JLabel additionalDetails = new JLabel("Additional Details");
        additionalDetails.setFont(new Font("Raleway",Font.BOLD,22));
        additionalDetails.setBounds(330,80,350,30);
        add(additionalDetails);

        JLabel religion = new JLabel("Religion : ");
        religion.setFont(new Font("Raleway",Font.BOLD,20));
        religion.setBounds(100,150,150,30);
        add(religion);
        String[] religionList = {"","Muslim","Hindu","Cristian","Sikh","Other"};
        religionBox = new JComboBox(religionList);
        religionBox.setFont(new Font("Raleway",Font.BOLD,14));
        religionBox.setBounds(300,150,350,30);
        add(religionBox);



        JLabel category = new JLabel("Category : ");
        category.setFont(new Font("Raleway",Font.BOLD,20));
        category.setBounds(100,200,200,30);
        add(category);
        String[] categoryList = {"","Current Account","Savings Account", "Others"};
        accountCategoryBox = new JComboBox(categoryList);
        accountCategoryBox.setFont(new Font("Raleway",Font.BOLD,14));
        accountCategoryBox.setBounds(300,200,350,30);
        add(accountCategoryBox);

        JLabel income = new JLabel("Income : ");
        income.setFont(new Font("Raleway",Font.BOLD,20));
        income.setBounds(100,250,150,30);
        add(income);
        incomeTextField = new JTextField();
        incomeTextField.setBounds(300,250,350,30);
        incomeTextField.setFont(new Font("Raleway",Font.BOLD,20));
        incomeTextField.setForeground(Color.BLACK);
        add(incomeTextField);

        JLabel qualification = new JLabel("Qualification : ");
        qualification.setFont(new Font("Raleway",Font.BOLD,20));
        qualification.setBounds(100,300,150,30);
        add(qualification);
        String[] qualificationList = {"","SSC", "HSSC", "Bachelors","Masters","Phd","Other"};
        qualificationBox = new JComboBox(qualificationList);
        qualificationBox.setFont(new Font("Raleway",Font.BOLD,14));
        qualificationBox.setBounds(300,300,350,30);
        add(qualificationBox);



        JLabel occupation = new JLabel("Occupation : ");
        occupation.setFont(new Font("Raleway",Font.BOLD,20));
        occupation.setBounds(100,350,150,30);
        add(occupation);
        String[] occupationList = {"","Salaried","Unemployed","Others"};
        occupationBox = new JComboBox(occupationList);
        occupationBox.setFont(new Font("Raleway",Font.BOLD,14));
        occupationBox.setBounds(300,350,350,30);
        add(occupationBox);

        JLabel cnic = new JLabel("Cnic : ");
        cnic.setFont(new Font("Raleway",Font.BOLD,20));
        cnic.setBounds(100,400,150,30);
        add(cnic);
        cnicTextField = new JTextField();
        cnicTextField.setFont(new Font("Raleway",Font.BOLD,14));
        cnicTextField.setBounds(300,400,350,30);
        add(cnicTextField);

        JLabel seniorCitizen = new JLabel("Senior Citizen : ");
        seniorCitizen.setFont(new Font("Raleway",Font.BOLD,20));
        seniorCitizen.setBounds(100,450,150,30);
        add(seniorCitizen);

        seniorCitizenY = new JRadioButton("Yes");
        seniorCitizenY.setBackground(Color.WHITE);
        seniorCitizenY.setBounds(300,450,200,30);
        add(seniorCitizenY);

        seniorCitizenN = new JRadioButton("No");
        seniorCitizenN.setBackground(Color.WHITE);
        seniorCitizenN.setBounds(500,450,200,30);
        add(seniorCitizenN);

        ButtonGroup buttonGroupGender = new ButtonGroup();
        buttonGroupGender.add(seniorCitizenN);
        buttonGroupGender.add(seniorCitizenY);

        JLabel existingAccount = new JLabel("Existing Account : ");
        existingAccount.setFont(new Font("Raleway",Font.BOLD,20));
        existingAccount.setBounds(100,500,200,30);
        add(existingAccount);

        existingAccountY = new JRadioButton("Yes");
        existingAccountY.setBackground(Color.WHITE);
        existingAccountY.setBounds(300,500,200,30);
        add(existingAccountY);

        existingAccountN = new JRadioButton("No");
        existingAccountN.setBackground(Color.WHITE);
        existingAccountN.setBounds(500,500,200,30);
        add(existingAccountN);

        ButtonGroup buttonGroupMartialStatus = new ButtonGroup();
        buttonGroupMartialStatus.add(existingAccountY);
        buttonGroupMartialStatus.add(existingAccountN);



        nextButton = new JButton("NEXT");
        nextButton.setFont(new Font("Raleway",Font.BOLD,16));
        nextButton.setBounds(550,650,100,30);
        nextButton.addActionListener(this);
        add(nextButton);


        getContentPane().setBackground(Color.WHITE);



        setSize(850,800);
        setLocation(350,10);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String formNo = formNumber;
        String religion = religionBox.getSelectedItem().toString();
        String category = accountCategoryBox.getSelectedItem().toString();
        String income = incomeTextField.getText().toString();
        String qualification = qualificationBox.getSelectedItem().toString();
        String occupation = occupationBox.getSelectedItem().toString();
        String cnic = cnicTextField.getText().toString();
        String seniorCitizen = null;
        if(seniorCitizenY.isSelected()) {
            seniorCitizen = "Yes";
        } else if (seniorCitizenN.isSelected()) {
            seniorCitizen = "No";
        }

        String existingAccount = null;
        if(existingAccountY.isSelected()) {
            existingAccount = "Yes";
        } else if (existingAccountN.isSelected()) {
            existingAccount = "No";
        }
        try {

        Conn c = new Conn();
        String query = "insert into signuptwo values(?,?,?,?,?,?,?,?,?)";

            PreparedStatement statement = c.con.prepareStatement(query);
            statement.setString(1,formNo);
            statement.setString(2,religion);
            statement.setString(3,category);
            statement.setString(4,income);
            statement.setString(5,qualification);
            statement.setString(6,occupation);
            statement.setString(7,cnic);
            statement.setString(8,seniorCitizen);
            statement.setString(9,existingAccount);

            statement.executeUpdate();

            setVisible(false);
            new SignUpThree(formNumber).setVisible(true);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


    }
}
