import java.math.BigInteger

fun booleanExpression(a: Boolean, b: Boolean, c: Boolean, d: Boolean) = arrayOf(a,b,c,d).count { it } == 2

fun leapYearCount(year: Int) = Array(year) { it + 1 }.filter { (it % 4 == 0 && it % 100 != 0) || (it % 400 == 0) }.size

fun flipBit(value: Int, bitIndex: Int): Int {
    fun flipStringifiedBit(bit: Char) = if(bit == '0') '1' else '0'
    return Integer.parseInt(Integer.toBinaryString(value).mapIndexed { index, bit ->
        if(bitIndex != index) bit else flipStringifiedBit(bit)
    }.joinToString(""), 2)
}

fun isPowerOfTwo(value: Int) = value and -value == value && value > 0

fun isPalindrome(test: String): Boolean {
    val cleaned = Regex("[^a-z0-9]").replace(test.toLowerCase(), "")
    return cleaned == cleaned.reversed()
}

fun factorial(value: Int) = Array(value) { BigInteger.valueOf((it + 1).toLong()) }.reduce { acc, cur -> acc * cur }

fun mergeSort(list: List<Int>): List<Int> {
    if (list.size <= 1) {
        return list
    }

    val middle = list.size / 2
    var left = list.subList(0,middle);
    var right = list.subList(middle,list.size);

    return mergeArrays(mergeSort(left), mergeSort(right))
}


fun mergeArrays(a1: List<Int>, a2: List<Int>): List<Int>  {
    var indexLeft = 0
    var indexRight = 0
    val buffer : MutableList<Int> = mutableListOf()

    while (indexLeft < a1.count() && indexRight < a2.count()) {
        if (a1[indexLeft] <= a2[indexRight]) {
            buffer.add(a1[indexLeft])
            indexLeft++
        } else {
            buffer.add(a2[indexRight])
            indexRight++
        }
    }

    while (indexLeft < a1.size) {
        buffer.add(a1[indexLeft])
        indexLeft++
    }

    while (indexRight < a2.size) {
        buffer.add(a2[indexRight])
        indexRight++
    }

    return buffer;
}

fun main() {
}