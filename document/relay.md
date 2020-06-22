# Relay

[Relay](https://relay.dev/) 是一套 React 的 GraphQL 前端框架，後端需要做的是依照 Relay 提供的 Spec 實作，GraphQL Java 也提供了非常基本的 Support，這邊簡單看一下概念就好，之後有機會使用再來深入研究

1. Schema
    - 加上 Connection，Connection 需包含 Edge 和 PageInfo

        ```txt
        type Query {
            allBooks(first: Int, after: String): BookConnection
        }

        type Book {
            id: ID!
            name: String
            author: Author
        }

        type Author {
            id: ID!
            name: String
        }

        type BookConnection {
            edges: [BookEdge]
            pageInfo: PageInfo
        }

        type BookEdge {
            cursor: String
            node: Book
        }

        type PageInfo {
            hasNextPage: Boolean!
            hasPreviousPage: Boolean!
            startCursor: String
            endCursor: String
        }
        ```

2. 回傳 Connection
    - 這邊使用 GraphQL Java 提供的 SimpleListConnection，如果需要自行決定排序策略可以參考其實作方式另外處理

        ```java
        public SimpleListConnection<Book> allBooks() {
            return new SimpleListConnection<>(bookRepository.findAll());
        }
        ```

前端使用 after 某 cursor 的 first 幾筆的方式查詢( 初次查詢不用提供 cursor )，查詢時可以取得物件的 cursor 與分頁資訊，以此判斷是否需要進行下次查詢，cursor 設計並非 Human-Readable，通常會使用 base64 進行編碼，而 Edge 中的 node 則是放置與 cursor 對應的資料