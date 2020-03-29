interface Clickable {
    fun click()
    fun showOff() = println("I'm clickable")
}

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

fun main() {
    val button = Button()
    button.showOff()
    button.setFocus(true)
    button.click()
}