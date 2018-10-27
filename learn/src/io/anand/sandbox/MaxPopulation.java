package io.anand.sandbox;

import java.util.HashMap;
import java.util.HashSet;

public class MaxPopulation {

    private static HashMap<Integer, Integer> births = new HashMap<>();
    private static HashMap<Integer, Integer> deaths = new HashMap<>();

    private static int maxPopulatiopnYear (int start, int end)
    {
        int year, maxYear;
        int population, maxPopulation;

        year = maxYear = start;
        population = maxPopulation = 0;

        while (year <= end) {
            population += births.getOrDefault(year, 0);
            population -= deaths.getOrDefault(year, 0);
            if (maxPopulation < population) {
                maxPopulation = population;
                maxYear = year;
            }
            year++;
        }
        return maxYear;
    }

    public static void main (String args[]) {

        // Prepare Data
        births.put(1940, 100);
        births.put(1950, 20);
        births.put(1990, 100);

        deaths.put(1950, 10);
        deaths.put(1960, 10);
        deaths.put(1970, 30);
        deaths.put(1995, 40);

        System.out.println("Births: " + births);
        System.out.println("Deaths: " + deaths);
        System.out.println("Year with maximum population: " + maxPopulatiopnYear(1920, 2010));

    }
}
