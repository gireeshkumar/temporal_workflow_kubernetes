package io.gk.workflow.string.reverse

import io.gk.workflow.string.intf.StringReverseWorkflow
import io.temporal.activity.ActivityOptions
import io.temporal.workflow.Workflow
import java.time.Duration

class StringReverseWorkflowImpl : StringReverseWorkflow {
    override val Q_NAME: String
        get() = StringReverseWorkflow.Q_NAME
    private val activity:StringReverseActivity = Workflow.newActivityStub(
        StringReverseActivity::class.java,
        ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(2)).build()
    )

    override fun transform(value: String): String {
        println("Execute StringReverseWorkflow")
        return activity.reverse(value)
    }

    internal class StringReverseActivityImpl : StringReverseActivity {
        override fun reverse(input: String): String {
            println("[Activity]Reverse the input String - $input")
            val reversed = StringBuilder()
            for (i in input.length - 1 downTo 0) {
                reversed.append(input[i])
            }
            println("[Activity]Reverse the input String - $reversed")
            return reversed.toString()
        }
    }

    companion object {
        @JvmStatic
        fun newActivity(): StringReverseActivity {
            return StringReverseActivityImpl()
        }
    }
}
