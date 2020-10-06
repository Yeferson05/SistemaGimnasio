public class Zona {
    public String zona1;
    public String zona2;
    public String zona3;
    public String zona4;
    public String zona5;

    public Zona(String zona1, String zona2, String zona3, String zona4,String zona5) {
        this.zona1=zona1;
        this.zona2=zona2;
        this.zona3=zona3;
        this.zona4=zona4;
        this.zona5=zona5;

    }

    @Override
    public String toString() {
        return "-   Zona:    " + "Zona #1:"+ zona1 +"," + " Zona #2:" + zona2 +"," + " Zona #3:"+zona3+
                ","+ " Zona #4:"+ zona4 +","+ " Zona #5:" + zona5 + ".";
    }
}