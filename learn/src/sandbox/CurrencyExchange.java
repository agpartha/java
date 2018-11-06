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

        @Override
        public String toString() {
            return "Currency{" +
                    "name=" + getName() +
                    ", peers=" + getPeers().toString() +
                    '}';
        }
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

        @Override
        public String toString() {
            return "Peer{" +
                    "name=" + getName() +
                    ", rate=" + getRate() +
                    '}';
        }
    }

    // Graph is a map of currencies and it's associated links.
    private static Map<String, Currency> currencies = new HashMap<>();

    public static Currency getCurrency(String name) {
        return currencies.get(name);
    }

    public static void printExchange () {
        for (String cur : currencies.keySet()) {
            System.out.println(currencies.get(cur));
        }
    }

    private static Currency addCurrency (String name) {
        Currency cur = getCurrency(name);
        if (null == cur) {
            cur = new Currency(name);
            currencies.put(name, cur);
        }
        return cur;
    }

    public void printCurrencies () {
        for (String name : currencies.keySet()) {
            System.out.println(currencies.get(name));
        }
    }

    public static void printExchangePeers() {
        for (String name : currencies.keySet()) {
            StringBuilder sb = new StringBuilder();
            Currency cur = currencies.get(name);
            sb.append("Currency: { name: " + cur.getName() + " , peers: [");
            HashSet<CurrencyPeer> peers = cur.getPeers();
            for (CurrencyPeer peer : peers) {
                Currency peerCur = currencies.get(peer.getName());
                sb.append(" " + peerCur.getName());
                sb.append(": " + peer.getRate());
            }
            sb.append(" ] }");
            System.out.println(sb.toString());
        }
    }

    private static void addConversion (String fromCurrency, String toCurrency, double rate) {
        // If currency exists, fine just add the link. Else create both and add the link.
        Currency fromCur = addCurrency(fromCurrency);
        Currency toCur  = addCurrency(toCurrency);
        fromCur.addPeer(new CurrencyPeer(toCurrency, rate));
        toCur.addPeer((new CurrencyPeer(fromCurrency, 1.0 /rate)));
    }

    private static  double exchange (Currency fromCur, Currency toCur, double amount, HashSet<String> visitedSet) {
        System.out.println("Currency: " + fromCur.getName());
        visitedSet.add(fromCur.getName());
        for (CurrencyPeer peer: fromCur.getPeers()) {
            if (!visitedSet.contains(peer.getName()))
                return exchange(currencies.get(peer.getName()), toCur, amount, visitedSet);
        }
        return 0;
    }

    private static double exchange (String fromCurrency, String toCurrency, int amount) {
        HashSet<String> visitedSet = new HashSet<>();
        return exchange(currencies.get(fromCurrency), currencies.get(toCurrency), 1.0 * amount, visitedSet);
    }

    public static void main (String [] args) {
        //Add Conversions
        addConversion("USD", "BTC", 6000);
        addConversion("USD", "ETH", 200);
        addConversion("EUR", "BTC", 5500);
        addConversion("EUR", "ETH", 150);

        printExchange();
        printExchangePeers();
    }

}
