package gl51.project.store

import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.http.client.RxHttpClient
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class MemoryProductStorageTest extends Specification {

    ProductStorage store = new MemoryProductStorage()

    def "empty storage returns empty list"() {
        expect:
        store.all() ==  []
    }

    def "adding a product returns the product in the list"() {
        setup:
        def id = store.save(new Product(name: "myproduct"))

        when:
        def all = store.all()

        then:
        all.size() == 1
        all.first().name == 'myproduct'
    }
    def "adding a product will generate a new id"() {
        setup:
        def myProduct = new Product(name: "myProduct")
        def id = store.save(myProduct)
    }

    def "deleting a product will remove it from the list"() {
        setup:
        def myProduct = new Product(name: "myProduct")
        def id = store.save(myProduct)
		
		when:
		store.delete(id)
		then:
        !store.all().contains(myProduct)
    }

    def "modifying a product will change it in the list"() {
        setup:
        def myProduct = new Product(name: "myProduct")
        def id = store.save(myProduct)

        when:
        def myUpdatedProduct = new Product(name: "myUpdatedProduct")
        store.update(id, myUpdatedProduct)

        then:
        myProduct != myUpdatedProduct
    }

    def "getting a product by its id will throw a NotExistingProductException if it doesn't exist"() {
        setup:
        def id = -1
		when:
        store.getByID(id)
		then:
		thrown NotExistingProductException
    }

    def "getting a product by its id will return it if it does exist"() {
        setup:
		def myProduct = new Product(name: "myProduct")
		def id = store.save(myProduct)

        when:
        def gettedProduct = store.getByID(id)

        then:
        myProduct == gettedProduct
    }
		
}
