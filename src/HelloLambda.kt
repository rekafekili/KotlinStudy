data class Person(val name: String, val age: Int)

// 컬렉션 직접 검색하기 : 최연장자 찾기
fun findTheOldest(people: List<Person>) {
    var maxAge = 0
    var theOldest: Person? = null
    for (person in people) {
        if (person.age > maxAge) {
            maxAge = person.age
            theOldest = person
        }
    }
    println(theOldest)
}

fun main() {
    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    println(people.maxBy(Person::age))
}