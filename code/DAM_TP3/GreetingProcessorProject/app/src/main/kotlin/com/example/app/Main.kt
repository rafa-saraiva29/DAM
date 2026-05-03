package com.example.app


fun main() {
    val myClass = MyClass()
    val wrappedClass = MyClassWrapper(myClass)

    wrappedClass.sayHello()
    wrappedClass.compute()
}
