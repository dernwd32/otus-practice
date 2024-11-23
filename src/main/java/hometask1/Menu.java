package hometask1;

import java.util.*;

public class Menu {

    public static void menuStart(){

        boolean showmenu = true;

        while (showmenu) {
            try {
                System.out.println( AnimalList.countAnimals()
                        + "Выберите дальнейшее действие, напечатав одну из команд: ");
                Arrays.stream(MenuChoiceEnum.values()).forEach(x -> System.out.println("\t - " + x.toString().toLowerCase()));
                Scanner console = new Scanner(System.in);
                String command = console.nextLine().toLowerCase().trim();

                // final String tmp = String.valueOf(hometask1.MenuChoiceEnum.EXIT.getMenuOption());
                //System.out.println(tmp);

                var action = MenuChoiceEnum.allOptions(command);

                switch (action) {
                    case MenuChoiceEnum.ADD -> menuAdd();
                    case MenuChoiceEnum.LIST -> menuList();
                    case MenuChoiceEnum.EXIT -> {
                        System.out.println("Покедова!");
                        showmenu = false;
                    }
                }

            } catch (NullPointerException e) {
                System.out.println("Некорректный выбор.");
            }

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
        AnimalList.showListAnimals();
       // AnimalList.countAnimals();

        //menuStart();
    }

    public static void menuAdd(){

        Scanner console = new Scanner(System.in);

//        String menuAnimal = "";
//        boolean correctAnimal = false;
//        while (!correctAnimal) {
//            System.out.println("Выберите животное: \n\t 1. cat \n\t 2. dog \n\t 3. duck");
//            menuAnimal = console.nextLine().toLowerCase().trim();
//            if (!List.of("cat", "dog", "duck").contains(menuAnimal)) {
//               System.out.println("Неправильно введен тип животного: " + menuAnimal);
//            }
//            else correctAnimal = true;
//        }

        String menuAnimal;
        for(;;){
            Set<?> childs = Animal.getChildClasses();
            System.out.println("Выберите животное: ");
            childs.forEach(x -> System.out.println("\t - " + x));
            menuAnimal = MiscFuncs.firstCapitalize(console.nextLine().trim());
            if (!childs.contains(menuAnimal)) System.out.println("Неправильно введен тип животного: " + menuAnimal);
            else break;
        }

        System.out.println("Введите имя животного");
        String name = console.nextLine().toLowerCase().trim();

        Integer intAge;
        for(;;){
            System.out.println("Введите возраст животного");
             try {
                intAge = Integer.valueOf(console.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Неправильно введен возраст животного." +
                        " Возраст должен быть целым числом. ");
            }
        }

        Float floatWeight;
        for(;;){
            System.out.println("Введите вес животного");
            try {
                floatWeight = Float.valueOf(console.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Неправильно введен вес животного." +
                        " Вес должен быть числом (с плавающей точкой). ");
            }
        }

        System.out.println("Введите цвет животного");
        String color = console.nextLine().trim();

//      String  age = console.nextLine().trim().replaceAll("[^\\d]+", "");
//        String weight = console.nextLine().trim().replaceAll("[^\\d.]+", "");;
        //вытаскиваем int/float из очищенных ранее строк, если не получается, то приравниваем к нулю
        //в идеале надо бы отлавливать некорректный ввод и заставлять вводить числа корректно...
//        Integer intAge;
//        Float floatWeight;
//        try {
//            intAge = Integer.valueOf(age);
//        } catch (NumberFormatException e) {
//            intAge = 0;
//        }
//        try {
//            floatWeight = Float.valueOf(weight);
//        } catch (NumberFormatException e) {
//            floatWeight = 0F;
//        }

//        Animal createdAnimal = switch (menuAnimal) {
//            case "Cat" -> new Animal.Cat(name, intAge, floatWeight, color);
//            case "Dog" -> new Animal.Dog(name, intAge, floatWeight, color);
//            case "Duck" -> new Animal.Duck(name, intAge, floatWeight, color);
//            default -> new Animal(); //недостижимо, ввиду наличия проверки correctAnimal
//        };
//        Class t = Class.forName("java.lang.Thread")
//        A call to forName("X") causes the class named X to be initialized.

        try {
            // Динамическая загрузка класса по имени
            Class<?> childAnimalClass = Class.forName("hometask1.Animal$" + menuAnimal);
            // вызываем через метод getConstructor подходящий конструктор с параметрами, присваиваем его переменной constructor
            var constructor = childAnimalClass.getConstructor(String.class, Integer.class, Float.class, String.class);
            // Создание экземпляра через метод newInstance с передачей параметров в вызванный конструктор.
            // (Animal) - приведение к типу, IDEA называет "Cast expression"
            Animal createdAnimal = (Animal) constructor.newInstance(name, intAge, floatWeight, color);

            AnimalList.setListAnimals(createdAnimal);
            createdAnimal.say();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Класс не найден: " + menuAnimal, e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Не найден подходящий конструктор для класса: " + menuAnimal, e);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при создании экземпляра класса: " + menuAnimal, e);
        }


/*
        if (menuAnimal.equals("cat")) {
            Cat catFromMenu = new Cat(name, intAge, floatWeight, color);
            //hometask1.Animal.listAnimals.add(catFromMenu);
            AnimalList.setListAnimals(catFromMenu);
            catFromMenu.say();
        }

        if (menuAnimal.equals("dog")) {
            Dog dogFromMenu = new Dog(name, intAge, floatWeight, color);
            //hometask1.Animal.listAnimals.add(dogFromMenu);
            AnimalList.setListAnimals(dogFromMenu);
            dogFromMenu.say();
        }

        if (menuAnimal.equals("duck")) {
            Duck duckFromMenu = new Duck(name, intAge, floatWeight, color);
            //hometask1.Animal.listAnimals.add(duckFromMenu);
            AnimalList.setListAnimals(duckFromMenu);
            duckFromMenu.say();
        }*/

      //  menuStart();

    }

}


