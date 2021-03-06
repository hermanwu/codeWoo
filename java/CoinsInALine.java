// Input:
// You have n coins in a line
//
// Two player starts to pick up coins.
// Each players can pick either one coin or two coins
// Whoever picks the last coin wins the game, and whoever has no coin left to pick loses the game

// Output:
// whether the first player will win (first player picks the last coin)

// Example:
// n = 4;
// answer: true;

// n = 5;
// answer: false;

public class CoinsInALine {
    public boolean firstWillWin(int n) {
        // for current player, if there is not coins, he lost.
        if (n == 0) {
            return false;
        }

        if (n < 2) {
            return true;
        }

        // dynamic programming
        boolean[] f = new boolean[n + 1];

        // When there is 1 coin, current player wins;
        f[1] = true;
        // When there are 2 coins, current play wins;
        f[2] = true;
        // When there are 3 coins, no matter whether he picked one or two coins, the other play will pick the rest;
        f[3] = false;

        for (int i = 4; i <= n; i++) {
            // whether player picks 1 coin or 2 coins ,
            // if the other player loses ((f[i - 1]) || (f[i - 2]) == false),
            // then current player wins
            if (f[i - 1] == false || f[i - 2] == false) {
                f[i] = true;
            }
        }

        return f[n];
    }

    public boolean firstWillWinII(int n) {
        // write your code here
        boolean []dp = new boolean[n+1];
        boolean []flag = new boolean[n+1];
        return MemorySearch(n, dp, flag);
    }
    boolean MemorySearch(int i, boolean []dp, boolean []flag) {
        if(flag[i] == true) {
            return dp[i];
        }
        if(i == 0) {
            dp[i] = false;
        } else if(i == 1) {
            dp[i] = true;
        } else if(i == 2) {
            dp[i] = true;
        } else {
            dp[i] = !MemorySearch(i-1, dp, flag) || !MemorySearch(i-2, dp, flag);
        }
        flag[i] =true;
        return dp[i];
    }

    public static void main(String[] args) {

        CoinsInALine cc = new CoinsInALine();

        // test case: n = 4 should print true
        System.out.println(cc.firstWillWin(4));
    }
}
