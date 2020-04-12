import java.lang.IllegalArgumentException

fun printAllCaps(s: String?) {
    val allCaps: String? = s?.toUpperCase()
    // allCaps는 null일 수도 있다.
    println(allCaps)
}

// null이 될 수 있는 프로퍼티를 다루기 위해 안전한 호출 사용하기
class Employee(val name: String, val manager: Employee?)

fun managerName(employee: Employee) : String? = employee.manager?.name

fun Person.countryName(): String =
    this.company?.address?.country ?: "Unknown"

fun foo(s: String?) {
    val t: String = s ?: ""
    // "s"가 null이면 결과는 빈 문자열("")이다.
}

// 엘비스 연산자를 활용해 null 값 다루기
fun strLenSafe(s: String?) : Int = s?.length ?: 0

// 안전한 호출 연쇄시키기
class Address(val streetAddress: String, val zipCode: Int,
              val city: String, val country: String)

class Company(val name: String, val address: Address?)

class Person(val name: String, val company: Company?)

fun printShippingLabel(person: Person) {
    val address = person.company?.address
        ?: throw IllegalArgumentException("No Address")

    with(address) {
        println(streetAddress)
        println("$zipCode $city, $country")
    }
}

fun main(){
    val address = Address("Elsestr. 47", 80687,
        "Munich", "Germany")
    val jetbrains = Company("JetBrains", address)
    val person = Person("Dmitry", jetbrains)

    printShippingLabel(person)
    printShippingLabel(Person("Alexey", null))
}