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
    public String toString() {
        return "-  Rutinas:"+"ID:"+id+" 1. "+rutina1+" "+"2. "+rutina2+" "+"3. "+rutina3+
                "4. "+rutina4+" "+"5. "+rutina5;
    }
}
