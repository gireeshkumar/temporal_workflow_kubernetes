package io.gk.workflow.string.upper

import io.gk.workflow.string.intf.StringUpperCaseWorkflow
import java.util.*

class StringCapsWorkflowImpl : StringUpperCaseWorkflow {

    override val Q_NAME: String
        get() = StringUpperCaseWorkflow.Q_NAME

    override fun transform(value: String): String {
        println("Convert string to UpperCase - $value")
        return value.uppercase(Locale.getDefault())
    }
}
