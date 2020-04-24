package operator

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport

/* 위임 프로퍼티 */
//class Foo {
//    private val delegate = Delegate()
//    var p : Type
//    set(value: Type) = delegate.setValue(..., value)
//    get() = delegate.getValue(...)
//}

//class Delegate {
//    operator fun getValue(...) {...}
//    operator fun setValue(..., value: Type) {...}
//}
//
//class Foo {
//    // by 키워드는 프로퍼티와 위임 객체를 연결한다.
//    var p : Type by Delegate()
//}

class Email { /*....*/ }
fun loadEmails(person: Person1) : List<Email> {
    println("${person.name}의 이메일을 가져옴")
    return listOf(/*....*/)
}

// 지연 초기화를 위임 프로퍼티를 통해 구현하기
class Person1(val name: String) {
    val emails by lazy { loadEmails(this) }
}

// PropertyChangeSupport 를 사용하기 위한 도우미 클래스
open class PropertyChangeAware {
    protected val changeSupport = PropertyChangeSupport(this)
    fun addPropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(listener)
    }

    fun removePropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.removePropertyChangeListener(listener)
    }
}

class Person2(
    val name: String,
    age: Int,
    salary: Int
) : PropertyChangeAware() {
    var age: Int = age
        set(newValue) {
            val oldValue = field
            // 뒷 받침하는 필드에 접근할 때 field 식별자를 사용한다.
            field = newValue
            changeSupport.firePropertyChange(
                "age", oldValue, newValue)
        }

    var salary: Int = salary
        set(newValue) {
            val oldValue = field
            field = newValue
            changeSupport.firePropertyChange(
                "salary", oldValue, newValue)
        }
}

class ObservableProperty(
    val propName: String, var propValue: Int,
    val changeSupport: PropertyChangeSupport
) {
    fun getValue() : Int = propValue
    fun setValue(newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(propName, oldValue, newValue)
    }
}

class Person3(
    val name: String,
    age: Int,
    salary: Int
) : PropertyChangeAware() {
    val _age = ObservableProperty("age", age, changeSupport)
    var age: Int
        get() = _age.getValue()
        set(value) {
            _age.setValue(value)
        }
    val _salary = ObservableProperty("salary", salary, changeSupport)
    var salary: Int
        get() = _salary.getValue()
        set(value) {
            _salary.setValue(value)
        }
}

fun main() {
    val p = Person2("Dmitry", 34, 2000)
    p.addPropertyChangeListener(
        PropertyChangeListener { event ->
            println("Property ${event.propertyName} changed " +
                "from ${event.oldValue} to ${event.newValue}")
        }
    )
    p.age = 35
    p.salary = 2100
}