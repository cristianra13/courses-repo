(() => 
{
    
    /* 
        PROMESAS

        Sirven para ejecutar un código sin bloquear el código de la aplicación
    */

    console.log('Inicio');
    

    /**
         Resolve se envia si todo es OK y reject se envia cuando se preseto un error,
         Las pormesas son muy utilizadas cuando se hacen peticiones a servicios web
     */
    const prom1 = new Promise( (resolve, reject) => {
        
        setTimeout(() => {
            reject('Se termino el timeout');
        }, 1000);


    });


    // the se usa cuando todo se ejecuta correctamente y catch para capturar un error
    prom1.then( mensaje => console.log(mensaje))
    .catch(err => console.warn(err))
    
    console.log('Fin');
    
      
         
})();
