package customer.test_xb;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.checkerframework.checker.units.qual.A;

import java.util.Arrays;
import customer.test_xb.Service.test;

public class leecode {
    // public static void main(String[] args) {

    // }
    // 首字母转大写
    // public static String capitalizeTitle(String title) {
    // String[] titles = title.split(" ");
    // String result = "";
    // for (String a : titles) {
    // if (a.length() > 2) {
    // a = a.substring(0, 1).toUpperCase() + a.substring(1).toLowerCase();
    // } else {
    // a = a.toLowerCase();
    // }

    // if (result == "") {
    // result = a;
    // } else {
    // result = result + " " + a;
    // }

    // }

    // return result;
    // }

    //
    // public int[] executeInstructions(int n, int[] startPos, String s) {
    // String[] instructions = s.split(s);
    // int i = 0;
    // int[] result = new int[instructions.length];
    // for (String a : instructions) {
    // int count = 0;
    // if (a.equals("U")) {
    // startPos[1] = startPos[1] + 1;

    // if (startPos[1] > n - 1) {
    // result[i] = count;
    // break;
    // } else {
    // count++;
    // }
    // } else if (a.equals("D")) {
    // startPos[1] = startPos[1] - 1;
    // if (startPos[1] < 0) {
    // result[i] = count;
    // break;
    // } else {
    // count++;
    // }

    // } else if (a.equals("L")) {
    // startPos[0] = startPos[0] - 1;
    // if (startPos[0] > 0) {
    // result[i] = count;
    // break;
    // } else {
    // count++;
    // }
    // } else if (a.equals("R") && startPos[0] < n - 1) {
    // startPos[0] = startPos[0] + 1;
    // if (startPos[0] > n - 1) {
    // result[i] = count;
    // break;
    // } else {
    // count++;
    // }
    // }

    // result[i] = count;
    // }

    // return result;

    // }

    // 交替合并字符串
    // public static String mergeAlternately(String word1, String word2) {
    // String result = "";
    // String[] word1s = word1.split("");
    // String[] word2s = word2.split("");
    // if (word1s.length >= word2s.length) {
    // for (int i = 0; i < word1s.length; i++) {
    // result = result + word1s[i] + word2s[i];
    // }
    // for (int i = word2s.length; i < word1s.length; i++) {
    // result = result + word1s[i];
    // }
    // } else {
    // for (int i = 0; i < word2s.length; i++) {
    // result = result + word1s[i] + word2s[i];
    // }
    // for (int i = word1s.length; i < word2s.length; i++) {
    // result = result + word2s[i];
    // }

    // }

    // return result;

    // }

    // public char findTheDifference(String s, String t) {
    // String[] ss = s.split("");
    // String[] tt = t.split("");
    // for (String a : ss) {
    // if (t.indexOf(a) == -1) {
    // return a.charAt(0);
    // }
    // }
    // return tt[tt.length - 1].charAt(0);
    // }

    // 字符串第一个匹配下标
    // public int strStr(String haystack, String needle) {
    // String[] haystacks = haystack.split("");
    // String[] needles = needle.split("");
    // for(int i = 0; i < haystacks.length; i++){
    // if(a.equals(needles[0])){
    // return haystack.indexOf(a);
    // }
    // }
    // return 0;

    // }

    // 奇数个数

    // public int countOdds(int low, int high) {

    // return low % 2 == 0 && high % 2 == 0 ? (high - low) / 2 : (high - low) / 2 +
    // 1;
    // }

    // 去掉最低最高的均值
    // public double average(int[] salary) {
    // int max = salary[0];
    // int min = salary[0];
    // double sum = 0;
    // for(int i = 0 ; i < salary.length ; i++){
    // if (max < salary[i]) {
    // max = salary[i];
    // }
    // if (min > salary[i]) {
    // min = salary[i];
    // }
    // sum = sum + salary[i];
    // }

    // return sum/(salary.length - 2);
    // }

    // 柠檬水找零 5 10 20
    // public boolean lemonadeChange(int[] bills) {
    // List<Integer> list = new ArrayList<>();
    // for (int i = 0; i < bills.length; i++) {
    // if (bills[i] == 5) {
    // list.add(5);
    // } else if (bills[i] == 10) {
    // if (list.contains(5)) {
    // list.remove(list.indexOf(5));
    // list.add(10);
    // } else {
    // return false;
    // }
    // } else if (bills[i] == 20) {
    // if (list.contains(10) && list.contains(5)) {
    // list.remove(list.indexOf(10));
    // list.remove(list.indexOf(5));
    // } else if (list.contains(5)) {
    // list.remove(list.indexOf(5));
    // if (list.contains(5)) {
    // list.remove(list.indexOf(5));
    // if (list.contains(5)) {
    // list.remove(list.indexOf(5));
    // } else {
    // return false;
    // }
    // } else {
    // return false;
    // }
    // } else {
    // return false;
    // }
    // }
    // }
    // return true;
    // }

    // public int minimumVisitedCells(int[][] grid) {

    // }
    // 等差数列
    // public boolean canMakeArithmeticProgression(int[] arr) {
    // arr = arr.stream().sorted();
    // if (arr.length < 2) {
    // return true; // 数组长度小于2时，认为是等差数列
    // }
    // int diff = arr[1] - arr[0];
    // for (int i = 2; i < arr.length; i++) {
    // if (arr[i] - arr[i-1] != diff) {
    // return false;
    // }
    // }
    // return true;

    // }

    // 测试时间
    // public static void testTime() {
    // Date date = new Date();
    // SimpleDateFormat data_time = new SimpleDateFormat("yyyyMMddHHmmss");
    // data_time.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
    // String datetimesString = data_time.format(date);
    // System.out.println(datetimesString + "xxxxxxxxxxxxxxx");
    // }

    // public int strStr(String haystack, String needle) {
    // String[] haystacks = haystack.split("");
    // String[] needles = needle.split("");
    // if (needles.length > haystacks.length) {
    // return -1;

    // }

    // for (int i = 0; i < haystacks.length; i++) {
    // if (haystacks[i].equals(needles[0])) {
    // if (haystack.substring(i, i + needle.length()).equals(needle)) {
    // return i;
    // }
    // }
    // }
    // return -1;
    // }

    // public static void moveZeroes(int[] nums) {
    // // int count = 0;
    // // for(int num : nums){
    // // if (num == 0) {
    // // count++;
    // // }
    // // }
    // int j = 0;
    // for (int i = 0; i < nums.length; i++) {
    // if (nums[i] != 0) {
    // nums[++j] = nums[i];
    // }
    // }

    // for (int i = j; i < nums.length; i++) {
    // nums[i] = 0;
    // }
    // }

    // public int[] plusOne(int[] digits) {
    // int n = digits.length;
    // for (int i = n - 1; i > 0; i--) {
    // if (i == 0 && digits[i] == 9) {
    // int[] re = new int[n+1];
    // for (int j = 0; j <= n; j++) {
    // if (j == 0) {
    // re[j] = 1;
    // } else {
    // re[j] = 0;
    // }

    // }
    // return re;
    // }
    // if (digits[i] != 9) {
    // digits[i]++;
    // return digits;
    // }

    // }
    // return null;
    // }

    // public int arraySign(int[] nums) {
    // int count = 0;
    // for(int i = 0 ; i< nums.length ;i++){
    // if (nums[i] == 0) {
    // return 0;
    // }
    // else if(nums[i] == -1){
    // count ++;
    // }
    // }
    // return count%2==0 ? 1 : -1;
    // }

    // public boolean canMakeArithmeticProgression(int[] arr) {

    // Arrays.sort(arr);

    // if (arr.length < 2) {
    // return true; // 数组长度小于2时，认为是等差数列
    // }
    // int diff = arr[1] - arr[0];
    // int diff2 = arr[0] - arr[1];
    // for (int i = 2; i < arr.length; i++) {
    // if (arr[i] - arr[i - 1] != diff || arr[i] - arr[i - 1] != diff2) {
    // return false;
    // }
    // }
    // return true;
    // }

    // // 单调
    // public static boolean isMonotonic(int[] nums) {
    // boolean isIncreasing = false;
    // boolean isDecreasing = false;
    // if (nums.length <= 2) {
    // return true;
    // } else {
    // for (int i = 1; i < nums.length; i++) {
    // if (isIncreasing) {
    // if (nums[i] < nums[i - 1]) {
    // return false;
    // }
    // continue;
    // }
    // if (isDecreasing) {
    // if (nums[i] > nums[i - 1]) {
    // return false;
    // }
    // continue;
    // }

    // if (nums[i] > nums[i - 1]) {
    // isIncreasing = true;
    // }
    // if (nums[i] < nums[i - 1]) {
    // isDecreasing = true;
    // }
    // }
    // }
    // return true;
    // }

    // public String toLowerCase(String s) {
    // return s.toLowerCase();
    // }

    // public int longestConsecutive(int[] nums) {

    // return 0;

    // }

    // public static void main(String[] args) {
    // // leecode leecode = new leecode();
    // // int[] bills = { 5, 5, 5, 10, 5, 5, 10, 20, 20, 20 };
    // // System.out.println(leecode.lemonadeChange(bills));
    // // testTime();
    // int[] test = { 1, 2, 2, 3 };
    // boolean bool1 = isMonotonic(test);
    // System.out.println(bool1);
    // }

    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        // Placeholder return value
        int[] cap2 = new int[capacity.length];
        int index = 0;
        for (int i : capacity) {
            for (int j : rocks) {
                if (i >= j) {
                    i = i - j;
                }
                cap2[index] = i;
                index++;
            }
        }
        int num = 0;
        cap2 = Arrays.stream(cap2).sorted().toArray();
        for (int i : cap2) {
            if (additionalRocks - i > 0) {
                additionalRocks -= i;
                num++;
            } else {
                return num;
            }
        }

        return capacity.length;
    }

    public int fillCups(int[] amount) {
        Arrays.sort(amount);
        if (amount[2] > amount[0] + amount[1]) {
            return amount[2];
        } else {
            return (amount[0] + amount[1] + amount[2]) / 2 + 1;

        }
    }

}
