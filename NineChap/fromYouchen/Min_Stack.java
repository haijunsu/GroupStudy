import java.util.*;
/**
 * O(n)
 * O(n)
 * 292 ms
 * 
 * Stack
 * 
 * Note(important!):
 *      if Stack is Stack<Integer>, when you peek()
 *      the return object is "Integer" typed.
 *      you CANNOT DO s.peek() == minS.peek() since you're 
 *      comparing two object NOT VALUE!!
 * 
 *      Correct way is to make comparison like
 *      s.peek().intValue() == minS.peek().intValue().
 */
class MinStack {
    Stack<Integer> s = new Stack<Integer>();
    Stack<Integer> minS = new Stack<Integer>();
    
    public void push(int x) {
        if(! minS.isEmpty() && x <= minS.peek())
            minS.push(x);
        s.push(x);
        if(minS.isEmpty())
            minS.push(x);
    }

    public void pop() {
        if(s.peek().intValue() == minS.peek().intValue())
            minS.pop();
        s.pop();
    }

    public int top() {
        return s.peek();
    }

    public int getMin() {
        return minS.peek();
    }
}
