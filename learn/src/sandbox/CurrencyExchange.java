package sandbox;

import java.util.*;

public class CurrencyExchange {

    // Currrency is a node in the graph
    private static class Currency {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public HashSet<CurrencyPeer> getPeers() {
            return peers;
        }

        public void setPeers(HashSet<CurrencyPeer> peers) {
            this.peers = peers;
        }

        public Currency (String name) {
            setName(name);
        }

        public boolean addPeer(CurrencyPeer peer) {
            return peers.add(peer);
        }

        public boolean delPeer(CurrencyPeer peer) {
            return peers.remove(peer);
        }
        private String      name;

       HashSet<CurrencyPeer> peers = new HashSet<>(); // All peer currencies we can trade to.
    }

    private static class CurrencyPeer {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        private String name;
        private double rate;

        public CurrencyPeer (String peerCur, double rate) {
            this.name = peerCur;
            this.rate    = rate;
        }
    }

    // Graph is a map of currencies and it's associated links.
    private static Map<String, Currency> currencies = new HashMap<>();

    public Currency addCurrency(String cur, Currency node) {
        return currencies.put(cur, node);
    }

    public Currency getCurrency(String name) {
        return currencies.get(name);
    }

    public void printExchange () {
        for (String cur : currencies.keySet()) {
            System.out.println(currencies.get(cur));
        }
    }

    private static void addConversion (String fromCurrency, String toCurrency, double rate) {
        // If currency exists, fine just add the link. Else create both and add the link.
        Currency fromCur = currencies.getOrDefault(fromCurrency, new Currency(fromCurrency));
        Currency toCur = currencies.getOrDefault(toCurrency, new Currency(fromCurrency));
        fromCur.addPeer(new CurrencyPeer(toCurrency, rate));
        toCur.addPeer((new CurrencyPeer(fromCurrency, 1.0 /rate)));
    }

    private double exchange (Currency fromCur, Currency toCur, double amount, HashSet<String> visitedSet) {
        System.out.println("Currency: " + fromCur.getName());
        visitedSet.add(fromCur.getName());
        for (CurrencyPeer peer: fromCur.getPeers()) {
            if (!visitedSet.contains(peer.getName()))
                exchange(currencies.get(peer.getName()), toCur, amount, visitedSet);
        }
    }

    private static double exchange (String fromCurrency, String toCurrency, int amount) {
        HashSet<String> visitedSet = new HashSet<>();
        // Look up links entry for fromCurrency.
        double convertedAmount = amount;
        Set<currencyLink> mapCurrencies = conversions.get(fromCurrency);
        Iterator<currencyLink>   iterator = mapCurrencies.iterator();

        // Traverse all the links from this currency till we find the toCurrency.
        // Add up the conversion using rates.
        if (null == mapCurrencies)
            return 0;

        Map<String, Boolean> consumedMap = new HashMap<>();
        List<currencyLink>  linksToConsume = new LinkedList<currencyLink>();
        linksToConsume.add(iterator.next());
        while (!linksToConsume.isEmpty()) {
            // Remember the visited node
            currencyLink currencyLink = linksToConsume.remove(0);
            boolean seen = consumedMap.getOrDefault(.fromCur, false);
            if (!seen) {
                // Get the conversion for the amount using the rate in this link
                convertedAmount *= currencyLink.rate;
                //
                linksToConsume.add(iterator.next());
            }
            if (currencyLink.toCur.equals(toCurrency)) {
                return convertedAmount;
            }
        }
    }

    public static void main (String [] args) {

        //Add Conversions
        addConversion("USD", "BTC", 6000);
        addConversion("USD", "ETH", 200);
        addConversion("EUR", "BTC", 5500);
        addConversion("EUR", "ETH", 150);







    }

}
