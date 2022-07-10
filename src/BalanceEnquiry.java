import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;
import java.util.*;

public class BalanceEnquiry extends JFrame implements ActionListener{
    JButton b1;
    JLabel l1, l2;
    String cardno;

    BalanceEnquiry(String cardno){
        this.cardno=cardno;

        getContentPane().setBackground(Color.black);
        l1 = new JLabel("Current Balance ");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 30));
        l1.setBounds(280,150,500,35);
        add(l1);

        l2 = new JLabel();
        l2.setForeground(Color.WHITE);
        l2.setFont(new Font("System", Font.BOLD, 25));
        l2.setBounds(350,250,500,35);
        add(l2);

        b1 = new JButton("BACK");
        b1.setBackground(Color.white);
        b1.setForeground(Color.BLACK);
        b1.setBounds(350,350,100,35);
        add(b1);
        setLayout(null);
        b1.addActionListener(this);

        int balance = 0;
        try{
            Connection c1 = new Connection();
            ResultSet rs = c1.s.executeQuery("select * from bank where cardno = '"+cardno+"'");
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }

        l2.setText( "Rs " + String.valueOf(balance));

        setSize(800,700);

        setLocation(500,0);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);

    }
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transactions(cardno).setVisible(true);
    }

    public static void main(String[] args) {
        new BalanceEnquiry("").setVisible(true);
    }


}
