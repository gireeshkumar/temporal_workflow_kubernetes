import io.gk.workflow.string.kebab.StringKebabWorker
import io.quarkus.runtime.Quarkus
import io.quarkus.runtime.QuarkusApplication
import io.quarkus.runtime.annotations.QuarkusMain

@QuarkusMain
class WorkerMain : QuarkusApplication {
    override fun run(vararg args: String?): Int {
        //"temporaltest-frontend:7233"
        println("Staring StringKebabWorker -1")
        StringKebabWorker().start()
        println("Worker Started - Waiting.....")
        Quarkus.waitForExit()
        println("Staring StringKebabWorker -  exit now")

        return 0
    }
}

fun main() {
    WorkerMain().run()
}
