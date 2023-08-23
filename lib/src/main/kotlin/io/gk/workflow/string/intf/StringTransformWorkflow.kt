package io.gk.workflow.string.intf

import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod

@WorkflowInterface
interface StringTransformWorkflow {
    @WorkflowMethod
    fun transform(value: String): String
}
