(function()
{
 
    /*
        Parametro opcionales y obligatorios
        primero van los argumentos obligatorios, luego los opcionales y por ultimo los que son por defecto
        con el signo ? se indica  que el argumento es opcional
    */
    function activar(quien: string, 
                    momento?: string,
                    objeto: string = 'objeto')
    {
        if(momento)
        {
            console.log(`${quien} en la ${momento} activo ${objeto}`);
        }
        else
        {
            console.log(`${quien} activo el ${objeto}`);
        }

    }

    activar('Cristian', 'carro', 'tarde');

    
})();
