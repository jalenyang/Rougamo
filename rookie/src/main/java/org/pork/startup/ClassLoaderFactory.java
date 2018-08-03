package org.pork.startup;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;


public class ClassLoaderFactory {

    public static ClassLoader createClassLoader(List<Repository> repositories, final ClassLoader parent) throws MalformedURLException {
        List<URL> urls = new ArrayList<>();
        for (Repository repository : repositories) {
            if (repository.getRepoType() == RepositoryType.DIR) {
                File file = new File(repository.getLocation());
                if (file.exists() && file.canRead()) {
                    urls.add(file.toURI().toURL());
                }
            } else if (repository.getRepoType() == RepositoryType.JAR) {
                File file = new File(repository.getLocation());
                if (file.exists() && file.canRead()) {
                    urls.add(file.toURI().toURL());
                }
            } else {
                File dir = new File(repository.getLocation());
                if (dir.exists() && dir.isDirectory()) {
                    File[] files = dir.listFiles();
                    for (File file : files) {
                        if (file.canRead() && file.getName().endsWith(".jar")) {
                            urls.add(file.toURI().toURL());
                        }
                    }
                }
            }
        }

        URLClassLoader classLoader = new URLClassLoader((URL[]) urls.toArray());
        return classLoader;
    }

    public enum RepositoryType {
        JAR, DIR, GLOB;
    }

    public static class Repository {

        private String location;
        private RepositoryType repoType;


        public Repository(String location, RepositoryType type) {
            this.location = location;
            this.repoType = type;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public RepositoryType getRepoType() {
            return repoType;
        }

        public void setRepoType(RepositoryType repoType) {
            this.repoType = repoType;
        }
    }
}
