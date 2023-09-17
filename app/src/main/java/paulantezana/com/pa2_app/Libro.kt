package paulantezana.com.pa2_app

data class Libro (
    val codigo:String,
    val titulo:String,
    val descripcion:String,
    val categoria:String,
    val autor:String = "",
    val fechaPublicacion:String = "",
)