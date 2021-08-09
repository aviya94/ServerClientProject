
package algorithm;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;

/**
 * This class finds the shortest route between index source and target index using the FindShortestRoutesa method
 */

public class ShortestRoutes implements Serializable {
    int[][] mat;
    Index start;
    Index end;
    ArrayList<HashSet<Index>> ListRoutes;

public ShortestRoutes()
    {
        ListRoutes = new ArrayList<HashSet<Index>>();
    }
    private void InputTest(int[][] Mat, Index Start, Index End){

        try
        {
            if(Mat.length>50) {
                throw new IllegalArgumentException();
            }
            if ((Start.row>Mat.length)||(Start.column>Mat.length)||(End.row>Mat.length)||(End.row>Mat.length))
            {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("This maximum matrix size is 50X50");
            throw e; // rethrowing the exception
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Array Index Out Of Bounds Exception");
            throw e; // rethrowing the exception
        }
        start = Start;
        end = End;
        mat = Mat;

    }
    public ArrayList<HashSet<Index>> FindShortestRoutesa(int[][] Mat, Index Start, Index End)
    {
        InputTest(Mat,Start,End);


        HashSet<Index> hash = new HashSet<Index>();
        ListRoutes = FindShortestRoutes(mat, start, end, hash);
        ListRoutes.sort(GroupSizeComparator);
        FilterRoutes();
        try
        {
            if(ListRoutes.size()==0) {
                throw new NullPointerException();
            }
        }
        catch(NullPointerException  e)
        {
            System.out.println("There is no route from index "+start.toString()+" to index "+end.toString());
            throw e; // rethrowing the exception
        }
    return ListRoutes;
    }


   private ArrayList<HashSet<Index>> FindShortestRoutes(int[][] Matt, Index Start, Index End, HashSet<Index> hash)
   {
        Matrix Matalgo = new Matrix(Matt);
        Collection<Index> rechables;
        rechables = Matalgo.getReachables(Start);
        HashSet<Index> Hash = new HashSet<Index>();

        for (Index index : hash)
        {
            Hash.add(index);
        }

        Hash.add(Start);
        ListRoutes.add(Hash);
        Matalgo.primitiveMatrix[Start.row][Start.column] = 0;

        for (Index i : rechables)
            {
            if (i == end) {
                return ListRoutes;
            }
            FindShortestRoutes(Matalgo.primitiveMatrix, i, End, Hash);

        }
        return ListRoutes;
    }



    private void FilterRoutes() ///פונקציה שמפלטרת מסלולים לא תקינים או שהגודל שלהם גדול מהמסלול הקצר ביותר
    {
        int minSize=0;
        ArrayList<HashSet<Index>> listRoutes = new ArrayList<HashSet<Index>>();

        for (HashSet<Index> index : ListRoutes) {//בדיקה אם אחד מהhashset מכיל את האינדקס סוף אם כן יכניס לMin size
            if (index.contains(end)) {
                minSize=index.size();

                break;
            }
        }
        for (HashSet<Index> index : ListRoutes) {
            if (index.contains(end)&&index.size()<minSize) {
                minSize=index.size();
                break;
            }
        }
        for (HashSet<Index> index : ListRoutes) {
            if (index.contains(end)&&index.size()==minSize) {
                listRoutes.add(index);
            }
        }
        ListRoutes = listRoutes;

    }

    public Comparator<HashSet<Index>> GroupSizeComparator = new Comparator <HashSet<Index>>()
    //קומפרטור שממיין גודל קבוצות hash בתוך המערך מהקטן לגדול
    {
        @Override
        public int compare(HashSet<Index> o1, HashSet<Index> o2) {
            return o1.size()- o2.size();
        }

    };

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (HashSet<Index> index : ListRoutes) {
            stringBuilder.append("[");
            for (Index i : index)
            {
                stringBuilder.append(i.toString());

            }
            stringBuilder.append("]");
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
