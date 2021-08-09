package algorithm;


import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
/**
 The connection between the server and the client
 connect into one handler
 After receiving the string sent by the customer,
 this department will perform the functionality needed to serve the customer's request
 */
public class RunTasksHandler implements IHandler {
    public RunTasks RunTasks;
    public Routes route;
    public NumOfSubmarines numOfSubmarines;
    public Index start;
    public Index end;

    public RunTasksHandler() {
        this.resetParams();
        route=new Routes();
        numOfSubmarines=new NumOfSubmarines();

    }

    private void resetParams() {
        this.RunTasks = null;
    }

    @Override
    public void handle(InputStream inClient, OutputStream outClient) throws Exception {

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outClient);
        ObjectInputStream objectInputStream = new ObjectInputStream(inClient);

        this.resetParams();

        boolean dowork = true;
        RunTasks=new RunTasks();
        while (dowork) {
            switch (objectInputStream.readObject().toString()) {
                case "stop": {
                    dowork = false;
                    break;
                }
                case "find Group Reachables": {
                    int[][] primitiveMatrix = (int[][]) objectInputStream.readObject();
                    ArrayList<HashSet<Index>> findGroup = new ArrayList<HashSet<Index>>();
                    findGroup.addAll(this.RunTasks.FindGroups(primitiveMatrix));
                    objectOutputStream.writeObject(findGroup);
                    break;
                }
                case "find shortest Routes": {
                    int[][] Matrix = (int[][]) objectInputStream.readObject();
                    this.start = (Index) objectInputStream.readObject();
                    this.end = (Index) objectInputStream.readObject();
                    ArrayList<HashSet<Index>> listroues = new ArrayList<HashSet<Index>>();
                    listroues.addAll(this.RunTasks.findShortestRoutes(Matrix,start,end));
                    objectOutputStream.writeObject(listroues);
                    break;
                }
                case "find Num of Submarines": {
                    int[][] primitiveMatrix = (int[][]) objectInputStream.readObject();
                    int countOfSubmarines= (int)numOfSubmarines.countSubmarines(primitiveMatrix);
                   /// System.out.println("result: " + countOfSubmarines);
                    objectOutputStream.writeObject(countOfSubmarines);
                    break;
                }
                case "FinAllRoutes": {

                    Integer[][] nodes = (Integer[][]) objectInputStream.readObject();
                    Cell start = (Cell) objectInputStream.readObject();
                    Cell end = (Cell) objectInputStream.readObject();

                    ArrayList<HashSet<Cell>> result = new ArrayList<HashSet<Cell>>();
                    if (nodes != null) {
                        result = route.routes(nodes, start, end, new HashSet<Cell>(), new HashSet<Cell>(), new ArrayList<HashSet<Cell>>());
                        objectOutputStream.writeObject(result);
                    }

                    break;
                }
            }
        }
    }
}
