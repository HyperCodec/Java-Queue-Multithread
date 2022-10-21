# Java Queue-Based Multithreading API
 A simple queue-based multithreading api that can be used to 
 
## How to Use

### Initialization
Run `ThreadManager.init(amount of threads)` in the beginning of your code (such as the main() function). This gets everything set up for 

### Creating Tasks
A Task object is similar to a Runnable, but you use start() rather than run(). 

#### Extending Via Separate Class
You can extend the class by doing this:
<details>
<summary>Example Class</summary>

```java
public class ExampleTask extends Task {
    @Override
    public void start() {
        // code here   
    }
}
```
</details>

#### Extending During Instantiation
If you don't need an entire class for it, you can just do it like this: 

<details>
<summary>Example Instantiation</summary>

```java
Task task = new Task() {
    @Override
    public void start() {
        // code here
    }
};
```
</details>

### Starting Tasks
A task can be started by simply doing `ThreadManager.startTask(task)` or `ThreadManager.startTasks(collection of tasks)` as shown below:

<details>
<summary>Task Starting Examples</summary>

```java 
// Somewhere during startup
ThreadManager.init();

// --------------------------------

// Using ExampleTask from earlier and startTask()
Task task = new ExampleTask();

ThreadManager.startTask(task);
    
// --------------------------------

// Using the other method of Task extension and a List of Tasks
List<Task> tasks = new ArrayList<>();

for(int i = 0; i < 1000; i++) {
    tasks.add(new Task() {
        @Override
        public void start() {
            for(int i2 = 0; i2 < 100; i2++) {
                System.out.println(String.format("%s from task %s in thread %s", i2, i, this.parent.getName()));
            }
        }
    });
}

ThreadManager.startTasks(tasks);
```
</details>

If recursively adding the same task for some reason, use task.clone() when adding or else it will error.