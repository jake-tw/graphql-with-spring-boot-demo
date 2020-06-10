# Variable

GraphQL 與 SQL 一樣都會有 Injection 的問題，可以使用 Variable 進行參數化 Query 來解決，Variable 與其他查詢不同，會獨立一個 Key，直接來看以下範例

```json
{
    "query": " query ($id: ID!) { book(id: $id) { name } } ",
	"variables": {
        "id": 1
    }
}
```

- Query : '$' 表示宣告變數，':'後表示型別，型別與必填與否需要和使用變數的地方一致，可以宣告多個變數，Alias 可加可不加但 query 不可省略

    ```txt
    query alias($bookId: ID!, $authorId: ID!) {
        book(id: $bookId) {
            name
        }
        author(id: $authorId) {
            name
        }
    }
    ```

- Variable : 依照宣告的變數定義的 JSON Object

    ```txt
    {
        "bookId": 2,
      	"authorId": 3
    }
    ```

Variable 也可以是物件型態

```txt
# Mutation
mutation createUser($input: CreateUserInput!) {
    createUser(input: $input) {
        user {
            email
            password
        }
    }
}
```

```txt
# Variables
{
    "input": {
        "email": "test@test.com"
        "password": "Test"
    }
}
```