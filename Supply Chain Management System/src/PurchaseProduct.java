import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PurchaseProduct {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JLabel id, pid, quantity;
    private static int count = 0;
    GridLayout experimentLayout = new GridLayout(0, 2);
    ResultSet rs;

    PurchaseProduct() {
        prepareGUI();
    }

    public static void main(String[] args) {
        PurchaseProduct swingControlDemo = new PurchaseProduct();
        swingControlDemo.showButtonDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Purchase Product");
        mainFrame.setSize(700, 400);
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.getContentPane().setBackground(Color.green);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                mainFrame.setVisible(false);
            }
        });
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);

        statusLabel.setSize(350, 400);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    public void showButtonDemo() {

        headerLabel.setText("Supply Chain Management System");
        headerLabel.setFont(new Font(null, Font.BOLD, 27));
        headerLabel.setForeground(Color.black);

        pid = new JLabel("Enter Product Id");
        JTextField tf2 = new JTextField();
        tf2.setSize(100, 30);

        quantity = new JLabel("Enter Quantity");
        JTextField tf3 = new JTextField();
        tf3.setSize(100, 30);
        JButton okButton = new JButton("Place Order");

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create a separate thread for database operation
                Thread dbThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        PreparedStatement pst;
                        DBConnection con = new DBConnection();
                        try {
                            pst = con.mkDataBase().prepareStatement("UPDATE supplychain SET f_quantity= ?, f_prize=? where f_name = ?");
                            pst.setString(3, tf2.getText());
                            pst.setDouble(2, Double.parseDouble(tf3.getText()));
                            pst.execute();

                            JOptionPane.showMessageDialog(null, "Product Ordered! Amount: " + tf2.getText());
                            mainFrame.setVisible(false);

                        } catch (Exception ex) {
                            System.out.println(ex);
                            JOptionPane.showMessageDialog(null, "Error");
                        } finally {

                        }
                    }
                });

                // Start the database thread
                dbThread.start();

                // Introduce a sleep concept in the main thread
                try {
                    Thread.sleep(2000); // Sleep for 2 seconds (adjust as needed)
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                // Continue with the main thread logic here if needed
            }
        });

        JPanel jp = new JPanel();
        jp.add(pid);
        jp.add(tf2);
        jp.add(quantity);
        jp.add(tf3);

        jp.setSize(200, 200);
        jp.setLayout(experimentLayout);
        controlPanel.add(jp);
        jp.add(okButton);

        mainFrame.setVisible(true);

        mainFrame.setLocationRelativeTo(null);
    }
}