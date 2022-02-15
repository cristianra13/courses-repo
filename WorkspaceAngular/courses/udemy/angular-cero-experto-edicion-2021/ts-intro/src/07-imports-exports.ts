// Importaciones y exportaciones
import { Producto, calculaISV } from "./06-desestructuracion-funcion";

const carritoCompras: Producto[] =[
  {
    desc: 'Celphone 1',
    precio: 300
  },
  {
    desc: 'Celphone 2',
    precio: 150
  },
];

const [total, isv] = calculaISV(carritoCompras);

console.log(`Total: ${total}`);
console.log(`ISV: ${isv}`);