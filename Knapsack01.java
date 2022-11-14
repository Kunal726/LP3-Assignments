import java.util.Arrays;
import java.util.Scanner;

class Item{
    private int profit;
    private int weight;
   
    public Item(int profit, int weight) {
        this.profit = profit;
        this.weight = weight;
    }
    public int getWeight() {
        return weight;
    }
    public int getProfit() {
        return profit;
    }
   
}
public class Knapsack01 {

    public int max(int a, int b)
    {
        return a > b ? a : b;
    }

    public void Solution(Item[] items, int weight)
    {
        //Generate Table
        int[][] table = new int[items.length][weight + 1];
        for(int i = 0; i < table.length; i++)
        {
            for(int w = 0; w <= weight; w++)
            {
                if(i == 0 || w == 0)
                {
                    table[i][w] = 0;
                }
                else if(items[i].getWeight() <= weight) 
                {
                    if (w - items[i].getWeight() < 0)
                        table[i][w] = table[i - 1][w];
                    else
                        table[i][w] = max(items[i].getProfit() + table[i - 1][w - items[i].getWeight()], table[i - 1][w]);
                }
                else 
                    table[i][w] = table[i - 1][w];
            }
        }
        for(int[] x : table)
            System.out.println(Arrays.toString(x));

        int maxProfit = 0;
        // find solution
        StringBuilder sb = new StringBuilder("}");
        for(int i = items.length - 1; i > 0 && weight >= 0; i--)
        {
            if(table[i][weight] != table[i-1][weight])
            {
                System.out.println("Item : " + i + " = 1");
                maxProfit += items[i].getProfit();
                weight -= items[i].getWeight();
                sb.append("1 ,");
            }
            else {
                System.out.println("Item : " + i + " = 0");
                sb.append("0 ,");
            }

        }
        sb.deleteCharAt(sb.length() -1);
        sb.deleteCharAt(sb.length() -1);
        sb.append("{");
        sb.reverse();

        System.out.println("Max Profit : " + maxProfit);
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        int maxSize;
        int itemCount;
        Item[] items;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Size of Knapsack : ");
        maxSize = sc.nextInt();
        System.out.print("Enter total number of items : ");
        itemCount = sc.nextInt();

        items = new Item[itemCount + 1];
        items[0] = new Item(0, 0);

        System.out.println("Enter profit and weight of items");
        for(int i = 1; i <= itemCount; i++)
            items[i] = new Item(sc.nextInt(), sc.nextInt());
        
        new Knapsack01().Solution(items, maxSize);
        
    }
    
}
