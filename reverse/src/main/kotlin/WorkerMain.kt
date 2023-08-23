import io.gk.workflow.string.reverse.StringReverseWorker
import io.quarkus.runtime.Quarkus
import io.quarkus.runtime.QuarkusApplication
import io.quarkus.runtime.annotations.QuarkusMain

@QuarkusMain
class WorkerMain : QuarkusApplication {
    override fun run(vararg args: String?): Int {
        //"temporaltest-frontend:7233"
        println("Staring StringReverseWorker -1")
        StringReverseWorker().start()
        println("Worker Started - Waiting.....")
        Quarkus.waitForExit()
        println("Staring StringReverseWorker -  exit now")

        return 0
    }
}
