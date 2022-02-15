import express from 'express'
import cors from 'cors'
import { ApolloServer } from 'apollo-server-express'
import { schema } from './graphql/index'
import { ApolloServerPluginLandingPageGraphQLPlayground } from "apollo-server-core"

const app = express()
app.use(cors)

const server = new ApolloServer({
    schema,
    plugins: [
        ApolloServerPluginLandingPageGraphQLPlayground(),
    ],
    introspection: true
})

server.applyMiddleware({app})

app.listen(5300, () => console.log(`http://localhost:5300`))