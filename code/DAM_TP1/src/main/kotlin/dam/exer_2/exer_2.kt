package dam.exer_2

fun main() {
    var exit = false
    while (!exit) {

        val operation = getArithmeticOrBool()

        if (operation == 1){
            val value1 = getValue(1)
            val operator = getArithmeticOperator()
            var value2 = getValue(2)

            while (operator == 4 && value2 == 0) {
                println("Não pode dividir por zero!")
                value2 = getValue(2)
            }

            val result = when (operator) {
                1 -> value1 + value2
                2 -> value1 - value2
                3 -> value1 * value2
                4 -> value1 / value2
                5 -> value1.shl(value2)
                6 -> value1.shr(value2)
                else -> 0
            }
            println("O resultado em decimal é: $result")
            println("O resultado em hexadecimal é: ${result.toString(16)}") // <-----------
        } else {
            val value1 = getBoolValue(1)
            println(value1)
            val operator = getBoolOperator()

            var result = false
            if (operator in 1..2) {
                val value2 = getBoolValue(2)
                result = when (operator) {
                    1 -> value1 && value2
                    2 -> value1 || value2
                    else -> false
                }
            } else {
                result = !value1
            }

            println("O resultado é: $result")
        }

        println("Deseja continuar?\n1. Sim\n2. Não")
        val choice = readlnOrNull()?.toInt()
        if (choice == 2){
            exit = true
        }
    }
}

fun getArithmeticOperator(): Int {

    var inputIsCorrect: Boolean
    var operator = 0
    do {
        println("Escolha um operador:\n1. +\n2. -\n3. *\n4. /" +
                "\n5. Bitwise left shift\n6. Bitwise right shift")
        val input = readlnOrNull()

        try {
            inputIsCorrect = true
            operator = input!!.toInt()
        } catch (e: NumberFormatException) {
            println("O valor introduzido tem que ser um número!")
            inputIsCorrect = false
        }

        if (operator !in 1..6){
            println("Valor escolhido não corresponde a uma opção válida (entre 1 e 6)!")
            inputIsCorrect = false
        }

    } while (!inputIsCorrect)

    return operator
}

fun getValue(n: Int): Int {
    var inputIsCorrect: Boolean
    var value = 0
    do {
        println("Introduza o " + n + "º valor:")
        val input = readlnOrNull()

        try {
            inputIsCorrect = true
            value = input!!.toInt()
        } catch (e: NumberFormatException) {
            println("O valor introduzido tem que ser um número!")
            inputIsCorrect = false
        }
    } while (!inputIsCorrect)

    return value
}

fun getBoolValue(n: Int): Boolean {
    var inputIsCorrect: Boolean
    var value = false
    do {
        println("Introduza o " + n + "º valor:")
        val input = readlnOrNull()

        try {
            inputIsCorrect = true
            value = input!!.toBoolean()
        } catch (e: NumberFormatException) {
            println("O valor introduzido tem que ser um número!")
            inputIsCorrect = false
        }
    } while (!inputIsCorrect)

    return value
}

fun getArithmeticOrBool(): Int{

    var inputIsCorrect: Boolean
    var operation = 0
    do {
        println("Pretende realizar uma operação aritmética ou booleana?\n1. Aritmética\n2. Booleana")
        val input = readlnOrNull()

        try {
            inputIsCorrect = true
            operation = input!!.toInt()
        } catch (e: NumberFormatException) {
            println("O valor introduzido tem que ser um número!")
            inputIsCorrect = false
        }

        if (operation !in 1..2){
            println("Valor escolhido não corresponde a uma opção válida (1 ou 2)!")
            inputIsCorrect = false
        }

    } while (!inputIsCorrect)

    return operation
}

fun getBoolOperator(): Int {
    var inputIsCorrect: Boolean
    var operator = 0
    do {
        println("Escolha um operador:\n1. AND\n2. OR\n3. NOT")
        val input = readlnOrNull()

        try {
            inputIsCorrect = true
            operator = input!!.toInt()
        } catch (e: NumberFormatException) {
            println("O valor introduzido tem que ser um número!")
            inputIsCorrect = false
        }

        if (operator !in 1..3){
            println("Valor escolhido não corresponde a uma opção válida (entre 1 e 3)!")
            inputIsCorrect = false
        }

    } while (!inputIsCorrect)

    return operator
}


