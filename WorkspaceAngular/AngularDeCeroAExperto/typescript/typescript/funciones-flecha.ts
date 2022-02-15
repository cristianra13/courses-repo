(function()
{
 
    // funciones de flecha

    let miFuncion = function(a: string)
    {
        return a;
    }
    
    // esta funcion restorna a
    const miFuncionF = (a: string) => a.toUpperCase();



    const sumar = function(a:number, b:number)
    {
        return a + b;
    }

    const sumarF = (a:number, b:number) => a+b;


    const hulk = {
        nombre: 'Hulk',
        smash(){
            
            setTimeout( () => {
                console.log(`${this.nombre} smash!!!`);
            }, 1000);
        }
    }

    hulk.smash();

})();
