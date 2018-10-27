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
        System.out.println("Year: " + maxYear + " had maximum population of: " + maxPopulation);
        return maxYear;
    }

    private static int maxPopulatiopnYear () {
        return maxPopulatiopnYear(firstYear, lastYear);
    }


    // Assume -1 for death means person still alive
    private static void addPerson (int birth, int death) {
        if (firstYear > birth) firstYear = birth;
        if (lastYear < death) lastYear = death;
        births.put(birth, 1 + births.getOrDefault(birth, 0));
        if (death > 0)
            deaths.put(death, 1 + deaths.getOrDefault(death, 0));
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
        addPerson(1740, 1790);
        addPerson(1840, -1);
        addPerson(1990, -1);
        addPerson(1990, 1999);
        addPerson(1980, 1999);
        addPerson(1950, -1);
        addPerson(1951, 1990);
        addPerson(1880, 1990);
        addPerson(2000, 2100);
        addPerson(2009, -1);
        addPerson(2001, -1);
        addPerson(1999, -1);
        addPerson(1980, 2018);
        addPerson(1998, -1);
        addPerson(2005, -1);
        addPerson(1999, 2017);
        // Prepare list by adding people to the array list


        System.out.println("Births: " + births);
        System.out.println("Deaths: " + deaths);
        maxPopulatiopnYear();
    }
}
