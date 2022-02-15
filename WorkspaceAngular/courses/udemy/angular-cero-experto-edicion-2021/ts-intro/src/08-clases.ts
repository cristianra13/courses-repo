class PersonaNormal {

  constructor(public nombre: string, public direccion: string) {}
}

class Heroe extends PersonaNormal {

  // private alterEgo: string;
  // private edad: number;
  // private nombreReal: string;

  // constructor(alterEgo: string, edad: number, nombreReal: string) {
  //   this.alterEgo = alterEgo;
  //   this.edad = edad;
  //   this.nombreReal = nombreReal;
  // }

  constructor(public alterEgo: string,
    public edad: number,
    public nombreReal?: string) {
      super(nombreReal, 'New York, USA');
    }
}

const spiderman = new Heroe('Spiderman', 22);
console.log(spiderman);