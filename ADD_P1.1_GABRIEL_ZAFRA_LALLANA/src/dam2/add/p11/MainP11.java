package dam2.add.p11;

public class MainP11 {

  public static void main(String[] args) {
    String usersFilePath =
        "D:/DAM/2022-2023/_repos/ADD_P1.1_GABRIEL_ZAFRA_LALLANA/ADD_P1.1_GABRIEL_ZAFRA_LALLANA/src/dam2/add/p11/files/acceso.txt";
    String tempFilePath =
        "D:/DAM/2022-2023/_repos/ADD_P1.1_GABRIEL_ZAFRA_LALLANA/ADD_P1.1_GABRIEL_ZAFRA_LALLANA/src/dam2/add/p11/files/temp.txt";

    Db test = new Db(usersFilePath, tempFilePath);
    test.getAllUsers().forEach((key, value) -> viewUserDetail(value));
    System.out.println("-----------------------");

    test.updateUser(new Usuario("admin", "trololo", 4545));

    test.getAllUsers().forEach((key, value) -> viewUserDetail(value));
    System.out.println("-----------------------");
    System.out.println(test.deleteUserByName("wololo"));
    System.out.println(test.deleteUserByName("admin"));

    test.getAllUsers().forEach((key, value) -> viewUserDetail(value));
    System.out.println("-----------------------");
    test.createNewUser(new Usuario("adam", "9999"));

    test.getAllUsers().forEach((key, value) -> viewUserDetail(value));
    System.out.println("-----------------------");
    test.createNewUser(new Usuario("Betty", "waea"));

    test.getAllUsers().forEach((key, value) -> viewUserDetail(value));
    System.out.println("-----------------------");
    System.out.println(test.deleteUserByName("adam"));

    test.getAllUsers().forEach((key, value) -> viewUserDetail(value));

  }

  private static void viewUserDetail(Usuario user) {
    System.out.println(
        "> " + user.getNombre() + " > " + user.getClave() + " > " + user.getIntentos() + " <");
  }

}
