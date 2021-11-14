package uk.ac.gla.handler;

import uk.ac.gla.view.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author Shangzhen Wei
 * @version 1.0
 */

public class MainViewHandler extends Component implements ActionListener {
    JDialog dialog = null;
    private MainView mainView;

    public MainViewHandler(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String jButtonTest = jButton.getText();

        if ("Select".equals(jButtonTest)) {
            MainView.getFileChooser().setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int state = MainView.getFileChooser().showOpenDialog(null);

            if (state == 1) {
                return;
            } else {
                File file = MainView.getFileChooser().getSelectedFile();
                MainView.getText1().setText(file.getAbsolutePath());
                String gradePath = file.getAbsolutePath();
                JOptionPane.showMessageDialog(this, gradePath, "The paths are as follows", JOptionPane.WARNING_MESSAGE);
            }
        } else if ("Submit".equals(jButtonTest)) {
            if (MainView.getText1().getText().length() == 0) {
                notCorrect();
            } else {
                showDialog();
            }
        } else if ("Reset".equals(jButtonTest)) {
            MainView.getText1().setText("");
        }

    }

    private void showDialog() {
        JPanel jPanel = new JPanel();
        JLabel information = new JLabel("Successful import of grades!");
        Font font = new Font("Calibre", Font.PLAIN, 18);
        dialog = new JDialog(this.mainView, true);
        dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        dialog.setSize(300, 180);
        dialog.setTitle("Information");
        information.setFont(font);
        dialog.add(jPanel);
        jPanel.add(information);
        information.setBounds(10, 20, 40, 35);
        dialog.setLocationRelativeTo(this);

        dialog.setVisible(true);
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
