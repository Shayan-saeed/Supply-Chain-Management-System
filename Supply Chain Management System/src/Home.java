import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.Color;

public class Home {

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public Home() {
        prepareGUI();
    }

    public static void main(String[] args) {
        Home swingControlDemo = new Home();
        swingControlDemo.showButtonDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Supply Chain Management System");
        mainFrame.setBounds(100, 100, 700, 400);
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.getContentPane().setBackground(Color.white);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(350, 300);
        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(1, 5));
        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    public void showButtonDemo() {
        headerLabel.setText("Supply Chain Management System");
        this.headerLabel.setFont(new Font(null, Font.BOLD, 27));
        headerLabel.setForeground(Color.black);
        JButton fkButton = new JButton("Product Details");
        JButton abButton = new JButton("About");
        JButton afButton = new JButton("Product");
        JButton ufButton = new JButton("Supplier Account");
        JButton dlButton = new JButton("Puchase Product");

        fkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acquire lock1
                synchronized (lock1) {
                    System.out.println("Acquired lock1 in fkButton");
                    SearchProduct ii = new SearchProduct();
                    ii.showButtonDemo();
                    
                    // Sleep to introduce potential deadlock
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                    // Try to acquire lock2
                    synchronized (lock2) {
                        System.out.println("Acquired lock2 in fkButton");
                    }
                }
            }
        });

        afButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acquire lock2
                synchronized (lock2) {
                    System.out.println("Acquired lock2 in afButton");
                    AddProduct ef = new AddProduct();
                    ef.showButtonDemo();
                    // Sleep to introduce potential deadlock
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                    // Try to acquire lock1
                    synchronized (lock1) {
                        System.out.println("Acquired lock1 in afButton");
                    }
                }
            }
        });

        ufButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Similar logic to fkButton, acquire lock1 first
                synchronized (lock1) {
                    System.out.println("Acquired lock1 in ufButton");
                    Suplier uf = new Suplier();
                    uf.showButtonDemo();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                    synchronized (lock2) {
                        System.out.println("Acquired lock2 in ufButton");
                    }
                }
            }
        });

        dlButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Similar logic to afButton, acquire lock2 first
                synchronized (lock2) {
                    System.out.println("Acquired lock2 in dlButton");
                    PurchaseProduct dl = new PurchaseProduct();
                    dl.showButtonDemo();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                    synchronized (lock1) {
                        System.out.println("Acquired lock1 in dlButton");
                    }
                }
            }
        });

        controlPanel.add(ufButton);
        controlPanel.add(afButton);
        controlPanel.add(abButton);
        controlPanel.add(fkButton);
        controlPanel.add(dlButton);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
    }
}