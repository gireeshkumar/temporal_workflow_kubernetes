/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package io.gk.workflow.string.actionswf

import io.gk.workflow.string.intf.*
import io.temporal.api.common.v1.WorkflowExecution
import io.temporal.workflow.Async
import io.temporal.workflow.ChildWorkflowOptions
import io.temporal.workflow.Promise
import io.temporal.workflow.Workflow
import java.util.*


class StringActionsWorkflowImpl : StringActionsWorkflow {

    private fun newChildWorkflowOptions(qname: String, workflowid: String?): ChildWorkflowOptions {
        return ChildWorkflowOptions.newBuilder()
            .setWorkflowId(workflowid)
            .setTaskQueue(qname)
            .build()
    }

    override fun transform(value: String, type: String?): Array<String> {
        println("Running multiple actions on String  [$type] - $value")
        val startTime = System.currentTimeMillis()
        val result: Array<String> = if (type.equals("async", ignoreCase = true)) {
            transformModeAsync(value)
        } else if (type.equals("dynamic", ignoreCase = true)) {
            transformMode2(value)
        } else {
            transformMode1(value)
        }
        val endTime = System.currentTimeMillis()
        val elapsedTime = endTime - startTime
        println("Execution elapsed time: $elapsedTime milliseconds")
        return result
    }

    /**
     * Workflow Static sequence execution
     *
     * @param input
     * @return
     */
    private fun transformMode1(input: String): Array<String> {
        // running fixed child workflow execution
        // call child workflows
        return arrayOf(
            stringReverse(input),
            stringKebabCase(input),
            stringUppercase(input)
        )
    }

    /**
     * Call child workflows dynamically using reactive / async style
     *
     * @param input
     * @return
     */
    private fun transformModeAsync(input: String?): Array<String> {
        println("transformModeAsync - Async")
        val prms: Array<Promise<String>?> = arrayOfNulls<Promise<String>>(StringTransformers.TRANSFORMERS.size)
        val exec: Array<Promise<WorkflowExecution>?> = arrayOfNulls<Promise<WorkflowExecution>>(StringTransformers.TRANSFORMERS.size)

        StringTransformers.TRANSFORMERS.forEachIndexed { index, clazz ->
            val workflowStub = newWorkflow(clazz)
            prms[index] = Async.function({ value: String? -> workflowStub.transform(value!!) }, input)
            exec[index] = Workflow.getWorkflowExecution(workflowStub)
        }

        println("Wait for promises to complete")
        for (workflowExecutionPromise in exec) {
            workflowExecutionPromise!!.get()
        }
        println("All completed, get result")
        return prms.filterNotNull().map { it.get() }.toTypedArray()

    }

    /**
     * Dynamic child workflow in sequence
     *
     * @param input
     * @return
     */
    private fun transformMode2(input: String): Array<String> {
        // Dynamic calling of workflow in sequence
        val vals: MutableList<String> = ArrayList()
        // Dynamically loading from a list of workflows at runtime using the interface reference
        StringTransformers.TRANSFORMERS.forEach {
            vals.add(
                newWorkflow(it).transform(input)
            )
        }
        return vals.toTypedArray<String>()
    }

    private fun stringUppercase(input: String): String {
        println("Call WF StringUpperCaseWorkflow")
        return stringUpperCaseWorkflow().transform(input)
    }


    private fun stringKebabCase(input: String): String {
        println("Call WF StringKebabCaseWorkflow")
        return stringKebabCaseWorkflow().transform(input)
    }


    private fun stringReverse(input: String): String {
        println("Call WF StringReverseWorkflow")
        return stringReverseWorkflow().transform(input)
    }

    private fun stringUpperCaseWorkflow() = newWorkflow(StringUpperCaseWorkflow::class.java)

    private fun stringKebabCaseWorkflow() = newWorkflow(StringKebabCaseWorkflow::class.java)

    private fun stringReverseWorkflow() = newWorkflow(StringReverseWorkflow::class.java)

    /**
     * Dynamically getting Workflow stub using the interface class reference
     */
    private fun <T : StrWorkflowInterface> newWorkflow(clazz: Class<T>): T {
        // all interface expected to have a Q_NAME property to dynamically get the Q_NAME for the given workflow
        val qNameField = clazz.getDeclaredField("Q_NAME")
        val qNameValue = qNameField.get(null) as String
        val c = Calendar.getInstance()
        return Workflow.newChildWorkflowStub(
            clazz, newChildWorkflowOptions(qNameValue, "$qNameValue-${c.timeInMillis}")
        )
    }

}

object StringTransformers {
    @JvmStatic
    val TRANSFORMERS: Array<Class<out StrWorkflowInterface>> = arrayOf(
        StringUpperCaseWorkflow::class.java,
        StringKebabCaseWorkflow::class.java,
        StringReverseWorkflow::class.java
    )
}