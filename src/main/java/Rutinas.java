public class Rutinas {
    public int id;
    public String rutina1;
    public String rutina2;
    public String rutina3;
    public String rutina4;
    public String rutina5;

    public Rutinas(String rutina1, String rutina2, String rutina3, String rutina4,String rutina5,int id){
        this.id=id;
        this.rutina1=rutina1;
        this.rutina2=rutina2;
        this.rutina3=rutina3;
        this.rutina4=rutina4;
        this.rutina5=rutina5;

    }

    @Override
    public String toString() {
        return "lista " + id + "\n" +
                "\t rutina 1 : " + rutina1 + "\n" +
                "\t rutina 2 : " + rutina2 + "\n" +
                "\t rutina 3 : " + rutina3 + "\n" +
                "\t rutina 4 : " + rutina4 + "\n" +
                "\t rutina 5 : " + rutina5 + "\n" +
                "\t ";
    }
}
