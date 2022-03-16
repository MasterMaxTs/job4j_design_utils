package ru.job4j.ood.dip.student;

import java.util.*;
import java.util.function.Predicate;

public class StudentRecord {

    private final Map<Integer, Set<Student>> register;

    /*Создание объекта класса инициализирует конкретную реализацию хранилища,
     * что является нарушением принципов DIP, OCP  */
    public StudentRecord() {
        Set<Student> hs1 = new HashSet<>();
        Set<Student> hs2 = new HashSet<>();
        Set<Student> hs3 = new HashSet<>();
        Set<Student> hs4 = new HashSet<>();
        Set<Student> hs5 = new HashSet<>();
        register = new HashMap<>(
                Map.of(
                    1, hs1,
                    2, hs2,
                    3, hs3,
                    4, hs4,
                    5, hs5
                )
        );
    }

    public void showStudentBy(Predicate<Student> filter) {
        Optional<Student> os = findBy(filter);
        if (os.isEmpty()) {
            System.out.println("Student not find");
        }
        System.out.println(os.get());
    }

    public boolean put(Student student) {
        return register
                .get(student.getCourse())
                .add(student);
    }

    public boolean remove(Student student) {
        return register
                .get(student.getCourse())
                .remove(student);
    }

    /*Метод возращает конкретную сущность, что является нарушением
    * принциов DIP и OCP*/
    public Optional<Student> findBy(Predicate<Student> filter) {
        return register
                .values()
                .stream()
                .flatMap(Collection::stream)
                .filter(filter)
                .findFirst();
    }
}
