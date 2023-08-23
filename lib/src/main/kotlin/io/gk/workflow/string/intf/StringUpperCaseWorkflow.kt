package io.gk.workflow.string.intf

import io.gk.workflow.string.StringActionsWorkerBase
import io.temporal.workflow.WorkflowInterface

@WorkflowInterface
interface StringUpperCaseWorkflow  : StrWorkflowInterface {
    companion object {
        const val Q_NAME = StringActionsWorkerBase.Q_NAME_PREFIX+"_upper"
    }

}
