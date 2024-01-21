
import java.awt.*;
import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login extends JFrame {

    JLabel idLabel;
    JLabel passLabel;
    JLabel background;
    JLabel headerLabel;
    JLabel devInfo;
    JTextField id;
    JPasswordField pass;
    JButton submit;

    public Login() {
        setTitle("Supply Chain Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.background = new JLabel(new ImageIcon("C:\\Users\\ADEAL COMPUTER 'S\\Downloads\\Wallpaper.jpg"));
        //mainFrame.getContentPane().setBackground(Color.white);
        this.init();
        add(background);
        background.setVisible(true);
        this.pack();
        this.setSize(700, 400);
        this.setLocationRelativeTo(null);
    }

    public void init() {
        headerLabel = new JLabel();
        //this.headerLabel.setText("Supply Chain Management System");
        this.headerLabel.setBounds(190, 1, 370, 150);
        this.headerLabel.setFont(new Font("Geomanist", Font.BOLD, 20));
        headerLabel.setForeground(Color.red);
        add(headerLabel);

        idLabel = new JLabel();
        this.idLabel.setText("Username");
        this.idLabel.setBounds(190, 110, 100, 50);
        this.idLabel.setFont(new Font(null, Font.BOLD, 20));
        idLabel.setForeground(Color.black);
        add(idLabel);

        passLabel = new JLabel("Password");
        this.passLabel.setBounds(190, 165, 100, 50);
        this.passLabel.setFont(new Font(null, Font.BOLD, 20));
        passLabel.setForeground(Color.black);
        add(passLabel);

        devInfo = new JLabel();
        this.devInfo.setText("");
        this.devInfo.setBounds(130, 300, 1000, 30);
        this.devInfo.setFont(new Font("Geomanist", Font.PLAIN, 15));
        devInfo.setForeground(Color.white);
        add(devInfo);

        id = new JTextField();
        this.id.setBounds(300, 125, 200, 30);
        add(id);

        pass = new JPasswordField();
        this.add(pass);
        this.pass.setBounds(300, 175, 200, 30);
        this.id.setVisible(true);
        this.submit = new JButton("Login");
        this.submit.setBounds(400, 230, 100, 25);
        add(submit);
        submit.addActionListener(this::submitActionPerformed);
    }

    public synchronized void submitActionPerformed(java.awt.event.ActionEvent evt) {
        SwingUtilities.invokeLater(() -> {
            if (id.getText().equals("admin") && new String(pass.getPassword()).equals("admin")) {
                this.setVisible(false);
                Home fn = new Home();
                fn.showButtonDemo();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid password!");
            }
        });
    }
}

class MyGui {

    public static void main(String[] a) {
        SwingUtilities.invokeLater(() -> {
            Login l = new Login();
            l.setVisible(true);
        });
    }
}
