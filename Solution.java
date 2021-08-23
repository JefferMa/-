package com.company;

import java.util.*;

public class Solution {
    public  Solution() {}

    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<Character>();
        Queue<Character> queue = new LinkedList<>();
        int[] record = new int[chars.length];
        for(int i=0; i<chars.length; i++) {
            if(isVowel(chars[i])) {
                stack.add(chars[i]);
                record[i] = 1;
            } else {
                queue.add(chars[i]);
            }
        }
        String result = "";
        for(int j=0; j<chars.length; j++) {
            if(record[j] == 1) {
                result += stack.pop();
            } else {
                result += queue.poll();
            }
        }
        return result;
    }
    private boolean isVowel(char ch) {
        return "aeiouAEIOU".indexOf(ch)>=0;
    }
    //反转字符串
    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while(left < right) {
            char temp = s[left];
            s[left++] = s[right];
            s[right--] = temp;
        }
    }

    //分发糖果，贪心算法，二次遍历
    public int candy(int[] ratings) {
        int[] left = new int[ratings.length];
        // 自左向右遍历
        for(int i=0; i<ratings.length; i++) {
            if(i>0 && ratings[i]>ratings[i-1])
                left[i] = left[i-1] + 1;
            else
                left[i] = 1;
        }
        int result = 0, right = 0;
        // 自右向左遍历
        for(int j=ratings.length-1; j>=0; j--) {
            if(j<ratings.length-1 && ratings[j]>ratings[j+1])
                right++;
            else
                right = 1;
            result += Math.max(right, left[j]);
        }
        return result;
    }
    //分发纸张（升级版小朋友排成一个圈）
    //不去考虑首位点大小比较，图伤脑筋，直接多次二次遍历，反正是一个圈
    public int allocatePaper(int[] ages) {
        int len = ages.length;
        int[] nums = new int[len];
        // 初始化，每人分配一个
        for(int i=0; i<len; i++)
            nums[i] = 1;
        // 判定数量是否变化，决定何时终止二次遍历循环
        boolean flag = true;
        while(flag) {
            flag = false;
            // 二次遍历
            for(int i=1; i<len; i++) {
                if(ages[i] > ages[i-1] && nums[i] <= nums[i-1]) {
                    nums[i] = nums[i-1] + 1;
                    flag = true;
                }
            }
            for(int j=len-2; j>=0; j--) {
                if(ages[j] > ages[j+1] && nums[j] <= nums[j+1]) {
                    nums[j] = nums[j+1] + 1;
                    flag = true;
                }
            }
            // 转圈换位，第一位换至最后一位，其余往前进一位
            int currentAge = ages[0];
            int currentNum = nums[0];
            for(int k=0; k<len-1; k++) {
                ages[k] = ages[k+1];
                nums[k] = nums[k+1];
            }
            ages[len-1] = currentAge;
            nums[len-1] = currentNum;
        }
        int result = 0;
        for(int i=0; i<len; i++)
            result += nums[i];
        return result;
    }
}
