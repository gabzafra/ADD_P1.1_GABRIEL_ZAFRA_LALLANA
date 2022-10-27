package dam2.add.p11;

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
      return createNewUser(user);// TODO INTENTA CREAR NUEVO USUARIO
    }
    return new Usuario();
  }

  private static Usuario createNewUser(Usuario user) {
    return new Usuario();
  }
}
