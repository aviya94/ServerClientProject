package algorithm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

/**
 המחלקה הזו משמת את המחלקה Routes
 היא יורשת מהInterface Iindex

 */
public class Cell implements Serializable,  Iindex {

    int x;
    int y;


    Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return "("+x+ ","+y+")";
    }

    @Override
    public boolean equals(Object O) {

        Cell other = (Cell) O;
        return O != null && other.x == x && other.y == y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (x == 0 ? 0 : x) + (y == 0 ? 0 : y);

        return result;
    }



}



