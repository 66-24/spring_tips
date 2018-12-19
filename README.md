# Custom Executors
This example shows how to catch and report on exceptions with a Runnable.
ExecutorService uses ScheduledThreadPoolExecutor which has a `do nothing` implementation
for `ThreadPoolExeccutor.afterExecute()`