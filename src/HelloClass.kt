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

fun main() {
    val client = Client("정석", 4122)
    println(client.copy(postalCode = 4000))
}