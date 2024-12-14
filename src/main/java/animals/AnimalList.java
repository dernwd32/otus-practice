package animals;

import data.AnimalTypesData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AnimalList {

    private ArrayList<AbstractAnimal> listAnimals = new ArrayList<>();
    private ArrayList<AbstractAnimal> listOfFoundAnimals = new ArrayList<>();

    public void setListAnimals(AbstractAnimal animal) {
        listAnimals.add(animal);
    }
    public ArrayList<AbstractAnimal> getListOfFoundAnimals() {
        return listOfFoundAnimals;
    }
    public void setListOfFoundAnimalsAnimals(AbstractAnimal animal) {
        listOfFoundAnimals.add(animal);
    }

    public void printListAnimals() {

        if (listAnimals.isEmpty()) System.out.println("Список животных пуст.");
        else listAnimals.forEach(thisAnimal -> {
                //чуть-чуть рефлексии для красивого вывода типа животного
                System.out.print(thisAnimal.getClass().getSimpleName() + "#");
                System.out.println(thisAnimal.toString());
            });

    }

    public void printTableListAnimals() {

            printTableListAnimals(listAnimals); //дефолтный список и далее через оверлоад

    }

    public void printTableListAnimals(ArrayList<AbstractAnimal> listAn) {

        String thRow = String.format("%-25s | %-10s | %-10s | %-10s | %-10s",
                "name", "age", "weight", "color", "type#\u001B[36mid\u001B[0m")
                + "\n"
                + "-----------------------------------------------------------------------";

        if (!Objects.isNull(listAn)) {
            if (listAn.isEmpty()) System.out.println("Список животных пуст.");
            else {
                System.out.println(thRow);
                listAn.forEach(thisAnimal -> System.out.println(
                                thisAnimal.toTableTr(thisAnimal.getClass().getSimpleName())
                        )
                );
            }
        }


    }


    public StringBuilder countAnimals() {

        Map<String, Integer> animalTypes = new HashMap<>();
        //ещё чуть-чуть рефлексии для счётчика. Можно без неё обновлять данные счётчика
        //в момент добавления экземпляров, но так скучно
        listAnimals.forEach(
                thisAnimal -> {
                    String thisClass = thisAnimal.getClass().getSimpleName();
                    if (!animalTypes.containsKey(thisClass))
                        animalTypes.put(thisClass, 1);
                    else
                        animalTypes.put(thisClass, animalTypes.get(thisClass) + 1);
                }
        );

        StringBuilder countStr = new StringBuilder("\n| Всего животных: " + (listAnimals.size()) + " | ");
        animalTypes.forEach((key, value) -> countStr.append(key).append(": ").append(value).append(" | "));


        return countStr;

    }
}

