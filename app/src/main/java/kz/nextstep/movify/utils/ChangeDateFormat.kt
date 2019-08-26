package kz.nextstep.movify.utils

import java.text.SimpleDateFormat
import java.util.*

object ChangeDateFormat {
    fun onChangeDateFormat(releasedDate: String?): String {
        var simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val date: Date = simpleDateFormat.parse(releasedDate)

        simpleDateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH)
        return simpleDateFormat.format(date)
    }


}