package bank.management.system;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.Random;

public class SignUpOne extends JFrame implements ActionListener {

    String strFormNumber;
    JTextField personNameTextField,fatherNameTextField,emailTextField,addressTextField,cityTextField,statetextField,postalCodeTextField;
   JButton nextButton;
   JRadioButton married, unmarried, genderMale, genderFemale;
   JDateChooser dateChooser;
    SignUpOne(){
        //set layout
        setLayout(null);
        setResizable(false);

        setTitle("New Account Registration Form - Page 1");

        // to generate random form number
        Random formNumber = new Random();
        strFormNumber = String.format("%04d", formNumber.nextInt(10000));

        JLabel labelFormNumber = new JLabel("APPLICATION FORM NO: "+strFormNumber);
        labelFormNumber.setBounds(220,20,600,40);
        labelFormNumber.setFont(new Font("Osward",Font.BOLD,28));
        add(labelFormNumber);

        JLabel personalDetails = new JLabel("Personal Details");
        personalDetails.setFont(new Font("Raleway",Font.BOLD,22));
        personalDetails.setBounds(330,80,350,30);
        add(personalDetails);

        JLabel personName = new JLabel("Name : ");
        personName.setFont(new Font("Raleway",Font.BOLD,20));
        personName.setBounds(100,150,150,30);
        add(personName);
        personNameTextField = new JTextField();
        personNameTextField.setFont(new Font("Raleway",Font.BOLD,14));
        personNameTextField.setBounds(300,150,350,30);
        add(personNameTextField);



        JLabel fatherName = new JLabel("Father's Name : ");
        fatherName.setFont(new Font("Raleway",Font.BOLD,20));
        fatherName.setBounds(100,200,200,30);
        add(fatherName);
        fatherNameTextField = new JTextField();
        fatherNameTextField.setFont(new Font("Raleway",Font.BOLD,14));
        fatherNameTextField.setBounds(300,200,350,30);
        add(fatherNameTextField);

        JLabel dob = new JLabel("Birth Date : ");
        dob.setFont(new Font("Raleway",Font.BOLD,20));
        dob.setBounds(100,250,150,30);
        add(dob);
        dateChooser = new JDateChooser();
        dateChooser.setBounds(300,250,350,30);
        dateChooser.setFont(new Font("Raleway",Font.BOLD,20));
        dateChooser.setForeground(Color.BLACK);
        add(dateChooser);

        JLabel gender = new JLabel("Gender : ");
        gender.setFont(new Font("Raleway",Font.BOLD,20));
        gender.setBounds(100,300,150,30);
        add(gender);

        genderMale = new JRadioButton("Male");
        genderMale.setBackground(Color.WHITE);
        genderMale.setBounds(300,300,200,30);
        add(genderMale);

        genderFemale = new JRadioButton("Female");
        genderFemale.setBackground(Color.WHITE);
        genderFemale.setBounds(500,300,200,30);
        add(genderFemale);

        ButtonGroup buttonGroupGender = new ButtonGroup();
        buttonGroupGender.add(genderFemale);
        buttonGroupGender.add(genderMale);

        JLabel email = new JLabel("Email : ");
        email.setFont(new Font("Raleway",Font.BOLD,20));
        email.setBounds(100,350,150,30);
        add(email);
        emailTextField = new JTextField();
        emailTextField.setFont(new Font("Raleway",Font.BOLD,14));
        emailTextField.setBounds(300,350,350,30);
        add(emailTextField);

        JLabel maritalStatus = new JLabel("Marital Status : ");
        maritalStatus.setFont(new Font("Raleway",Font.BOLD,20));
        maritalStatus.setBounds(100,400,150,30);
        add(maritalStatus);

        married = new JRadioButton("Married");
        married.setBackground(Color.WHITE);
        married.setBounds(300,400,200,30);
        add(married);

        unmarried = new JRadioButton("Unmarried");
        unmarried.setBackground(Color.WHITE);
        unmarried.setBounds(500,400,200,30);
        add(unmarried);

        ButtonGroup buttonGroupMartialStatus = new ButtonGroup();
        buttonGroupMartialStatus.add(married);
        buttonGroupMartialStatus.add(unmarried);




        JLabel address = new JLabel("Address : ");
        address.setFont(new Font("Raleway",Font.BOLD,20));
        address.setBounds(100,450,150,30);
        add(address);
        addressTextField = new JTextField();
        addressTextField.setFont(new Font("Raleway",Font.BOLD,14));
        addressTextField.setBounds(300,450,350,30);
        add(addressTextField);

        JLabel city = new JLabel("City : ");
        city.setFont(new Font("Raleway",Font.BOLD,20));
        city.setBounds(100,500,150,30);
        add(city);
        cityTextField = new JTextField();
        cityTextField.setFont(new Font("Raleway",Font.BOLD,14));
        cityTextField.setBounds(300,500,350,30);
        add(cityTextField);

        JLabel state = new JLabel("State : ");
        state.setFont(new Font("Raleway",Font.BOLD,20));
        state.setBounds(100,550,150,30);
        add(state);
        statetextField = new JTextField();
        statetextField.setFont(new Font("Raleway",Font.BOLD,14));
        statetextField.setBounds(300,550,350,30);
        add(statetextField);

        JLabel postalAddress = new JLabel("Postal Address : ");
        postalAddress.setFont(new Font("Raleway",Font.BOLD,20));
        postalAddress.setBounds(100,600,200,30);
        add(postalAddress);
        postalCodeTextField = new JTextField();
        postalCodeTextField.setFont(new Font("Raleway",Font.BOLD,14));
        postalCodeTextField.setBounds(300,600,350,30);
        add(postalCodeTextField);

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

    public static void main(String[] args) {
        new SignUpOne();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String formNumber = strFormNumber;
        String name = personNameTextField.getText();
        String fName = fatherNameTextField.getText();
        String dob =((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String gender = null;
        if(genderMale.isSelected()){
            gender = "Male";
        } else if (genderFemale.isSelected()) {
            gender = "Female";
        }

        String email = emailTextField.getText();

        String maritalStatus = null;
        if(married.isSelected()){
            maritalStatus = "Married";
        } else if (unmarried.isSelected()) {
            maritalStatus = "Unmarried";
        }

        String address= addressTextField.getText();
        String city = cityTextField.getText();
        String state = statetextField.getText();
        String postalCode = postalCodeTextField.getText();

        try{
            if(name.equals("")){
                JOptionPane.showMessageDialog(null,"Name field is required");
            }else{
                Conn c = new Conn();
              /*String query = "insert into signup values('"+formNumber+"', '"+name+"', '"+fName+"', '"+dob+"', '"+"', '"+gender+"', '"+"', '"+email+"', '"+"', '"+maritalStatus+"', '"+
                        "', '"+address+"', '"+"', '"+city+"', '"+"', '"+state+"', '"+"', '"+postalCode+"')"; */
                String query = "insert into signup values(?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement statement = c.con.prepareStatement(query);
                statement.setString(1,formNumber);
                statement.setString(2,name);
                statement.setString(3,fName);
                statement.setString(4,dob);
                statement.setString(5,gender);
                statement.setString(6,email);
                statement.setString(7,maritalStatus);
                statement.setString(8,address);
                statement.setString(9,city);
                statement.setString(10,state);
                statement.setString(11,postalCode);

                statement.executeUpdate();

                setVisible(false);
                new SignUpTwo(formNumber).setVisible(true);



            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
