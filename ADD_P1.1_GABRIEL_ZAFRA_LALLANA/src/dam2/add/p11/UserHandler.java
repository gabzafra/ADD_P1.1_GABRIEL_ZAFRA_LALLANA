package dam2.add.p11;

import java.util.Scanner;

public class UserHandler {
  public static Usuario loginUser(Db db, Usuario user) {
    Usuario foundUserData = db.getUserByName(user.getNombre());

    if (!foundUserData.getNombre().equals("")) {
      if (foundUserData.getIntentos() >= 3) {
        ViewCreator.showError("El usuario " + foundUserData.getNombre() + " esta BLOQUEADO.");
        return new Usuario();
      } else {
        // TODO INTENTAR AUTORIZAR
      }
    } else {
      return createNewUser(db, user);// TODO INTENTA CREAR NUEVO USUARIO
    }
    return new Usuario();
  }

  private static Usuario createNewUser(Db db, Usuario user) {
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
}
