(() => 
{

    interface Xmen{
        nombre: string;
        edad: number;
        poder?: string; // es opcional
    }
    
    /**
     * Interfaces Typescript
     */

    const enviarMision = (xMen: Xmen) => {

        console.log(`Enviando a ${xMen.nombre} a la misión`);
        

    }
         

    const wolverine: Xmen = {
        nombre: 'Logan',
        edad: 117
    }

    enviarMision(wolverine);
     
         
})();
