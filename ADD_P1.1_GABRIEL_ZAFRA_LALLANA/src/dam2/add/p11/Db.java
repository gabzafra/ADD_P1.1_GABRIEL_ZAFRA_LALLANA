package dam2.add.p11;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Db {

  private File localFile;
  private File tempFile;

  public Db(String usersPath, String tempPath) {
    this.localFile = new File(usersPath);
    this.tempFile = new File(tempPath);
  }

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

  public Usuario getUserByName(String username) {
    Usuario respuesta = new Usuario();
    try (BufferedReader output = new BufferedReader(new FileReader(localFile))) {
      String cursor;
      while ((cursor = output.readLine()) != null) {
        String[] colsArr = cursor.split(":");
        if (colsArr[0].equals(username)) {
          respuesta.setNombre(colsArr[0]);
          respuesta.setClave(colsArr[1]);
          respuesta.setIntentos(Integer.valueOf(colsArr[2]));
          return respuesta;
        }
      }
    } catch (Exception e) {
      return respuesta;
    }
    return respuesta;
  }

  public Usuario updateUser(Usuario newUser) {
    Usuario respuesta = new Usuario();

    HashMap<String, Usuario> userList = getAllUsers();

    if (userList.containsKey(newUser.getNombre())) {
      userList.put(newUser.getNombre(), newUser);
      try {
        tempFile.createNewFile();
      } catch (IOException e) {
        return respuesta;
      }

      try (BufferedWriter input = new BufferedWriter(new FileWriter(tempFile))) {
        for (Usuario user : userList.values()) {
          String formatedRow = user.getNombre() + ":" + user.getClave() + ":" + user.getIntentos();
          input.write(formatedRow);
          input.newLine();
        }
      } catch (Exception e) {
        return respuesta;
      }

      localFile.delete();
      tempFile.renameTo(localFile);

      respuesta.setNombre(userList.get(newUser.getNombre()).getNombre());
      respuesta.setClave(userList.get(newUser.getNombre()).getClave());
      respuesta.setIntentos(userList.get(newUser.getNombre()).getIntentos());

      return respuesta;

    } else {
      return respuesta;
    }
  }

  public boolean deleteUserByName(String userName) {
    HashMap<String, Usuario> userList = getAllUsers();

    if (userList.containsKey(userName)) {
      userList.remove(userName);
      try {
        tempFile.createNewFile();
      } catch (IOException e) {
        return false;
      }

      try (BufferedWriter input = new BufferedWriter(new FileWriter(tempFile))) {
        for (Usuario user : userList.values()) {
          String formatedRow = user.getNombre() + ":" + user.getClave() + ":" + user.getIntentos();
          input.write(formatedRow);
          input.newLine();
        }
      } catch (Exception e) {
        return false;
      }

      localFile.delete();
      tempFile.renameTo(localFile);

      return true;

    } else {
      return false;
    }
  }

  public Usuario createNewUser(Usuario user) {
    HashMap<String, Usuario> userList = getAllUsers();

    if (!userList.containsKey(user.getNombre())) {
      try (BufferedWriter input = new BufferedWriter(new FileWriter(localFile, true))) {
        String formatedRow =
            user.getNombre() + ":" + user.getClave() + ":" + user.getIntentos();
        input.write(formatedRow);
        input.newLine();
      } catch (Exception e) {
        return new Usuario();
      }
      return new Usuario(user.getNombre(), user.getClave());
    } else {
      return new Usuario();
    }
  }
}
