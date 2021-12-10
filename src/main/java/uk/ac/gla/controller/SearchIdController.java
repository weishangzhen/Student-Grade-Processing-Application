package uk.ac.gla.controller;

import uk.ac.gla.model.IndexModel;
import uk.ac.gla.model.ReadCsvModel;
import uk.ac.gla.view.MainView;
import uk.ac.gla.view.SearchView;
import uk.ac.gla.view.SearchViewForID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @author Shangzhen Wei
 * @version 1.2
 */

/*
 * Action Listening for Search Student Page page
 */

public class SearchIdController extends Component implements ActionListener {
    public static IndexModel indexModel = new IndexModel();
    static int index;
    JDialog dialog = null;
    private SearchViewForID searchViewForID;

    public SearchIdController(SearchViewForID searchViewForID) {
        this.searchViewForID = searchViewForID;
    }

    public static int getIndex() {
        return index;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String jButtonTest = jButton.getText();

        if ("Search".equals(jButtonTest)) {
            String id = SearchViewForID.getStudentIDTextField().getText();
            Vector<Vector<String>> data = new Vector<>();
            if (id == null || id.isEmpty() || searchUsedById1(id, MainView.getText1().getText()) == -1) {
                JOptionPane.showMessageDialog(this, "Input is empty or student does not exist, please re-enter!",
                        "Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                List<List<String>> res = searchUsedById(id, SearchView.getPath());

                if (res.isEmpty()) {
                    studentFailDialog();
                    JOptionPane.showMessageDialog(this, "The student does not exist, please re-enter!",
                            "Information", JOptionPane.INFORMATION_MESSAGE);
                } else if (res.size() == 2) {
                    JOptionPane.showMessageDialog(this, "The student exist in the table!",
                            "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    for (int j = 0; j < res.size(); j++) {
                        Vector<String> vector = new Vector<>(res.get(j));
                        data.addElement(vector);
                    }
                    SearchView.tableModel1.addRow(data.get(0));
                    indexModel.setIndex(searchUsedById1(id, SearchView.getPath()));
                    String lastName;
                    String firstName;
                    lastName = res.get(0).get(0);
                    firstName = res.get(0).get(1);
                    JOptionPane.showMessageDialog(this, lastName.toUpperCase() + " " + firstName.toUpperCase() + " has been found! ",
                            "Information", JOptionPane.INFORMATION_MESSAGE);
                    studentSuccessDialog();
                }
            }
        } else if ("Exit".equals(jButtonTest)) {
            searchViewForID.dispose();
        }
    }


    public List<List<String>> searchUsedById(String id, String path) {
        List<List<String>> res = new ArrayList<>();
        List<List<String>> lists = ReadCsvModel.readCsvToList(path);
        for (int i = 2; i < lists.size(); i++) {
            if (lists.get(i).get(3).equals(id)) {
                res.add(lists.get(i));
            }
        }
        boolean isFind = false;
        for (String s : SearchView.ids) {
            if (s.equals(res.get(0).get(3))) {
                isFind = true;
            }
        }
        if (isFind) {
            res.add(lists.get(0));
        } else {
            SearchView.ids.add(res.get(0).get(3));
        }

        return res;
    }

    public int searchUsedById1(String id, String path) {
        List<List<String>> lists = ReadCsvModel.readCsvToList(path);
        for (int i = 2; i < lists.size(); i++) {
            if (lists.get(i).get(3).equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private void studentSuccessDialog() {
        JPanel jPanel = new JPanel();
        JLabel information = new JLabel("The student has been found!");
        Font font = new Font("Calibre", Font.PLAIN, 18);
        dialog = new JDialog(this.searchViewForID, true);
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

    private void studentFailDialog() {
        JPanel jPanel = new JPanel();
        JLabel information = new JLabel("The student does not exist, please re-enter!");
        Font font = new Font("Calibre", Font.PLAIN, 18);
        dialog = new JDialog(this.searchViewForID, true);
        dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        dialog.setSize(500, 180);
        dialog.setTitle("Information");
        information.setFont(font);
        dialog.add(jPanel);
        jPanel.add(information);
        information.setBounds(10, 20, 40, 35);
        dialog.setLocationRelativeTo(this);

        dialog.setVisible(true);
    }
}
