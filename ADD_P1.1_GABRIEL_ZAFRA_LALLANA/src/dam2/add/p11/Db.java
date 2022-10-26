package dam2.add.p11;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Db {
  private File localFile;

  public Db(String path) {
    this.localFile = new File(path);
  }

  public Usuario createNewUser(Usuario user) {
    Usuario response = new Usuario();
    try {
      BufferedWriter input = new BufferedWriter(new FileWriter(localFile, true));
    } catch (IOException e) {
      return response;
    }

    // TODO lógica usuario nuevo
    return response;
  }
}
