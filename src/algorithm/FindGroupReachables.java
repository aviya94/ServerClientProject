/*
package algorithm;

import java.util.*;

public class FindGroupReachables<namesComparator1> {
    public Matrix matrix;
    public ArrayList<HashSet<Index>> listOfReachables;

    public FindGroupReachables(int[][] oArray) {
        matrix = new Matrix(oArray);
        listOfReachables=new ArrayList<HashSet<Index>>();
        FindGroup();
    }






    public ArrayList<HashSet<Index>> FindGroup() {

        for (int countOne = 0; countOne < matrix.primitiveMatrix.length; countOne++) {
            for (int countTwo = 0; countTwo < matrix.primitiveMatrix[countOne].length; countTwo++)
            {

                if(matrix.primitiveMatrix[countOne][countTwo]==1)
                {

                    Index IsTheIndexInside=new Index(countOne,countTwo);
                    checIfkContainHash (IsTheIndexInside);
                }
            }
        }
        listOfReachables.sort(GroupSizeComparator);
        return listOfReachables;
    }

    public void checIfkContainHash (Index indexOut)
    { boolean bool=false;
        Collection<Index> GroupReachables=matrix.getReachables(indexOut);
        for(HashSet<Index> indexHash: listOfReachables)
        {
            for(Index x :GroupReachables)
            {
                if(indexHash.contains(x))
                {
                    bool= true;
                    indexHash.add(indexOut);
                    break;
                }
            }

        }
        if(bool==false)
        {
            HashSet<Index> newHash=new HashSet<Index>();
            newHash.add(indexOut);
            listOfReachables.add(newHash);
        }


    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (HashSet<Index> index : listOfReachables) {
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

    Comparator <HashSet<Index>> GroupSizeComparator = new Comparator <HashSet<Index>>() {
        @Override
        public int compare(HashSet<Index> o1, HashSet<Index> o2) {
            return o1.size()- o2.size();
        }

    };

        public static void main(String[] args) {
            int[][] source = {
                    {1, 0, 1},
                    {1, 0, 1},
                    {1, 1, 1}
            };

        FindGroupReachables findGroupReachables = new FindGroupReachables(source);
        ArrayList<HashSet<Index>> j=findGroupReachables.FindGroup();
        // findGroupReachables.printMatrix();
        System.out.println(findGroupReachables.toString());

    }
}
*/
package algorithm;

import java.beans.IndexedPropertyDescriptor;
import java.util.*;
/**
This class will find for us all the routes that are within the matrix whose value is 1
 and can be reached from any vertex to any vertex within a specific Hashset
The main method is FindGroupReachables
It calls the checIfkContainHash method that checks for the vertex neighbors that have a value of 1
 if they are already in a particular hashset,
 if they do not open a new hashset for it.
 */

public class FindGroupReachables {
    public Matrix matrix;
    public ArrayList<HashSet<Index>> listOfReachables;

    public FindGroupReachables() {

        listOfReachables=new ArrayList<HashSet<Index>>();

    }

    public ArrayList<HashSet<Index>> FindGroup(int[][] oArray) {
        matrix = new Matrix(oArray);
        Index IsTheIndexInside;
        for (int countOne = 0; countOne < matrix.primitiveMatrix.length; countOne++) {
            for (int countTwo = 0; countTwo < matrix.primitiveMatrix[countOne].length; countTwo++)
            {
                if(matrix.primitiveMatrix[countOne][countTwo]==1)
                {
                    IsTheIndexInside=new Index(countOne,countTwo);
                    checIfkContainHash (IsTheIndexInside);
                }
            }
        }

        ArrayList<HashSet<Index>> listOfReachablesCopy=listOfReachables;
        for(HashSet<Index> inHash :listOfReachables)
        {
            if(inHash.size()==0)
            {
                listOfReachablesCopy.remove(inHash);
            }
        }
        listOfReachables=listOfReachablesCopy;
        listOfReachables.sort(GroupSizeComparator);
        return listOfReachables;
    }

    public void checIfkContainHash (Index indexOut)
    { boolean bool=false;
        Collection<Index> GroupReachables=matrix.getReachables(indexOut);
        GroupReachables.add(indexOut);

        for(HashSet<Index> indexHash: listOfReachables)
        {
            for(Index x :GroupReachables)
            {
                if(indexHash.contains(x))
                {
                    bool= true;
                    indexHash.add(indexOut);

                    for(HashSet<Index> inHash :listOfReachables)// אם יש לי כפילות של hashset ננקה אחד מהם ונעביר לhashset חדש
                    {
                        if(inHash!=indexHash && inHash.contains(x))
                        { indexHash.addAll(inHash);
                            inHash.clear();
                        }
                    }
                }
            }
        }

        if(bool==false)//פותחים Hashset חדש כי לא נמצאו שכנים הנמצאים באף hashset
        {
            HashSet<Index> newHash=new HashSet<Index>();
            newHash.add(indexOut);
            listOfReachables.add(newHash);
        }
    }


    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (HashSet<Index> index : listOfReachables) {
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

    Comparator <HashSet<Index>> GroupSizeComparator = new Comparator <HashSet<Index>>() {
        @Override
        public int compare(HashSet<Index> o1, HashSet<Index> o2) {
            return o1.size()- o2.size();
        }

    };
}









