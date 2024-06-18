# CompletableFuture Example

## Description

This project contains an example of how to use [CompletableFuture](https://download.java.net/java/early_access/valhalla/docs/api/java.base/java/util/concurrent/CompletableFuture.html) in a Spring Boot application.
With [CompletableFuture](https://download.java.net/java/early_access/valhalla/docs/api/java.base/java/util/concurrent/CompletableFuture.html), you can run multiple tasks in parallel and wait for all of them to complete.

For this example, we have a `DelayController` with `/delay` path, that receive a query parameter called `inSeconds`. This parameter is used to simulate a delay in the response to make parallel simulation works.

Every endpoint will call the same `DelayController` controller twice, but the first one will emulate 3 seconds response and the second one will emulate 2 seconds response.
The differences between them are the way that the calls are made. The `async` endpoint will use `CompletableFuture` to make the calls in parallel, while the `sync` call will make the calls in sequence.

Calling `http://localhost:8080/async` will print the following output:
```
Total time: 3 seconds
```
In the other hand, calling `http://localhost:8080/sync` will print the following output:
```
Total time: 5 seconds
```


## Features
- Java 21
- Spring Boot 3.3.0

## How to start

Run the command:

```shell
$ make start
```

You can start the project with native image with the command:
```shell
$ make native-start
```
With Native Image, applications can run faster, use less memory, and be more secure as shown [here](https://github.com/valdemarjuniorr/spring-boot-graalvm-performance-comparation).

## How to use
After starting the application, you can call two endpoints to simulate `Async` and `Sync` calls, respectively:

```shell
$ curl http://localhost:8080/async
```
and 
```shell
$ curl http://localhost:8080/sync
```

## References
 - [Harnessing the Power of Parallel Calls in Java Spring Boot Using CompletableFuture](https://medium.com/@shrivastavamohit628/harnessing-the-power-of-parallel-calls-in-java-spring-boot-using-completablefuture-a678ef22edde)
