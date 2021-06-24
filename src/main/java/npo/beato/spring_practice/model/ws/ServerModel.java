package npo.beato.spring_practice.model.ws;

public class ServerModel {

    private StringBuilder response;

    public void setResponse(String response) {
        this.response.append(response);
    }

    public String getResponse() {
        return response.toString();
    }
}
