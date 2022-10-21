package me.hypercodec.multithreading;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A class that manages all of the workers and tasks.
 */
public class ThreadManager {
    final static Set<WorkerThread> threads;
    final static ThreadQueue queue;

    static {
        threads = new HashSet<>();
        queue = new ThreadQueue();
    }

    /**
     * Initializes the API by starting the worker threads and such.
     * @param threadcount The amount of worker threads to be created.
     */
    public static void init(int threadcount) {
        if(threadcount <= 0) {
            throw new IllegalArgumentException("Thread count must be greater than 0");
        }

        for(int i = 0; i < threadcount; i++) {
            threads.add(new WorkerThread("worker " + (i + 1)));
        }

        new Thread(() -> {
            while(true) {
                if(queue.empty()) continue;

                for(WorkerThread thread : threads) {
                    if(queue.empty()) break;

                    if(!thread.isWorking()) {
                        Task task = queue.pop();
                        task.assignParent(thread);
                        thread.runTask(task);
                    }
                }
            }
        }, "taskassigner").start();
    }

    /**
     * Adds the tasks to the queue to be assigned whenever a worker thread opens up.
     * @param tasks A Collection of the tasks to be added to the queue.
     */
    @Contract(pure = true)
    public static void startTasks(@NotNull Collection<Task> tasks) {
        queue.addAll(tasks);
    }

    /**
     * startTasks() but doesn't require building a collection to use.
     * @param task The task to be added to the queue.
     * @see ThreadManager#startTasks(Collection)
     */
    public static void startTask(Task task) {
        startTasks(List.of(task));
    }
}
