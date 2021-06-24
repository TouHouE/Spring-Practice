package npo.beato.spring_practice;

import java.io.File;
import java.io.IOException;
import java.lang.*;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class Test {

    public static void main(String[] args) throws Exception {
        //arrayTest();
    }

    private static void arrayTest() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("String 1");
        arr.add("String 2");

        System.out.println(arr.toString());
    }

    private static void newSetup() throws Exception{
        ClassLoader cL = Thread.currentThread().getContextClassLoader();
        Enumeration enumeration = cL.getResources("npo/beato/spring_practice/controller");
        Object obj = null;

        while((obj = enumeration.nextElement()) != null) {
            System.out.printf("getClass(): %s  toString():%s\n\n", obj.getClass(), obj.toString());
        }
    }


    private static void setup() throws ClassNotFoundException  {
        File javaFilePackage = new File("src/main/java/npo/beato/spring_practice/controller");
        File[] javaFiles = javaFilePackage.listFiles();
        List<Class> classes = new ArrayList<>();

        for(File javaFile : javaFiles) {
            System.out.printf("%s\n\n\n", javaFile.getName());
            String javaName = javaFile.getName().replaceAll("\\.java", "");
            classes.add(Class.forName(String.format("npo.beato.spring_practice.controller.%s", javaName)));
        }

        for(Class c : classes) {
            System.out.printf("C:%s", c.getName());
        }

        String p = Class.forName("npo.beato.spring_practice.controller.MessageController").toString();
        System.out.printf("\n\n%s", p);
    }

    private static List<Class> findClass(File directory, String packageName) {
        List<Class> classes = new ArrayList<Class>();

        if(!directory.exists()) {
            return classes;
        }

        File[] files = directory.listFiles();

        for(File file : files) {

            if(file.isDirectory()) {

            }
        }
        return null;
    }

    private static void getClasses(String packageName) {
        ArrayList classes = new ArrayList();

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String path = packageName.replaceAll("\\.", "/");
            Enumeration resource = classLoader.getResources(path);
            List<File> dirs = new ArrayList<File>();

            while(resource.hasMoreElements()) {
                URL srcURL = (URL)resource.nextElement();
                dirs.add(new File(srcURL.getFile()));
            }

            for(File directory : dirs) {

            }
        } catch (IOException IOE) {
            IOE.printStackTrace();
        }
    }


    private static void setupName() {
        StackTraceElement[] stackTraceElements = new RuntimeException().getStackTrace();

        for(StackTraceElement element : stackTraceElements) {
            System.out.printf("className: %s\nMethodName: %s\nlineName: %s\nFileName: %s\n\n\n\n", element.getClassName(), element.getMethodName(), element.getLineNumber(), element.getFileName());

        }
    }

}
