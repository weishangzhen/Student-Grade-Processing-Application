package uk.ac.gla.handler;

import uk.ac.gla.view.SearchView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Shangzhen Wei
 * @version 1.1
 */

public class SearchViewHandler extends Component implements ActionListener {

    private SearchView searchView;

    public SearchViewHandler(SearchView searchView) {
        this.searchView = searchView;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String jButtonTest = jButton.getText();

        if ("Search By Student Name".equals(jButtonTest)) {

        }

        if ("Search By Student ID".equals(jButtonTest)) {

        }

        if ("Generate A Report".equals(jButtonTest)) {


        }
    }
}
