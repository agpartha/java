package io.anand.sandbox;

import java.util.ArrayList;
import java.util.HashMap;

public class MaxPopulation {

    private static class Person {
        private int birth;

        public int getBirth() {
            return birth;
        }

        public void setBirth(int birth) {
            this.birth = birth;
        }

        public int getDeath() {
            return death;
        }

        public void setDeath(int death) {
            this.death = death;
        }

        private int death;
    }

    private static int firstYear = Integer.MAX_VALUE;
    private static int lastYear  = 0;
    private ArrayList<Person>   people = new ArrayList<>();
    private static HashMap<Integer, Integer> births = new HashMap<>();
    private static HashMap<Integer, Integer> deaths = new HashMap<>();

    private static int maxPopulationYear (int start, int end)
    {
        int year, maxYear;
        int population, maxPopulation;

        year = maxYear = start;
        population = maxPopulation = 0;

        int numYears = 0;
        while (year <= end) {
            int bYear = year;
// This will not work well since we will skip the non-birth years and deaths that reduce population
// will get skipped. So we have to run through the first birth year to last birth year all years.
// for (int bYear : births.keySet()) {
            population += births.getOrDefault(bYear, 0) - deaths.getOrDefault(bYear, 0);
            if (maxPopulation < population) {
                maxPopulation = population;
                maxYear = bYear;
            }
            year++;
            numYears++;
        }
        System.out.println("Searched (" + numYears + ") years from: " + firstYear + " to: " + lastYear);
        System.out.println("Year: " + maxYear + " had maximum population of: " + maxPopulation);
        return maxYear;
    }

    private static int maxPopulationYear () {
        return maxPopulationYear(firstYear, lastYear);
    }


    // Assume -1 for death means person still alive
    private static void addPerson (int birth, int death) {
        if (firstYear > birth) firstYear = birth;
        if (lastYear < birth) lastYear = birth;
        births.put(birth, 1 + births.getOrDefault(birth, 0));
        // Count deaths for next year, so that person is counted in the last year of
        // their life. if not desired, then don't increment.
        if (death > 0)
            deaths.put(death + 1, 1 + deaths.getOrDefault(death, 0));
    }

    public static void main (String args[]) {

        /* Prepare Data
        births.put(1940, 100);
        births.put(1950, 20);
        births.put(1990, 100);

        deaths.put(1950, 10);
        deaths.put(1960, 10);
        deaths.put(1970, 30);
        deaths.put(1995, 40);
        */
        addPerson(1940, 1976);
        addPerson(1965, 2010);
        addPerson(1990, 2034);
        addPerson(1990, 1994);
        addPerson(1980, 1987);
        addPerson(1950, -1);
        addPerson(1951, 1978);
        addPerson(1967, 2004);
        addPerson(1990, 2003);
        addPerson(1956, 1978);
        addPerson(1991, 2006);
        addPerson(1999, 1999);
        addPerson(1980, 1996);
        addPerson(1998, -1);
        addPerson(1998, -1);
        addPerson(1999, 2017);
        // Prepare list by adding people to the array list


        System.out.println("Births: " + births);
        System.out.println("Deaths: " + deaths);
        maxPopulationYear();
    }
}
