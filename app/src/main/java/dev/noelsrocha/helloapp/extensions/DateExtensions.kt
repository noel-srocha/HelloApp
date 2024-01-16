package dev.noelsrocha.helloapp.extensions

import dev.noelsrocha.helloapp.util.FORMATO_DATA_EXIBIR
import java.text.SimpleDateFormat
import java.util.*

fun Date.converteParaString(): String {
    return SimpleDateFormat(
        FORMATO_DATA_EXIBIR,
        Locale.getDefault()
    ).format(this)
}
