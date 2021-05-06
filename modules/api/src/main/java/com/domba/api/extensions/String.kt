package com.domba.api.extensions

internal fun String.ordinalIndexOf(subString : String, numbers : Int) : Int {
    var n = numbers
    var pos = indexOf(subString)
    while (--n > 0 && pos != -1)
        pos = indexOf(subString, pos + 1)
    return pos
}