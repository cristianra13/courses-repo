class Usuarios {

    constructor() {
        // Arrelo de personas conectadas al chat
        this.personas = [];
    }

    agregarPersona(id, nombre, sala) {
        let persona = { id, nombre, sala }

        this.personas.push(persona);

        return this.personas;
    }

    getPeronaById(id) {

        let persona = this.personas.filter(persona => persona.id === id)[0]; // devolvemos la primera posición del arreglo que retorna

        return persona;
    }

    getPersonas() {
        return this.personas;
    }

    /**
     * Obtenemos las personas según la sala
     * @param {*} sala 
     */
    getPersonasBySala(sala) {
        let personasEnSala = this.personas.filter(persona => persona.sala === sala);
        return personasEnSala;
    }


    /**
     * Personas que salen del chat
     */
    eliminarPersona(id) {

        // para conocer5 quien es la persona que sale del chat antes de perder la relacion
        let personaBorrada = this.getPeronaById(id);

        // reemplazamos el arreglo actual de las personas con el nuevo 
        this.personas = this.personas.filter(persona => persona.id != id);

        return personaBorrada;
    }

}

module.exports = {
    Usuarios
}