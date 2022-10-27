package dam2.add.p11;

import java.util.Arrays;
import java.util.Scanner;

public class ViewCreator {
  public static void showError(String message) {
    System.err.println("ERROR : " + message);
  }

  public static void showInfo(String message) {
    System.out.println(message);
  }

  public static void showUserList(String[] usersList) {
    Arrays.stream(usersList).forEach(System.out::println);
  }

  public static void showUserDetail(Usuario user) {
    System.out.println(
        "> " + user.getNombre() + " > " + user.getClave() + " > " + user.getIntentos() + " <");
  }

  public static boolean askUserYesNo(String message) {
    showInfo(message);

    Scanner input = new Scanner(System.in);

    String userInput;
    boolean response = false;
    boolean haTerminado = false;
    while (!haTerminado) {
      userInput = input.nextLine();

      if (userInput.equalsIgnoreCase("s")) {
        response = true;
        haTerminado = true;
      } else if (userInput.equalsIgnoreCase("n")) {
        haTerminado = true;
      } else {
        ViewCreator.showError("Debe introducir S/N:");
      }
    }
    return response;
  }

}
