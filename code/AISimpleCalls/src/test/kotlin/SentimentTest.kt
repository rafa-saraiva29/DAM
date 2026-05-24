import dam.AIAssistant
import dam.AIAssistantFactory
import dam.getProperties
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class SentimentTest {
    @Test
    fun testSentiment() = runBlocking {

        val prompts = listOf("The movie was incredible, I loved it!",
            "The movie was awful, I hated it!",
            "The movie was okay, I've seen better.")
        val properties = getProperties()
        val assistant: AIAssistant = AIAssistantFactory.createAssistant(properties)
        for (prompt in prompts) {
            val output = assistant.processInput(prompt)
            println("Answer: $output")
        }
    }
}