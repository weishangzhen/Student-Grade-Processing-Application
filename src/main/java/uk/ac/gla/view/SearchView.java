package uk.ac.gla.view;

import uk.ac.gla.controller.SearchViewController;
import uk.ac.gla.model.ReadCsvModel;
import uk.ac.gla.util.ScreenDimensionUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.List;
import java.util.Vector;

/**
 * @author Shangzhen Wei
 * @version 1.1
 */

/*
 * Display page of read student grade data
 */

public class SearchView extends JFrame {

    private static final String PATH = MainView.getText1().getText();
    public static JLabel jLabelName = new JLabel();
    public static DefaultTableModel tableModel1 = new DefaultTableModel();
    public static Vector<String> columns = new Vector<>();
    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 50, 10));
    JButton searchByName = new JButton("Search By Student Name");
    JButton searchByID = new JButton("Search By Student ID");
    JButton generateReport = new JButton("Generate A Report");
    JLabel jLabel = new JLabel("The currently selected students are");
    JLabel jLabelForCurrentStudent = new JLabel();
    ;
    SearchViewController searchViewController;


    public SearchView() {
        super("Student Grades Results");
        SearchViewController searchViewController = new SearchViewController(this);
        Font font = new Font("Calibre", Font.PLAIN, 20);


        List<List<String>> lists = ReadCsvModel.readCsvToList(PATH);
        for (int i = 0; i < lists.get(0).size(); i++) {
            columns.addElement(lists.get(0).get(i));
            System.out.println(columns);
        }

        Vector<Vector<String>> data = new Vector<>();
        for (int j = 0; j < lists.size() - 2; j++) {
            Vector<String> vector = new Vector<>(lists.get(j + 2));
            System.out.println(vector);
            data.addElement(vector);
            System.out.println(data);
        }

        tableModel1.setDataVector(data, columns);

        JTable jTable = new JTable(tableModel1) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Container contentPane = getContentPane();
        JScrollPane jScrollPane = new JScrollPane(jTable);
        contentPane.add(jScrollPane);

        jLabelName.setForeground(Color.RED);
        searchByName.setFont(font);
        searchByID.setFont(font);
        jLabel.setFont(font);
        jLabelForCurrentStudent.setFont(font);
        generateReport.setFont(font);

        searchByID.addActionListener(searchViewController);
        searchByName.addActionListener(searchViewController);
        generateReport.addActionListener(searchViewController);
        northPanel.add(searchByName);
        northPanel.add(searchByID);
        northPanel.add(jLabel);
        northPanel.add(jLabelName);
        northPanel.add(jLabelForCurrentStudent);
        northPanel.add(generateReport);
        contentPane.add(northPanel, BorderLayout.NORTH);

        JTableHeader tableHeader = jTable.getTableHeader();
        tableHeader.setFont(new Font("Calibre", Font.BOLD, 16));
        tableHeader.setForeground(Color.RED);

        jTable.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        jTable.setForeground(Color.BLACK);
        jTable.setGridColor(Color.BLACK);
        jTable.setRowHeight(30);

        jTable.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        StudentCellRender studentCellRender = new StudentCellRender();

        for (int i = 0; i < columns.size(); i++) {
            TableColumn tableColumn = jTable.getColumn(columns.get(i));
            tableColumn.setCellRenderer(studentCellRender);
        }


        setBounds(ScreenDimensionUtil.getBounds());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);

    }

    public static String getPath() {
        return PATH;
    }

    class StudentCellRender extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            if (row % 2 == 0) {
                setBackground(Color.LIGHT_GRAY);
            } else {
                setBackground(Color.WHITE);
            }
            setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }

}
