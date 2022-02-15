(() => 
{
    
    /**
     * Tipado retorno funciones
     */

     const sumar = (a: number, b:number): number => a + b;

     const nombre = (): string => 'Hola mundo';

     const obtenerSalario = (): Promise<string> =>
     {
         return new Promise((resolve, reject) => 
         {
            resolve('Cristian');
         });
     }

     obtenerSalario().then(a => console.log( a.toUpperCase() ));
     
         
})();
