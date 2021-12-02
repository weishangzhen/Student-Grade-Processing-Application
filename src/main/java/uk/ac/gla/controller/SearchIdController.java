package uk.ac.gla.controller;

import uk.ac.gla.model.IndexModel;
import uk.ac.gla.model.ReadCsvModel;
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
            if (id == null || id.isEmpty()) {
                java.util.List<java.util.List<String>> lists = ReadCsvModel.readCsvToList(SearchView.getPath());

                for (int j = 0; j < lists.size() - 2; j++) {
                    Vector<String> vector = new Vector<>(lists.get(j + 2));
                    data.addElement(vector);
                }

                SearchView.tableModel1.setDataVector(data, SearchView.columns);
            } else {
                java.util.List<java.util.List<String>> res = searchUsedById(id, SearchView.getPath());

                if (res.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "The student does not exist, please re-enter!",
                            "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    for (int j = 0; j < res.size(); j++) {
                        Vector<String> vector = new Vector<>(res.get(j));
                        data.addElement(vector);
                    }
                    SearchView.tableModel1.setDataVector(data, SearchView.columns);
                    indexModel.setIndex(searchUsedById1(id, SearchView.getPath()));

                    String lastName;
                    String firstName;
                    lastName = res.get(0).get(0);
                    firstName = res.get(0).get(1);
                    JOptionPane.showMessageDialog(this, lastName.toUpperCase() + " " + firstName.toUpperCase() + " has been found! ",
                            "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else if ("Exit".equals(jButtonTest)) {
            searchViewForID.dispose();
        }
    }

    public java.util.List<java.util.List<String>> searchUsedById(String id, String path) {
        java.util.List<java.util.List<String>> res = new ArrayList<>();
        java.util.List<java.util.List<String>> lists = ReadCsvModel.readCsvToList(path);
        for (int i = 2; i < lists.size(); i++) {
            if (lists.get(i).get(3).equals(id)) {
                res.add(lists.get(i));
            }
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
}
