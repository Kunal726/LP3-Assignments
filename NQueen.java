import java.util.Arrays;

public class NQueen {
    private static int n = 16;
    
    private static boolean solveNQueen(int[][] board, int col)
    {
        if(col >= n)
            return true;
        for(int i = 0; i < n; i++)
        {
            if(isSafe(board, i, col))
            {
                board[i][col] = 1;
                if(solveNQueen(board, col + 1))
                    return true;
                board[i][col] = 0;
            }
        }

        return false;
    }
    private static boolean isSafe(int[][] board, int row, int col) {

        for(int i = 0; i < col; i++)
            if(board[row][i] == 1)
                return false;
        
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
               return false;
        
        for (int i = row, j = col; j >= 0 && i < n; i++, j--)
            if (board[i][j] == 1)
                return false;
        return true;
    }

    private static void printSolution(int[][] board)
    {
        for(int[] x: board)
        {
            System.out.println(Arrays.toString(x));
        }
    }

    public static void solveNQ()
    {
        int[][] board = new int[n][n];
        if(!solveNQueen(board, 0))
        {
            System.out.println("Solution Does Not Exists");
        }
        else {
            printSolution(board);
        }
    }

    public static void main(String[] args) {
        solveNQ();
    }
}
