package org.example.dam.exer_1

import org.example.dam.exer_1.Event.Purchase

sealed class Event {
    class Login(val username: String, val timestamp: Long) : Event(){
        override fun toString(): String {
            return "Login(username='$username', timestamp=$timestamp)"
        }
    }
    class Purchase(val username: String, val amount: Double, val timestamp: Long) : Event(){
        override fun toString(): String {
            return "Purchase(username='$username', amount= $amount, timestamp=$timestamp)"
        }
    }
    class Logout(val username: String, val timestamp: Long) : Event(){
        override fun toString(): String {
            return "Logout(username='$username', timestamp=$timestamp)"
        }
    }
}
fun List<Event>.filterByUser(username: String): List<Event> {
    return this.filter { event ->
        when (event) {
            is Event.Login -> event.username == username
            is Event.Purchase -> event.username == username
            is Event.Logout -> event.username == username
        }
    }
}

fun List<Event>.totalSpent(username: String): Double {
    val filteredEvents = this.filterByUser(username)
    val purchases = filteredEvents.filterIsInstance<Purchase>()
    return purchases.sumOf{it.amount}
}

fun processEvents(events: List<Event>, handler: (Event) -> Unit){
    events.forEach(handler)
}

fun main(){
    val events = listOf (
        Event.Login("alice", 1_000),
        Event.Purchase("alice", 49.99, 1_100),
        Event.Purchase("bob", 19.99, 1_200),
        Event.Login("bob", 1_050),
        Event.Purchase("alice", 15.00, 1_300),
        Event.Logout("alice", 1_400),
        Event.Logout("bob", 1_500 )
    )

    processEvents(events) { event ->
        when (event) {
            is Event.Login -> println("[LOGIN]     ${event.username} logged in at t=${event.timestamp}")
            is Event.Purchase -> println("[PURCHASE]  ${event.username} spent $${event.amount} at t=${event.timestamp}")
            is Event.Logout -> println("[LOGOUT]    ${event.username} logged out at t=${event.timestamp}")
        }
    }
    println()
    println("Total spent by alice: $${events.totalSpent("alice")}")
    println("Total spent by bob: $${events.totalSpent("bob")}")
    println()

    val aliceEvents = events.filterByUser("alice")
    println("Events for Alice:")
    aliceEvents.forEach { println(it) }

}

