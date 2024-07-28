package org.rancore.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    static int [] nums = new int[]{2,7,11,15};
    static int target = 9;

    public static void main(String[] args) {
        int[] result = twoSumHashMap(nums, target);
        System.out.println(Arrays.toString(result));
    }

    public static int[] twoSumHashMap(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>(nums.length);
        int[] result = new int[2];

        for (int i = 0; i<nums.length; i++) {
            int diff = target - nums[i];
            if (seen.containsKey(diff)) {
                result[0] = seen.get(diff);
                result[1] = i;
                return result;
            } else {
                seen.put(nums[i], i);
            }
        }
        return result;
    }

}
