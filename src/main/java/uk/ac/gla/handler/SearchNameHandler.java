package uk.ac.gla.handler;

import uk.ac.gla.model.ReadCsvModel;
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

public class SearchNameHandler extends Component implements ActionListener {
    static int index;
    private SearchViewForName searchViewForName;

    public SearchNameHandler(SearchViewForName searchViewForName) {
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

            if ((lastName == null || lastName.isEmpty()) && (firstName == null || firstName.isEmpty())) {
                java.util.List<java.util.List<String>> lists = ReadCsvModel.readCsvToList(SearchView.getPath());
                for (int j = 0; j < lists.size() - 2; j++) {
                    Vector<String> vector = new Vector<>(lists.get(j + 2));
                    data.addElement(vector);
                }
                SearchView.tableModel1.setDataVector(data, SearchView.columns);
            } else {
                List<List<String>> res = searchUsedByName(lastName, firstName, SearchView.getPath());

                if (res.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "The student does not exist, please re-enter!",
                            "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    for (int j = 0; j < res.size(); j++) {
                        Vector<String> vector = new Vector<>(res.get(j));
                        data.addElement(vector);
                    }
                    SearchView.tableModel1.setDataVector(data, SearchView.columns);
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
        for (int i = 0; i < lists.size(); i++) {
            if (lastName.equalsIgnoreCase(lists.get(i).get(0)) && firstName.equalsIgnoreCase(lists.get(i).get(1))) {
                res.add(lists.get(i));
            }
        }
        return res;
    }
}