package animals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AnimalList {

    private ArrayList<AbstractAnimal> listAnimals = new ArrayList<>();
    private ArrayList<AbstractAnimal> listOfFoundAnimals = new ArrayList<>();

    //булева переменная, отвечающая далее на вопрос - были ли обновления в таблице, надо ли ее заново считывать в listAnimals
    public static boolean ifUpdated = true;

    public void setListAnimals(AbstractAnimal animal) {
        listAnimals.add(animal);
    }
    public ArrayList<AbstractAnimal> getListOfFoundAnimals() {
        return listOfFoundAnimals;
    }
    public void setListOfFoundAnimals(AbstractAnimal animal) {
        listOfFoundAnimals.add(animal);
    }

    public void printListAnimals() {

        if (listAnimals.isEmpty()) System.out.println("Список животных пуст.");
        else listAnimals.forEach(thisAnimal -> {
                //чуть-чуть рефлексии для красивого вывода типа животного
                System.out.print(thisAnimal.getClass().getSimpleName() + "#");
                System.out.println(thisAnimal); //.toString() не нужен, вызывается по умолчанию
            });

    }

    public void printTableListAnimals() {
            printTableListAnimals(listAnimals, "id"); //дефолтный список и далее через оверлоад
    }

    public void printTableListAnimals(ArrayList<AbstractAnimal> listAn, String highlightColumn) {

        String thRow = String.format("%-5s | %-8s | %-25s | %-10s | %-10s | %-10s ",
                "id", "type", "name", "age", "weight", "color")
                + "\n"
                + "---------------------------------------------------------------------------------------";


        if (!Objects.isNull(listAn)) {
            if (listAn.isEmpty()) System.out.println("Список животных пуст.");
            else {
                System.out.println(thRow);
                listAn.forEach(thisAnimal -> System.out.println(
                                thisAnimal.toTableTr(highlightColumn)
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

