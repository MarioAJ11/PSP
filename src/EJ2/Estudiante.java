package EJ2;

public class Estudiante {
    private int codigo;
    private String nombre;
    private int edad;
    private float asig1;
    private float asig2;
    private float asig3;
    private float asig4;
    private float asig5;
    private float asig6;
    private float asig7;
    private float asig8;
    private float media;

    public Estudiante(int codigo,String nombre, int edad, float asig1, float asig2, float asig3, float asig4, float asig5, float asig6, float asig7, float asig8, float media) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.edad = edad;
        this.asig1 = asig1;
        this.asig2 = asig2;
        this.asig3 = asig3;
        this.asig4 = asig4;
        this.asig5 = asig5;
        this.asig6 = asig6;
        this.asig7 = asig7;
        this.asig8 = asig8;
    }

    public float getMedia() {
        return media;
    }

    public void setMedia(float media) {
        this.media = media;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getAsig1() {
        return asig1;
    }

    public void setAsig1(float asig1) {
        this.asig1 = asig1;
    }

    public float getAsig2() {
        return asig2;
    }

    public void setAsig2(float asig2) {
        this.asig2 = asig2;
    }

    public float getAsig3() {
        return asig3;
    }

    public void setAsig3(float asig3) {
        this.asig3 = asig3;
    }

    public float getAsig4() {
        return asig4;
    }

    public void setAsig4(float asig4) {
        this.asig4 = asig4;
    }

    public float getAsig5() {
        return asig5;
    }

    public void setAsig5(float asig5) {
        this.asig5 = asig5;
    }

    public float getAsig6() {
        return asig6;
    }

    public void setAsig6(float asig6) {
        this.asig6 = asig6;
    }

    public float getAsig7() {
        return asig7;
    }

    public void setAsig7(float asig7) {
        this.asig7 = asig7;
    }

    public float getAsig8() {
        return asig8;
    }

    public void setAsig8(float asig8) {
        this.asig8 = asig8;
    }
    @Override
    public String toString() {
        return "\nCodigo: " + this.getCodigo() + "\nNombre: " + this.getNombre() + "\nEdad: " + this.getEdad() + "\nMedia: " +this.getMedia();
    }
}
