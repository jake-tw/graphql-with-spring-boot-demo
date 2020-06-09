# Schema and Resolver

GraphQL API 最重要的兩個元件就是 Schema 和 Resolver

- Schema : 定義 GraphQL API 的資料架構
- Resolver : 負責取得資料的行為，類似 RESTful 中的 Controller

打個比方

- 餐廳有菜單，上面列出所有客戶需要的資訊，包含價格、品項...等，當客戶點完單後，店員接收訂單並準備出餐
- 系統有 Schema，上面列出所有 Client 可取得的資訊，包含價格、品項...等，當 Client 發送 Query 後，Resolver 負責解析 Query 並取得資料

GraphQL schema 是由數個 Type 與 Type 之間的關聯所組成，若使用 GraphQL Voyager(*1) 可以將 Schema 解析為一幅視圖，所有的元素都由 Root 出發，並根據 Type 的定義逐步往下查找。

> 1. https://apis.guru/graphql-voyager/