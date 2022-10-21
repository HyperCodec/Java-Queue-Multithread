package me.hypercodec.multithreading;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * A custom type of thread that can run the tasks.
 * @see Thread
 */
public class WorkerThread extends Thread {
    protected boolean working = false;

    /**
     * Instantiates the worker thread with a generated name.
     * @see Thread#Thread(String)
     */
    public WorkerThread() {super("worker " + UUID.randomUUID());}

    /**
     * Instantiates the worker thread with the specified name.
     * @param name The name of the worker thread.
     * @see Thread#Thread()
     */
    public WorkerThread(String name) {super(name);}

    /**
    * @throws UnsupportedOperationException If the function is run.
    */
    public WorkerThread(Runnable target) {throw new UnsupportedOperationException();}

    /**
     * @throws UnsupportedOperationException If the function is run.
     */
    public WorkerThread(@Nullable ThreadGroup group, Runnable target) {throw new UnsupportedOperationException();}

    /**
     * Instantiates the worker thread.
     * @param group The group that the worker thread is in.
     * @param name The name of the worker thread.
     */
    public WorkerThread(@Nullable ThreadGroup group, @NotNull String name) {super(group, name);}

    /**
     * @throws UnsupportedOperationException If the function is run.
     */
    public WorkerThread(Runnable target, String name) {throw new UnsupportedOperationException();}

    /**
     * @throws UnsupportedOperationException If the function is run.
     */
    public WorkerThread(@Nullable ThreadGroup group, Runnable target, @NotNull String name) {throw new UnsupportedOperationException();}

    /**
     * @throws UnsupportedOperationException If the function is run.
     */
    public WorkerThread(@Nullable ThreadGroup group, Runnable target, @NotNull String name, long stackSize) {throw new UnsupportedOperationException();}

    /**
     * @throws UnsupportedOperationException If the function is run.
     */
    public WorkerThread(ThreadGroup group, Runnable target, String name, long stackSize, boolean inheritThreadLocals) {throw new UnsupportedOperationException();}

    /**
     * @return Whether the thread is currently trying to do something.
     */
    public boolean isWorking() {return working;}

    /**
     * Runs a task on the thread.
     * @param task The task that is to be run.
     */
    public void runTask(Task task) {
        if(working) {
            throw new IllegalThreadStateException("Cannot assign task while thread is currently working");
        }

        task.run();
    }
}
