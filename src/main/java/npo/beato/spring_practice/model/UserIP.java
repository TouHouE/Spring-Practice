package npo.beato.spring_practice.model;

public class UserIP {
    private String ip;
    private int time;

    public UserIP(String ip, int time) {
        this.ip = ip;
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
