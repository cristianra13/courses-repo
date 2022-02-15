const mongoose = require('mongoose');

const dbConnection = async() => {

    try {

        // con await esperamos que se haga la conexión
        await mongoose.connect(process.env.MONGO_CONN, {
            useNewUrlParser: true,
            useUnifiedTopology: true,
            useCreateIndex: true,
            useFindAndModify: false
        });

        console.log('DB online!');

    } catch (error) {
        throw new Error('Error connecting to database', error);
    }

}


module.exports = {
    dbConnection
}