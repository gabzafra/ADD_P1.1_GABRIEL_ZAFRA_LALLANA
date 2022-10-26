package dam2.add.p11;

public class Usuario {
  private String nombre;
  private String clave;
  private int intentos;

  public Usuario() {
    this("", "");
  }

  public Usuario(String nombre, String clave) {
    this(nombre, clave, 0);
  }

  public Usuario(String nombre, String clave, int intentos) {
    this.nombre = nombre;
    this.clave = clave;
    this.intentos = intentos;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getClave() {
    return clave;
  }

  public void setClave(String clave) {
    this.clave = clave;
  }

  public int getIntentos() {
    return intentos;
  }

  public void setIntentos(int intentos) {
    this.intentos = intentos;
  }
}
