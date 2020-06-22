# DataLoader

當業務增長到一定程度的時候，N + 1 Query 就會開始拖慢效能，常見的效能調校方案是使用 [DataLoader](https://github.com/graphql/dataloader)，需要注意的是，過早添加 DataLoader 只是徒增程式複雜度，不見得能獲得很好的收益，使用上需要經過審慎的評估，這邊只簡單介紹一下 DataLoader 的概念

1. 原本取值的地方改用 DataLoader 載入查詢 key

    ```java
    public DataFetcher<CompletableFuture<Author>> author() {
        return env -> {
            Book book = env.getSource();
            Integer id = book.getAuthor().getId();
            DataLoader<Integer, Author> dataLoader = env.getDataLoader(AuthorLoader.class.getSimpleName());
            return dataLoader.load(id);
        };
    }
    ```

2. 當 key 收集完成後一次查詢

    ```java
    @Override
    public CompletionStage<List<Author>> load(List<Integer> ids) {
        System.out.println("DataLoader execute ids: " + ids);
        return CompletableFuture.supplyAsync(() -> authorRepository.findByIds(ids));
    }
    ```

可以在這邊添加一個 print 觀察查詢結果，當同時傳入多個重複 id 時，DataLoader 會做一次過濾，減少重複查詢，並且會將結果進行 cache，在第二次查詢時，若是查詢過的 id 就不會再重複查詢