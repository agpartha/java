package io.anand.sandbox;

import java.util.ArrayList;
import java.util.HashMap;

public class MaxPopulation {

    private static class Person {
        public Person(int birth, int death) {
            this.birth = birth;
            this.death = death;
        }

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

        @Override
        public String toString() {
            return " " + birth + ":" + death + " ";
        }
    }

    private static int firstYear = Integer.MAX_VALUE;
    private static int lastYear  = 0;
    private static ArrayList<Person>   people = new ArrayList<>();
    private static HashMap<Integer, Integer> yearlyPopulation = new HashMap<>();

    private static int maxPopulationYear (int start, int end)
    {
        int year, maxYear;
        int population, maxPopulation;

        year = maxYear = start;
        population = maxPopulation = 0;

        int numYears = 0;
        while (year <= end) {
            population += yearlyPopulation.getOrDefault(year, 0);
            if (maxPopulation < population) {
                maxPopulation = population;
                maxYear = year;
            }
            System.out.println("Year: " + year +", population: " + population + ", maxPopulation: " + maxPopulation);
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
        yearlyPopulation.put(birth, 1 + yearlyPopulation.getOrDefault(birth, 0));
        // Count deaths for next year, so that person is counted in the last year of
        // their life. if not desired, then don't increment.
        if (death > 0)
            yearlyPopulation.put(death + 1, yearlyPopulation.getOrDefault(death + 1, 0) - 1);
        // Not needed, just for display for sanity checking.
        people.add(new Person(birth, death));
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
        addPerson(1940, 1945);
        addPerson(1965, 1987);
        addPerson(1990, 1994);
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

        System.out.println("People: " + people);
        System.out.println("Yearly Population: " + yearlyPopulation);
        maxPopulationYear();
    }
}
