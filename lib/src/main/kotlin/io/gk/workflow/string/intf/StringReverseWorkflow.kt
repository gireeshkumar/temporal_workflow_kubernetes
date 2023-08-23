package io.gk.workflow.string.intf

import io.gk.workflow.string.StringActionsWorkerBase
import io.temporal.workflow.WorkflowInterface

@WorkflowInterface
interface StringReverseWorkflow : StrWorkflowInterface {
    companion object {
        const val Q_NAME = StringActionsWorkerBase.Q_NAME_PREFIX + "_reverse"
    }


}
