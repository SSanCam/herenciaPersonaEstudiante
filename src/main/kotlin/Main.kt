open class Persona(val nombre: String, edad: Int = 0) {

    var edad: Int = edad
        private set(value) {
            requireEdad()
            field = value
        }

    init {
        //requireEdad()
        require(nombre.isNotBlank()) { "El nombre no puede ser una cadena vacía." }
    }

    fun cumple() {
        println("Ha cunmplido ${++edad} añitos!!")
    }

    open fun actividad(): String {
        return ("$nombre está realizando una actividad")
    }

    private fun requireEdad() {
        require(edad >= 0) { "La edad no puede ser negativa" }
    }

    override fun toString(): String {
        return "Persona (nombre: $nombre, edad: $edad.)"
    }


}//FIN CLASS PERSONA

class Estudiante(nombre: String, edad: Int, val carrera: String) : Persona(nombre, edad) {
    override fun toString(): String {
        return ("${super.toString().dropLast(2).replace("Persona (", "Estudiante (")}, carrera: $carrera)")
    }

    override fun actividad(): String {
        return "$nombre está estudiando a ratos..."
    }


}//FIN CLASE ESTUDIANTE

fun pedirPersona(): Persona {
    var p: Persona? = null
    do {
        try {
            println("Introduzca el nombre: ")
            val nombre = readln()

            println("Introduzca la edad: ")
            val edad = readln().toInt()

            p = Persona(nombre, edad)
        } catch (e: IllegalArgumentException) {
            println(e.message)
        } catch (e: NumberFormatException) {
            println("La edad debe ser un número entero positivo o 0.")
        }
    } while (p == null)
    return p
}
fun main() {
    val p = Persona("Juan", 55)
    println(p)

    p.cumple().also { println("Ha cumplido añitos") }
    println(p)
    //println("Nombre: $p.nombre, Edad: $p.edad")

    val e = Estudiante("Pablo", 23, "Medicina")
    println(e)
    println(e.actividad())

    e.cumple()
    println(e)

    val nuevaPersona = pedirPersona()
    println (nuevaPersona)
}