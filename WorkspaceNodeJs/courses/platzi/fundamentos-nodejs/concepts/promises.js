function hola(nombre, callback) {
  return new Promise (function (resolve, reject) {
    setTimeout(function() {
      console.log(`Hola ${nombre}`);
      resolve(nombre)
    }, 1000)
  });


}

function hablar(nombre) {
  return new Promise((resolve, reject) => {
    setTimeout(function() {
      console.log('Bla Bla Bla Bla...')
      resolve(nombre)
    }, 1000)
  });
}

function adios(nombre, callback) {
  return new Promise( (resolve, reject) => {
    setTimeout(function() {
      console.log(`Adios ${nombre}`);
      // resolve();
      reject('hay un error')
    }, 1000)
  })  
}


// --
console.log('Iniciando el proceso ...');
hola('Cristian')
.then(adios)
.then(hablar)
.then((nombre) => {
  console.log('Terminado el proceso');
})
.catch(error => console.log(error))