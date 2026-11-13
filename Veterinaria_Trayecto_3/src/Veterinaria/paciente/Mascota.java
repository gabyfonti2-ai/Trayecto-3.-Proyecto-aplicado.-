package Veterinaria.paciente;

public abstract class Mascota {

    // Atributos encapsulados
    private TipoAnimal tipoAnimal;
    private String nombre;
    private SexoAnimal sexoAnimal;
    private int edad;
    private double peso;
    private String raza;
    private DiaSemana diaSemana;
    private String dueno;

    // Constructor
    public Mascota(
            TipoAnimal tipoAnimal,
            String nombre,
            SexoAnimal sexoAnimal,
            int edad,
            double peso,
            String raza,
            DiaSemana diaSemana,
            String dueno) {

        this.tipoAnimal = tipoAnimal;
        this.nombre = nombre;
        this.sexoAnimal = sexoAnimal;
        this.edad = edad;
        this.peso = peso;
        this.raza = raza;
        this.diaSemana = diaSemana;
        this.dueno = dueno;
    }

    // Método abstracto para aplicar el ajuste según la especie
    public abstract double aplicarAjuste(double precio);

    // Getters
    public TipoAnimal getTipoAnimal() {
        return tipoAnimal;
    }

    public String getNombre() {
        return nombre;
    }

    public SexoAnimal getSexoAnimal() {
        return sexoAnimal;
    }

    public int getEdad() {
        return edad;
    }

    public double getPeso() {
        return peso;
    }

    public String getRaza() {
        return raza;
    }

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public String getDueno() {
        return dueno;
    }

    // Setters
    public void setTipoAnimal(TipoAnimal tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSexoAnimal(SexoAnimal sexoAnimal) {
        this.sexoAnimal = sexoAnimal;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public void setDueno(String dueno) {
        this.dueno = dueno;
    }
}