package npo.beato.spring_practice.repository;
import npo.beato.spring_practice.model.ImageData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileService {

    public final String[] IMAGE_SUB_NAME = {".png", ".jpg", ".gif", ".PNG", ".jpeg"};
    private final String[] DOCS_PATH = {"docs/static/img", "docs/static/js", "docs/static/css"};
    private final String HTML = ".html", JAVA_SCRIPT = ".js", CSS = ".css", TXT = ".txt";
    private final String OK = "Success", FAIL = "FXXK Done!!!!";
    private final DateFormat FORMAT = new SimpleDateFormat("YYYY_MM_DD_hh_mm_ss");
    private String root = "docs";
    //other path
    public String getHtml(String path, String name) {
        path = path.replaceAll("&", "/").replaceAll("\\?", "_");

        File htmlPath = new File(String.format("%s/%s", root, path));
        File[] documents = htmlPath.listFiles();
        File tar = null;

        for(File doc : documents) {

            if(doc.getName().equals(String.format("%s.html", name))) {
                tar = doc;
                break;
            }
        }

        String htmlText = reader(tar);
        return htmlText;
    }

    public String getHtml(String fileName) {
        File htmlPath = new File(this.root);
        File[] documents = htmlPath.listFiles();
        File tar = null;

        if(!fileName.matches(".*\\.html")) {
            fileName = fileName + ".html";
        }

        for(File doc : documents) {
            if(doc.getName().equals(fileName)) {
                tar = doc;
                break;
            } else {

            }
        }
        String htmlText = reader(tar);
        return htmlText;
    }

    //without sub name
    public String getJs(String fileName) {
        File jsPath = new File(String.format("%s/static/js", root));
        File[] documents = jsPath.listFiles();
        File tar = null;



        if(!fileName.matches(".*\\.js")) {
            fileName = fileName + ".js";
        }

        for(File file : documents) {

            if(file.getName().equals(fileName)) {
                tar = file;
                break;
            }
        }

        String jsText = reader(tar);
        return jsText;
    }

    public String getJs(String name, String ...paths) {
        StringBuilder finalPath = new StringBuilder("docs");

        for(String path : paths) {
            finalPath.append("/").append(path);
        }

        File path = new File(finalPath.toString());
        File documents[] = path.listFiles();
        File tar = null;

        for(File doc : documents) {
            if(doc.getName().equals(String.format("%s.js", name))) {
                tar = doc;
                break;
            }
        }

        String jsText = reader(tar);
        return jsText;

    }

    public String getCss(String name) {
        if(!name.matches(".*\\.css")) {
            name = String.format("%s.css", name);
        }


        String path = "";

        path = String.format("%s/static/css/%s", root, name);
        File tar = new File(path);
        String cssText = reader(tar);
        return cssText;
    }

    public ImageData getImg(String imgName) {
        ImageData data = null;

        for(String subName : IMAGE_SUB_NAME) {
            String nowPath = String.format("docs/static/img/%s%s", imgName, subName);
            System.out.println("now Path:" + nowPath);


            if(new File(nowPath).exists()) {
                try {
                    FileInputStream fis = new FileInputStream(new File(nowPath));
                    byte[] bytes = new byte[fis.available()];
                    fis.read(bytes, 0, fis.available());

                    data = new ImageData(bytes, subName);
                } catch (IOException ioe) {
                    System.out.println(ioe.getMessage());
                }
                break;
            }

        }

        return data;
    }

    private String reader(File readFile) {
        StringBuilder text = new StringBuilder();

        try {
            FileReader fr = new FileReader(readFile);
            BufferedReader reader = new BufferedReader(fr);
            String temp = "";

            while((temp = reader.readLine()) != null) {
                text.append(temp);
                text.append("\n");
            }

            reader.close();
            fr.close();
        } catch(FileNotFoundException FNF_E) {
            FNF_E.printStackTrace();
        } catch(IOException IO_E) {
            IO_E.printStackTrace();
        }

        return text.toString();
    }

    public String saveTxt(String content) {
        Date now = new Date();
        String fileName=  FORMAT.format(now);

        if(writer(content, fileName, TXT)) {
            return OK;
        } else {
            return FAIL;
        }
    }

    public String saveTxt(String content, String fileName) {
        if(writer(content, fileName, TXT)) {
            return OK;
        } else {
            return FAIL;
        }
    }


    public String saveHtml(String content) {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("YYYY_MM_DD_hh_mm_ss");
        String fileName = format.format(date);

        if(writer(content, fileName, HTML)) {
            return OK;
        } else {
            return FAIL;
        }


    }

    public void setRoot(String rootName) {
        this.root = rootName;
    }


    public String saveHtml(String content, String fileName) {

        if(writer(content, fileName, HTML)) {
            return OK;
        } else {
            return FAIL;
        }


    }

    private boolean writer(String content, String fileName, String type) {
        File tar = new File(fileName + type);
        boolean isSuccess;

        try {

            if (!tar.exists()) {
                tar.createNewFile();
            }
            FileWriter fw = new FileWriter(tar);
            BufferedWriter writer = new BufferedWriter(fw);

            writer.write(content);
            writer.flush();
            writer.close();
            fw.close();
            isSuccess = true;

        } catch(IOException ioe) {
            isSuccess = false;
            System.out.printf(ioe.getMessage() + "\n");
        }

        return isSuccess;
    }

    public String getTextData(String dataName) {
        File data = new File(String.format("%s/data/%s", this.root, dataName));

        String dataText = reader(data);
        return dataText;
    }

}
