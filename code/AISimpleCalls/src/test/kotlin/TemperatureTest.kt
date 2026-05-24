import dam.AIAssistant
import dam.AIAssistantFactory
import dam.getProperties
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class TemperatureTest {
    @Test
    fun testTemps() = runBlocking {

        val prompt = "How would you survive the zombie apocalypse if you were a human?"

        val temps = listOf(0.1, 0.5, 0.9)

        for (temp in temps) {
            val properties = getProperties()
            properties.setProperty("TEMPERATURE", temp.toString())

            val assistant: AIAssistant = AIAssistantFactory.createAssistant(properties)
            println(properties.getProperty("TEMPERATURE"))
            println("Temperature: $temp")
            val output = assistant.processInput(prompt)
            println("Answer: $output")
        }
    }

}