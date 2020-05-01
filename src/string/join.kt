@file:JvmName("StringFunctions")

package string

val String.lastChar: Char
    get() = get(length - 1)

// 최상위 프로퍼티와 const 변경자
const val UNIX_LINE_SEPARATOR = "\n"
// (==) public static final UNIX_LINE_SEPARATOR = "\n";

var opCount = 0
fun performOperation() {
    opCount++
    // ...
}

fun reportOperationCount() {
    println("Operation performed $opCount times")
}

// 디폴트 파라미터를 사용하여 joinToString 함수 개선
fun <T> Collection<T>.joinToString(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
        // 기본 toString 메소드를 사용해 객체를 문자열로 변환한다.
    }

    result.append(postfix)
    return result.toString()
}

fun Collection<String>.join(
    separator: String = " ",
    prefix: String = "",
    postfix: String = ""
) = joinToString(separator, prefix, postfix)