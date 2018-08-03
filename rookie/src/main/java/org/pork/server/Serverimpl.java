package org.pork.server;

import org.pork.connector.Http11Processor;
import org.pork.http.RequestHeader;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Serverimpl extends Server {
    private ServerSocketChannel ssc;
    private ServerSocket ss;
    private Selector selector;

    public Serverimpl(String host, int port) throws IOException {
        ssc = ServerSocketChannel.open();
        ss = ssc.socket();
        ss.bind(new InetSocketAddress(host, port));
        ssc.configureBlocking(false);

        selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);
    }

    @Override
    public void start() throws IOException, ClassNotFoundException {
        while (true) {
            int num = selector.select();
            if (num == 0) continue;
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator it = keys.iterator();

            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();
                it.remove();
                if (key.isAcceptable()) {
                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    String request = readMsg(channel);
                    RequestHeader requestHeader = new RequestHeader(request);
                    key.attach(requestHeader);
                    key.interestOps(SelectionKey.OP_WRITE);
                } else if (key.isWritable()) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    RequestHeader requestHeader = (RequestHeader) key.attachment();
                    Http11Processor processor = new Http11Processor();
                    StringBuffer headerBuffer = processor.doGet(requestHeader);
                    System.out.println("headerBuffer:" + headerBuffer.toString());
                    ByteBuffer byteBuffer = ByteBuffer.wrap(headerBuffer.toString().getBytes());
                    try {
                        sc.write(byteBuffer);
                        sc.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

    }

    private String readMsg(SocketChannel channel) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        StringBuffer message = new StringBuffer();
        int count;
        while ((count = channel.read(byteBuffer)) > 0) {
            String msg = (new String(byteBuffer.array(), 0, count));
            message.append(msg);
            byteBuffer.compact();
        }
        return message.toString();
    }

    @Override
    public void stop() throws IOException {
        if (ss != null) {
            ss.close();
        }
        if (ssc != null) {
            ssc.close();
        }
        if (selector != null) {
            selector.close();
        }
    }
}