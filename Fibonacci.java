public class Fibonacci {
    public static void fibIter(int n)
    {
        int a = 0, b = 1, c, count = 0;
        System.out.print(a + " " + b + " ");
        count++;
        for(int i = 2; i < n; i++)
        {
            c = a + b;
            a = b;
            b = c;
            System.out.print(c + " ");
            count += 4;
        }

        System.out.println("\n " + count);
    }

    public static int cnt = 0;
    public static void fibRec(int s, int n, int a, int b)
    {
        cnt++;
        if(s == n + 1) {
            cnt ++;
            return;
        }
        cnt++;
        System.out.print((a + b) + " ");
        cnt++;
        fibRec(s + 1, n, b, b + a);
    }

    public static void main(String[] args) {
        fibIter(10);
        System.out.print(0 + " " + 1 + " ");
        cnt++;
        fibRec(3, 10, 0, 1);
        System.out.println("\n " + cnt);
    }
}
