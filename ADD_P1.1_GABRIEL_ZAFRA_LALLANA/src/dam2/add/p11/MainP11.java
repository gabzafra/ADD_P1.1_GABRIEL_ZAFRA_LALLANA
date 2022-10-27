package dam2.add.p11;

public class MainP11 {

  public static void main(String[] args) {
    String usersFilePath =
        "D:/DAM/2022-2023/_repos/ADD_P1.1_GABRIEL_ZAFRA_LALLANA/ADD_P1.1_GABRIEL_ZAFRA_LALLANA/src/dam2/add/p11/files/acceso.txt";
    String tempFilePath =
        "D:/DAM/2022-2023/_repos/ADD_P1.1_GABRIEL_ZAFRA_LALLANA/ADD_P1.1_GABRIEL_ZAFRA_LALLANA/src/dam2/add/p11/files/temp.txt";

    Db db = new Db(usersFilePath, tempFilePath);

    ViewCreator.showUserList(
        db.getAllUsers().values().stream().map(user -> user.getNombre()).toArray(String[]::new));

    Usuario currentUser = UserHandler.loginUser(db, new Usuario("a", "a"));

    if (currentUser.getNombre().length() > 0) {
      ViewCreator.showUserDetail(currentUser);
    } else {
      ViewCreator.showError("No se ha podido autorizar al usuario.");
    }

  }


}
