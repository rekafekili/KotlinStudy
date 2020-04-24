package operator

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import javax.swing.event.RowSorterEvent
import javax.swing.text.html.parser.Entity
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

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

// ObservableProperty 를 프로퍼티 위임에 사용할 수 있게 바꾼 모습
class ObservableProperty(
    var propValue: Int, val changeSupport: PropertyChangeSupport
) {
    operator fun getValue(p: Person3, prop: KProperty<*>) : Int = propValue
    operator fun setValue(p: Person3, prop: KProperty<*>, newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
}

// 위임 프로퍼티를 통해 프로퍼티 변경 통지 받기
class Person3(
    val name: String,
    age: Int,
    salary: Int
) : PropertyChangeAware() {
    var age: Int by ObservableProperty(age, changeSupport)
    var salary: Int by ObservableProperty(salary, changeSupport)
}

// Delegate.observable 을 사용해 프로퍼티 변경 통지 구현하기
class Person4(
    val name: String,
    age: Int,
    salary: Int
) : PropertyChangeAware() {
    private val observer = {
        prop: KProperty<*>, oldValue: Int, newValue: Int ->
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }

    var age: Int by Delegates.observable(age, observer)
    var salary: Int by Delegates.observable(salary, observer)
}

// 위임 프로퍼티 컴파일 규칙
//class C {
//    private val <delegate> = MyDelegate()
//    var prop: Type
//        get() = <delegate>.getValue(this, <property>)
//        set(value: Type) = <delegate>.setValue(this, <property>, value)
//}

// 값을 맵에 저장하는 위임 프로퍼티 정의하기
class Person5 {
    // 추가 정보
    private val _attributes = hashMapOf<String, String>()
    fun setAttribute(attrName: String, value: String) {
        _attributes[attrName] = value
    }

    // 필수 정보
    val name: String by _attributes
}

//// 위임 프로퍼티를 사용해 데이터베이스 칼럼 접근하기
//object Users: IdTable() { // 객체는 데이터베이스 테이블에 해당한다.
//    // 프로퍼티는 테이블 칼럼에 해당한다.
//    val name = varchar("name", length = 50).index()
//    val age = integer("age")
//}
//
//// 각 User 인스턴스는 테이블에 들어있는 구체적인 엔티티에 해당한다.
//class User(id: EntityID) : Entity(id) {
//    // 사용자 이름은 데이터베이스 "name" 칼럼에 들어 있다.
//    var name: String by Users.name
//    var age: Int by Users.age
//}

fun main() {
    val p = Person5()
    val data =
        mapOf("name" to "Dmitry", "company" to "JetBrains")
    for((attrName, value) in data)
        p.setAttribute(attrName, value)
    println(p.name)
}