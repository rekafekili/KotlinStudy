package highorderfunction

fun performRequest(
    url: String,
    callback: (code: Int, content: String) -> Unit
) {
    /* ... */
}

// 간단한 고차 함수 정의하기
fun twoAndThree(operation: (Int, Int) -> Int) {
    val result = operation(2,3)
    println("The Result is $result")
}

// filter 함수를 단순하게 만든 버전 구현하기
fun String.filter(predicate: (Char) -> Boolean): String {
    val sb = StringBuilder()
    for(index in 0 until length) {
        val element = get(index)
        if(predicate(element)) sb.append(element)
    }
    return sb.toString()
}

fun main() {
    println("abc".filter { it in 'a'..'z' })

    val url = "http://kotl.in"

    // API에서 제공하는 이름을을 람다에 사용할 수 있다.
    performRequest(url) { code, content -> /* ... */ }

    // 원하는 다른 이름을 붙여도 된다.
    performRequest(url) { code, page -> /* ... */ }

    // Int 파라미터를 2개 받아서 Int 값을 반환하는 함수
    val sum: (Int, Int) -> Int = { x, y -> x + y }

    // 아무 인자도 받지 않고 아무 값도 반환하지 않는 함수
    val action: () -> Unit = { println(42) }

    // null 이 될 수 있는 반환 타입
    var canReturnNull: (Int, Int) -> Int? = { x, y -> null }

    // null 이 될 수 있는 함수 타입
    var funOrNull: ((Int, Int) -> Int)? = null
}