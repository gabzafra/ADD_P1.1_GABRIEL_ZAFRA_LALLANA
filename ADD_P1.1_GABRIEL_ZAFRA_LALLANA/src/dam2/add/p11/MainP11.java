package dam2.add.p11;

public class MainP11 {

  public static void main(String[] args) {
    String usersFilePath =
        "D:/DAM/2022-2023/_repos/ADD_P1.1_GABRIEL_ZAFRA_LALLANA/ADD_P1.1_GABRIEL_ZAFRA_LALLANA/src/dam2/add/p11/files/acceso.txt";

    Db test = new Db(usersFilePath);
    test.getAllUsers().forEach((key, value) -> System.out.println(value.getNombre()));

    System.out.println(">>>>>" + test.getUserByName("pepe").getNombre());
  }

}
