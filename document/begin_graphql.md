# GraphQL 入門


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



Content-Type : application/json
{"query": "{ book(id:1){ id name author {name}} countBooks countAuthors allBooks {id name} }"}