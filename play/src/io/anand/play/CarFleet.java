// Simple Min-Heap implementation using Array Lists for Generic Types.
package io.anand.play;

import java.util.Arrays;
import java.util.HashMap;

class CarFleet {
    static public int carFleet(int target, int[] position, int[] speed) {

        boolean raceDone = false;
        int     timeTick = 0;
        int     numFleet = 0;

        // This loop serves a unit of time for which speed is set
        // say 2 means 2 units of distance per unit of time ex: 2 miles/hour
        // in this case, the loop runs ticks at 1 hour interval.
        // after each loop, cars would have advanced to speed units of distance
        while (!raceDone) {
            // Move the cars to next position.
            boolean targetReached = false;
            // Assume this tick of time we are done with all cars reaching the target.
            // if we not we will know in the loop.
            raceDone = true;
            HashMap<Integer, Integer> bumpMap = new HashMap<>();

            for (int j = 0; j < position.length; j++)  {
                boolean carCrossedTarget = position[j] >= target;
                position[j] += speed[j];
                // Did we bump into any slowpoke ?
                // if so from now one we go at same speed.
                // Update our speed to the slowpoke's value.
                // Lookup if this position is seen in this loop and if not add it for any others to bump into us.
                if (bumpMap.containsKey(position[j])){
                    int bumpIndex = bumpMap.get(position[j]);
                    int bumpSpeed = speed[bumpIndex];
                    if (bumpSpeed < speed[j])
                        speed[j] = bumpSpeed;
                    else
                        speed[bumpIndex] = speed[j];
                } else {
                    bumpMap.put(position[j], j);
                    // If any car reaches the target, note it.
                    if ((!carCrossedTarget) && (position[j] >= target))
                        numFleet++;
                }

                if (position[j] < target)
                    raceDone = false;
            }

            System.out.println("numFleet: " + numFleet +
                            ", raceDone: " + raceDone +
                            ", targetReached: " + targetReached +
                            ", position: " + Arrays.toString(position) +
                            ", speed: " + Arrays.toString(speed));
            // We had some cars that reached the target, so one fleet is done
//            if (targetReached)
//                numFleet++;
        }
        return numFleet;
    }

    public static void main(String[] args) {
        int position[]  = {6,2,17};
        int speed []    = {3,9,2};
        int target      = 20;
        System.out.println("Number of Fleets: " + carFleet(target, position, speed) +
                ", for position: " + Arrays.toString(position) +
                ", speed: " + Arrays.toString(speed) +
                ", target: " + target);
    }
}
