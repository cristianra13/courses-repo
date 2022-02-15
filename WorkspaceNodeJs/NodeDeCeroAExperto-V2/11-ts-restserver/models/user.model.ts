import { DataTypes } from "sequelize";
import db from "../databse/connection";

const Usuario  = db.define('Users', {

    nombre: {
        type: DataTypes.STRING
    },
    email: {
        type: DataTypes.STRING
    },
    estado: {
        type: DataTypes.BOOLEAN
    }
});

export default Usuario;