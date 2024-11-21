package hometask1;

import java.util.ArrayList;

public class AnimalList {

    private static ArrayList<Animal> listAnimals = new ArrayList<>();

    public static ArrayList<Animal> getListAnimals() {
        return listAnimals;
    }

    public static void setListAnimals(Animal animal) {
        listAnimals.add(animal);
    }

    public static void showListAnimals() {
        listAnimals.forEach(x -> {
            x.say();
            System.out.println(x.toString() + "\n");
        });
    }

    public static String countAnimals() {

        ArrayList<String> animalTypes = new ArrayList<>();
        listAnimals.forEach(x -> animalTypes.add(x.getClass().toString()) );

      //  System.out.println(animalTypes.toString());
        int cats=0, dogs=0, ducks=0;
        for (String elem:animalTypes) {
            //String elemTrash = "class " + System.class.getPackage().getName() + ".";
            //String cleanedElem = elem.replaceFirst(elemTrash, elem);
            String[] elems = elem.split("\\.");
            String cleaned = elems[elems.length-1];
            //System.out.println(cleaned);
            switch (cleaned) {
                case "Cat": cats++; break;
                case "Dog": dogs++; break;
                case "Duck": ducks++; break;
            }
        }
        return "\nВсего животных: " + (listAnimals.size()) + " | Кошек: " + cats  + " | Собак: " + dogs + " | Уток: " + ducks + "\n";

    }
}

