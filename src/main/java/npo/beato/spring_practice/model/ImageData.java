package npo.beato.spring_practice.model;

public class ImageData {
    private byte[] imgBytes;
    private String mediaType;

    public ImageData(byte[] bytes, String mediaType) {
        this.imgBytes = bytes;
        this.mediaType = mediaType;
    }

    public byte[] getImgBytes() {
        return imgBytes;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setImgBytes(byte[] imgBytes) {
        this.imgBytes = imgBytes;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}
