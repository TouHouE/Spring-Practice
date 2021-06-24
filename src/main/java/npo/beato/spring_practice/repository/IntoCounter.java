package npo.beato.spring_practice.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import npo.beato.spring_practice.model.UserIP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class IntoCounter extends Thread {

    public IntoCounter(String ip) {
        this.ip = ip;
    }

    private String ip;
    private final File IP_LOG = new File("data/ipLog.txt");

    public void count() {
        Gson gson = new Gson();
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get("data/ipLog.txt"));
            List<UserIP> usersIP = new Gson().fromJson(reader, new TypeToken<List<UserIP>>(){}.getType());

            for(UserIP userip : usersIP) {
                if(userip.getIp() == ip) {
                    userip.setTime(userip.getTime() + 1);
                    break;
                }
            }

        } catch(IOException ioe) {

        }
    }

    @Override
    public void run() {
        count();
    }


}
