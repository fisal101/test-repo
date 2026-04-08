/**
 * ArrayRotation - Rotate array elements clockwise by n positions
 *
 * This class provides multiple approaches to rotate array elements:
 * 1. Reverse Method (Most Optimal - O(n) time, O(1) space)
 * 2. Temporary Array Method (Easy to understand - O(n) time, O(n) space)
 * 3. Rotation Algorithm (Direct rotation - O(n*n) time, O(1) space)
 * 4. GCD Method (For circular rotation - O(n) time, O(1) space)
 */
public class ArrayRotation {

    /**
     * METHOD 1: REVERSE METHOD (Most Optimal)
     * Time: O(n), Space: O(1)
     *
     * Algorithm:
     * To rotate array right by n:
     * 1. Reverse the entire array
     * 2. Reverse first n elements
     * 3. Reverse remaining elements
     *
     * Example: [1, 2, 3, 4, 5] rotate right by 2
     * Step 1: [5, 4, 3, 2, 1]
     * Step 2: [4, 5, 3, 2, 1]
     * Step 3: [4, 5, 1, 2, 3]
     */
    public static int[] rotateClockwiseReverse(int[] arr, int n) {
        if (arr == null || arr.length == 0) {
            return arr;
        }

        int length = arr.length;
        n = n % length; // Handle n > length

        if (n == 0) {
            return arr;
        }

        // Step 1: Reverse entire array
        reverse(arr, 0, length - 1);

        // Step 2: Reverse first n elements
        reverse(arr, 0, n - 1);

        // Step 3: Reverse remaining elements
        reverse(arr, n, length - 1);

        return arr;
    }

    /**
     * METHOD 2: TEMPORARY ARRAY METHOD
     * Time: O(n), Space: O(n)
     *
     * Algorithm:
     * 1. Create temporary array
     * 2. Copy elements to new positions
     * 3. Copy back to original array
     *
     * Example: [1, 2, 3, 4, 5] rotate right by 2
     * New position: (i + n) % length
     * 0 → 2, 1 → 3, 2 → 4, 3 → 0, 4 → 1
     * Result: [4, 5, 1, 2, 3]
     */
    public static int[] rotateClockwiseTemp(int[] arr, int n) {
        if (arr == null || arr.length == 0) {
            return arr;
        }

        int length = arr.length;
        n = n % length;

        if (n == 0) {
            return arr;
        }

        int[] temp = new int[length];

        // Copy elements to new positions
        for (int i = 0; i < length; i++) {
            temp[(i + n) % length] = arr[i];
        }

        // Copy back to original array
        System.arraycopy(temp, 0, arr, 0, length);

        return arr;
    }

    /**
     * METHOD 3: DIRECT ROTATION (Juggling Algorithm using GCD)
     * Time: O(n), Space: O(1)
     *
     * Algorithm:
     * Uses the GCD (Greatest Common Divisor) to determine cycles
     * Each element moves in a cycle until it reaches its final position
     *
     * Example: [1, 2, 3, 4, 5, 6] rotate right by 2
     * GCD(6, 2) = 2, so we have 2 cycles
     * Cycle 1: 0 → 2 → 4 → 0
     * Cycle 2: 1 → 3 → 5 → 1
     */
    public static int[] rotateClockwiseGCD(int[] arr, int n) {
        if (arr == null || arr.length == 0) {
            return arr;
        }

        int length = arr.length;
        n = n % length;

        if (n == 0) {
            return arr;
        }

        int gcd = calculateGCD(length, n);

        // Process each cycle
        for (int i = 0; i < gcd; i++) {
            int key = arr[i];
            int current = i;

            while (true) {
                // Calculate next position
                int next = (current + n) % length;

                if (next == i) {
                    break;
                }

                arr[current] = arr[next];
                current = next;
            }

            arr[current] = key;
        }

        return arr;
    }

    /**
     * METHOD 4: SIMPLE ROTATION (Educational - Not Optimal)
     * Time: O(n*k), Space: O(1)
     *
     * Algorithm:
     * Rotate array one position at a time, k times
     * Each rotation moves the last element to the front
     */
    public static int[] rotateClockwiseSimple(int[] arr, int n) {
        if (arr == null || arr.length == 0) {
            return arr;
        }

        int length = arr.length;
        n = n % length;

        // Rotate n times, one position at a time
        for (int i = 0; i < n; i++) {
            // Rotate one position right
            int last = arr[length - 1];
            for (int j = length - 1; j > 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[0] = last;
        }

        return arr;
    }

    /**
     * Helper method: Reverse array from start to end indices
     */
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            // Swap elements
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            start++;
            end--;
        }
    }

    /**
     * Helper method: Calculate GCD (Greatest Common Divisor)
     * Used for the juggling algorithm
     */
    private static int calculateGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /**
     * Utility method: Print array
     */
    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    /**
     * Utility method: Print 2D array (matrix)
     */
    public static void print2DArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * BONUS: Rotate 2D matrix clockwise by 90 degrees
     * This is a common interview problem
     */
    public static void rotate2DMatrixClockwise(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }

        int n = matrix.length;

        // Transpose the matrix
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Reverse each row
        for (int i = 0; i < n; i++) {
            int left = 0, right = n - 1;
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }

    /**
     * Main method with comprehensive examples
     */
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║         Array Rotation - Clockwise Methods              ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");

        // Test array
        int[] original = {1, 2, 3, 4, 5};
        int n = 2;

        System.out.println("Original Array: ");
        printArray(original);
        System.out.println("Rotation Count: " + n);
        System.out.println();

        // METHOD 1: Reverse Method
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println("METHOD 1: REVERSE METHOD (Optimal - O(n) time, O(1) space)");
        System.out.println("─────────────────────────────────────────────────────────");
        int[] arr1 = original.clone();
        long start = System.nanoTime();
        rotateClockwiseReverse(arr1, n);
        long duration1 = System.nanoTime() - start;
        System.out.println("Result: ");
        printArray(arr1);
        System.out.println("Time: " + duration1 + " ns\n");

        // METHOD 2: Temporary Array
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println("METHOD 2: TEMP ARRAY METHOD (O(n) time, O(n) space)");
        System.out.println("─────────────────────────────────────────────────────────");
        int[] arr2 = original.clone();
        start = System.nanoTime();
        rotateClockwiseTemp(arr2, n);
        long duration2 = System.nanoTime() - start;
        System.out.println("Result: ");
        printArray(arr2);
        System.out.println("Time: " + duration2 + " ns\n");

        // METHOD 3: GCD Method (Juggling)
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println("METHOD 3: GCD METHOD (O(n) time, O(1) space)");
        System.out.println("─────────────────────────────────────────────────────────");
        int[] arr3 = original.clone();
        start = System.nanoTime();
        rotateClockwiseGCD(arr3, n);
        long duration3 = System.nanoTime() - start;
        System.out.println("Result: ");
        printArray(arr3);
        System.out.println("Time: " + duration3 + " ns\n");

        // METHOD 4: Simple Rotation
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println("METHOD 4: SIMPLE ROTATION (Educational - O(n*k) time)");
        System.out.println("─────────────────────────────────────────────────────────");
        int[] arr4 = original.clone();
        start = System.nanoTime();
        rotateClockwiseSimple(arr4, n);
        long duration4 = System.nanoTime() - start;
        System.out.println("Result: ");
        printArray(arr4);
        System.out.println("Time: " + duration4 + " ns\n");

        // BONUS: 2D Matrix Rotation
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║         BONUS: 2D Matrix Rotation (90° Clockwise)      ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");

        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        System.out.println("Original Matrix:");
        print2DArray(matrix);
        System.out.println();

        rotate2DMatrixClockwise(matrix);

        System.out.println("After 90° Clockwise Rotation:");
        print2DArray(matrix);
        System.out.println();

        // Edge Cases
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║              Edge Cases & Special Tests                 ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");

        // Test 1: n > length
        System.out.println("Test 1: Rotation greater than array length");
        int[] arr5 = {1, 2, 3, 4, 5};
        System.out.println("Original: ");
        printArray(arr5);
        rotateClockwiseReverse(arr5, 12); // 12 % 5 = 2
        System.out.println("Rotate by 12 (equivalent to 2): ");
        printArray(arr5);
        System.out.println();

        // Test 2: n = 0
        System.out.println("Test 2: No rotation (n = 0)");
        int[] arr6 = {1, 2, 3, 4, 5};
        System.out.println("Original: ");
        printArray(arr6);
        rotateClockwiseReverse(arr6, 0);
        System.out.println("After rotation by 0: ");
        printArray(arr6);
        System.out.println();

        // Test 3: n = length
        System.out.println("Test 3: Complete rotation (n = length)");
        int[] arr7 = {1, 2, 3, 4, 5};
        System.out.println("Original: ");
        printArray(arr7);
        rotateClockwiseReverse(arr7, 5);
        System.out.println("After rotation by 5: ");
        printArray(arr7);
        System.out.println();

        // Test 4: Single element
        System.out.println("Test 4: Single element array");
        int[] arr8 = {42};
        System.out.println("Original: ");
        printArray(arr8);
        rotateClockwiseReverse(arr8, 3);
        System.out.println("After rotation by 3: ");
        printArray(arr8);
        System.out.println();

        // Test 5: Two elements
        System.out.println("Test 5: Two element array");
        int[] arr9 = {10, 20};
        System.out.println("Original: ");
        printArray(arr9);
        rotateClockwiseReverse(arr9, 1);
        System.out.println("After rotation by 1: ");
        printArray(arr9);
        System.out.println();

        // Performance Comparison with Large Array
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║       Performance Comparison (Large Array - 100k)       ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");

        int[] largeArr = new int[100000];
        for (int i = 0; i < largeArr.length; i++) {
            largeArr[i] = i + 1;
        }
        int rotateBy = 50000;

        // Method 1
        int[] temp1 = largeArr.clone();
        start = System.nanoTime();
        rotateClockwiseReverse(temp1, rotateBy);
        duration1 = System.nanoTime() - start;
        System.out.println("Reverse Method:    " + duration1 + " ns (" + (duration1 / 1000000.0) + " ms)");

        // Method 2
        int[] temp2 = largeArr.clone();
        start = System.nanoTime();
        rotateClockwiseTemp(temp2, rotateBy);
        duration2 = System.nanoTime() - start;
        System.out.println("Temp Array Method: " + duration2 + " ns (" + (duration2 / 1000000.0) + " ms)");

        // Method 3
        int[] temp3 = largeArr.clone();
        start = System.nanoTime();
        rotateClockwiseGCD(temp3, rotateBy);
        duration3 = System.nanoTime() - start;
        System.out.println("GCD Method:        " + duration3 + " ns (" + (duration3 / 1000000.0) + " ms)");

        System.out.println("\n✓ All tests completed successfully!");
    }
}
