// Simple Min-Heap implementation using Array Lists for Integers.
package io.anand.play;

import java.util.ArrayList;
import java.util.List;

// Implement a Consistent Hash Object.
// Intent is to provide it with Nodes which either serve the requests or host the keys
// [for load balance or data store applications respectively]
//
// Initially we will provide the number of nodes to start the hash, but either add or remove
// the nodes.
//
// For the implementation use Integer Ids as the node Ids. In reality you must use hostname or mac address
// or something that gives us a unique node id.
//
// Requests themselves could be uuids or numbers, for this exercise we will use simple integers.
//
// The possible positions on this ring is the enitre range of Integer (32 bit)
// All hash values returned will be a number in this long integer min and max value
//
// murmur hash should provide a 32 bit value for the position on the circular ring of values.
// For this exercise we will use java object hashcode in it's place.
//
//
//

class ConsistentHash {

    // Count of number of nodes in the system
    private int numNodes = 0;

    // Need a storage to note the nodes available in the system
    private List<Integer> nodes = new ArrayList<Integer>();

    public ConsistentHash(int numNodes) {
        this.numNodes = numNodes;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ConsistentHash h = new ConsistentHash(3);

    }
}
