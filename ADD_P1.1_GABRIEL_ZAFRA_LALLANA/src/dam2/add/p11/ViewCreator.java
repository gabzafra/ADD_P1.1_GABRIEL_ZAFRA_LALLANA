package dam2.add.p11;

import java.util.Arrays;

public class ViewCreator {
  public static void showError(String message) {
    System.err.println("ERROR : " + message);
  }

  public static void showInfo(String message) {
    System.err.println(message);
  }

  public static void showUserList(String[] usersList) {
    Arrays.stream(usersList).forEach(System.out::println);
  }

  public static void showUserDetail(Usuario user) {
    System.out.println(
        "> " + user.getNombre() + " > " + user.getClave() + " > " + user.getIntentos() + " <");
  }

}
