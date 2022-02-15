fun main() {
    val listaTareas = arrayListOf(
        Tarea("lavar", EstadoTarea.PENDIENTE),
        Tarea("Barrer", EstadoTarea.PENDIENTE)
    )

    var tareasPendientes  = 0
    // listaTareas.filter { tarea -> tarea.estado.equals("Pendiente") }
    listaTareas.forEach {
        if (it.estado == EstadoTarea.PENDIENTE) tareasPendientes++
    }
    println("tareas pendientes: $tareasPendientes")
}

class Tarea(var nombre: String, var estado: EstadoTarea) {

}

enum class EstadoTarea {
    PENDIENTE,
    EN_PROCESO,
    TERMINADO
}