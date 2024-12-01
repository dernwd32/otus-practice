package animals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AnimalList {

    private ArrayList<AbsAnimal> listAnimals = new ArrayList<>();

    public void setListAnimals(AbsAnimal animal) {
        listAnimals.add(animal);
    }

    public void printListAnimals() {

        if (listAnimals.isEmpty()) System.out.println("Список животных пуст.");
        else listAnimals.forEach(thisAnimal -> {
                //чуть-чуть рефлексии для красивого вывода типа животного
                System.out.print(thisAnimal.getClass().getSimpleName() + ": ");
                System.out.println(thisAnimal.toString());
            });

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

