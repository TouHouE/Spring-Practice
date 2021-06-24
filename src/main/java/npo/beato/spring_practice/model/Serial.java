package npo.beato.spring_practice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.internal.NotNull;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Serial {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String serial;

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getSerial() {
        return serial;
    }
}
