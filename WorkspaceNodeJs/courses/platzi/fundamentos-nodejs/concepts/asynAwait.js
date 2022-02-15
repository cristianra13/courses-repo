async function hola(nombre, callback) {
  return new Promise (function (resolve, reject) {
    setTimeout(function() {
      console.log(`Hola ${nombre}`);
      resolve(nombre)
    }, 1000)
  });


}

async function hablar(nombre) {
  return new Promise((resolve, reject) => {
    setTimeout(function() {
      console.log('Bla Bla Bla Bla...')
      resolve(nombre)
    }, 1000)
  });
}

async function adios(nombre, callback) {
  return new Promise( (resolve, reject) => {
    setTimeout(function() {
      console.log(`Adios ${nombre}`);
      resolve();
      // reject('hay un error')
    }, 1000)
  })  
}

async function main() {
  let nombre = await hola('Cristian')
  await hablar()
  await adios(nombre)
  console.log('Terminamos el proceso...')
}


console.log('Empezamos el proceso...')

main()

