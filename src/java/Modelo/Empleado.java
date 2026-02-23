package Modelo;

public class Empleado {
    int idempleado;
    String nombre;
    String apellido;
    String telefono;
    String user;
    String password;

    public Empleado(){
    }

    public Empleado(int idempleado,String nombre, String apellido, String telefono, String user, String password) {
        this.idempleado = idempleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.user = user;
        this.password = password;
    }

    public int getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
