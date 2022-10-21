package me.hypercodec.multithreading;

import java.util.Stack;

/**
 * A Stack but it pops to the left.
 * @see Stack
 */
public class ThreadQueue extends Stack<Task> {
    /**
     * Pops like a Stack, but to the left rather than to the right to ensure that the older tasks have priority.
     * @return The popped value.
     * @see Stack#pop()
     */
    @Override
    public synchronized Task pop() {
        Task task = this.firstElement();

        super.removeElementAt(0);

        return task;
    }
}
