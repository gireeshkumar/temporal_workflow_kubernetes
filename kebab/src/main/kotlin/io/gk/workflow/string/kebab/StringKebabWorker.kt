/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package io.gk.workflow.string.kebab

import io.gk.workflow.string.StringActionsWorkerBase
import io.gk.workflow.string.intf.StringKebabCaseWorkflow

class StringKebabWorker : StringActionsWorkerBase() {


    override val implementation: Class<*>
        get() = StringKebabWorkflowImpl::class.java
    override val queueName: String
        get() = StringKebabCaseWorkflow.Q_NAME
    override val workflowVersion: String
        get() = "0.1.2"
    override val activityImplementations: Array<Any>
        get() = arrayOf(StringKebabWorkflowImpl.newActivity())

}
