package org.example

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

class App {
    val greeting: String
        get() {
            return "Hello World!"
        }
}

@Component
class Foo {}

@Scope("prototype")
@Service
class Bar {}


class Baz {}

@Configuration
open class Configuration {
    @Bean
    open fun baz(): Baz = Baz()
}

fun main() {
    val context = AnnotationConfigApplicationContext()
    context.scan("org.example")
    context.refresh()

    // Fetching Beans created from "Component"
    val foo1 = context.getBean("foo")
    val foo2 = context.getBean(Foo::class.java)

    // Default Scope is "Singleton" so only one Bean gets created and served to all clients.
    println(foo1)
    println(foo2)

    // Get/Create Beans that have been created with the "Service" Annotation
    context.getBeansWithAnnotation(Service::class.java)
        .keys
        .forEach { println(it) }

    // Get/Create bean that was defined use Java Based Configuration
    val baz = context.getBean("baz")
    println(baz)

    println(App().greeting)
}
