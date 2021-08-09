package algorithm;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Stream;
/**
 This class forwards commands from the client to the server
 The communication will be done through a socket which is a conduit for transmitting information, sent written IP and port
 */

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // In order to request something over TCP from a server, we need a port number and an IP address
        Socket socket = new Socket("127.0.0.1",8010);
        // socket is an abstraction of 2-way data pipe
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        // use decorators
        ObjectInputStream fromServer = new ObjectInputStream(inputStream);
        ObjectOutputStream toServer = new ObjectOutputStream(outputStream);

        int[][] source = {
                {1, 0, 0,1,0},
                {0, 1, 1,0,1},
                {1, 0, 1,1,1}

        };
        Integer[][] matrix = {
                {1,0,1},
                {1,1,0},
                {0,0,1}
        };

        toServer.writeObject("find Group Reachables");
        // according to protocol, after "matrix" string, send 2d int array
        toServer.writeObject(source);
        ArrayList<HashSet<Index>> GroupReachables =(ArrayList<HashSet<Index>>) fromServer.readObject();
        System.out.println("Group Reachables: " + GroupReachables);


        toServer.writeObject("find shortest Routes");
        toServer.writeObject(source);
        toServer.writeObject(new Index(0, 0));
        toServer.writeObject(new Index(2, 4));
        ArrayList<HashSet<Index>> listroues=(ArrayList<HashSet<Index>>) fromServer.readObject();
        System.out.println("shortest Routes: " + listroues);
        int[][] mat = { { 1, 1, 0, 1, 1},
                { 0, 0, 0, 1, 1},
                { 1, 1, 0, 1, 1}
        };
        toServer.writeObject("find Num of Submarines");
        toServer.writeObject(mat);
        int NumOfSubmarines= (int) fromServer.readObject();
        System.out.println("num of submarines: " + NumOfSubmarines);


        toServer.writeObject("FinAllRoutes");
        toServer.writeObject(matrix);
        Cell start = new Cell(0,0);
        Cell end = new Cell(2,2);
        toServer.writeObject(start);
        toServer.writeObject(end);
        ArrayList<HashSet<Cell>>result = new ArrayList<HashSet<Cell>>((ArrayList<HashSet<Cell>>)fromServer.readObject());
        if (result.size()!=0) {
            System.out.println(" All routes between points " + start +" to points "+ end);
            result.stream().flatMap(num -> Stream.of(num)).
                    forEach(System.out::println);
        }else {
            System.out.println(" There are no routes between points " + start +" to points "+ end);
        }

        toServer.writeObject("stop");
        fromServer.close();
        toServer.close();
        socket.close();
        System.out.println("All streams are closed");

    }
}
