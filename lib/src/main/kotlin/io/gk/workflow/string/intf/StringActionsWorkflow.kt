package io.gk.workflow.string.intf

import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod

@WorkflowInterface
interface StringActionsWorkflow {
    companion object {
        const val Q_NAME = "string-transform-q"
    }

    @WorkflowMethod
    fun transform(value: String, type: String?): Array<String>
}
