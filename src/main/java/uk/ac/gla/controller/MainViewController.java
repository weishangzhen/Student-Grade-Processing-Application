package uk.ac.gla.controller;

import uk.ac.gla.model.ReadCsvModel;
import uk.ac.gla.view.MainView;
import uk.ac.gla.view.SearchView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author Shangzhen Wei
 * @version 1.0
 */

/*
 * Action Listening for CSV file reading page and software operating instructions page
 */

public class MainViewController extends Component implements ActionListener {

    private MainView mainView;
    JDialog dialog = null;

    public MainViewController(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String jButtonTest = jButton.getText();

        if ("Select".equals(jButtonTest)) {
            MainView.getjFileChooser().setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int state = MainView.getjFileChooser().showOpenDialog(null);

            if (state == 1) {
                return;
            } else {
                File file = MainView.getjFileChooser().getSelectedFile();
                MainView.getText1().setText(file.getAbsolutePath());
                String gradePath = file.getAbsolutePath();
                JOptionPane.showMessageDialog(this, gradePath, "The paths are as follows", JOptionPane.WARNING_MESSAGE);
            }
        } else if ("Submit".equals(jButtonTest)) {
            String path = MainView.getText1().getText();
            String lastName = ReadCsvModel.readCsvToList(path).get(0).get(0);
            String firstName = ReadCsvModel.readCsvToList(path).get(0).get(1);
            String userName = ReadCsvModel.readCsvToList(path).get(0).get(2);
            String studentID = ReadCsvModel.readCsvToList(path).get(0).get(3);
            if (path.length() == 0) {
                notCorrect();
            }
            if (firstName.equals("First Name") && userName.equals("Username") && studentID.equals("Student ID")) {
                JOptionPane.showMessageDialog(this, "Successful import of grades!",
                        "Information", JOptionPane.INFORMATION_MESSAGE);
                new SearchView();
            } else {
                JOptionPane.showMessageDialog(this, "The data in the uploaded file is in the wrong format, please correct it before uploading!",
                        "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if ("Reset".equals(jButtonTest)) {
            MainView.getText1().setText("");
        }

    }

    private void notCorrect() {
        JPanel jPanel = new JPanel();
        JLabel information = new JLabel("Please select the file first!");
        Font font = new Font("Calibre", Font.PLAIN, 18);
        dialog = new JDialog(this.mainView, true);
        dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        information.setFont(font);
        dialog.setSize(300, 180);
        dialog.setTitle("Information");
        dialog.add(jPanel);
        jPanel.add(information);
        information.setBounds(10, 20, 40, 35);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

}
