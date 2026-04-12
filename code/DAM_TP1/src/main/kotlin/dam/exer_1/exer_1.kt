package dam.exer_1

fun main(){
    // IntArray
    val arr1 = IntArray(50) { i -> ((i+1) * (i+1)) }
    println("IntArray constructor:\n" + arr1.joinToString())

    // range and map()
    val arr2 = (1..50).map { it * it }
    println("range and map():\n" + arr2.joinToString())

    // Array
    val arr3 = Array(50) { (it+1) * (it+1) }
    println("Array constructor:\n" + arr3.joinToString())
}