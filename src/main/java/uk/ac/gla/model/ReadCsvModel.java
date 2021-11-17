package uk.ac.gla.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Shangzhen Wei
 * @version 1.1
 */
public class ReadCsvModel {

    public static List<List<String>> readCsvToList(String path) {
        String line;
        List<List<String>> columnList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                List<String> column = Arrays.asList(line.split(","));
                columnList.add(column);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return columnList;
    }

    public static String getModuleName(String path) {

        File file = new File(path);
        String moduleName1 = file.getName();
        String[] partsOfName = moduleName1.split("_");
        return partsOfName[1] + "_" + partsOfName[2] + "_" + partsOfName[3];

    }

    public static String getStudentInformation(String path, int num) {
        String lastName = readCsvToList(path).get(num).get(0);
        String firstName = readCsvToList(path).get(num).get(1);
        String studentID = readCsvToList(path).get(num).get(3);

        return lastName + " " + firstName + " (" + studentID + ") ";

    }
}

