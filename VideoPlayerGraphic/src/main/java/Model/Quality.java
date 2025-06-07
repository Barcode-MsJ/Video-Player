package Model;

public enum Quality {
    LOW(360) , MEDIUM(720) , HIGH(1080);

    private final int quality;

    Quality (int quality){
        this.quality = quality;
    }

    public int getQuality() { return quality; }
}
