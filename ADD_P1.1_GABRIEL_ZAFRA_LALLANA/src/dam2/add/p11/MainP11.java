package dam2.add.p11;

import java.util.Scanner;

public class MainP11 {

  public static void main(String[] args) {
    String usersFilePath =
        "D:/DAM/2022-2023/_repos/ADD_P1.1_GABRIEL_ZAFRA_LALLANA/ADD_P1.1_GABRIEL_ZAFRA_LALLANA/src/dam2/add/p11/files/acceso.txt";
    String tempFilePath =
        "D:/DAM/2022-2023/_repos/ADD_P1.1_GABRIEL_ZAFRA_LALLANA/ADD_P1.1_GABRIEL_ZAFRA_LALLANA/src/dam2/add/p11/files/temp.txt";
    String logFilePath =
        "D:/DAM/2022-2023/_repos/ADD_P1.1_GABRIEL_ZAFRA_LALLANA/ADD_P1.1_GABRIEL_ZAFRA_LALLANA/src/dam2/add/p11/files/login.log";

    Db db = new Db(usersFilePath, tempFilePath);

    Logger logger = new Logger(logFilePath);

    UserHandler users = new UserHandler(db);

    ViewCreator.showUserList(
        db.getAllUsers().values().stream().map(user -> user.getNombre()).toArray(String[]::new));

    Usuario currentUser = users.loginUser(new Usuario("admin", "admin"));

    logger.addLog("Trololo", false);

    Scanner sc = new Scanner(System.in);
    sc.nextLine();

    System.out.println(logger.addLog("Trololo", true));
  }

}
