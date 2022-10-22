# Java Queue-Based Multithreading API
 A simple queue-based multithreading api that can be used to 

## Importing
### Maven
<details>
<summary>Repository</summary>

```xml
<repository>
    <id>Java-Queue-Multithread-mvn-repo</id>
    <url>https://github.com/HyperCodec/Java-Queue-Multithread/raw/mvn-repo/</url>
    <snapshots>
        <enabled>true</enabled>
        <updatePolicy>always</updatePolicy>
    </snapshots>
</repository>
```
</details>

<details>
<summary>Dependency</summary>

```xml
<dependency>
    <groupId>me.hypercodec</groupId>
    <artifactId>java-queue-multithread</artifactId>
    <version>1.0</version> <!-- replace with latest release version (Intellij should prompt you) -->
</dependency>
```
</details>

### Gradle
<details>
<summary>Repository</summary>

```gradle
repositories: {
   maven {
      url "https://github.com/HyperCodec/Java-Queue-Multithread/raw/mvn-repo/"
   }
   
   // ...
}
```
</details>

<details>
<summary>Dependency</summary>

```gradle
dependencies: {
   implementation("me.hypercodec:java-queue-multithread:1.0") // replace with latest release version (Intellij should prompt you)
   
   // ...
}
```
</details>

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

#### Extending Via Instantiation
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
