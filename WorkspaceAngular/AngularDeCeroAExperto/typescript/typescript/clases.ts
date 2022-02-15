(() => 
{
    /*class Avenger
    {
        nombre: string;
        equipo: string;
        nombreReal: string;

        puedePelear: boolean;
        peleasganadas: number;

        constructor(nombre:string, equipo:string, nombreReal:string, puedePelear:boolean, peleasGanadas:number)
        {
            this.nombre = nombre;
            this.equipo = equipo;
            this.nombreReal = nombreReal;
            this.puedePelear = puedePelear;
            this.peleasganadas = peleasGanadas;
        }

    }*/

    class Avenger
    {

        constructor( public nombre:string,
                    public equipo:string,
                    public nombreReal?:string,
                    public puedePelear:boolean = true,
                    public peleasganadas:number = 0
            )
            {}

    }

    const antMan = new Avenger('AntMan', 'Avengers');

    console.log(antMan);
    
     
         
})();
