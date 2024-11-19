import org.example.Main;

import java.util.Arrays;
import java.util.Scanner;

public class Menu {

    public static void menuStart(){

        System.out.println("Выберите дальнейшее действие, напечатав одну из команд: ");
        Arrays.stream(MenuChoiceEnum.values()).forEach(x -> System.out.println("\t - " + x.toString().toLowerCase()));
        Scanner console = new Scanner(System.in);
        String command = console.nextLine().toUpperCase().trim();

        // final String tmp = String.valueOf(MenuChoiceEnum.EXIT.getMenuOption());
        //System.out.println(tmp);

        var action = MenuChoiceEnum.valueOf(command);

        switch (action) {
            case MenuChoiceEnum.ADD:
                menuAdd();
                break;
            case MenuChoiceEnum.LIST:
                menuList();
                break;
            case MenuChoiceEnum.EXIT:
                System.out.println("Покедова!");
                System.exit(0);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + action);
        }

//        if (menuAction.equals(String.valueOf(menuChoice.ADD.getMenuOption()))) {
//            menuAdd();
//        }
//        if (menuAction.equals(String.valueOf(menuChoice.LIST.getMenuOption()))) {
//            menuList();
//        }
//        if (menuAction.equals(String.valueOf(menuChoice.EXIT.getMenuOption()))) {
//            System.out.println("Покедова!");
//            System.exit(0);
//        }
//        String menuAction = "";
//        Scanner console = new Scanner(System.in);
//        System.out.println("Выберите дальнейшее действие, напечатав одну из команд: \n\t - add \n\t - list\n\t - exit");
//        menuAction = console.nextLine().toLowerCase().trim();
//
//        switch (menuAction) {
//            //case valueOf(MenuActions.EXIT):
//            case "exit":
//                System.out.println("Покедова!");
//                System.exit(0);
//                break;
//            case "add":
//                menuAdd();
//                break;
//            case "list":
//                menuList();
//                break;
//            default:
//                throw new IllegalStateException("Учись писать: " + menuAction);
//        }

    }

    public static void menuList(){
        Animal.getListAnimals().forEach(x -> System.out.println(x.toString()));
        menuStart();
    }

    public static void menuAdd(){

        Scanner console = new Scanner(System.in);

        System.out.println("Выберите животное: \n\t 1. cat \n\t 2. dog \n\t 3. duck");
        String menuAnimal = console.nextLine().toLowerCase().trim();
        System.out.println("Введите имя животного");
        String name = console.nextLine().trim();
        System.out.println("Введите возраст животного");
        String age = console.nextLine().toLowerCase().trim();
        System.out.println("Введите вес животного");
        String weight = console.nextLine().toLowerCase().trim();
        System.out.println("Введите цвет животного");
        String color = console.nextLine().trim();

        Integer intAge = Integer.valueOf(age);
        Float floatWeight = Float.valueOf(weight);

        if (menuAnimal.equals("cat")) {
            Cat catFromMenu = new Cat(name, intAge, floatWeight, color);
            //Animal.listAnimals.add(catFromMenu);
            Animal.setListAnimals(catFromMenu);
            catFromMenu.say();
        }

        if (menuAnimal.equals("dog")) {
            Dog dogFromMenu = new Dog(name, intAge, floatWeight, color);
            //Animal.listAnimals.add(dogFromMenu);
            Animal.setListAnimals(dogFromMenu);
            dogFromMenu.say();
        }

        if (menuAnimal.equals("duck")) {
            Duck duckFromMenu = new Duck(name, intAge, floatWeight, color);
            //Animal.listAnimals.add(duckFromMenu);
            Animal.setListAnimals(duckFromMenu);
            duckFromMenu.say();
        }

        menuStart();

    }

    private static record valueOf() {
    }
}


