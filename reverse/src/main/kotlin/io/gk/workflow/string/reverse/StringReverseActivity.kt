package io.gk.workflow.string.reverse

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod

@ActivityInterface
interface StringReverseActivity {
    @ActivityMethod(name = "io/gk/workflow/string/reverse")
    fun reverse(input: String): String
}
