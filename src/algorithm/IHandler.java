package algorithm;


import java.io.*;
/**
 This class accepts InputStream and returns OutputStream to allow communication between the server and the client properly
 */

public interface IHandler {
    public void handle(InputStream inClient, OutputStream outClient) throws IOException, ClassNotFoundException, Exception;
}
