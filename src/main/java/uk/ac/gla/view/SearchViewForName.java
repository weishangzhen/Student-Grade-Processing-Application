package uk.ac.gla.view;

import uk.ac.gla.handler.SearchNameHandler;
import uk.ac.gla.handler.SearchViewHandler;

import javax.swing.*;
import java.awt.*;

/**
 * @author Shangzhen Wei
 * @version 1.2
 */

/*
 * Search Student Page
 */

public class SearchViewForName extends JDialog {
    static JTextField lastNameTextField = new JTextField();
    static JTextField firstNameTextField = new JTextField();
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 30));
    JLabel lastName = new JLabel("Last Name: ");
    JLabel firstName = new JLabel("First Name: ");
    JButton jButton = new JButton("Search");
    JButton jButton1 = new JButton("Exit");

    SearchViewHandler searchViewHandler;
    SearchNameHandler searchNameHandler;


    public SearchViewForName(SearchView searchView) {
        super(searchView, "Search By Student Name", true);

        SearchViewHandler searchViewHandler = new SearchViewHandler(searchView);
        SearchNameHandler searchNameHandler = new SearchNameHandler(this);

        Font font = new Font("Calibre", Font.PLAIN, 20);

        lastName.setFont(font);
        firstName.setFont(font);
        jButton.setFont(font);
        jButton1.setFont(font);

        jButton.addActionListener(searchNameHandler);
        jButton1.addActionListener(searchNameHandler);

        lastName.setPreferredSize(new Dimension(120, 50));
        jPanel.add(lastName);
        lastNameTextField.setPreferredSize(new Dimension(250, 50));
        jPanel.add(lastNameTextField);
        firstName.setPreferredSize(new Dimension(120, 50));
        jPanel.add(firstName);
        firstNameTextField.setPreferredSize(new Dimension(250, 50));
        jPanel.add(firstNameTextField);
        jPanel.add(jButton);
        jPanel.add(jButton1);

        Container contentPane = getContentPane();
        contentPane.add(jPanel);


        setSize(500, 400);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int offSetX = (screenSize.width - 800) / 2;
        int offSetY = (screenSize.height - 500) / 2;
        setLocation(offSetX, offSetY);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }

    public static JTextField getLastNameTextField() {
        return lastNameTextField;
    }

    public void setLastNameTextField(JTextField lastNameTextField) {
        SearchViewForName.lastNameTextField = lastNameTextField;
    }

    public static JTextField getFirstNameTextField() {
        return firstNameTextField;
    }

    public void setFirstNameTextField(JTextField firstNameTextField) {
        SearchViewForName.firstNameTextField = firstNameTextField;
    }
}
