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
                .filter((Person x) -> x.getAge() >= 18 && (x.getAge() <= 27))
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(conscript);

        //3.Работоспособные люди
        List<Person> workableMan = persons.stream()
                .filter((Person educations) -> educations.getEducation() == Education.HIGHER)
                .filter((Person y) -> y.getSex() == Sex.MAN && y.getAge() >= 18 && (y.getAge() < 66))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        
        workableMan.forEach(System.out::println);
        List<Person> workableWoman = persons.stream()
                .filter((Person educations) -> educations.getEducation() == Education.HIGHER)
                .filter((Person x) -> x.getSex() == Sex.WOMEN && x.getAge() >= 18 && (x.getAge() < 61))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());


        workableWoman.forEach(System.out::println);

    }
}