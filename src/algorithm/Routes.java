package algorithm;

import java.util.*;

/**
 * הפונקציה הראשית היאroutes, היא שולחת לפונקציה FindAllRoutes
 * היא מוצאת את כל המסלולים בשיטת DFS ברקורסיה, מסדרת ומחזירה אותם
 */

public class Routes {

    public ArrayList<HashSet<Cell>> list;
    public Cell cell;
    public Matrix matrix;
    public Routes()
    {
        list=new ArrayList<HashSet<Cell>>();
    }
    public ArrayList<HashSet<Cell>> routes (Integer[][] matrix, Cell start, Cell end, HashSet<Cell> current, HashSet<Cell> visited, ArrayList<HashSet<Cell>> result)
    {
        list=FindAllRoutes(matrix,start,end,current,visited,result);
        list.sort(GroupSizeComparator);
        return list;
    }

    public  ArrayList<HashSet<Cell>> FindAllRoutes(Integer[][] matrix, Cell start, Cell end, HashSet<Cell> current, HashSet<Cell> visited, ArrayList<HashSet<Cell>> result){
        if ((start.x > matrix.length) & (start.y > matrix[0].length)){
            return result;
        };
        if(matrix[start.x][start.y] == 0 || visited.contains(start)){
            return result;
        }else if(start.x == end.x && start.y == end.y){
            visited.add(start);
            current.add(start);
            result.add(current);
            return result;
        }else {
            visited.add(start);
            current.add(start);
        }

        if(start.x + 1 < matrix.length)
            FindAllRoutes(matrix,new Cell(start.x + 1, start.y),end,new HashSet<>(current),new HashSet<>(visited),result );
        if(start.x - 1 >= 0)
            FindAllRoutes(matrix,new Cell(start.x - 1, start.y),end,new HashSet<>(current),new HashSet<>(visited),result );
        if(start.y + 1 < matrix[0].length)
            FindAllRoutes(matrix,new Cell(start.x , start.y + 1),end,new HashSet<>(current),new HashSet<>(visited),result );
        if(start.y - 1 >= 0)
            FindAllRoutes(matrix,new Cell(start.x , start.y - 1),end,new HashSet<>(current),new HashSet<>(visited),result );

        if(start.x + 1 < matrix.length&&start.y + 1 < matrix[0].length)
            FindAllRoutes(matrix,new Cell(start.x + 1, start.y+1),end,new HashSet<>(current),new HashSet<>(visited),result );
        if(start.x - 1 >= 0&&start.y - 1 >= 0)
            FindAllRoutes(matrix,new Cell(start.x - 1, start.y-1),end,new HashSet<>(current),new HashSet<>(visited),result );
        if(start.y + 1 < matrix[0].length&&start.x - 1 >= 0)
            FindAllRoutes(matrix,new Cell(start.x-1 , start.y + 1),end,new HashSet<>(current),new HashSet<>(visited),result );
        if(start.x + 1 < matrix.length&&start.y - 1 >= 0)
            FindAllRoutes(matrix,new Cell(start.x+1 , start.y - 1),end,new HashSet<>(current),new HashSet<>(visited),result );

        return result;
    }

    public Comparator<HashSet<Cell>> GroupSizeComparator = new Comparator <HashSet<Cell>>() {
        @Override
        public int compare(HashSet<Cell> o1, HashSet<Cell> o2) {
            return o1.size()- o2.size();
        }

    };

}
