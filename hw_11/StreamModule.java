package go_it_hw.hw_11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Nazarii Soviak
 */

public class StreamModule {

    //TASK #1
    private static String namesPrintedFromOddPositions(List<String> names) {
        return IntStream.range(0, names.size())
                .filter(n -> n % 2 != 0)
                .mapToObj(index -> String.format("%d. %s", index, names.get(index)))
                .collect(Collectors.joining(", "));
    }

    //TASK #2
    private static List<String> namesInReverseOrder(List<String> names) {
        return names.stream()
                .map(name -> name.toUpperCase())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    /*
    *  Є масив:
       ["1, 2, 0", "4, 5"]
       Необхідно отримати з масиву всі числа, і вивести їх у відсортованому вигляді через кому ,, наприклад:
        "0, 1, 2, 4, 5"  */

//  TASK #3
    private static String numbersSort (String[] strings) {
        return Arrays.stream(strings)
                .flatMap(s -> Stream.of(s.split((", "))))
                .map(Integer::valueOf)
                .sorted(Integer::compareTo)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    //TASK #4
    private static Stream<Long> infinityNumbers () {
        long a = 25214903917L;
        long c = 11;
        long m = (long) Math.pow(2,48);
        long seed = 0;
        return Stream.iterate(seed, x -> (a * (x + c) % m));
    }

    //Task #5
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        List<T> firstList = first.collect(Collectors.toList());
        List<T> secondList = second.collect(Collectors.toList());

        int size = Math.min(firstList.size(), secondList.size());
        List<T> result = new ArrayList<>();

        IntStream.range(0,size).forEach((element) ->{
            result.add(firstList.get(element));
            result.add(secondList.get(element));
                }
                );
        return result.stream();
    }

    public static void main(String[] args) {
        // TASK #1
        List<String> names = Arrays.asList("Ivan", "Peter", "Sasha", "Petro", "Olha", "Karina", "Dean", "Oleg", "Anna", "Oksana", "Dima", "Sam",
                "Ivan", "Orest", "Ihor", "Misha", "Derek");
        System.out.println("Task # 1:");
        System.out.println("The list of names positioned under the odd number = " + namesPrintedFromOddPositions(names));
        System.out.println();

        //Task #2
        List<String> names1 = Arrays.asList("Ivan", "Peter", "Sasha", "Petro", "Olha", "Karina", "Dean", "Oleg", "Anna", "Oksana", "Dima", "Sam",
                "Ivan", "Orest", "Ihor", "Misha", "Derek");
        System.out.println("Task # 2:");
        System.out.println("The list of names in reverse order and in upper case = " + namesInReverseOrder(names1));
        System.out.println();

        //Task #3
        System.out.println("Task # 3:");
        String[] input = new String[]{"1, 2, 0", "4, 5"};
        System.out.println(numbersSort(input));
        System.out.println();

        //Task #4
        // Uncomment to run this method :)
        // System.out.println("Task # 4:");
        // infinityNumbers().forEach(System.out::println);

        //Task 5
        Stream<String> first = Stream.of("One", "Two", "Three", "Four", "Five");
        Stream<String> second = Stream.of("Six", "Seven", "Eight", "Nine", "Ten");
        List<String> result = zip(first, second).collect(Collectors.toList());
        System.out.println("Task # 5:");
        System.out.println(result);
    }
}


