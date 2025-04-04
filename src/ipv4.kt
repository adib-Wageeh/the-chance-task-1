fun main() {

    // region valid ipv4
    val validIpv4 = "0.0.0.0"
    val validResult = checkValidIPv4(validIpv4)
    check(
        text = "Given a valid IPv4 address, When checking validity, Then it should return true",
        result = validResult,
        correctResult = true,
    )
    // endregion

    // region less dots in ipv4
    val lessDotsIpv4 = "0.0.0"
    val lessDotsResult = checkValidIPv4(lessDotsIpv4)
    check(
        text = "Given an IPv4 address with less than three dots, When checking validity, Then it should return false",
        result = lessDotsResult,
        correctResult = false,
    )
    // endregion

    // region more dots in ipv4
    val moreDotsIpv4 = "0.0.0.0.0"
    val moreDotsResult = checkValidIPv4(moreDotsIpv4)
    check(
        text = "Given an IPv4 address with more than three dots, When checking validity, Then it should return false",
        result = moreDotsResult,
        correctResult = false,
    )
    // endregion

    // region wrong segment in ipv4
    val charInSegmentIpv4 = "0.a.0.0"
    val charInSegmentResult = checkValidIPv4(charInSegmentIpv4)
    check(
        text = "Given an IPv4 address containing non-numeric characters in a segment, When checking validity, Then it should return false",
        result = charInSegmentResult,
        correctResult = false,
    )
    // endregion

    // region number more than 255 in segment in ipv4
    val outOfRangeIpv4 = "0.256.0.0"
    val outOfRangeResult = checkValidIPv4(outOfRangeIpv4)
    check(
        text = "Given an IPv4 address containing a segment out of valid range (0-255), When checking validity, Then it should return false",
        result = outOfRangeResult,
        correctResult = false,
    )
    // endregion

    // region leading zero in segment in ipv4
    val leadingZeroIpv4 = "0.01.0.0"
    val leadingZeroResult = checkValidIPv4(leadingZeroIpv4)
    check(
        text = "Given an IPv4 address containing a segment with a leading zero, When checking validity, Then it should return false",
        result = leadingZeroResult,
        correctResult = false,
    )
    // endregion

}

fun checkValidIPv4(ipv4: String): Boolean {
    val segments = ipv4.split('.')
    if (segments.size != 4) {
        return false
    }
    segments.forEach {
        if (it.length > 1 && it.startsWith('0')) {
            return false
        }
        val segment: Int = it.toIntOrNull() ?: return false
        if (segment !in 0..255) {
            return false
        }
    }
    return true
}