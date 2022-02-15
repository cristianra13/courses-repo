(() => 
{
    
    /**
     * Promesas parte II
     */

     // despues de los dos puntos, le decimos que la promesa va a regresar un valor de tipo Promise que resuelve un number <number>
     const retirarDinero = (montoRetirar:number): Promise<number> =>  {
        
        let dineroActual = 1000;
        
        return new Promise((resolve, reject) => {
            if(montoRetirar > dineroActual)
            {
                reject('No hay suficiente dinero');
            }
            else{
                dineroActual -= montoRetirar;
                resolve(dineroActual);
            }
        });
     }
    
      
    /**
     * Retiramos menos de 1000 y se ejecuta el resolve o en el caso siguiente, el then.
     * de lo contrario al retirar mas de 1000 se ejecuta el reject y se captura el error con el .catch(...)
     */
     //retirarDinero(1500).then(montoActual => console.log(`Me queda ${montoActual}`)).catch( err => console.warn(err));
     retirarDinero(1500).then(montoActual => console.log(`Me queda ${montoActual}`)).catch( console.warn);
     
         
})();
