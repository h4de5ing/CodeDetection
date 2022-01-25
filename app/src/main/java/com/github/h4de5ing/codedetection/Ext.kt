package com.github.h4de5ing.codedetection

import kotlin.properties.Delegates

private var changeSendString: Change? = null
var stringSendChange: String by Delegates.observable("<no result>") { _, _, new ->
    changeSendString?.change(new)
}

fun setSendString(ch: ((String) -> Unit)) {
    changeSendString = object : Change {
        override fun change(text: String) {
            ch(text)
        }
    }
}