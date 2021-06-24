package npo.beato.spring_practice.model.ws;

public class ClientModel {
    private StringBuilder clientSerial;

    public String getClientSerial() {
        return clientSerial.toString();
    }

    public void setClientSerial(String clientSerial) {
        this.clientSerial.append(clientSerial);
    }
}
