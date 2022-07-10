import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
import java.sql.*;

public class Withdraw extends JFrame implements ActionListener{
    JTextField t1,t2;
    JButton b1,b2;
    JLabel l1,l2;
    String cardno;

    Withdraw(String cardno){
        this.cardno = cardno;

        l1 = new JLabel("ENTER THE AMOUNT YOU WANT TO WITHDRAW");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 20));

        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 20));

        b1 = new JButton("WITHDRAW");
        b2 = new JButton("BACK");

        setLayout(null);
        l1.setBounds(200,150,500,35);
        add(l1);

        t1.setBounds(220,250,400,35);
        add(t1);

        b1.setBounds(300,350,100,35);
        b1.setBackground(Color.white);
        b1.setForeground(Color.BLACK);
        add(b1);

        b2.setBounds(430,350,100,35);
        b2.setBackground(Color.white);
        b2.setForeground(Color.BLACK);
        add(b2);

        b1.addActionListener(this);
        b2.addActionListener(this);

        setSize(800,700);
        getContentPane().setBackground(Color.black);
        setLocation(500,0);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae){
        try{
            String amount = t1.getText();
            Date date = new Date();
            if(ae.getSource()==b1){
                if(t1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to Withdraw");
                }else{
                    Connection c1 = new Connection();

                    ResultSet rs = c1.s.executeQuery("select * from bank where cardno = '"+cardno+"'");
                    int balance = 0;
                    while(rs.next()){
                        if(rs.getString("type").equals("Deposit")){
                            balance += Integer.parseInt(rs.getString("amount"));
                        }else{
                            balance -= Integer.parseInt(rs.getString("amount"));
                        }
                    }
                    if(balance < Integer.parseInt(amount)){
                        JOptionPane.showMessageDialog(null, "Insufficient Balance");
                        return;
                    }

                    c1.s.executeUpdate("insert into bank values('"+cardno+"', '"+date+"', 'Withdrawal', '"+amount+"')");
                    JOptionPane.showMessageDialog(null, "Rs. "+amount+" Debited Successfully");

                    setVisible(false);
                    new Transactions(cardno).setVisible(true);
                }
            }else if(ae.getSource()==b2){
                setVisible(false);
                new Transactions(cardno).setVisible(true);
            }
        }catch(Exception e){
            System.out.println(e);
        }

    }
    public static void main(String[] args){
           new Withdraw("").setVisible(true);
    }
}
