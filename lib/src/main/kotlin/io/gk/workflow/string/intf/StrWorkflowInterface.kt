package io.gk.workflow.string.intf

import io.temporal.workflow.WorkflowMethod

interface StrWorkflowInterface {
    // this is used in the parent workflow to dynamically get the QNAME using the class instance, to avoid hardcoding the QNAME in parent workflow
    val Q_NAME: String
    @WorkflowMethod
    fun transform(value: String): String
}