package uk.ac.gla.view;

import uk.ac.gla.handler.SearchIdHandler;
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

public class SearchViewForID extends JDialog {
    static JTextField studentIDTextField = new JTextField();
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 30));
    JLabel studentID = new JLabel("Student ID: ");
    JButton jButton = new JButton("Search");
    JButton jButton1 = new JButton("Exit");

    SearchViewHandler searchViewHandler;
    SearchIdHandler searchIdHandler;

    public SearchViewForID(SearchView searchView) {
        super(searchView, "Search By Student ID", true);

        SearchViewHandler searchViewHandler = new SearchViewHandler(searchView);
        SearchIdHandler searchIdHandler = new SearchIdHandler(this);

        Font font = new Font("Calibre", Font.PLAIN, 20);

        studentID.setFont(font);
        studentIDTextField.setFont(font);
        jButton.setFont(font);
        jButton1.setFont(font);

        jButton1.addActionListener(searchIdHandler);
        jButton.addActionListener(searchIdHandler);

        studentID.setPreferredSize(new Dimension(120, 50));
        jPanel.add(studentID);
        studentIDTextField.setPreferredSize(new Dimension(200, 50));
        jPanel.add(studentIDTextField);

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

    public static JTextField getStudentIDTextField() {
        return studentIDTextField;
    }

    public void setStudentIDTextField(JTextField studentIDTextField) {
        SearchViewForID.studentIDTextField = studentIDTextField;
    }
}
