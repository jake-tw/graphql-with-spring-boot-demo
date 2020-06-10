# Query and Mutation and Subscription

GraphQL 有三個 root type，所有的操作都是由 root type 向下延伸

- Query 用來表示查詢，查詢的內容稱為 Fields
    - Query 關鍵字必須是小寫，一般查詢時可省略
    - Fields 可使用下滑線且大小寫敏感，bookName、bookname 與 book_name 都是不同物件
    - '#' 在查詢時使用註解
    - 可以傳入 Arguments : field(arg1: argVal1, arg2: argVal2...)
    - 回傳可預期結構的 JSON
    - example
        - Request

        ```txt
        query {
            # This is a book.
            book(id: 1) {
                name
            }
        }
        ```
        
        - Response

        ```json
        {
            "data": {
                "book": {
                    "name": "Effective Java"
                }
            }
        }
        ```

- Mutation 用來表示增刪改，使用方法與 Query 完全相同，也能有回傳值，Mutation 關鍵字不可省略，跟 RESTful 的 POST, PUT, DELETE 相同，也可以不依規定進行查詢，或只使用 Query 完成 CRUD 的功能，但這會造成混淆，一般不推薦這樣做。

- Subscription 表示長鏈結，Server 可主動推送回傳值到 Client
    - 發送 Request 訂閱 roomId 123 的聊天室

    ```txt
    subscription NewMessages {
        newMessage(roomId: 123) {
            sender
            text
        }
    }
    ```

    - 每次有新消息發送到 roomId 123 的聊天室都會收到 Response

    ```txt
    {
        "data": {
            "newMessage": {
            "sender": "Hagrid",
            "text": "You're a wizard!"
            }
        }
    }
    ```

- 使用 Operation name 與 Alias 進一步調整 Query 內容
    - Operation name : 單純命名無實際功能
    - Alias : 變更回傳物件名稱
    - example
        - Request

        ```txt
        query aliasTest {
            hello: book(id: 1) {
                world: name
            }
        }
        ```

        - Response

        ```json
        {
            "data": {
                "hello": {
                    "world": "Effective Java"
                }
            }
        }
        ```

接下來看看實際發送 POST /graphql(*1) 需要哪些東西
- Header : Content-Type:application/json / Content-Type:application/graphql
- Request Body
    - application/json : 較常規的做法
        - operationName : 別名可省略
        - variables : 查詢參數，若未使用可省略，會在後面的章節詳細介紹
        - query : 查詢語句，不管是哪種操作都直接放這

        ```json
        {
            "operationName": "aliasTest",
            "variables": {},
            "query": "query aliasTest { book(id: 1) { name } }"
        }
        ```

        ```json
        {
            "query":"mutation { deleteBook(id: 5) } "
        }
        ```
    - application/graphql : 部分 server 不接受此類型的 Header
        - 支援 graphql header 的情況，直接將 Query 放在 Request body 中即可

        ```txt
        query aliasTest {
            hello: book(id: 1) {
                world: name
            }
        }
        ```

> 1. 也可以使用 GET /graphql?query={me{name}} 的形式發送，但不推薦