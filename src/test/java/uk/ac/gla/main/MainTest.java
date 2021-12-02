package uk.ac.gla.main;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import uk.ac.gla.controller.SearchIdController;
import uk.ac.gla.controller.SearchNameController;
import uk.ac.gla.model.ReadCsvModel;
import uk.ac.gla.view.MainView;

import java.util.List;


/**
 * Unit testing of key functions
 */

public class MainTest {
    private static String path = "src/main/resources/gc_GA30003_SEM0000_2021_SMP.csv";
    private static List<List<String>> lists = null;
    private static SearchIdController searchIdController = null;
    private static SearchNameController searchNameController = null;

    @BeforeClass
    public static void before() {
        MainView.getText1().setText(path);
        lists = ReadCsvModel.readCsvToList(path);
        searchIdController = new SearchIdController(null);
        searchNameController = new SearchNameController(null);
    }

    @Test
    public void searchUsedById() {
        List<String> list = searchIdController.searchUsedById("2256968", path).get(0);
        Assert.assertEquals("Jones", list.get(0));
        Assert.assertEquals("Laura", list.get(1));
    }

    @Test
    public void searchUsedByName() {
        List<String> list = searchNameController.searchUsedByName("Jones", "Laura", path).get(0);
        Assert.assertEquals("Jones", list.get(0));
        Assert.assertEquals("Laura", list.get(1));
    }

}
