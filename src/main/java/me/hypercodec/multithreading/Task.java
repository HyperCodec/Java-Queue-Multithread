package me.hypercodec.multithreading;

/**
 * A Runnable used in worker threads.
 */
public abstract class Task implements Runnable, Cloneable {
    protected WorkerThread parent = null;

    /**
     * The function containing the custom code to be run by the worker thread, similar to a normal Runnable#run() function.
     * @see Runnable#run()
     */
    protected abstract void start();

    /**
     * Assigns the parent thread of the task; Shouldn't need to be manually run at all, as ThreadManager handles that when assigning them to worker threads.
     * @param parent The worker thread that is running the task
     * @see WorkerThread
     * @see ThreadManager#init(int)
     */
    public void assignParent(WorkerThread parent) {
        if(this.parent != null) {
            throw new IllegalArgumentException("Task already has a parent thread");
        }

        this.parent = parent;
    }

    /**
     * Runs start() and sets the parent's working variable.
     * @throws IllegalThreadStateException If assignParent() hasn't been run yet on this task.
     * @see Task#assignParent(WorkerThread)
     */
    @Override
    public void run() {
        if(parent == null) {
            throw new IllegalThreadStateException("No parent has been assigned");
        }

        parent.working = true;

        start();

        parent.working = false;
    }

    /**
     * @return A new instance of the Task object that won't affect the original one when methods change it.
     */
    @Override
    public Task clone() {
        try {
            return (Task) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
