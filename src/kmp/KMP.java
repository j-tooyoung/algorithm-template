package kmp;

/**
 * 状态机
 * https://zhuanlan.zhihu.com/p/83334559
 */
public class KMP {

    // 暴力匹配（伪码）
    int search(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();
        for (int i = 0; i < N - M; i++) {
            int j;
            for (j = 0; j < M; j++) {
                if (pat.charAt(j) != txt.charAt(i + j)) {
                    break;
                }
            }
            // pat 全都匹配了
            if (j == M) return i;
        }
        // txt 中不存在 pat 子串
        return -1;
    }

    /**
     * dp[j][c] = next
     * 0 <= j < M，代表当前的状态
     * 0 <= c < 256，代表遇到的字符（ASCII 码）
     * 0 <= next <= M，代表下一个状态
     *
     * dp[4]['A'] = 3 表示：
     * 当前是状态 4，如果遇到字符 A，
     * pat 应该转移到状态 3
     *
     * dp[1]['B'] = 2 表示：
     * 当前是状态 1，如果遇到字符 B，
     * pat 应该转移到状态 2
     */
    // 状态机
    private int[][] dp;
    private String pat;

    public KMP(String pat) {
        this.pat = pat;
        // 通过 pat 构建 dp 数组
        // 需要 O(M) 时间
        this.pat = pat;
        int M = pat.length();
        // dp[状态][字符] = 下个状态
        dp = new int[M][256];
        // BASE CASE
        dp[0][pat.charAt(0)] = 1;

        // 影子状态 X 初始为 0
//        前者是在 pat 中匹配 pat[1..end]，状态 X 总是落后状态 j 一个状态，与 j 具有最长的相同前缀。
        int X = 0;
        // 当前状态 j 从 1 开始
        for (int j = 0; j < M; j++) {
            for (int c = 0; c < 256; c++) {
                // 状态匹配，推进
                if (pat.charAt(j) == c) {
                    dp[j][c] = j + 1;
                } else {
                    // 回退
                    dp[j][c] = dp[X][c];
                }
            }
            // 更新影子状态
            X = dp[X][pat.charAt(j)];
        }
    }

    public int search(String txt) {
        // 借助 dp 数组去匹配 txt
        // 需要 O(N) 时间
        int j = 0;
        for (int i = 0; i < txt.length(); i++) {
            // 当前是状态 j，遇到字符 txt[i]，
            // pat 应该转移到哪个状态？
            j = dp[j][txt.charAt(i)];
            // 如果达到终止态，返回匹配开头的索引
            if (j == pat.length()) return i - pat.length() + 1;
        }
        // 没到达终止态，匹配失败
        return -1;
    }


    public static void main(String[] args) {

    }
}
