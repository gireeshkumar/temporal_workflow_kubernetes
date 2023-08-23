package io.gk.workflow.string

import io.gk.workflow.string.intf.StringKebabCaseWorkflow
import io.gk.workflow.string.intf.StringReverseWorkflow
import io.gk.workflow.string.intf.StringUpperCaseWorkflow

object StringTransformers {
    @JvmStatic
    val TRANSFORMERS: Array<Class<out Any>> = arrayOf(
        StringUpperCaseWorkflow::class.java,
        StringKebabCaseWorkflow::class.java,
        StringReverseWorkflow::class.java
    )
}
