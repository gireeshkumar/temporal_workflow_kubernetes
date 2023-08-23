
import io.gk.workflow.string.upper.StringUpperWorker
import io.quarkus.runtime.Quarkus
import io.quarkus.runtime.QuarkusApplication
import io.quarkus.runtime.annotations.QuarkusMain

@QuarkusMain
class WorkerMain : QuarkusApplication {
    override fun run(vararg args: String?): Int {
        //"temporaltest-frontend:7233"
        println("Staring StringUpperWorker -1")
        StringUpperWorker().start()
        println("Worker Started - Waiting.....")
        Quarkus.waitForExit()
        println("Staring StringUpperWorker -  exit now")

        return 0
    }
}
