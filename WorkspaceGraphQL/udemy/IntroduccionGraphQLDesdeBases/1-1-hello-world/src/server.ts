import { title } from "process"

const express = require('express')
const { graphqlHTTP } = require('express-graphql')
const { buildSchema, GraphQLSchema } = require('graphql')

const app = express()

// GraphQL Schema
const schema = buildSchema(`
    type Query {
        course(id: Int!): Course
        courses(topic: String): [Course]
    }

    type Course {
        id: Int
        title: String
        author: String
        description: String
        topic: String
        url: String
    }
`)

const coursesData = [
    {
        id: 1,
        title: 'GraphQL Course',
        author: 'Any Author',
        description: 'Any description',
        topic: 'NodeJS',
        url: 'http://anyurl.com.co'
    },
    {
        id: 2,
        title: 'Express Course',
        author: 'Any Author',
        description: 'Any description',
        topic: 'NodeJS',
        url: 'http://anyurl.com.co'
    },
    {
        id: 3,
        title: 'Node Course',
        author: 'Any Author',
        description: 'Any description',
        topic: 'NodeJS',
        url: 'http://anyurl.com.co'
    }
]

const getCourse = (args: any) => {
    let id = args.id
    return coursesData.filter(course => {
        return course.id === id
    })[0]
}

const getCourses = (args: any) => {
    if (args.topic)  {
        let topic = args.topic
        return coursesData.filter(courses => courses.topic == topic)
    } else {
        return coursesData
    }
}


// Root resolve
const root = {
    
    course: getCourse,
    courses: getCourses
    
    
    // hola: () => 'Hello World!',
    // holaConNombre(__: void, { nombre = '' }): string {
    //     return 'Hola Mundo en el curos de GraphQL'
    // },
    // holaAlCursoGraphQL(): string {
    //     return 'Hola Mundo en el curso de GraphQL'
    // }
    
}

// Create an endpoint to GraphQL
app.use('/graphql', graphqlHTTP({
    schema,
    rootValue: root,
    graphiql: true
}))

// Port and listen
const PORT = 5300
app.listen(PORT, () => console.log(`listening on port ${PORT} and GrapQL server in http://localhost:5300/graphql`))