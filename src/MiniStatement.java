import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class MiniStatement extends JFrame implements ActionListener{
String cardno;
    JButton b1, b2;
    JLabel l1,l2,l3,l4;

    MiniStatement(String cardno){
        this.cardno=cardno;

        l1 = new JLabel();
        l1.setBounds(40, 210, 400, 160);
        l1.setForeground(Color.white);
        add(l1);

        l2 = new JLabel("AVS Bank");
        l2.setBounds(250, 100, 300, 20);
        l2.setFont(new Font("Raleway", Font.BOLD, 25));
        l2.setForeground(Color.white);
        add(l2);

        l3 = new JLabel();
        l3.setBounds(220, 150, 300, 20);
        l3.setFont(new Font("Raleway", Font.BOLD, 20));
        l3.setForeground(Color.white);
        add(l3);

        l4 = new JLabel();
        l4.setBounds(40, 500, 300, 20);
        l4.setForeground(Color.white);
        add(l4);

        setLayout(null);
        b1 = new JButton("Exit");

        b1.setBackground(Color.white);
        b1.setForeground(Color.BLACK);
        add(b1);

        b1.addActionListener(this);


        b1.setBounds(40, 550, 100, 25);

        l3.setText("XXXX-XXXX-XXXX-" + cardno.substring(13));


        try{
            int balance = 0;
            Connection c1  = new Connection();
            ResultSet rs = c1.s.executeQuery("SELECT * FROM bank where cardno = '"+cardno+"'");
            while(rs.next()){
                l1.setText(l1.getText() + "<html>"+rs.getString("date")+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");
                if(rs.getString("type").equals("Deposit")){
                    balance += Integer.parseInt(rs.getString("amount"));
                }else{
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
            l4.setText("Your total Balance is Rs "+balance);
        }catch(Exception e){
            System.out.println(e);
        }




        setSize(600,700);
        getContentPane().setBackground(Color.black);
        setLocation(500,0);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        this.setVisible(false);
    }

    public static void main(String[] args){
        new MiniStatement("").setVisible(true);
    }
}
