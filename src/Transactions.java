import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Transactions extends JFrame implements ActionListener{
    JLabel l1 , l2;
    JButton b1,b2,b3,b4,b5,b6,b7;
    String cardno;
    Transactions(String cardno){
        this.cardno=cardno;

        l1 = new JLabel("Welcome to AVS ATM");
        l1.setFont(new Font("System", Font.BOLD, 35));

        l2 = new JLabel("Please Select Your Transaction from below ");
        l2.setFont(new Font("System", Font.BOLD, 30));

        b1 = new JButton("DEPOSIT");
        b2 = new JButton("CASH WITHDRAWAL");
        b3 = new JButton("FAST CASH");
        b4 = new JButton("MINI STATEMENT");
        b5 = new JButton("PIN CHANGE");
        b6 = new JButton("BALANCE ENQUIRY");
        b7 = new JButton("EXIT");

        setLayout(null);
        l1.setBounds(300,100,700,35);
        l1.setForeground(Color.white);
        add(l1);

        l2.setBounds(180,200,700,35);
        l2.setForeground(Color.white);
        add(l2);

        b1.setBounds(300,350,150,35);
        b1.setBackground(Color.white);
        b1.setForeground(Color.black);
        add(b1);

        b2.setBounds(550,350,150,35);
        b2.setBackground(Color.white);
        b2.setForeground(Color.black);
        add(b2);

        b3.setBounds(300,450,150,35);
        b3.setBackground(Color.white);
        b3.setForeground(Color.black);
        add(b3);

        b4.setBounds(550,450,150,35);
        b4.setBackground(Color.white);
        b4.setForeground(Color.black);
        add(b4);

        b5.setBounds(300,550,150,35);
        b5.setBackground(Color.white);
        b5.setForeground(Color.black);
        add(b5);

        b6.setBounds(550,550,150,35);
        b6.setBackground(Color.white);
        b6.setForeground(Color.black);
        add(b6);

        b7.setBounds(425,650,150,35);
        b7.setBackground(Color.white);
        b7.setForeground(Color.black);
        add(b7);


        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);

        setSize(960,1080);

        setLocation(500,0);
        getContentPane().setBackground(Color.black);
        setUndecorated(true);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==b1){
            setVisible(false);
            new Deposit(cardno).setVisible(true);
        }else if(ae.getSource()==b2){
           setVisible(false);
           new Withdraw(cardno).setVisible(true);
        }else if(ae.getSource()==b3){
            setVisible(false);
            new FastCash(cardno).setVisible(true);
        }else if(ae.getSource()==b5){
            setVisible(false);
            new Pin(cardno).setVisible(true);
        }else if(ae.getSource()==b6){
            setVisible(false);
            new BalanceEnquiry(cardno).setVisible(true);
        }else if(ae.getSource()==b4){
            setVisible(false);
            new MiniStatement(cardno).setVisible(true);
        }else if(ae.getSource()==b7){
            System.exit(0);
        }

    }




    public static void main(String[] args){
        new Transactions("").setVisible(true);
    }

}
