package io.gk.workflow.string

import io.temporal.client.WorkflowClient
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.serviceclient.WorkflowServiceStubsOptions
import io.temporal.worker.Worker
import io.temporal.worker.WorkerFactory


abstract class WorkerBase {
    companion object {
        const val version = "0.1.1"
        val temporalServerAddress = System.getenv("TEMPORAL_SERVER") ?: "127.0.0.1:7233"
    }

    abstract val workflowVersion: String
    abstract val queueName: String
    abstract val implementation: Class<*>
    open val activityImplementations: Array<Any>? get() = null
    private lateinit var worker: Worker

    open fun start(target: String? = null) {

        println("Worker starting, Workflow - $implementation V: $workflowVersion, Lib: $version")
        println("Connect to Temporal server - ${target ?: temporalServerAddress}")

        val wfOptions:WorkflowServiceStubsOptions = WorkflowServiceStubsOptions.newBuilder().setTarget(
            temporalServerAddress
        ).build()
        val service:WorkflowServiceStubs = WorkflowServiceStubs.newServiceStubs(wfOptions)

        val client = WorkflowClient.newInstance(service)
        val factory = WorkerFactory.newInstance(client)
        worker = factory.newWorker(queueName)
        println("Register new worker on Q $queueName")
        worker.registerWorkflowImplementationTypes(*arrayOf(implementation))
        val impls = activityImplementations
        if (impls != null) {
            for (impl in impls) {
                println("Register activity : " + impl.javaClass.name)
                worker.registerActivitiesImplementations(impl)
            }
        }
        factory.start()
    }
}
//string-transform-wf-child-reverse