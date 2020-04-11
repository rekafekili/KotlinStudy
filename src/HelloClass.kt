import java.awt.Window
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.io.File
import java.io.Serializable

// 동일한 메소드를 구현하는 다른 인터페이스 정의
interface Focusable {
    fun setFocus(b: Boolean) = println("I ${if (b) "got" else "lost"} focus.")
    fun showOff() = println("I'm focusable!")
}

// 상속한 인터페이스의 메소드 구현 호출하기
class Button: Clickable, Focusable {
    override fun click() = println("I was Clicked")
    override fun showOff(){
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}

// 열린 메소드를 포함하는 열린 클래스 정의하기
open class RichButton : Clickable {
    final override fun click() {}
}

// 추상 클래스 정의하기
abstract class Animated {
    abstract fun animate()

    open fun stopAnimating() {
    }

    fun animateTwice() {
    }
}

interface Clickable {
    fun click()
    fun showOff() = println("I'm clickable")
}

internal open class TalkativeButton : Focusable {
    private fun yell() = println("Hey!")
    protected fun whisper() = println("Let's talk!")
}

// 확장함수는 그 클래스의 private, protected 멤버에 접근할 수 없다.
//fun TalkativeButton.giveSpeech() {
//    yell()
//    whisper()
//}

// 직렬화할 수 있는 상태가 있는 뷰 선언
interface State : Serializable

interface View {
    fun getCurrentState() : State
    fun restoreState(state: State)
}

class Outer {
    inner class Inner {
        fun getOuterReference() : Outer = this@Outer
    }
}

// Client에 hashCode() 구현하기
//class Client(val name: String, val postalCode: Int) {
//    override fun hashCode(): Int = name.hashCode() * 31 + postalCode
//    override fun equals(other: Any?): Boolean {
//        if(other == null || other !is Client)
//            return false
//        return name == other.name && postalCode == other.postalCode
//    }
//
//    override fun toString(): String = "Client(name=$name, postalCode=$postalCode)"
//}

data class Client(val name: String, val postalCode: Int)

//class DelegatingCollection<T> : Collection<T> {
//    private val innerList = arrayListOf<T>()
//    override val size: Int get() = innerList.size
//    override fun isEmpty(): Boolean = innerList.isEmpty()
//    override fun contains(element: T): Boolean
//            = innerList.contains(element)
//    override fun iterator(): Iterator<T> = innerList.iterator()
//    override fun containsAll(elements: Collection<T>): Boolean
//            = innerList.containsAll(elements)
//}

class DelegatingCollection<T>(
    innerList: Collection<T> = ArrayList<T>()
) : Collection<T> by innerList { }

class CountingSet<T>(
    val innerSet: MutableCollection<T> = HashSet<T>()
) : MutableCollection<T> by innerSet {
    var objectsAdded = 0
    override fun add(element: T) : Boolean {
        objectsAdded++
        return innerSet.add(element)
    }

    override fun addAll(c: Collection<T>) : Boolean {
        objectsAdded += c.size
        return innerSet.addAll(c)
    }
}

//object Payroll {
//    val allEmployees = arrayListOf<Person>()
//
//    fun calculateSalary() {
//        for(person in allEmployees) {
//            // ...
//        }
//    }
//}

// 객체 선언을 사용해 Comparator 구현하기
object CaseInsensitiveFileComparator: Comparator<File> {
    override fun compare(file1: File, file2: File): Int {
        return file1.path.compareTo(file2.path, ignoreCase = true)
    }
}

class A {
    companion object {
        fun bar() {
            println("Companion object called")
        }
    }
}

// 부 생성자가 여럿 있는 클래스 정의하기
//class User {
//    val nickName: String
//    constructor(email: String) {
//        nickName = email.substringBefore('@')
//    }
//    constructor(facebookAccountId: Int) {
//        nickName = getFacebookName(facebookAccountId)
//    }
//}

//// 부 생성자를 팩토리 메소드로 대신하가
//class User private constructor(val nickName: String) {
//    companion object {
//        // 팩토리 메소드
//        fun newSubscribingUser(email: String) =
//            User(email.substringBefore('@'))
//        fun newFacebookUser(accountId: Int) =
//            User(getFacebookName(accountId))
//    }
//}

// 동반 객체에 이름 붙이기
//class Person(val name: String) {
//    companion object Loader {
//        fun fromJSON(jsonText: String) : Person = ...
//    }
//}

// 동반 객체에서 인터페이스 구현하기
//interface JSONFactory<T> {
//    fun fromJSON(jsonText: String) : T
//}

//class Person(val name: String) {
//    companion object : JSONFactory<Person> {
//        override fun fromJSON(jsonText: String): Person {
//            // ....
//        }
//    }
//}

// 동반 객체에 대한 확장 함수 정의하기
/* 비즈니스 로직 모듈 */
//class Person(val firstName: String, val lastName: String) {
//    companion object {
//        // 비어있는 동반 객체 선언
//    }
//}

/* 클라이언트/서버 통신 모듈 */
//fun Person.Companion.fromJSON(json: String) : Person {
//    // 확장 함수 선언
//}


//val listener = object : MouseAdapter() { // MouseAdapter를 확장하는 무명 객체 선언
//    override fun mouseClicked(e: MouseEvent?) {
//        // ...
//    }
//
//    override fun mouseEntered(e: MouseEvent) {
//        // ...
//    }
//}

// 무명 객체 안에서 로컬 변수 사용하기
fun countClicks(window: Window) {
    var clickCount = 0
    window.addMouseListener(object: MouseAdapter() {
        override fun mouseClicked(e: MouseEvent?) {
            clickCount++
        }
    })
}

fun main() {

}
