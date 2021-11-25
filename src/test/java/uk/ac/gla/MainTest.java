package uk.ac.gla;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import uk.ac.gla.handler.SearchIdHandler;
import uk.ac.gla.handler.SearchNameHandler;
import uk.ac.gla.model.ReadCsvModel;
import uk.ac.gla.view.MainView;

import java.util.List;


/**
 * Unit testing of key functions
 */

public class MainTest {
    private static String path = "src/main/resources/gc_GA30003_SEM0000_2021_SMP.csv";
    private static List<List<String>> lists = null;
    private static SearchIdHandler searchIdHandler = null;
    private static SearchNameHandler searchNameHandler = null;

    @BeforeClass
    public static void before() {
        MainView.getText1().setText(path);
        lists = ReadCsvModel.readCsvToList(path);
        searchIdHandler = new SearchIdHandler(null);
        searchNameHandler = new SearchNameHandler(null);
    }

    @Test
    public void searchUsedById() {
        List<String> list = searchIdHandler.searchUsedById("2256968", path).get(0);
        Assert.assertEquals("Jones", list.get(0));
        Assert.assertEquals("Laura", list.get(1));
    }

    @Test
    public void searchUsedByName() {
        List<String> list = searchNameHandler.searchUsedByName("Jones", "Laura", path).get(0);
        Assert.assertEquals("Jones", list.get(0));
        Assert.assertEquals("Laura", list.get(1));
    }

}
