import mysql = require('mysql');


export default class MySQL{


    /**
     * Implementamos patrón singleton
     * evitamos multiples instancias de conexión corriendo
     */
    private static _instance: MySQL;

    connection: mysql.Connection;
    conectado = false;

    constructor(){
        console.log('Clase inicializada');

        /**
         * Establecemos la conexión
         */
        this.connection = mysql.createConnection({
            host: 'localhost',
            user: 'node_user',
            password: 'nodejs',
            database: 'node_db'
        });

        this.conectarDB();
    }

    public static get instance(){
        return this._instance || (this._instance = new this());
    }

    public static ejecutarQuery(query: string, callback: Function){


        this.instance.connection.query(query, (err, results: Object[], fields) => {

            if(err){
                console.log('Error en query');
                console.log(err);
                return callback( err );
            }

            if(results.length === 0){
                callback('El registro solicitado no existe');
            }else{
                callback(null, results);
            }

        });
    }

    private conectarDB(){
    
        this.connection.connect((err: mysql.MysqlError) => {

            if(err){
                console.log(err.message);
                return;
            }
            
            this.conectado = true;

            console.log('BD Online');

        });
    }
    


}