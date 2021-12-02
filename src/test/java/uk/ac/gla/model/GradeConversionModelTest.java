package uk.ac.gla.model;

import org.junit.Before;
import org.junit.Test;
import uk.ac.gla.controller.SearchIdController;
import uk.ac.gla.view.MainView;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Shangzhen Wei
 * @version 1.3
 */

public class GradeConversionModelTest {
    private static String path = "src/main/resources/gc_GA30003_SEM0000_2021_SMP.csv";
    private static ArrayList<String> lists = new ArrayList<>();

    private static ArrayList<String> gradeAfterConversion = new ArrayList<>();
    private static ArrayList<Integer> toPoints = new ArrayList<>();
    private static ArrayList<Integer> assessmentsCredits = new ArrayList<>();
    private static ArrayList<Integer> grades = new ArrayList<>();


    @Before
    public void setUp() throws Exception {
        MainView.getText1().setText(path);
        assessmentsCredits.add(5);
        assessmentsCredits.add(10);
        assessmentsCredits.add(15);
        assessmentsCredits.add(8);
        grades.add(20);
        grades.add(15);
        grades.add(12);
        grades.add(10);
    }

    @Test
    public void changeGrade() {
        SearchIdController.indexModel.setIndex(4);
        lists = (ArrayList<String>) GradeConversionModel.changeGrade();
        assertEquals("B1", lists.get(4));
        lists.clear();

        SearchIdController.indexModel.setIndex(9);
        lists = (ArrayList<String>) GradeConversionModel.changeGrade();
        assertEquals("AB", lists.get(4));
        lists.clear();

        SearchIdController.indexModel.setIndex(2);
        lists = (ArrayList<String>) GradeConversionModel.changeGrade();
        assertEquals("A1", lists.get(4));
        lists.clear();
    }

    @Test
    public void calculateGpa() {
        String res = GradeConversionModel.calculateGpa(assessmentsCredits, grades);
        assertEquals("C3 (13.42)", res);
    }

    @Test
    public void percentageTo23Scale() {
        String res = GradeConversionModel.percentageTo23Scale("15.13");
        assertEquals("C1", res);
        String res2 = GradeConversionModel.percentageTo23Scale("18.78");
        assertEquals("A5", res2);
        String res3 = GradeConversionModel.percentageTo23Scale("6.28");
        assertEquals("CF", res3);
        String res4 = GradeConversionModel.percentageTo23Scale("1.09");
        assertEquals("BF", res4);
    }

    @Test
    public void formatDecimal() {
        String res = GradeConversionModel.formatDecimal(12.91273641);
        assertEquals("12.91", res);
        String res2 = GradeConversionModel.formatDecimal(14.999973641);
        assertEquals("14.99", res2);
    }

    @Test
    public void isNumber() {
        boolean res = GradeConversionModel.isNumber("Agile Sprint 1");
        assertFalse(res);
    }
}
