import org.testcontainers.spock.Testcontainers

runner {
    // This is Groovy script and we
    // add arbitrary code.
    println "Small Test Runner Config."

    // Include only test classes or test
    // methods with the @Testcontainers annotation
    exclude {
        annotation Testcontainers
    }

}