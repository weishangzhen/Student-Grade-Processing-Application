package uk.ac.gla.controller;

import uk.ac.gla.model.GradeConversionModel;
import uk.ac.gla.model.ReadCsvModel;
import uk.ac.gla.util.GradesReportUtil;
import uk.ac.gla.view.MainView;
import uk.ac.gla.view.SearchView;
import uk.ac.gla.view.SearchViewForID;
import uk.ac.gla.view.SearchViewForName;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

/**
 * @author Shangzhen Wei
 * @version 1.1
 */

/*
 * Action Listening and Keyboard Listening for Search Student Page page
 */

public class SearchViewController extends Component implements ActionListener {

    private SearchView searchView;

    public SearchViewController(SearchView searchView) {
        this.searchView = searchView;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String jButtonTest = jButton.getText();

        if ("Search By Student Name".equals(jButtonTest)) {
            new SearchViewForName(searchView);
        }

        if ("Search By Student ID".equals(jButtonTest)) {
            new SearchViewForID(searchView);
        }

        if ("Generate Reports".equals(jButtonTest)) {
            if (SearchView.ids.isEmpty()) {
                JOptionPane.showMessageDialog(this, " No students have been selected, please search first. ",
                        "Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                for (String s : SearchView.ids) {
                    int i = 2;
                    for (Vector<String> strings : SearchView.data) {
                        System.out.println(strings.get(3));
                        if (strings.get(3).equals(s)) {
                            SearchIdController.indexModel.setIndex(i);
                            GradeConversionModel.gradeAfterConversion.clear();
                            GradeConversionModel.toPoints.clear();
                            GradeConversionModel.assessmentsCredits.clear();

                            String path = MainView.getText1().getText();
                            List<List<String>> lists = ReadCsvModel.readCsvToList(path);
                            System.out.println(SearchIdController.indexModel.getIndex());

                            GradesReportUtil gradesReport = new GradesReportUtil();
                            try {
                                gradesReport.generatePdf(gradesReport.document);
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                        i++;
                    }

                }
            }
        }
    }
}
