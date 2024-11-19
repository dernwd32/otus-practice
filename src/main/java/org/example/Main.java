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
      //  EXIT,ADD,LIST
    }

    public static void main(String[] args) {

        System.out.println("test output string: " + menuChoice.EXIT.getMenuOption());
        System.out.println("Выберите дальнейшее действие, напечатав одну из команд: ");
        Arrays.stream(menuChoice.values()).forEach(x -> System.out.println("\t - " + x.getMenuOption()));
        Scanner console = new Scanner(System.in);
        String menuAction = console.nextLine().toLowerCase().trim();

        final String TEXIT = String.valueOf(menuChoice.EXIT);
        final String TADD;
        TADD = String.valueOf(menuChoice.ADD.getMenuOption());
        final String TLIST = menuChoice.LIST.getMenuOption();

        final String TEST = "123";

        System.out.println("666 : " + TEXIT + TADD + TLIST + menuChoice.EXIT);

//        String tExit = String.valueOf(menuChoice.EXIT);
        switch (menuAction) {
            case TEST:
                System.out.println("Прощай, смертный");
                break;
            case TADD:
                System.out.println(" add смертный");
                break;
            case TLIST:
                System.out.println("list смертный");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + menuAction);
        }
    }
}
