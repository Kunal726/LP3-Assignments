import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {
    
    public static void main(String[] args) {
        int[] arr = {6, 3, 9, 5, 2, 8, 7};
        int n = 7;
        System.out.println(Arrays.toString(arr));
        deterministicQuickSort(arr, 0, n - 1);
        System.out.println(Arrays.toString(arr));
        int[] arr1 = {6, 3, 9, 5, 2, 8, 7};
        System.out.println(Arrays.toString(arr1));
        randomisedQuickSort(arr1, 0, n - 1);
        System.out.println(Arrays.toString(arr1));
    }

    private static void randomisedQuickSort(int[] arr, int start, int end) {
        if(start < end)
        {

            int randomInd = randomIndexGenerator(start, end);

            System.out.println(randomInd);
            swap(arr, randomInd, end);
            int index = partition(arr, start, end);
            randomisedQuickSort(arr, start, index - 1);
            randomisedQuickSort(arr, index + 1, end);
        }
    }

    private static int randomIndexGenerator(int start, int end) {
        return ThreadLocalRandom.current().nextInt(start, end + 1);
    }

    private static void deterministicQuickSort(int[] arr, int start, int end) {
        if(start < end)
        {
            int index = partition(arr, start, end);
            deterministicQuickSort(arr, start, index - 1);
            deterministicQuickSort(arr, index + 1, end);
        }
    }

    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];

        int i = start - 1;
        for(int j = start; j < end; j++)
        {
            if(arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, end);
        return i + 1;
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
