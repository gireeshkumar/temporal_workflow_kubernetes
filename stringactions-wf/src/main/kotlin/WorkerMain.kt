import io.gk.workflow.string.actionswf.StringWorkflowWorker
import io.quarkus.runtime.Quarkus
import io.quarkus.runtime.QuarkusApplication
import io.quarkus.runtime.annotations.QuarkusMain

@QuarkusMain
class WorkerMain : QuarkusApplication {
    override fun run(vararg args: String?): Int {
        //"temporaltest-frontend:7233"
        println("Staring String Actions parent workflow -1")
        StringWorkflowWorker().start()
        println("Worker Started - Waiting.....")
        Quarkus.waitForExit()
        println("Staring String Actions parent workflow  -  exit now")

        return 0
    }
}
