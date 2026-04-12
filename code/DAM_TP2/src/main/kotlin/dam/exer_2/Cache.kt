package org.example.dam.exer_2

class Cache<K: Any, V: Any> {
    private val data = mutableMapOf<K, V>()

    fun put(key: K, value: V){
        data[key] = value
    }

    fun get(key: K): V? {
        return data[key]
    }

    fun evict(key: K) {
        data.remove(key)
    }

    fun size(): Int {
        return data.size
    }

    fun getOrPut(key: K, default: () -> V): V {
        val v = get(key)
        if (v != null) {
            return v
        } else {
            put(key, default())
        }
        return default()
    }

    fun transform(key: K, action: (V) -> V): Boolean {
        val v = get(key)
        if (v != null) {
            val updatedV = action(v)
            put(key, updatedV)
            return true
        }
        return false
    }

    fun snapshot(): Map<K, V> {
        val copy = data.toMap()
        return copy
    }

    fun filterValues(predicate: (V) -> Boolean): Map<K, V> {
        val tempMap = mutableMapOf<K, V>()
        for ((k, v) in data) {
            if (predicate(v)) {
                tempMap[k] = v
            }
        }
        return tempMap.toMap()
    }
}

fun main(){
    val wordCache = Cache<String, Int>()

    wordCache.put("kotlin", 1)
    wordCache.put("scala", 1)
    wordCache.put("heskell", 1)

    println("--- Word frequency cache ---")
    println(wordCache.size())
    println("Frequency of kotlin: ${wordCache.get("kotlin")}")
    println("getOrPut kotlin: ${wordCache.getOrPut("kotlin") {1}}")
    println("getOrPut java: ${wordCache.getOrPut("java") {0}}")
    println("Size after getOrPut: ${wordCache.size()}")
    println("Transform kotlin (+1): ${wordCache.transform("kotlin", {it + 1})}")
    println("Transform cobol (+1): ${wordCache.transform("cobol", {it + 1})}")
    println("Snapshot: ${wordCache.snapshot()}")
    println("Words with frequency count greater than zero: ${wordCache.filterValues { it > 0 }}")
    println()

    val idRegistryCache = Cache<Int, String>()

    idRegistryCache.put(1, "Alice")
    idRegistryCache.put(2, "Bob")

    println("--- Id registry cache ---")
    println("Id 1 -> ${idRegistryCache.get(1)}")
    println("Id 2 -> ${idRegistryCache.get(2)}")
    idRegistryCache.evict(1)
    println("After evict id 1, size: ${idRegistryCache.size()}")
    println("Id 1 after evict -> ${idRegistryCache.get(1)}")
}