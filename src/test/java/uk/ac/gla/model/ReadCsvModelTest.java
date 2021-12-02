package uk.ac.gla.model;

import org.junit.Before;
import org.junit.Test;
import uk.ac.gla.view.MainView;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Shangzhen Wei
 * @version 1.3
 */

public class ReadCsvModelTest {
    private static String path = "src/main/resources/gc_GA30003_SEM0000_2021_SMP.csv";
    private static List<List<String>> lists = null;

    @Before
    public void setUp() throws Exception {
        MainView.getText1().setText(path);
    }

    @Test
    public void readCsvToList() {
        lists = ReadCsvModel.readCsvToList(path);
        String res = lists.get(0).get(0);
        assertEquals("\uFEFFLast Name", res);
    }

    @Test
    public void getModuleName() {
        String res = ReadCsvModel.getModuleName(path);
        assertEquals("GA30003_SEM0000_2021", res);
    }

    @Test
    public void getStudentInformation() {
        String res = ReadCsvModel.getStudentInformation(path, 4);
        assertEquals("Tim Cook (2357968) ", res);
    }
}
