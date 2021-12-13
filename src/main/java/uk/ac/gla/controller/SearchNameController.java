package uk.ac.gla.controller;

import uk.ac.gla.model.ReadCsvModel;
import uk.ac.gla.view.MainView;
import uk.ac.gla.view.SearchView;
import uk.ac.gla.view.SearchViewForName;

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

public class SearchNameController extends Component implements ActionListener {
    static int index;
    private SearchViewForName searchViewForName;
    JDialog dialog = null;

    public SearchNameController(SearchViewForName searchViewForName) {
        this.searchViewForName = searchViewForName;
    }

    public static int getIndex() {
        return index;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String jButtonTest = jButton.getText();

        if ("Search".equals(jButtonTest)) {
            String lastName = SearchViewForName.getLastNameTextField().getText();
            String firstName = SearchViewForName.getFirstNameTextField().getText();
            System.out.println(lastName + firstName);
            Vector<Vector<String>> data = new Vector<>();
            if ((lastName == null || firstName == null || searchUsedByNameCheck(lastName, firstName, MainView.getText1().getText()) == -1)) {
                JOptionPane.showMessageDialog(this, "Input is empty or student does not exist, please re-enter!",
                        "Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                List<List<String>> res = searchUsedByName(lastName, firstName, SearchView.getPath());
                if (res.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "The student does not exist, please re-enter!",
                            "Information", JOptionPane.INFORMATION_MESSAGE);
                } else if (res.size() == 2) {
                    JOptionPane.showMessageDialog(this, "This student has been chosen!",
                            "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    for (int j = 0; j < res.size(); j++) {
                        Vector<String> vector = new Vector<>(res.get(j));
                        data.addElement(vector);
                    }
                    SearchView.tableModel1.addRow(data.get(0));
                    studentSuccessDialog();
                    JOptionPane.showMessageDialog(this, lastName.toUpperCase() + " " + firstName.toUpperCase() + " has been found! ",
                            "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else if ("Exit".equals(jButtonTest)) {
            searchViewForName.dispose();
        }

    }

    public List<List<String>> searchUsedByName(String lastName, String firstName, String path) {
        List<List<String>> res = new ArrayList<>();
        List<List<String>> lists = ReadCsvModel.readCsvToList(path);
        for (List<String> list : lists) {
            if (lastName.equalsIgnoreCase(list.get(0)) && firstName.equalsIgnoreCase(list.get(1))) {
                res.add(list);
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

    public int searchUsedByNameCheck(String lastName, String firstName, String path) {
        List<List<String>> lists = ReadCsvModel.readCsvToList(path);
        for (int i = 0; i < lists.size(); i++) {
            if (lastName.equalsIgnoreCase(lists.get(i).get(0)) && firstName.equalsIgnoreCase(lists.get(i).get(1))) {
                return i;
            }
        }
        return -1;
    }

    private void studentSuccessDialog() {
        JPanel jPanel = new JPanel();
        JLabel information = new JLabel("Search ends");
        Font font = new Font("Calibre", Font.PLAIN, 18);
        dialog = new JDialog(this.searchViewForName, true);
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
}
