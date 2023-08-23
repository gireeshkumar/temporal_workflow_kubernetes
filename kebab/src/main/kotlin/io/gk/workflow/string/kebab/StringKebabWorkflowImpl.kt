package io.gk.workflow.string.kebab

import io.gk.workflow.string.intf.StringKebabCaseWorkflow
import io.temporal.activity.LocalActivityOptions
import io.temporal.workflow.Workflow
import java.time.Duration

class StringKebabWorkflowImpl : StringKebabCaseWorkflow {
    override val Q_NAME: String
        get() = StringKebabCaseWorkflow.Q_NAME

    private val kebabActivity: StringKebabActivity = Workflow.newLocalActivityStub(
        StringKebabActivity::class.java,
        LocalActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(2))
            .build()
    )

    override fun transform(value: String): String {
        println("Execute StringKebabWorkflowImpl")
        return kebabActivity.kebab(value)
    }

    internal class StringKebabLocalActivityImpl : StringKebabActivity {
        override fun kebab(input: String): String {
            println("Starting Kebab Local Activity: $input")
            val kebabCase = StringBuilder()
            for (i in input.indices) {
                val currentChar = input[i]
                if (Character.isWhitespace(currentChar)) {
                    kebabCase.append("-")
                } else if (Character.isUpperCase(currentChar)) {
                    if (i > 0 && !Character.isWhitespace(input[i - 1])) {
                        kebabCase.append("-")
                    }
                    kebabCase.append(currentChar.lowercaseChar())
                } else {
                    kebabCase.append(currentChar)
                }
            }
            println("Output: $kebabCase")
            return kebabCase.toString()
        }
    }

    companion object {
        fun newActivity(): StringKebabActivity {
            return StringKebabLocalActivityImpl()
        }
    }
}
