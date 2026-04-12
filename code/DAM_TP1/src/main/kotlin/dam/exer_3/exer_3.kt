package dam.exer_3

fun main() {
    val bounceHeights = generateSequence(100.0) { it * 0.6 }
        .drop(1)
        .takeWhile {it >= 1}
        .take(15)
        .toList()

    bounceHeights.forEach {
        println("%.2f".format(it))
    }
}