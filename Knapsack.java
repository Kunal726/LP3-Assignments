import java.util.Arrays;
import java.util.Comparator;

class Item {
    private int profit;
    private int weight;
    private double profitByWeight;

    public Item(int profit, int weight)
    {
        this.profit = profit;
        this.weight = weight;
        this.profitByWeight = (double) profit / (double) weight;
    }
   
    public int getProfit() {
        return profit;
    }

    public double getProfitByWeight() {
        return profitByWeight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Item [profitByWeight=" + profitByWeight + "]";
    }
   
}

public class Knapsack implements Comparator<Item> {

    public static void getKnapsack(Item[] items, int weight)
    {
        Arrays.sort(items, new Knapsack());
        //System.out.println(Arrays.toString(items));
        double maxProfit = 0.0;
        for(int i = 0; i < items.length; i++)
        {
            if(items[i].getWeight() <= weight)
            {
                weight -= items[i].getWeight();
                maxProfit += items[i].getProfit();
            }
            else {
                maxProfit += (items[i].getProfit() * ((double) weight / (double) items[i].getWeight()));
                break;
            }
        }

        System.out.println(maxProfit);
    }

    @Override
    public int compare(Item i1, Item i2) {
        return Double.compare(i2.getProfitByWeight(), i1.getProfitByWeight());
    }

    public static void main(String[] args) {
        int[][] vals = { { 60, 10 }, { 100, 20 }, { 120, 30 } };
        Item[] items = new Item[vals.length];
        int weight = 50;
        for(int i = 0; i < vals.length; i++)
        {
            items[i] = new Item(vals[i][0], vals[i][1]);
        }

        getKnapsack(items, weight);
    }
    
}
