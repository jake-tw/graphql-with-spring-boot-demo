# Introspection

GraphQL 有著強大的內省機制，讓開發者完成 Schema 的同時就能完成文件，使用範例如下

1. 查詢 schema

	```txt
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
	```

2. 查詢物件 type

	```txt
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
	```

3. 與一般查詢混合使用

	```txt
	{
		search(text: "an") {
			__typename
			... on Human {
				name
			}
			... on Droid {
				name
			}
			... on Starship {
				name
			}
		}
	}
	```

針對命名容易混淆的內容也能用註解標註

```txt
"""
這是多行註解，通常用於描述 Object
"""
# 這個註解不會出現在前端
type Book {
    id: ID!
    name: String

    "這是單行註解，通常用於描述 Field"
    authorId: ID!

    """
    如果 Field 內容過長也可以使用多行註解
    """
    author: Author
}
```

透過 GraphQL Playground 之類的工具，不用自行撰寫 { __schema {...} } 也能取得文件，不過有一點要注意的是，Introspection 同時也對外暴露了完整的資料結構，雖然 Server 端可以決定是否開放 Introspection 給外部使用，但關閉此功能會違反 GraphQL spec 與大多數使用者的期望。