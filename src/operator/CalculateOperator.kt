package operator

// 화면 상의 점을 표현 하는 Point 클래스
data class Point(val x: Int, val y: Int) {
    // "plus" 라는 이름의 연산자 함수를 정의
    operator fun plus(other: Point) : Point {
        // 좌표를 성분 별로 더한 새로운 점을 반환
        return Point(x + other.x, y + other.y)
    }
}

// 연산자를 확장 함수로 정의 하기
operator fun Point.plus(other: Point) : Point {
    return Point(x + other.x, y + other.y)
}

// 두 피연산자의 타입이 다른 연산자 정의하기
operator fun Point.times(scale: Double) : Point {
    return Point((scale * x).toInt(), (scale * y).toInt())
}

// 결과 타입이 피연산자 타입과 다른 연산자 정의하기
operator fun Char.times(count: Int) : String {
    return toString().repeat(count)
}

// plusAssign
operator fun <T> MutableCollection<T>.plusAssign(element: T) {
    this.add(element)
}

fun main() {
    val list = arrayListOf(1, 2)
    list += 3 // list 변경

    val newList = list + listOf(4, 5)
    // 두 리스트의 모든 원소를 포함하는 새로운 리스트 반환

    println(list)
    println(newList)
}