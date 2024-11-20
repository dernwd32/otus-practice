import animals.Dog;
import animals.Puppy;

public class Lesson3 {
    public static void main(String[] args) {
        Dog thisOne = new Dog("Бобег");
        //thisOne.setName("Бобег");
        thisOne.setAge(4);

        Dog thisAnother = new Dog("Туша");
        // thisAnother.setName("Туша");
        thisAnother.setAge(8);

        Integer age = thisOne.getAge();

        thisOne.test = 3;

        Dog thisThird = new Dog("Василий", 3,"red",123);
        //is

        System.out.println(thisThird.getColor());
       // thisThird.bark();
        //thisOne.bark();

        Puppy pup = new Puppy();
        pup.setName("Мелочь");
        pup.setAge(1);

        System.out.println(pup.getName());

       //pup.bark();
        //pup.lay();
        pup.setName("qwe");

        thisOne.bark(" тяф!");

        pup.bark();


       // Puppy p00p = new hometask1.Dog();

    }
}
