package algorithm;
import java.util.*;

/**
 Creates an instance of the classes calling the relevant functions
 */
public class RunTasks {
    FindGroupReachables findGroupReachables;
    ShortestRoutes shortestRoutes;


    public RunTasks(){
        findGroupReachables = new FindGroupReachables();
        shortestRoutes=new ShortestRoutes();

    }

    public ArrayList<HashSet<Index>> FindGroups(int [][]source)
    {
        return  findGroupReachables.FindGroup(source);
    }
    public ArrayList<HashSet<Index>> findShortestRoutes(int [][]source,Index start,Index end)

    {
        return  shortestRoutes.FindShortestRoutesa(source,start,end);
    }
}

