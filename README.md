# how-to-use-graphql-with-spring-boot
GraphQL Spring Boot tutorial


http://localhost/graphql 
Content-Type : application/graphql

query {
    countBooks
    countAuthors
    allBooks {
      id
      name
      author {
        id
        name
      }
    }
    allAuthors {
      id
      name
    }
}

mutation {
    deleteBook(id:5)
    addBook(name:"test", authorId:1) {
      name
    }
}

{
	__schema {
		queryType {
	      name
	    }
		types {
			name
			description
			fields (includeDeprecated: true) {
				name
				isDeprecated
				description
			    type {
			        name
			        kind
			    }
			}
		}
	}
}

{
	__type(name: "Book") {
		name
		fields {
			name
			description
		    type {
		        name
		        kind
		    }
		}
	}
}

Content-Type : application/json
{"query": "{ book(id:1){ id name author {name}} countBooks countAuthors allBooks {id name} }"}

- [GraphQL 簡介](./document/instro)
- [GraphQL vs RESTful](./document/graphql_vs_restful)
- [GraphQL 優缺點](./document/pros_and_cons)
- [開發工具選擇](./document/developer_tools)
- [GraphQL 入門](./document/begin_graphql)
	- [Schema + 解析器](./document/schema_and_resolver)
	- [Query + Mutation](./document/query_and_mutation) + filed + alias
	- [Subscription](./document/subsciption)
	- [Fragment](./document/fragment) + Inline Fragments
	- [Variable](./document/variable)
	- [Directive](./document/directive)
	- [Introspection](./document/instrospection) + Meta fields
- [Schema 設計](./document/schema_design)
	- [Type](./document/type) + Scalar type + Enumeration type + Lists and Non-Null
	- [Entry point](./document/entry_point)
	- [Interface](./document/interface)
	- [Union type](./document/union_type)
	- [input type](./document/input_type)
- [實作 GraphQL server](./document/graphql_server)
	- [GraphQL Java](./document/graphql_java)
	- [GraphQL Java Tools](./document/graphql_java_tools)
	- [GraphQL SPQR](./document/graphql_spqr)
- [Debug](./document/debug)
- [總結](./document/summary)

<br>

參考資料

> https://graphql.org/  
> https://engineering.fb.com/core-data/graphql-a-data-query-language/  
> https://ithelp.ithome.com.tw/articles/10200678
