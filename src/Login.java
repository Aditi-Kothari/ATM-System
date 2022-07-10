import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JButton login,signup,clear;
    JTextField cardtextfield;
    JPasswordField pintextfield;
    public Login(){
        setSize(800,480);
        setLocation(350,200);
        setTitle("AVS ATM");
        setLayout(null);//default layout is border so we are changing it

        // adding icon
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("atm.png"));
        //for changing image height and width
        Image i2 = i1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        //where we want image
        label.setBounds(70,20,100,100);
        add(label);

       //label for heading -Welcome to AVS ATM
        JLabel text = new JLabel("Welcome to AVS ATM");
        text.setBounds(220,40,400,40);
        text.setForeground(Color.white);
        text.setFont(new Font("Osward",Font.BOLD,38));
        add(text);

        //label for card number
        JLabel cardno = new JLabel("Card No : ");
        cardno.setBounds(120,180,150,30);
        cardno.setForeground(Color.white);
        cardno.setFont(new Font("Railway",Font.BOLD,28));
        add(cardno);

        cardtextfield = new JTextField();
        cardtextfield.setBounds(300,180,230,30);
        cardtextfield.setForeground(Color.black);
        cardtextfield.setFont(new Font("Arial",Font.BOLD,14));
        add(cardtextfield);

        //label for pin
        JLabel pin = new JLabel("PIN : ");
        pin.setBounds(120,250,250,30);
        pin.setForeground(Color.white);
        pin.setFont(new Font("Railway",Font.BOLD,28));
        add(pin);

        pintextfield = new JPasswordField();
        pintextfield.setBounds(300,250,230,30);
        pintextfield.setForeground(Color.black);
        pintextfield.setFont(new Font("Arial",Font.BOLD,14));
        add(pintextfield);

        //buttons
        login = new JButton("SIGN IN");
        login.setBounds(300,330,100,30);
        login.setBackground(Color.white);
        login.setForeground(Color.black);
        login.addActionListener(this);
        add(login);

        clear = new JButton("CLEAR");
        clear.setBounds(430,330,100,30);
        clear.setBackground(Color.white);
        clear.setForeground(Color.black);
        clear.addActionListener(this);
        add(clear);

        signup = new JButton("SIGN UP");
        signup.setBounds(300,380,230,30);
        signup.setBackground(Color.white);
        signup.setForeground(Color.black);
        signup.addActionListener(this);
        add(signup);



        //Changing background of frame
        getContentPane().setBackground(Color.black);


        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);



    }


    public void actionPerformed(ActionEvent ae) {
        try {

            if (ae.getSource() == clear) {
                cardtextfield.setText("");
                pintextfield.setText("");
            } else if (ae.getSource() == login) {
                Connection conn = new Connection();
                String cardno = cardtextfield.getText();
                String pin = pintextfield.getText();
                String query = "select * from login where cardnumber = '" + cardno + "' and pin = '" + pin + "'";
                ResultSet res = conn.s.executeQuery(query);//it basically return a resultset so we have resultset class in sql package

                if(res.next()){
                    setVisible(false);
                    new Transactions(cardno).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null,"Incorrect Credentials");
                }

            } else if (ae.getSource() == signup) {
                setVisible(false);
                new SignupOne().setVisible(true);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }





    public static void main(String[] args){

        new Login();
    }
}
