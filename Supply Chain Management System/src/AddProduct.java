
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddProduct {

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JPanel controlPanel;
    private JLabel id, name, price, quantity, qt;
    private static int count = 0;
    GridLayout experimentLayout = new GridLayout(0, 2);
    ResultSet rs;

    AddProduct() {
        prepareGUI();
    }

    public static void main(String[] args) {
        AddProduct swingControlDemo = new AddProduct();
        swingControlDemo.showButtonDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Add Product Details");
        mainFrame.setSize(700, 500);
        mainFrame.setLayout(new GridLayout(3, 1));

        mainFrame.getContentPane().setBackground(Color.green);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                mainFrame.setVisible(false);
            }
        });
        headerLabel = new JLabel("", JLabel.CENTER);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.setVisible(true);
    }

    public void showButtonDemo() {

        headerLabel.setText("Supply Chain Management System");
        headerLabel.setFont(new Font(null, Font.BOLD, 27));

        name = new JLabel("Enter Product Id");
        JTextField tf2 = new JTextField();
        tf2.setSize(100, 40);

        price = new JLabel("Enter Product Name");
        JTextField tf3 = new JTextField();
        tf3.setSize(100, 40);

        quantity = new JLabel("Enter Quantity");
        JTextField tf4 = new JTextField();
        tf4.setSize(100, 40);

        qt = new JLabel("Enter Price");
        JTextField tf5 = new JTextField();
        tf5.setSize(100, 40);

        JButton okButton = new JButton("Add");

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PreparedStatement pst;
                DBConnection con = new DBConnection();
                try {
                    pst = con.mkDataBase().prepareStatement("INSERT INTO product(product_id, product_name, quantity, price) VALUES (?, ?, ?,?)");
                    pst.setInt(1, Integer.parseInt(tf2.getText()));
                    pst.setString(2, tf3.getText());
                    pst.setInt(3, Integer.parseInt(tf4.getText()));
                    pst.setInt(4, Integer.parseInt(tf5.getText()));
                    pst.execute();

                    JOptionPane.showMessageDialog(null, "Product Added!" + tf2.getText());
                    mainFrame.setVisible(false);
                } catch (SQLException ex) {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
                } catch (NumberFormatException | NullPointerException ex) {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null, "Invalid Input: Please enter valid data for product details.");
                } finally {
                    // Any cleanup code can go here, though in this case, there's none.
                }
            }
        });

        JPanel jp = new JPanel(null);
        jp.add(name);
        jp.add(tf2);
        jp.add(price);
        jp.add(tf3);
        jp.add(quantity);
        jp.add(tf4);
        jp.add(qt);
        jp.add(tf5);

        jp.setSize(700, 700);
        jp.setLayout(experimentLayout);
        controlPanel.add(jp);
        jp.add(okButton);

        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
}
