package io.anand.play;

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

    private static void addExchange(String fromCurrency, String toCurrency, double rate) {
        // If currency exists, fine just add the link. Else create both and add the link.
        Currency fromCur = addCurrency(fromCurrency);
        Currency toCur  = addCurrency(toCurrency);
        fromCur.addPeer(new CurrencyPeer(toCurrency, rate));
        toCur.addPeer((new CurrencyPeer(fromCurrency, 1.0 / rate)));
    }

    private static boolean findConversions (Currency cur, Currency toCur, double amount, double rate, boolean isPossible, HashSet<String> visitedSet) {
        double amountIn = amount;
        amount *= rate;
        System.out.println("Currency: " + cur.getName() + ", rate: " + rate + ", amountIn: " + amountIn + ", amount: " + amount);
        if (cur.getName().equals(toCur.getName())) {
            System.out.println("****** Yay!, Converted amount: " + amount + " ******");
            return true;
        }
        visitedSet.add(cur.getName());
        for (CurrencyPeer peer: cur.getPeers()) {
            if (!visitedSet.contains(peer.getName())) {
                isPossible = findConversions(currencies.get(peer.getName()), toCur, amount, peer.getRate(), isPossible, visitedSet);
            }
        }
        return isPossible;
    }

    private static boolean findConversions (String fromCurrency, String toCurrency, int amount) {
        HashSet<String> visitedSet = new HashSet<>();
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        System.out.println(fromCurrency + " -> " +  toCurrency + " (amount: " + amount + ")");
        return findConversions(currencies.get(fromCurrency), currencies.get(toCurrency), 1.0 * amount, 1.0, false, visitedSet);
    }

    public static void main (String [] args) {
        //Add Conversions
        addExchange("BTC", "USD", 6000);
        addExchange("ETH", "USD", 200);
        addExchange("BTC", "EUR", 5500);
        addExchange("ETH", "EUR", 150);
        addExchange("YEN", "INR", 800);
        addExchange("ETH", "YEN", 1000);

        printExchange();
        printExchangePeers();

        System.out.println("ETH -> BTC: " + (findConversions("ETH", "BTC", 4) ? "Possible" : "Not Possible"));
        System.out.println("ETH -> INR: " + (findConversions("ETH", "INR", 6) ? "Possible" : "Not Possible"));
        System.out.println("USD -> BTC: " + (findConversions("USD", "BTC", 1) ? "Possible" : "Not Possible"));
        System.out.println("USD -> EUR: " + (findConversions("USD", "EUR", 2) ? "Possible" : "Not Possible"));
        System.out.println("BTC -> ETH: " + (findConversions("BTC", "ETH", 3) ? "Possible" : "Not Possible"));
        System.out.println("INR -> BTC: " + (findConversions("INR", "BTC", 50000) ? "Possible" : "Not Possible"));
    }

    /*
      Run output:

Currency{name=BTC, peers=[Peer{name=EUR, rate=5500.0}, Peer{name=USD, rate=6000.0}]}
Currency{name=EUR, peers=[Peer{name=ETH, rate=0.006666666666666667}, Peer{name=BTC, rate=1.818181818181818E-4}]}
Currency{name=YEN, peers=[Peer{name=INR, rate=74.0}]}
Currency{name=USD, peers=[Peer{name=BTC, rate=1.6666666666666666E-4}, Peer{name=ETH, rate=0.005}]}
Currency{name=ETH, peers=[Peer{name=EUR, rate=150.0}, Peer{name=USD, rate=200.0}]}
Currency{name=INR, peers=[Peer{name=YEN, rate=0.013513513513513514}]}
Currency: { name: BTC , peers: [ EUR: 5500.0 USD: 6000.0 ] }
Currency: { name: EUR , peers: [ ETH: 0.006666666666666667 BTC: 1.818181818181818E-4 ] }
Currency: { name: YEN , peers: [ INR: 74.0 ] }
Currency: { name: USD , peers: [ BTC: 1.6666666666666666E-4 ETH: 0.005 ] }
Currency: { name: ETH , peers: [ EUR: 150.0 USD: 200.0 ] }
Currency: { name: INR , peers: [ YEN: 0.013513513513513514 ] }
Currency: USD, rate: 1.0, amount: 1.0
Currency: BTC, rate: 1.6666666666666666E-4, amount: 1.6666666666666666E-4
USD-> BTC: 1.6666666666666666E-4
Currency: USD, rate: 1.0, amount: 2.0
Currency: BTC, rate: 1.6666666666666666E-4, amount: 3.333333333333333E-4
Currency: EUR, rate: 5500.0, amount: 1.8333333333333333
USD-> EUR: 1.8333333333333333
Currency: BTC, rate: 1.0, amount: 3.0
Currency: EUR, rate: 5500.0, amount: 16500.0
Currency: ETH, rate: 0.006666666666666667, amount: 110.0
BTC-> ETH: 110.0
Currency: ETH, rate: 1.0, amount: 4.0
Currency: EUR, rate: 150.0, amount: 600.0
Currency: BTC, rate: 1.818181818181818E-4, amount: 0.10909090909090909
ETH-> BTC: 0.10909090909090909
Currency: INR, rate: 1.0, amount: 50000.0
Currency: YEN, rate: 0.013513513513513514, amount: 675.6756756756757
INR-> BTC: 0.0
Currency: ETH, rate: 1.0, amount: 6.0
Currency: EUR, rate: 150.0, amount: 900.0
Currency: BTC, rate: 1.818181818181818E-4, amount: 0.16363636363636364
Currency: USD, rate: 6000.0, amount: 981.8181818181818
ETH-> INR: 0.0

     */

}
