package com.example.demo


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

import javax.persistence.EntityManager

@SpringBootTest
class Test extends Specification {

    @Autowired
    EntityManager entityManager

    @Autowired
    FooRepository fooRepository

    @Transactional
    def "Spring Boot 2.6.7"() {
        given:
        def foo = new Foo(id: new FooId("foo"))
        def bar = new Bar(id: new BarId(1L))
        foo.bar = bar
        bar.foo = foo

        when:
        fooRepository.save(foo)

        and:
        entityManager.flush()

        then:
        noExceptionThrown()
    }
}
