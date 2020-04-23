package operator

// 일반 클래스에서 componentN 함수 구현
//class Point(val x: Int, val y: Int) {
//    operator fun component1() = x
//    operator fun component2() = y
//}

data class NameComponents(val name: String, val extension: String)

// 컬렉션에 대해 구조 분해 선언 사용하기
fun splitFilename(fullName: String) : NameComponents {
//    val result = fullName.split('.', limit = 2)
//    return NameComponents(result[0], result[1])
    val (name, extension) = fullName.split('.', limit = 2)
    return NameComponents(name, extension)
}

// 구조 분해 선언을 사용해 맵 이터레이션하기
fun printEntries(map: Map<String, String>) {
    // 루프 변수에 구조 분해 선언을 사용한다.
    for ((key, value) in map) {
        println("$key -> $value")
    }
}

fun main() {
    val map = mapOf("Oracle" to "Java", "JetBrains" to "Kotlin")
    printEntries(map)
}