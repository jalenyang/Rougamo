package org.pork.startup;


import org.pork.server.Server;
import org.pork.server.Serverimpl;
import org.pork.util.RougamoProperty;
import org.pork.util.ServerProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class Bootstrap {
    Logger LOGGER = LogManager.getLogger(Bootstrap.class);
    private ClassLoader serverClassLoader;

    private ClassLoader createClassLoader(String name, ClassLoader parent) throws MalformedURLException {
        String value = RougamoProperty.getProperty(name);
        if (value.isEmpty()) {
            return parent;
        }
        List<ClassLoaderFactory.Repository> list = new ArrayList<>();
        for (String repo : value.split(",")) {
            if (repo.endsWith("*.jar")) {
                String locaton = repo.substring(0, repo.length() - "*.jar".length());
                list.add(new ClassLoaderFactory.Repository(locaton, ClassLoaderFactory.RepositoryType.GLOB));
            } else if (repo.endsWith(".jar")) {
                String locaton = repo.substring(0, repo.length() - "*.jar".length());
                list.add(new ClassLoaderFactory.Repository(locaton, ClassLoaderFactory.RepositoryType.JAR));
            } else {
                list.add(new ClassLoaderFactory.Repository(repo, ClassLoaderFactory.RepositoryType.JAR));
            }
        }

        return ClassLoaderFactory.createClassLoader(list, null);
    }

    private void initClassLoader() throws MalformedURLException, ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        serverClassLoader = createClassLoader("server", null);
        Thread.currentThread().setContextClassLoader(serverClassLoader);
    }

    private void initServer() {
        String port = ServerProperty.getProperty("server.port", "8080");
        String host = ServerProperty.getProperty("server.host", "localhost");
        Server server = null;
        try {
            server = new Serverimpl(host, Integer.valueOf(port));
            server.start();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }


    private void init() throws ClassNotFoundException, MalformedURLException, InvocationTargetException,
            IllegalAccessException, NoSuchMethodException, InstantiationException {
        initClassLoader();
        initServer();
    }

    public static void main(String[] args) throws MalformedURLException,
            ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
            IllegalAccessException, InstantiationException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.init();
    }
}
