package io.anand.play;

import java.util.*;

/*

 Find the minimum number of platforms needed to cover the schedule of the trains arriving and departing
 at a train station.

 */

public class MinTrainPlatforms {

    private static int findMinPlatformd (int arrivals[], int departures[]) {
        int numPlatforms, maxPlatforms, arrIndex, depIndex;

        // First sort the arrays to ensure we find overlapping segments for trains to be present at any given time.
        // maximum number of such segments will be our needed number of platforms.
        Arrays.sort(arrivals);
        Arrays.sort(departures);

        // Traverse the arrivals and departures one by one
        // advance arrival or departure index based on if we detect a new start of segment of time or end of segment of time.
        numPlatforms = maxPlatforms = arrIndex = depIndex = 0;
        while ((arrIndex < arrivals.length) && (depIndex < departures.length)) {
            // if arrival time is less than departure time, we need a platform and let us advance to next arrival time.
            if (arrivals[arrIndex] < departures[depIndex]) {
                numPlatforms++;
                arrIndex++;
                // if we detect we have a situation of more trains than what max numner of trains we had seen earlier, capture it
                if (maxPlatforms < numPlatforms)
                    maxPlatforms = numPlatforms;
            } else {
                // one train is now leaving the station, number of platorms needed is less, also let is move to next departure time.
                numPlatforms--;
                depIndex++;
            }
        }
        return maxPlatforms;
    }

    public static void main(String[] args) {
        int arrival[]    = {9,  10, 11, 1, 3, 2,  5,  7};
        int departures[] = {16, 17, 14, 18, 4, 8, 10, 11};

        System.out.println("Arrival: " + Arrays.toString(arrival) + "\nDepartures: " + Arrays.toString(departures) + "\nPlatforms needed: " +
                findMinPlatformd(arrival, departures));

    }

}
