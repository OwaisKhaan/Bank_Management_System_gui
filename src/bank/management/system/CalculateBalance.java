package bank.management.system;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CalculateBalance {

    public static int checkRemainingAmount(String username) {
        int currentBalance = 0;
        try {
            Conn c = new Conn();
            String query = "select * from bank where username = ?";
            PreparedStatement statement = c.con.prepareStatement(query);
            statement.setString(1,username);



            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                if (rs.getString("type").equals("deposit")){
                    currentBalance += Integer.parseInt( rs.getString("amount"));
                } else if (rs.getString("type").equals("withdraw")) {
                    currentBalance -= Integer.parseInt(rs.getString("amount"));
                }
            }



        }catch (Exception ex){
            ex.printStackTrace();
        }

        return currentBalance;
    }

}
