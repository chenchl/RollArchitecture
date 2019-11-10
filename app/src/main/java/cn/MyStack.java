package cn;

import java.util.Stack;

/**
 * created by ccl on 2019/11/6
 **/
public class MyStack {
    private Stack<Integer> stackmain;
    private Stack<Integer> stackmin;

    public MyStack() {
        this.stackmain = new Stack<>();
        this.stackmin = new Stack<>();
    }

    public void push(int i) {
        stackmain.push(i);
        if (stackmin.empty()) {
            stackmin.push(i);
        } else if (i < getMin()) {
            stackmin.push(i);
        }
    }

    public int pop() {
        Integer num = (Integer) stackmain.pop();
        if (num == getMin()) {
            return (Integer) stackmin.pop();
        }
        return num;
    }

    public int getMin() {
        if (stackmin.empty()) {
            return -1;
        }
        return stackmin.peek();
    }
}
