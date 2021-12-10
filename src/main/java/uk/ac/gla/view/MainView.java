package uk.ac.gla.view;

import uk.ac.gla.controller.MainViewController;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;
import java.util.Locale;

/**
 * @author Shangzhen Wei
 * @version 1.0
 */

/*
 * CSV file reading page and software operating instructions page
 */

public class MainView extends JFrame {
    static JTextField text1 = new JTextField();
    static JFileChooser jFileChooser = new JFileChooser(new File("./gc_GA30003_SEM0000_2021_SMP.csv"));
    JTabbedPane tabPane = new JTabbedPane();
    JDialog dialog = null;
    Container containerForDirectory = new Container();
    Container containerForInstructions = new Container();
    JLabel label1 = new JLabel("Select a File: ");
    JLabel jLabel2 = new JLabel("Directions for Use: ");
    JLabel jLabel3 = new JLabel("1. You should start by importing student grades. (Notice: Must be a csv file)");
    JLabel jLabel4 = new JLabel("2. Once you have successfully read the data, you can view the imported student grades data");
    JLabel jLabel7 = new JLabel("in a pop-up screen.");
    JLabel jLabel5 = new JLabel("3. You can search for students by using their name or unique student number.");
    JLabel jLabel6 = new JLabel("4. This application can generate PDF reports individually or in bulk.");
    JLabel finalLabel = new JLabel("When you have finished reading this guide, you can click on the tab at the top of this screen");
    JLabel finalLabel2 = new JLabel("to import the data.");
    JButton reset = new JButton("Reset");
    JButton fileChooseButton = new JButton("Select");
    JButton checkButton = new JButton("Submit");
    MainViewController mainViewController;


    public MainView() {
        super("MyGrade - A Student Grade Processing Application");
        MainViewController mainViewController = new MainViewController(this);
        Font mainViewBaseFont = new Font("Calibre", Font.PLAIN, 20);
        Font font = new Font("Calibre", Font.PLAIN, 16);

        jFileChooser.setCurrentDirectory(new File("d:\\"));
        jFileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase(Locale.ROOT).endsWith(".csv");
            }

            @Override
            public String getDescription() {
                return ".csv File (*.csv)";
            }
        });

        setContentPane(tabPane);
        label1.setFont(mainViewBaseFont);
        label1.setBounds(20, 20, 180, 120);
        text1.setBounds(200, 60, 370, 40);
        fileChooseButton.setFont(mainViewBaseFont);
        fileChooseButton.setBounds(600, 60, 100, 40);
        reset.setFont(mainViewBaseFont);
        reset.setBounds(200, 170, 100, 40);
        checkButton.setFont(mainViewBaseFont);
        checkButton.setBounds(500, 170, 100, 40);

        fileChooseButton.addActionListener(mainViewController);
        checkButton.addActionListener(mainViewController);
        reset.addActionListener(mainViewController);

        containerForDirectory.add(label1);
        containerForDirectory.add(text1);
        containerForDirectory.add(fileChooseButton);
        containerForDirectory.add(checkButton);
        containerForDirectory.add(jFileChooser);
        containerForDirectory.add(reset);

        jLabel2.setFont(font);
        jLabel2.setForeground(Color.RED);
        jLabel3.setFont(font);
        jLabel4.setFont(font);
        jLabel5.setFont(font);
        jLabel6.setFont(font);
        jLabel7.setFont(font);
        finalLabel.setFont(font);
        finalLabel2.setFont(font);

        jLabel2.setBounds(0, 0, 200, 150);
        jLabel3.setBounds(15, 50, 750, 100);
        jLabel4.setBounds(15, 100, 750, 100);
        jLabel7.setBounds(35, 120, 750, 100);
        jLabel5.setBounds(15, 150, 750, 100);
        jLabel6.setBounds(15, 200, 750, 100);
        finalLabel.setBounds(30, 280, 750, 100);
        finalLabel2.setBounds(30, 315, 750, 100);

        containerForInstructions.add(jLabel2);
        containerForInstructions.add(jLabel3);
        containerForInstructions.add(jLabel4);
        containerForInstructions.add(jLabel7);
        containerForInstructions.add(jLabel5);
        containerForInstructions.add(jLabel6);
        containerForInstructions.add(finalLabel);
        containerForInstructions.add(finalLabel2);

        tabPane.add("Instructions", containerForInstructions);
        tabPane.add("Directory/File Selection", containerForDirectory);


        setSize(800, 500);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int offSetX = (screenSize.width - 800) / 2;
        int offSetY = (screenSize.height - 500) / 2;
        setLocation(offSetX, offSetY);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);
        setVisible(true);

    }

    public static JFileChooser getjFileChooser() {
        return jFileChooser;
    }

    public static JTextField getText1() {
        return text1;
    }

}
