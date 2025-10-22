public class Question3 {


    public static int findMissing(int[] nums) {
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2;  // formula sum(0..n)
        int actualSum = 0;

        for (int num : nums) {
            actualSum += num;
        }

        return expectedSum - actualSum;  // difference gives missing number
    }

    public static void main(String[] args) {
        int[] nums = {3, 0, 1};
        System.out.println("Missing number: " + findMissing(nums));
    }
}
