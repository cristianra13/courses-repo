const express = require('express')
const compression = require("compression")
import cors from 'cors'
import { IResolvers, makeExecutableSchema } from 'graphql-tools'
import { GraphQLSchema } from "graphql"
import { graphqlHTTP } from "express-graphql"

const app = express()

app.use('*', cors)
app.use(compression())

// definición de types
const typeDefs = `
    type Query {
        hola: String!
        holaConNombre(nombre: String!): String!
        holaAlCursoGraphQL: String!
    }
`;

// resolvers
const resolvers : IResolvers = {
    Query : {
        hola(): string { return 'Hola mundo' },
        holaConNombre(__: void, { nombre }): string {
            return `Hola mundo ${nombre}`
        },
        holaAlCursoGraphQL(): string {
            return 'Hola Mundo en el curso de GraphQL'
        }
    }
}

// unión de schema y resolvers
const schema: GraphQLSchema = makeExecutableSchema({
    typeDefs,
    resolvers
})


app.use('/graphql', graphqlHTTP({
    schema,
    graphiql: true
}))

const PORT = 5300

app.listen(PORT, () => console.log(`app listening on  http://localhost:${PORT}/graphql`))

