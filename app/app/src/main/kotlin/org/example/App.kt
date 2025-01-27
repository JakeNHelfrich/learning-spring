package org.example

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.stereotype.Component

class App {
    val greeting: String
        get() {
            return "Hello World!"
        }
}

@Component
class Foo {}

fun main() {
    val context = AnnotationConfigApplicationContext()
    context.scan("org.example")
    context.refresh()

    val foo1 = context.getBean(Foo::class.java)
    val foo2 = context.getBean(Foo::class.java)
    println(foo1)
    println(foo2)
    println(App().greeting)
}
