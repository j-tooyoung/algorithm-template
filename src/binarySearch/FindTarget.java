package binarySearch;

import java.util.Arrays;

/**
 * 二分
 */
public class FindTarget {

    public int search(int[] nums, int target) {
        Arrays.sort(nums);
        int lo = 0, hi = Integer.MAX_VALUE;
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            if (check(mid, nums, target) ){
                lo = mid + 1;
            }else {
                hi = mid;
            }
        }
        return lo;
    }

    boolean check(int val, int[] nums, int target) {
        if (nums[val] < target) {
            return true;
        }
        return false;
    }
    public static void main(String[] args) {

    }
}
