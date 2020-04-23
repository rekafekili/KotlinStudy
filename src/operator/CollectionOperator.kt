package operator

import java.time.LocalDate

// get 관례 구현하기
operator fun Point.get(index: Int): Int {
    return when (index) {
        0 -> x
        1 -> y
        else ->
            throw IndexOutOfBoundsException("Invalid Coordinate $index")
    }
}

// 관례를 따르는 set 구현하기
data class MutablePoint(var x: Int, var y: Int)
operator fun MutablePoint.set(index: Int, value: Int) {
    when(index) {
        0 -> x = value
        1 -> y = value
        else ->
            throw IndexOutOfBoundsException("Invalid Coordinate $index")
    }
}

// in 관례 구현하기
data class Rectangle(val upperLeft: Point, val lowerRight: Point)

operator fun Rectangle.contains(p: Point) : Boolean {
    return p.x in upperLeft.x until lowerRight.x &&
            p.y in upperLeft.y until lowerRight.y
}

// 날짜 범위에 대한 이터레이터 구현하기
operator fun ClosedRange<LocalDate>.iterator() : Iterator<LocalDate> =
    // 이 객체는 LocalDate 원소에 대한 iterator를 구현한다.
    object : Iterator<LocalDate> {
        var current = start
        override fun hasNext() =
            // compareTo 관례를 사용해 날짜를 비교한다.
            current <= endInclusive

        override fun next() = current.apply {
            // 현재 날짜를 저장한 다음에 날짜를 변경한다.
            // 그 후 저장해둔 날짜를 반환한다.
            current = plusDays(1 )
        }
    }

fun main() {
    val newYear = LocalDate.ofYearDay(2017, 1)
    val daysOff = newYear.minusDays(1)..newYear
    for(dayOff in daysOff) { println(dayOff) }
}