package io.gk.workflow.string.kebab

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

@ActivityInterface
interface StringKebabActivity {
    @ActivityMethod(name = "kebab")
    fun kebab(input: String): String
}
