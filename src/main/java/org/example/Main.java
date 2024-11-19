package org.example;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public enum menuChoice {
        EXIT("exit"), ADD("add"), LIST("list");

        private menuChoice(String menuOption) {
            this.menuOption = menuOption;
        }
        public String getMenuOption() {
            return menuOption;
        }
        private final String menuOption;
    }

    public static void main(String[] args) {

        System.out.println("test output string: " + menuChoice.EXIT.getMenuOption());
        System.out.println("Выберите дальнейшее действие, напечатав одну из команд: ");
        Arrays.stream(menuChoice.values()).forEach(x -> System.out.println("\t - " + x.getMenuOption()));
        Scanner console = new Scanner(System.in);
        String menuAction = console.nextLine().toLowerCase().trim();

        String tExit = menuChoice.EXIT.getMenuOption();
        String tAdd = menuChoice.ADD.getMenuOption();
        String tList = menuChoice.LIST.getMenuOption();

        switch (menuAction) {
            case tExit:
                System.out.println("Прощай, смертный");
                break;
            case tAdd:
                System.out.println(" смертный");
                break;
            case tList:
                System.out.println("1213Прощай, смертный");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + menuAction);
        }
    }
}
