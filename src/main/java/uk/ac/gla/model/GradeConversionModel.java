package uk.ac.gla.model;

import uk.ac.gla.controller.SearchIdController;
import uk.ac.gla.view.MainView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shangzhen Wei
 * @version 1.3
 */

public class GradeConversionModel {
    private static final String path = MainView.getText1().getText();
    public static ArrayList<String> gradeAfterConversion = new ArrayList<>();
    public static ArrayList<Integer> toPoints = new ArrayList<>();
    public static ArrayList<Integer> assessmentsCredits = new ArrayList<>();


    /*
     * Converting percentage student grades in CSV files to 23-point scale and store in List
     */
    public static List<String> changeGrade() {

        for (int i = 0; i < 4; i++) {
            String temp = ReadCsvModel.readCsvToList(path).get(SearchIdController.indexModel.getIndex()).get(i);
            gradeAfterConversion.add(temp);
        }

        for (int i = 4; i < ReadCsvModel.readCsvToList(path).get(SearchIdController.indexModel.getIndex()).size(); i++) {
            String point;
            int associatedAggregationScale;
            String check = ReadCsvModel.readCsvToList(path).get(SearchIdController.indexModel.getIndex()).get(i);
            boolean number = isNumber(check);
            if (number) {
                assessmentsCredits.add(Integer.valueOf(ReadCsvModel.readCsvToList(path).get(1).get(i)));
                int grade = Integer.parseInt(check);
                if (grade >= 95 && grade <= 100) {
                    point = "A1";
                    associatedAggregationScale = 23;
                    gradeAfterConversion.add(point);
                    toPoints.add(associatedAggregationScale);
                } else if (grade >= 89 && grade < 95) {
                    point = "A2";
                    associatedAggregationScale = 22;
                    gradeAfterConversion.add(point);
                    toPoints.add(associatedAggregationScale);
                } else if (grade >= 83 && grade < 89) {
                    point = "A3";
                    associatedAggregationScale = 21;
                    gradeAfterConversion.add(point);
                    toPoints.add(associatedAggregationScale);
                } else if (grade >= 76 && grade < 83) {
                    point = "A4";
                    associatedAggregationScale = 20;
                    gradeAfterConversion.add(point);
                    toPoints.add(associatedAggregationScale);
                } else if (grade >= 70 && grade < 76) {
                    point = "A5";
                    associatedAggregationScale = 19;
                    gradeAfterConversion.add(point);
                    toPoints.add(associatedAggregationScale);
                } else if (grade >= 67 && grade < 70) {
                    point = "B1";
                    associatedAggregationScale = 18;
                    gradeAfterConversion.add(point);
                    toPoints.add(associatedAggregationScale);
                } else if (grade >= 64 && grade < 67) {
                    point = "B2";
                    associatedAggregationScale = 17;
                    gradeAfterConversion.add(point);
                    toPoints.add(associatedAggregationScale);
                } else if (grade >= 60 && grade < 64) {
                    point = "B3";
                    associatedAggregationScale = 16;
                    gradeAfterConversion.add(point);
                    toPoints.add(associatedAggregationScale);
                } else if (grade >= 57 && grade < 60) {
                    point = "C1";
                    associatedAggregationScale = 15;
                    gradeAfterConversion.add(point);
                    toPoints.add(associatedAggregationScale);
                } else if (grade >= 54 && grade < 57) {
                    point = "C2";
                    associatedAggregationScale = 14;
                    gradeAfterConversion.add(point);
                    toPoints.add(associatedAggregationScale);
                } else if (grade >= 50 && grade < 54) {
                    point = "C3";
                    associatedAggregationScale = 13;
                    gradeAfterConversion.add(point);
                    toPoints.add(associatedAggregationScale);
                } else if (grade >= 47 && grade < 50) {
                    point = "D1";
                    associatedAggregationScale = 12;
                    gradeAfterConversion.add(point);
                    toPoints.add(associatedAggregationScale);
                } else if (grade >= 44 && grade < 47) {
                    point = "D2";
                    associatedAggregationScale = 11;
                    gradeAfterConversion.add(point);
                    toPoints.add(associatedAggregationScale);
                } else if (grade >= 40 && grade < 44) {
                    point = "D3";
                    associatedAggregationScale = 10;
                    gradeAfterConversion.add(point);
                    toPoints.add(associatedAggregationScale);
                } else if (grade >= 37 && grade < 40) {
                    point = "M1";
                    associatedAggregationScale = 9;
                    gradeAfterConversion.add(point);
                    toPoints.add(associatedAggregationScale);
                } else if (grade >= 34 && grade < 37) {
                    point = "M2";
                    associatedAggregationScale = 8;
                    gradeAfterConversion.add(point);
                    toPoints.add(associatedAggregationScale);
                } else if (grade >= 30 && grade < 34) {
                    point = "M3";
                    associatedAggregationScale = 7;
                    gradeAfterConversion.add(point);
                    toPoints.add(associatedAggregationScale);
                } else if (grade >= 20 && grade < 30) {
                    point = "CF";
                    associatedAggregationScale = 5;
                    gradeAfterConversion.add(point);
                    toPoints.add(associatedAggregationScale);
                } else if (grade >= 0 && grade < 20) {
                    point = "BF";
                    associatedAggregationScale = 2;
                    gradeAfterConversion.add(point);
                    toPoints.add(associatedAggregationScale);
                }
            } else {
                if ("CA".equals(check)) {
                    point = "CA";
                    gradeAfterConversion.add(point);
                    assessmentsCredits.add(0);
                    associatedAggregationScale = 0;
                    toPoints.add(associatedAggregationScale);
                } else if ("DC".equals(check)) {
                    point = "DC";
                    gradeAfterConversion.add(point);
                    assessmentsCredits.add(0);
                    associatedAggregationScale = 0;
                    toPoints.add(associatedAggregationScale);
                } else if ("DS".equals(check)) {
                    point = "DS";
                    gradeAfterConversion.add(point);
                    assessmentsCredits.add(0);
                    associatedAggregationScale = 0;
                    toPoints.add(associatedAggregationScale);
                } else if ("MC".equals(check)) {
                    point = "MC";
                    gradeAfterConversion.add(point);
                    assessmentsCredits.add(0);
                    associatedAggregationScale = 0;
                    toPoints.add(associatedAggregationScale);
                } else if ("ME".equals(check)) {
                    point = "ME";
                    gradeAfterConversion.add(point);
                    assessmentsCredits.add(0);
                    associatedAggregationScale = 0;
                    toPoints.add(associatedAggregationScale);
                } else if ("QF".equals(check)) {
                    point = "QF";
                    gradeAfterConversion.add(point);
                    assessmentsCredits.add(0);
                    associatedAggregationScale = 0;
                    toPoints.add(associatedAggregationScale);
                } else if ("ST".equals(check)) {
                    point = "ST";
                    gradeAfterConversion.add(point);
                    assessmentsCredits.add(0);
                    associatedAggregationScale = 0;
                    toPoints.add(associatedAggregationScale);
                } else if ("WD".equals(check)) {
                    point = "WD";
                    gradeAfterConversion.add(point);
                    assessmentsCredits.add(0);
                    associatedAggregationScale = 0;
                    toPoints.add(associatedAggregationScale);
                } else if ("P".equals(check)) {
                    point = "P";
                    gradeAfterConversion.add(point);
                    assessmentsCredits.add(0);
                    associatedAggregationScale = 0;
                    toPoints.add(associatedAggregationScale);
                } else if ("F".equals(check)) {
                    point = "F";
                    gradeAfterConversion.add(point);
                    assessmentsCredits.add(0);
                    associatedAggregationScale = 0;
                    toPoints.add(associatedAggregationScale);
                } else if ("AB".equals(check)) {
                    point = "AB";
                    gradeAfterConversion.add(point);
                    assessmentsCredits.add(Integer.valueOf(ReadCsvModel.readCsvToList(path).get(1).get(i)));
                    associatedAggregationScale = 0;
                    toPoints.add(associatedAggregationScale);
                } else if ("NM".equals(check)) {
                    point = "NM";
                    gradeAfterConversion.add(point);
                    assessmentsCredits.add(Integer.valueOf(ReadCsvModel.readCsvToList(path).get(1).get(i)));
                    associatedAggregationScale = 0;
                    toPoints.add(associatedAggregationScale);
                }
            }

        }
        return gradeAfterConversion;

    }

    /*
     * Calculation of GPA (assessment grade * assessment credits / total credits)
     */
    public static String calculateGpa(List<Integer> credits, List<Integer> transGrades) {
        changeGrade();
        double totalCredits = 0;
        double tempPlus = 0;

        for (Integer cre : credits) {
            totalCredits = totalCredits + cre;
        }

        for (int i = 0; i < credits.size(); i++) {
            double temp = credits.get(i) * transGrades.get(i);
            tempPlus = tempPlus + temp;

        }

        return percentageTo23Scale(formatDecimal(tempPlus / totalCredits)) + " " + "(" + formatDecimal(tempPlus / totalCredits) + ")";

    }

    /*
     * Transfer overall grade to 23-point scale grade
     */
    public static String percentageTo23Scale(String averageScale) {
        double averageForCalculate = Double.parseDouble(averageScale);
        String scale = null;
        if (averageForCalculate >= 22.50) {
            scale = "A1";
        } else if (averageForCalculate >= 21.50 && averageForCalculate < 22.50) {
            scale = "A2";
        } else if (averageForCalculate >= 20.50 && averageForCalculate < 21.50) {
            scale = "A3";
        } else if (averageForCalculate >= 19.50 && averageForCalculate < 20.50) {
            scale = "A4";
        } else if (averageForCalculate >= 18.50 && averageForCalculate < 19.50) {
            scale = "A5";
        } else if (averageForCalculate >= 17.50 && averageForCalculate < 18.50) {
            scale = "B1";
        } else if (averageForCalculate >= 16.50 && averageForCalculate < 17.50) {
            scale = "B2";
        } else if (averageForCalculate >= 15.50 && averageForCalculate < 16.50) {
            scale = "B3";
        } else if (averageForCalculate >= 14.50 && averageForCalculate < 15.50) {
            scale = "C1";
        } else if (averageForCalculate >= 13.50 && averageForCalculate < 14.50) {
            scale = "C2";
        } else if (averageForCalculate >= 12.50 && averageForCalculate < 13.50) {
            scale = "C3";
        } else if (averageForCalculate >= 11.50 && averageForCalculate < 12.50) {
            scale = "D1";
        } else if (averageForCalculate >= 10.50 && averageForCalculate < 11.50) {
            scale = "D2";
        } else if (averageForCalculate >= 9.50 && averageForCalculate < 10.50) {
            scale = "D3";
        } else if (averageForCalculate >= 8.50 && averageForCalculate < 9.50) {
            scale = "M1";
        } else if (averageForCalculate >= 7.50 && averageForCalculate < 8.50) {
            scale = "M2";
        } else if (averageForCalculate >= 6.50 && averageForCalculate < 7.50) {
            scale = "M3";
        } else if (averageForCalculate < 6.50 && averageForCalculate >= 2.50) {
            scale = "CF";
        } else if (averageForCalculate < 2.50 && averageForCalculate >= 0) {
            scale = "BF";
        }
        return scale;
    }

    /*
     * Retain 2 decimal places and do not round up
     */
    public static String formatDecimal(double value) {
        final DecimalFormat formater = new DecimalFormat();
        formater.setMaximumFractionDigits(2);
        formater.setMinimumFractionDigits(2);
        formater.setGroupingSize(0);
        formater.setRoundingMode(RoundingMode.FLOOR);
        return formater.format(value);
    }

    public static boolean isNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
