import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame();
            }
        });
    }
}

class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        // Set frame properties
        setTitle("User Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");
        JButton exitButton = new JButton("Exit");

        // Panel for input fields
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(passLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(exitButton);

        add(panel);

        // Action listener for buttons
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Dummy validation
                if (username.equals("user") && password.equals("123")) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    new ImageViewerFrame();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }
}

class ImageViewerFrame extends JFrame {
    private JLabel imageLabel;

    public ImageViewerFrame() {
        // Set frame properties
        setTitle("Image Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);

        JButton openButton = new JButton("Open Image");
        JButton backButton = new JButton("Logout");

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        buttonPanel.add(backButton);

        add(imageLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action listener for open image button
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(ImageViewerFrame.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        // Load the image with proper orientation and resize to 3:4 ratio (vertical)
                        File file = fileChooser.getSelectedFile();
                        BufferedImage image = ImageIO.read(file);
                        
                        // Resize image to 3:4 vertical ratio
                        int width = 300; // Fixed width
                        int height = 400; // Fixed height for 3:4 aspect ratio
                        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

                        ImageIcon imageIcon = new ImageIcon(resizedImage);
                        imageLabel.setIcon(imageIcon);

                        // Resize frame to fit the image
                        setSize(width, height + 100);
                        setLocationRelativeTo(null);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(ImageViewerFrame.this, "Failed to load image.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Action listener for back button
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginFrame();
                dispose();
            }
        });

        setVisible(true);
    }
}
