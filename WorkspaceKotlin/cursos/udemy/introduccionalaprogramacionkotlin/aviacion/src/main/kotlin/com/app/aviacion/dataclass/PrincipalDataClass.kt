fun main() {
    val personaA = PersonaA("Cristian", "545445")
    val personaB = PersonaB("Cristian", "545445")

    // Diferencias entre las clases
    println(personaA.toString()) // Clase normal
    println(personaB.toString()) // Data Class

    val personaC = PersonaA(personaA.nombre, "4857465")
    // Data class nos provee un método para hacer una copia de un objeto
    val personaD = personaB.copy(telefono = "3546846154254")
    println(personaD.toString())

    // Nos permiten desestructurar un objeto
    var (nombre, telefono) = personaB
    println("$nombre $telefono")
}


class PersonaA(var nombre: String, var telefono: String)

data class PersonaB(var nombre: String, var telefono: String) {

    fun getLargoNombre(): Int {
        return nombre.length
    }
}