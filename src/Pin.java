
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Pin extends JFrame implements ActionListener{
    JPasswordField t1,t2;
    JButton b1,b2;
    JLabel l1,l2,l3;
    String cardno;

    Pin(String cardno){
        this.cardno=cardno;

        l1 = new JLabel("CHANGE YOUR PIN");
        l1.setFont(new Font("System", Font.BOLD, 30));
        l1.setForeground(Color.WHITE);

        l2 = new JLabel("New PIN:");
        l2.setFont(new Font("System", Font.BOLD, 16));
        l2.setForeground(Color.WHITE);

        l3 = new JLabel("Re-Enter New PIN:");
        l3.setFont(new Font("System", Font.BOLD, 16));
        l3.setForeground(Color.WHITE);

        t1 = new JPasswordField();
        t1.setFont(new Font("Raleway", Font.BOLD, 25));

        t2 = new JPasswordField();
        t2.setFont(new Font("Raleway", Font.BOLD, 25));

        b1 = new JButton("CHANGE");
        b2 = new JButton("BACK");

        b1.addActionListener(this);
        b2.addActionListener(this);

        setLayout(null);

        l1.setBounds(250,150,500,35);
        add(l1);

        l2.setBounds(220,250,400,35);
        add(l2);

        t1.setBounds(220,300,400,35);
        add(t1);

        l3.setBounds(220,350,400,35);
        add(l3);

        t2.setBounds(220,400,400,35);
        add(t2);

        b1.setBounds(300,480,100,35);
        b1.setBackground(Color.white);
        b1.setForeground(Color.BLACK);
        add(b1);

        b2.setBounds(450,480,100,35);
        b2.setBackground(Color.white);
        b2.setForeground(Color.BLACK);
        add(b2);


        setSize(800,700);
        getContentPane().setBackground(Color.black);
        setLocation(500,0);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);


    }

    public void actionPerformed(ActionEvent ae){
        try{
            String npin = t1.getText();
            String rpin = t2.getText();

            if(!npin.equals(rpin)){
                JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                return;
            }

            if(ae.getSource()==b1){
                if (t1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Enter New PIN");
                }
                if (t2.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Re-Enter new PIN");
                } Connection c1 = new Connection();

                String q1 = "update login set pin = '"+rpin+"' where cardnumber = '"+cardno+"' ";
                String q2 = "update signupthree set pin = '"+rpin+"' where cardno = '"+cardno+"' ";

                c1.s.executeUpdate(q1);
                c1.s.executeUpdate(q2);


                JOptionPane.showMessageDialog(null, "PIN changed successfully");
                setVisible(false);
                new Transactions(cardno).setVisible(true);

            }else if(ae.getSource()==b2){
                new Transactions(cardno).setVisible(true);
                setVisible(false);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args){
        new Pin("").setVisible(true);
    }
}
