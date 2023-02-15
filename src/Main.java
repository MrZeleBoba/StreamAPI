import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();

        for (long i = 0; i < 100_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])


            );


        }
        //1.Поиск несовершеннолетних
        long count = persons.stream()
                .filter((Person minors) -> minors.getAge() < 18)
                .count();
        System.out.println(count);

        //2.Список призывников
        List<String> conscript = persons.stream()
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(person -> person.getAge() >= 18 && (person.getAge() <= 27))
                .map(Person::getFamily)
                .collect(Collectors.toList());
        conscript.forEach(System.out::println);

        //3.Работоспособные люди
        List<Person> work = persons.stream()
                .filter(person -> person.getEducation() == (Education.HIGHER))
                .filter(person -> person.getSex() == Sex.MAN ? person.getAge() >= 18 && (person.getAge() <= 66)
                        : person.getAge() >= 18 && (person.getAge() < 61))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        work.forEach(System.out::println);


    }
}