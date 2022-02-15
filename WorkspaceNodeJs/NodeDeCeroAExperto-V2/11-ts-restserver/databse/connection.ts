import { Sequelize } from "sequelize";

const db = new Sequelize('node_course', 'root', 'Cristianr34l..', {
    host: 'localhost',
    dialect: 'mysql',
    logging: true
});

export default db;