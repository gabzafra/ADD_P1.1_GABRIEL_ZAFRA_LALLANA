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

    UserHandler users = new UserHandler(db, logger);

    initUsers(db);

    Scanner input = new Scanner(System.in);

    Usuario user = new Usuario();
    boolean haTerminado = false;

    while (!haTerminado) {
      ViewCreator.showInfo("Introduzca su nombre de usuario:");
      user.setNombre(input.nextLine());
      ViewCreator.showInfo("Introduzca su clave de acceso:");
      user.setClave(input.nextLine());
      user = users.loginUser(user);

      if (user.getNombre().equals("admin")) {
        boolean goBack = false;
        while (!goBack) {
          ViewCreator.showInfo("ZONA DE ADMINISTRACIÓN");
          ViewCreator.showInfo("Usuarios BLOQUEADOS");
          ViewCreator.showUserList(db.getAllUsers());
          ViewCreator.showInfo(
              "Escriba el nombre del usuario que desea desbloquear o 0 para volver atrás:");
          String userInput = input.nextLine();
          if (userInput.equals("0")) {
            goBack = true;
          } else if (users.resetUserLogAttemps(userInput).getNombre().length() > 0) {
            ViewCreator.showInfo("Usuario desbloqueado.");
          } else {
            ViewCreator.showError("No se ha podido desbloquear el usuario.");
          }
        }
      } else if (user.getNombre().length() > 0) {
        ViewCreator.showInfo("Hola " + user.getNombre() + ".");
        haTerminado = true;
      }
    }
    ViewCreator.showInfo("Programa terminado.");



  }

  public static void initUsers(Db usersDb) {
    usersDb.createNewUser(new Usuario("admin", "admin", 0));
    usersDb.createNewUser(new Usuario("pepe", "12345", 1));
    usersDb.createNewUser(new Usuario("adam", "11111", 3));
  }

}
