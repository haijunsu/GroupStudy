package com.navysu.java.basic.algorithm.matrix;

/**
 * Cracking the coding interview 1.7
 * Rotate Matrix
 * Leetcode 48 Rotate Image
 * https://leetcode.com/problems/rotate-image/
 *
 * You are given an n x n 2D matrix representing an image, rotate the image by
 * 90 degrees (clockwise).
 *
 * You have to rotate the image in-place, which means you have to modify the
 * input 2D matrix directly. DO NOT allocate another 2D matrix and do the
 * rotation.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[7,4,1],[8,5,2],[9,6,3]]
 * Example 2:
 *
 *
 * Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 *
 */

public class TotateMatrix {

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        rotate(matrix);
        printMatrix(matrix);
        matrix = new int[][] { { 5, 1, 9, 11 }, { 2, 4, 8, 10 }, { 13, 3, 6, 7 }, { 15, 14, 12, 16 } };
        rotate(matrix);
        printMatrix(matrix);
        matrix = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        rotate2(matrix);
        printMatrix(matrix);
        matrix = new int[][] { { 5, 1, 9, 11 }, { 2, 4, 8, 10 }, { 13, 3, 6, 7 }, { 15, 14, 12, 16 } };
        rotate2(matrix);
        printMatrix(matrix);
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }

    /**
     * Rotates the given 2D matrix in-place clockwise by 90 degrees. The matrix
     * is rotated directly without allocating another 2D matrix.
     *
     * @param matrix the 2D matrix to be rotated.
     */
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = temp;
            }
        }
    }

    /**
     * 这个题就是你可以每次只旋转外圈元素，然后旋转完毕后把内圈元素看成一个新的矩阵，继续对矩阵进行旋转。
     * 在对外圈元素进行旋转时，我们只需要先将最上方一条边的元素与右方元素交换，再与下方元交换，
     * 最后在于左方元素交换，这样最好就旋转成功了。
     *
     * 例如： [1,2,3], [4,5,6], [7,8,9]
     * 对于这个矩阵来说，我们只旋转外圈元素，也就是1，2，3，6，9，8，7，4，1。然后把里面的元素，
     * 也就是5看成一个新的矩阵，继续对外圈元素进行旋转，直到最后矩阵只剩下一个元素。
     *
     * 如何对外圈元素进行旋转呢？
     * 我们对最上面的一条边进行遍历，也就是[1,3)进行遍历，例如一开始的元素是1，将左上角的元素1与右上角的元素3交换，
     * 时左上角元素为3，再将左上角的3与右下角的9交换，此时左上角为9，再将左上角的9与左下角的7进行交换，
     * 这样对于四个角的元素来说，就完成了旋转，然后继续遍历，对2，6，8，4四个元素按照相同的方法进行旋转。
     *
     * @param matrix
     */
    public static void rotate2(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        int n = matrix.length;
        rotateSquare(matrix, 0, n - 1, 0, matrix[0].length - 1);
    }

    private static void rotateSquare(int[][] matrix, int startRow, int endRow, int startCol, int endCol) {
        if (startRow >= endRow || startCol >= endCol) {
            return;
        }
        for (int i = startCol; i < endCol; i++) {
            // 当前i从原点走了几步
            int steps = i - startCol;
            // 左上角与右上角元素交换
            swap(matrix, startRow, i, startRow + steps, endCol);
            // 左上角与右下角元素交换
            swap(matrix, startRow, i, endRow, endCol - steps);
            // 左上角与左下角元素交换
            swap(matrix, startRow, i, endCol - steps, startCol);
        }
        // 然后对内圈元素进行旋转
        rotateSquare(matrix, startRow + 1, endRow - 1, startCol + 1, endCol - 1);
    }

    private static void swap(int[][] matrix, int row1, int col1, int row2, int col2) {
        int temp = matrix[row1][col1];
        matrix[row1][col1] = matrix[row2][col2];
        matrix[row2][col2] = temp;
    }

}
