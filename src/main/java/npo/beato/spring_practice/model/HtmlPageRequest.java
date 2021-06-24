package npo.beato.spring_practice.model;

public class HtmlPageRequest {

    private String html;
    private String ip;
    private int linkTime;
    private boolean isMobDevice;
    private String serial;

    public HtmlPageRequest() {

    }

    public HtmlPageRequest(String serial, String html, String ip, boolean isMob) {
        this.html = html;
        this.ip = ip;
        this.isMobDevice = isMob;
        this.linkTime = 1;
        this.serial = serial;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }

    public void addTime() {
        linkTime += 1;
    }

    public String getIp() {
        return ip;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public void setMobDevice(boolean mobDevice) {
        isMobDevice = mobDevice;
    }

    public String getHtml() {
        return html;
    }

    public boolean isFirst(){
        if(linkTime <= 1) {
            return true;
        } else {
            return false;
        }
    }
}
