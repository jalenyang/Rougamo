package org.pork.server;


import java.io.IOException;

public abstract class Server {

    public abstract void start() throws IOException, ClassNotFoundException;

    public abstract void stop() throws IOException;

}
