

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

fun main() {
    val button = Button()
    button.showOff()
    button.setFocus(true)
    button.click()
}