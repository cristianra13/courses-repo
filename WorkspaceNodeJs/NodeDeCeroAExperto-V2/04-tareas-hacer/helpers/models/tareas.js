const Tarea = require("./tarea");

class Tareas {

    _listadoTareas = {};

    get listadoArr() {
        const listado = [];
        // regresa una rreglo de todas las llaves
        Object.keys(this._listadoTareas).forEach(key => listado.push(this._listadoTareas[key]));

        return listado;
    }

    constructor() {
        this._listadoTareas = {};
    }

    cargarTareasFromArray(tareas = []) {
        tareas.forEach(tarea => this._listadoTareas[tarea.id] = tarea);
        console.log(this._listadoTareas);
    }

    creatTarea(desc = '') {
        const tarea = new Tarea(desc);
        this._listadoTareas[tarea.id] = tarea;
    }


    listadoCompleto() {
        console.log();
        this.listadoArr.forEach((tarea, idx) => {
            const id = `${idx + 1}`.green;
            const { descripcion, completadoEn } = tarea;

            const estado = (completadoEn) ? 'Completado'.green : 'Pendiente'.red;
            console.log(`${id}. ${descripcion} :: ${estado}`);
        });

    }

    listarPendientesCompletadas(completadas = true) {

        this.listadoArr.forEach((tarea, i) => {

            if (tarea.completadoEn === completadas) {
                const id = `${i + 1}`.green;
                const estado = (tarea.completadoEn) ? 'Completado'.green : 'Pendiente'.red;
                console.log(`${id}. ${tarea.descripcion} :: ${estado}`);
            }

        });
    }

    borrarTareas(id = '') {
        if (this._listadoTareas[id]) {
            delete this._listadoTareas[id];
        }
    }

    toggleCompletdas(ids = []) {
        ids.forEach(id => {
            const tarea = this._listadoTareas[id];
            if (!tarea.completadoEn) {
                tarea.completadoEn = true;
            }
        });

        this.listadoArr.forEach(tarea => {
            // si en el arreglo de id está el id de la tarea que llega de BD
            if (!ids.includes(tarea.id)) {
                // si no existe dentro del listado, cambiamos el completadeoEn por false
                this._listadoTareas[tarea.id].completadoEn = false;
            }
        });
    }
}

module.exports = Tareas;