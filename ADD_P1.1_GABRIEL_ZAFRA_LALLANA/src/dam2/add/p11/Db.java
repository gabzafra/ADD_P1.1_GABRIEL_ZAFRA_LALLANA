package dam2.add.p11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class Db {
  private File localFile;

  public Db(String path) {
    this.localFile = new File(path);
  }

  // public Usuario createNewUser(Usuario user) {
  // Usuario response = new Usuario();
  // BufferedWriter input = new BufferedWriter(new FileWriter(localFile, true));
  // return response;
  // }

  public HashMap<String, Usuario> getAllUsers() {
    HashMap<String, Usuario> respuesta = new HashMap<>();

    try (BufferedReader output = new BufferedReader(new FileReader(localFile))) {
      String cursor;
      while ((cursor = output.readLine()) != null) {
        String[] rowsArr = cursor.split(":");
        respuesta.put(rowsArr[0], new Usuario(rowsArr[0], rowsArr[1], Integer.valueOf(rowsArr[2])));
      }
    } catch (Exception e) {
      return respuesta;
    }
    return respuesta;
  }
}
