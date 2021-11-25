package uk.ac.gla.model;

/**
 * @author Shangzhen Wei
 * @version 1.2
 */

/*
 * Build index objects to search for students by student ID or name
 */

public class IndexModel {
    private int index;

    public IndexModel(int index) {
        this.index = index;
    }

    public IndexModel() {
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
