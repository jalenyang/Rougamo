package org.pork.connector;


import org.pork.http.Request;
import org.pork.http.RequestHeader;
import org.pork.http.Response;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Http11Processor {

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public StringBuffer doGet(RequestHeader requestHeader) {
        StringBuffer headerBuffer = new StringBuffer();
        headerBuffer.append("HTTP/1.0 200 \r\n");
        headerBuffer.append("Content-Type: text/html\r\n");
        headerBuffer.append("\n");
        File file = new File("D:\\opensource\\Rougamo\\http\\src\\main\\webapps\\examples\\WEB-INF\\classes");
        try {
            URL[] url = new URL[]{file.toURI().toURL()};
            ClassLoader classLoader = new URLClassLoader(url);
            Thread.currentThread().setContextClassLoader(classLoader);

            Class<?> clazz = classLoader.loadClass("HelloWorldExample");
            Object example = clazz.getConstructor().newInstance();
            String methodName = "doGet";
            Request httpServletRequest = new Request();
            Response httpServletResponse = new Response();
            Class<?> paramTypes[] = new Class[2];
            paramTypes[0] = Class.forName("javax.servlet.http.HttpServletRequest");
            paramTypes[1] = Class.forName("javax.servlet.http.HttpServletResponse");
            Object paramValues[] = new Object[2];
            paramValues[0] = httpServletRequest;
            paramValues[1] = httpServletResponse;

            Method method = example.getClass().getMethod(methodName, paramTypes);
            method.invoke(example, paramValues);

            String conent=new String(httpServletResponse.getOutputBuffer().array());
            System.out.println("Content:" + conent);
            headerBuffer.append(conent);
            return headerBuffer;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return headerBuffer;
    }
}
