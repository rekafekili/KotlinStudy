package operator

//class Foo {
//    private val delegate = Delegate()
//    var p : Type
//    set(value: Type) = delegate.setValue(..., value)
//    get() = delegate.getValue(...)
//}

class Delegate {
    operator fun getValue(...) {...}
    operator fun setValue(..., value: Type) {...}
}

class Foo {
    // by 키워드는 프로퍼티와 위임 객체를 연결한다.
    var p : Type by Delegate()
}

fun main() {
    val foo = Foo()
    val oldValue = foo.p
    foo.p = newValue
}