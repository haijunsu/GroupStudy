/**
 *	O(n)
 *	190 ms
 *
 * Array
 * 
 * http://mojijs.com/2014/09/156562/index.html
 * 扫描数组一遍的方法： 
 * nextPos数组中记录三种颜色的下一个位置 
 * 考虑A={0，2，1，1，0}时我们应该如何更新nextPos 
 * 初始：nextPos = {0，0，0} 
 * 第一个颜色是0，所以nextPos[0] = 1。A={0…} 但是由于1和2必须在0的后面，所以nextPos[1], nextPos[2]均为1
 * {1, 1, 1} {0...} 
 * 第二个颜色是2，所以nextPos[2] = 2。A={0, 2…} 1和0均在2的前面，所以不用更新其他值
 * {1, 1, 2} {0, 2...} 
 * 第三个颜色是1，nextPos[1]++，值为2。同时应把2往后挪一位，A为{0, 1, 2…}。nextPos[2] = 3。
 * {1, 2, 3} {0, 1, 2...} 
 * 第四个颜色是1，nextPos[1]++，值为3。2还要再往后挪一位，nextPos[2] = 4。
 * {1, 3, 4} {0, 1, 1, 2...} 
 * 最后一个颜色是0，所以A[1]=0，其他所有颜色均需往后挪一位，A[3] = 1, A[4] = 2。nextPos[1]=4, nextPos[2]=5。
 * {2, 4, 5} {0, 0, 1, 1, 2} 
 * 观察以后可以发现当前颜色color = A[i]，我们需要将所有大于color的颜色均往后挪，同时对应的nextPos++
 * 可以在一句话里写完：
 * for (int c = A[i]; c < 3; c++)
 *       A[nextPos[c]++] = c;
 * 回到A中，i=0的时候，nextPos变成了{1, 1, 1}，没有问题，但是A[0]变成2了。所以我们需要将for循环倒过来，使得A[0]仍为0  
 * for (int c = 2; c >= A[i]; c--)
 *       A[nextPos[c]++] = c;
 * 但是这个循环不会正确运行，因为c=2时将A[0]变成2了，c– < 2，循环结束。所以我们需要用另外一个变量记录下A[i]最初的值，得到最终的程序：
 * void sortColors(int A[], int n) {
 *     int nextPos[3] = {0};
 *     for (int i = 0; i < n; i++)
 *     {
 *         int color = A[i];
 *         for (int c = 2; c >= color; c--)
 *             A[nextPos[c]++] = c;
 *     }
 * }
 */
public class Sort_Colors {
    public void sortColors(int[] A) {
        //#1 O(n) Time 190 ms
        // int r = 0, w = 0, b = 0;
        // for(int item : A){
        //     switch(item){
        //         case 0: 
        //             ++r;
        //             break;
        //         case 1: 
        //             ++w;
        //             break;
        //         case 2:
        //             ++b;
        //             break;
        //     }//switch
        // }//for
        // int index = 0;
        // while(r > 0){
        //     A[index++] = 0;
        //     r--;
        // }
        // while(w > 0){
        //     A[index++] = 1;
        //     w--;
        // }
        // while(b > 0){
        //     A[index++] = 2;
        //     b--;
        // }
          
        //#2 O(n^2) Time, O(1) Space 189 ms
        int r = 0, w = 0, b = 0;//rwb -> 0, 1, 2
        for(int i = 0; i < A.length; i++){
            if(A[i] == 0){
                A[b++] = 2;
                A[w++] = 1;
                A[r++] = 0;
            }
            else if(A[i] == 1){
                A[b++] = 2;
                A[w++] = 1;
                // A[r++] = 0;
            }
            else if(A[i] == 2){
                A[b++] = 2;
                // A[w++] = 1;
                // A[r++] = 0;
            }
        } 
    }
}