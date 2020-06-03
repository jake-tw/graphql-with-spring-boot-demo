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