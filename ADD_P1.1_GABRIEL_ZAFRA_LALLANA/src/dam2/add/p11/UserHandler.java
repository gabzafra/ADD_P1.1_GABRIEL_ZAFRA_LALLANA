package dam2.add.p11;

import java.util.Scanner;

public class UserHandler {

  private final Db db;

  public UserHandler(Db db) {
    this.db = db;
  }

  public Usuario loginUser(Usuario user) {
    Usuario foundUserData = db.getUserByName(user.getNombre());

    if (!foundUserData.getNombre().equals("")) {
      if (foundUserData.getIntentos() >= 3) {
        ViewCreator.showError("El usuario " + foundUserData.getNombre() + " esta BLOQUEADO.");
        return new Usuario();
      } else {
        return autenticateUser(user.getNombre(), user.getClave());
      }
    } else {
      return createNewUser(user);
    }
  }

  private Usuario autenticateUser(String userName, String password) {
    Scanner input = new Scanner(System.in);

    Usuario foundUserData = new Usuario();

    boolean isUserBlocked = false;
    while (!isUserBlocked) {
      foundUserData = db.getUserByName(userName);
      if (foundUserData.getNombre().length() > 0) {
        if (foundUserData.getClave().equals(password)) {
          foundUserData.setIntentos(0);
          foundUserData = db.updateUser(foundUserData);
          if (foundUserData.getNombre().length() == 0) {
            ViewCreator.showError("No se ha podido acceder a la base de datos de usuarios.");
          }
          return foundUserData;
        } else {
          foundUserData.setIntentos(foundUserData.getIntentos() + 1);
          foundUserData = db.updateUser(foundUserData);
          if (foundUserData.getNombre().length() == 0) {
            ViewCreator.showError("No se ha podido acceder a la base de datos de usuarios.");
            return foundUserData;
          }

          ViewCreator.showError("La clave introducida no es correcta.");

          if (foundUserData.getIntentos() > 2) {
            isUserBlocked = true;
          } else {
            if (ViewCreator.askUserYesNo("¿Desea intentarlo de nuevo? S/N")) {
              ViewCreator.showInfo("Introduzca su clave de acceso:");
              password = input.nextLine();
            } else {
              return new Usuario();
            }
          }
        }
      } else {
        ViewCreator.showError("No se ha podido acceder a la base de datos de usuarios.");
        return foundUserData;
      }
    }
    ViewCreator.showError(
        "Ha realizado demasiados intentos, el usuario " + userName + " ha sido BLOQUEADO.");
    return new Usuario();
  }

  private Usuario createNewUser(Usuario user) {
    Usuario newUser = new Usuario();

    ViewCreator.showInfo("No hay un usuario " + user.getNombre() + " en el sistema.");

    Scanner input = new Scanner(System.in);

    if (ViewCreator.askUserYesNo("¿Desea registrarlo? S/N")) {
      boolean passwordsMatch = false;
      while (!passwordsMatch) {
        ViewCreator.showInfo("Repita la clave de acceso:");
        passwordsMatch = input.nextLine().equals(user.getClave());
        if (passwordsMatch) {
          newUser = db.createNewUser(user);
          if (newUser.getNombre().length() > 0) {
            ViewCreator.showInfo("Se ha registrado con exito al usuario " + newUser.getNombre());
          } else {
            ViewCreator.showError("No se ha podido registrar el nuevo usuario");
          }
        } else {
          ViewCreator.showError("La clave no coincide con la introducida anteriormente.");
          if (!ViewCreator.askUserYesNo("¿Desea intentarlo de nuevo? S/N")) {
            passwordsMatch = true;
          }
        }
      }
    }
    return newUser;
  }

  public Usuario resetUserLogAttemps(String userName) {
    Usuario user = db.getUserByName(userName);
    if (user.getNombre().length() > 0) {
      user.setIntentos(0);
      user = db.updateUser(user);
    }
    return user;
  }
}
