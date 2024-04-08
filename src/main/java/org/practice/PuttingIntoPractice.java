package org.practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PuttingIntoPractice {
    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        {
//      1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).
            System.out.println("Exercise №1");
            transactions.stream()
                    .filter(x -> {
                        return x.getYear() == 2011;
                    })
                    .sorted(Comparator.comparingInt(Transaction::getValue))
                    .forEach(System.out::println);

//      2. Вывести список неповторяющихся городов, в которых работают трейдеры.
            System.out.println("Exercise №2");
            transactions.stream()
                    .map(x -> {
                        return x.getTrader().getCity();
                    })
                    .distinct()
                    .forEach(System.out::println);

//      3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.
            System.out.println("Exercise №3");
            transactions.stream()
                    .map(trader -> trader.getTrader())
                    .distinct()
                    .filter(trader -> {
                        return trader.getCity().equals("Cambridge");
                    })
                    .map(trader -> trader.getName())
                    .sorted()
                    .forEach(System.out::println);

//      4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке.
            System.out.println("Exercise №4");
            String str = transactions.stream()
                    .map(transaction -> transaction.getTrader())
                    .map(trader -> trader.getName())
                    .distinct()
                    .sorted()
                    .collect(Collectors.joining(", "));
            System.out.println(str);

//      5. Выяснить, существует ли хоть один трейдер из Милана.
            System.out.println("Exercise №5");
            boolean isExist = transactions.stream()
                    .map(Transaction::getTrader)
                    .map(trader -> trader.getCity())
                    .anyMatch(city -> {
                        return city.equals("Milan");
                    });
            System.out.println(isExist);

//      6. Вывести суммы всех транзакций трейдеров из Кембриджа.
            System.out.println("Exercise №6");
            transactions.stream()
                    .filter(transaction -> {
                        return transaction.getTrader().getCity().equals("Cambridge");
                    })
                    .map(Transaction::getValue)
                    .forEach(System.out::println);

//      7. Какова максимальная сумма среди всех транзакций?
            System.out.println("Exercise №7");
            int max = transactions.stream()
                    .max(Comparator.comparingInt(Transaction::getValue))
                    .get()
                    .getValue();
            System.out.println("MaxSum = " + max);

//      8. Найти транзакцию с минимальной суммой.
            System.out.println("Exercise №8");
            int min = transactions.stream()
                    .min(Comparator.comparingInt(Transaction::getValue))
                    .get()
                    .getValue();
            System.out.println("MinSum = " + min);
        }
    }
}