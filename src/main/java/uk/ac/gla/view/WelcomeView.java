package uk.ac.gla.view;

import uk.ac.gla.handler.WelcomeHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Shangzhen Wei
 * @version 1.0
 */

public class WelcomeView extends JFrame {
    JLabel applicationName = new JLabel("Welcome to the system!", JLabel.CENTER);
    JLabel right = new JLabel("© 2021 Shangzhen Wei All rights reserved.", JLabel.CENTER);

    SpringLayout springLayout = new SpringLayout();
    JPanel jPanel = new JPanel(springLayout);

    JButton enterButton = new JButton("Enter");
    JButton exitButton = new JButton("Exit");
    JLabel systemTime = new JLabel();

    SystemTray systemTray;
    TrayIcon trayIcon;

    WelcomeHandler welcomeHandler;

    public WelcomeView() {
        super("Student Achievement Report Generation Application");

        WelcomeHandler welcomeHandler = new WelcomeHandler(this);


        Container contentPane = getContentPane();
        applicationName.setFont(new Font("Calibre", Font.BOLD, 40));

        Font buttonAndTimeFont = new Font("Times New Roman", Font.PLAIN, 30);

        right.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        systemTime.setForeground(Color.BLACK);
        systemTime.setFont(buttonAndTimeFont);
        this.setTimer(systemTime);
        enterButton.setFont(buttonAndTimeFont);
        exitButton.setFont(buttonAndTimeFont);

        jPanel.add(systemTime);
        exitButton.addActionListener(welcomeHandler);
        jPanel.add(exitButton);
        enterButton.addActionListener(welcomeHandler);
        enterButton.addKeyListener(welcomeHandler);
        jPanel.add(enterButton);


        SpringLayout.Constraints systemTimeLocation = springLayout.getConstraints(systemTime);
        systemTimeLocation.setX(Spring.constant(260));
        systemTimeLocation.setY(Spring.constant(200));
        SpringLayout.Constraints enterButtonLocation = springLayout.getConstraints(enterButton);
        enterButtonLocation.setX(Spring.constant(200));
        enterButtonLocation.setY(Spring.constant(300));
        SpringLayout.Constraints exitButtonLocation = springLayout.getConstraints(exitButton);
        exitButtonLocation.setX(Spring.constant(480));
        exitButtonLocation.setY(Spring.constant(300));

        contentPane.add(applicationName, BorderLayout.NORTH);
        contentPane.add(jPanel, BorderLayout.CENTER);
        contentPane.add(right, BorderLayout.SOUTH);

        if (SystemTray.isSupported()) {
            systemTray = SystemTray.getSystemTray();
            URL imgURL = WelcomeView.class.getClassLoader().getResource("images.png");
            trayIcon = new TrayIcon(new ImageIcon(imgURL).getImage());
            trayIcon.setImageAutoSize(true);
            try {
                systemTray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowIconified(WindowEvent e) {
                    WelcomeView.this.dispose();
                }
            });
            trayIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int clickCount = e.getClickCount();
                    if (clickCount == 1) {
                        WelcomeView.this.setExtendedState(JFrame.NORMAL);
                    }
                    WelcomeView.this.setVisible(true);
                }
            });
        }

        URL url = WelcomeView.class.getClassLoader().getResource("images.png");
        Image image = new ImageIcon(url).getImage();
        setIconImage(image);
        setSize(800, 500);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int offSetX = (screenSize.width - 800) / 2;
        int offSetY = (screenSize.height - 500) / 2;
        setLocation(offSetX, offSetY);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);
        setVisible(true);
    }


    private void setTimer(JLabel systemTime) {
        final JLabel varTime = systemTime;
        Timer timeAction = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long timeMillis = System.currentTimeMillis();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                varTime.setText(df.format(new Date(timeMillis)));
            }
        });
        timeAction.start();
    }

}
