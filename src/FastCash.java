import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {
    JLabel l1;
    JButton b1, b2, b3, b4, b5, b6, b7, b8;
    JTextField t1;
    String cardno;

    FastCash(String cardno){
        this.cardno=cardno;

        l1 = new JLabel("SELECT WITHDRAWAL AMOUNT ");
        l1.setFont(new Font("System", Font.BOLD, 30));

        b1 = new JButton("Rs 100");
        b2 = new JButton("Rs 500");
        b3 = new JButton("Rs 1000");
        b4 = new JButton("Rs 200");
        b5 = new JButton("Rs 5000");
        b6 = new JButton("Rs 10000");
        b7 = new JButton("BACK");

        setLayout(null);
        l1.setBounds(180,200,700,35);
        l1.setForeground(Color.white);
        add(l1);

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
        try {
            String amount = ((JButton)ae.getSource()).getText().substring(3); //k
            Connection c = new Connection();
            ResultSet rs = c.s.executeQuery("select * from bank where cardno = '"+cardno+"'");
            int balance = 0;
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
            if (ae.getSource() != b7 && balance < Integer.parseInt(amount)) {
                JOptionPane.showMessageDialog(null, "Insuffient Balance");
                return;
            }

            if (ae.getSource() == b7) {
                this.setVisible(false);
                new Transactions(cardno).setVisible(true);
            }else{
                Date date = new Date();
                c.s.executeUpdate("insert into bank values('"+cardno+"', '"+date+"', 'Withdraw', '"+amount+"')");
                JOptionPane.showMessageDialog(null, "Rs. "+amount+" Debited Successfully");

                setVisible(false);
                new Transactions(cardno).setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new FastCash("").setVisible(true);
    }

}
