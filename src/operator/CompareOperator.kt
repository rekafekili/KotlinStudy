package operator

class Point2(val x: Int, val y: Int) {
    // Any에 정의된 메소드를 오버라이딩한다.
    override fun equals(other: Any?): Boolean {
        // 최적화 : 파라미터가 "this"와 같은 객체인지 살펴본다.
        if(other === this) return true

        // 파라미터 타입을 검사한다.
        if(other !is Point2) return false

        // Point2로 스마트 캐스트해서 x와 y 프로퍼티에 접근한다.
        return other.x == x && other.y == y
    }
}
fun main() {
    println(Point2(10, 20) == Point2(10, 20))

    println(Point2(10, 20) != Point2(5, 5))

    println(null == Point2(1, 2))
}