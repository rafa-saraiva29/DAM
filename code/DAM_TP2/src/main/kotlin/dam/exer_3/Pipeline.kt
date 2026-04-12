package org.example.dam.exer_3

class Pipeline {
    private val stages = mutableListOf<Pair<String, (List<String>) -> List<String>>>()

    fun addStage(name: String, transform : (List<String>) -> List<String>) {
        stages.add(name to transform)
    }

    fun execute(input: List<String>): List<String> {
        var result = input

        for ((name, transform) in stages) {
            result = transform(result)
        }
        return result
    }

    fun describe(){
        println("Pipeline stages:")
        for (i in stages.indices) {
            println("${i+1}. ${stages[i].first}")
        }
    }

    /*
    fun compose(name1: String, name2: String) {
        val newStage = "$name1 + $name2"
        val transform1 = stages.find { it.first == name1 }?.second
        val transform2 = stages.find { it.first == name2 }?.second
        val composedFunction = transform1.andThen(transform2)
        stages.add(newStage to composedFunction)
    }*/

}

fun buildPipeline(lambda: Pipeline.() -> Unit): Pipeline {
    val pipeline = Pipeline()
    pipeline.lambda()
    return pipeline
}

fun main(){
    val logs = listOf (
        " INFO : server started ",
        " ERROR : disk full ",
        " DEBUG : checking config ",
        " ERROR : out of memory ",
        " INFO : request received ",
        " ERROR : connection timeout "
    )

    val pipeline = buildPipeline {
        addStage("Trim"){
            strings -> strings.map { it.trim() }
        }

        addStage("Filter Errors") {
            strings -> strings.filter { it.contains("ERROR") }
        }

        addStage("Uppercase") {
            strings -> strings.map { it.uppercase() }
        }

        addStage("Add index") {
            strings -> strings.mapIndexed { index, string ->  "${index+1}. ${string}" }
        }
    }

    pipeline.describe()
    println()
    println("Result:")
    pipeline.execute(logs).forEach { println(it) }

}