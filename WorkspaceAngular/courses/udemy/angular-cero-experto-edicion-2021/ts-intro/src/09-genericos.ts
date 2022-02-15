
function queTipoSoy<T>(argumento: T) {
  return argumento;
}

let soyString = queTipoSoy('Hola mundo');
let soyNumero = queTipoSoy(100);
let soyArreglo = queTipoSoy([100, 200, 300]);
let soyExplicito = queTipoSoy<number>(1);