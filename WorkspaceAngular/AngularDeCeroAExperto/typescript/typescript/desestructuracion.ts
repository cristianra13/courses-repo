(() => 
{
        const avenger = {
            nombre: 'Steve',
            clave: 'Capitan America',
            poder: 'Algo'
        }

        /*
            Desestructuración de objetos

            Desestructuración por argumento
        */

        const extraer = ({nombre, clave, poder}: any) => {
            // const { nombre, clave, poder } = avenger;

            console.log(nombre);
            console.log(clave);
            console.log(poder);
        }

        // extraer(avenger);

        /**
         * desestructuracion de arreglos
         */

         const avengers: string[] = ['Thor', 'Iron man', 'Spiderman'];
         
         //const [thor, ironman, spiderman]  = avengers;
         const [, , spiderman]  = avengers;

         //console.log(thor);
         //console.log(ironman);
         //console.log(spiderman);

        const extraerArray = ([thor, ironman, spiderman]: string[]) => {
            console.log(thor);
            console.log(ironman);
            console.log(spiderman);
        }
         
        extraerArray(avengers);
         
})();
