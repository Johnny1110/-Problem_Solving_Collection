# invoke 5000 api and store in DB

<br>

---

<br>

pom.xml:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>

<dependency>
    <groupId>org.apache.httpcomponents.client5</groupId>
    <artifactId>httpclient5</artifactId>
    <version>5.1</version>
</dependency>
```

<br>


```java
@Service
public class JobService {

    @Autowired
    private ApiResponseRepository apiResponseRepository;

    private ExecutorService executorService;
    private CloseableHttpAsyncClient httpClient;
    private ConcurrentLinkedQueue<ApiResponse> responseQueue;

    @PostConstruct
    public void init() {
        executorService = Executors.newFixedThreadPool(50); // TODO Test case 1: 50-5000 threads.
        responseQueue = new ConcurrentLinkedQueue<>(); // for store response result.
        httpClient = HttpAsyncClients.createDefault();
        httpClient.start();
    }

    @PreDestroy
    public void cleanup() {
        httpClient.close(CloseMode.GRACEFUL);
        executorService.shutdown();
    }

    public void processRequests(List<String> urls) throws Exception {
        System.out.println("start time: " + new Date().getTime());

        List<Future<ApiResponse>> futures = new ArrayList<>();
        for (String url : urls) {
            futures.add(executorService.submit(() -> fetchAndSaveResponse(url)));
        }
        for (Future<ApiResponse> future : futures) {
            future.get(); // Wait for all tasks to complete
        }

        batchedInsert();

        System.out.println("end time: " + new Date().getTime());
    }

    private ApiResponse fetchAndSaveResponse(String url) throws Exception {
        CompletableFuture<ApiResponse> future = new CompletableFuture<>();
        AsyncRequestProducer requestProducer = AsyncRequestBuilder.get(url).build();
        AsyncResponseConsumer<String> responseConsumer = new StringAsyncEntityProducer();

        httpClient.execute(requestProducer, responseConsumer, new FutureCallback<String>() {
            @Override
            public void completed(String result) {
                ApiResponse response = new ApiResponse();
                response.setData(result);
                saveResponseToQueue(response);
                future.complete(response);
            }

            @Override
            public void failed(Exception ex) {
                future.completeExceptionally(ex);
            }

            @Override
            public void cancelled() {
                future.cancel(true);
            }
        });

        return future.get(); // Wait for the HTTP request to complete
    }

    private void batchedInsert() {
        List<ApiResponse> responses = new ArrayList<>();
        ApiResponse response;
        while ((response = responseQueue.poll()) != null) {
            responses.add(response);
        }
        if (!responsesToInsert.isEmpty()) {
            apiResponseRepository.batchInsert(responses);
        }
    }

    // TODO test-case: 2
    public void saveResponseToQueue(ApiResponse response) {
        // save to thread safe queue.
        responseQueue.add(response);
    }

    // TODO test-case: 3
    public void saveResponseToTable(ApiResponse response) {
        // save to db.
        apiResponseRepository.insert(response);
    }
}
```
