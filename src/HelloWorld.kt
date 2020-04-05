import string.join
import string.lastChar
import java.io.BufferedReader
import javax.naming.Context
import javax.swing.text.AttributeSet

// 경로 파싱에 정규식 사용하기
fun parsePathRegular(path: String) {
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val matchResult = regex.matchEntire(path)
    if(matchResult != null) {
        val (directory, fileName, extension) = matchResult.destructured
        println("Dir: $directory, name: $fileName, ext: $extension")
    }
}

// String 확장 함수를 사용해 경로 파싱하기
fun parsePath(path: String) {
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")
    val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")

    println("Dir: $directory, name: $fileName, ext: $extension")
}

// try 사용하기
fun readNumber(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    }
    catch(e: NumberFormatException) {
        null
    }
    println(number)
}

// in을 사용해 값이 범위에 속하는지 검사하기
fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
fun isNotDigit(c: Char) = c !in '0'..'9'

// when을 사용해 피즈버즈 게임 구현하기
fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz "
    i % 5 == 0 -> "Buzz "
    i % 3 == 0 -> "Fizz "
    else -> "$i "
}

sealed class Expr {
    class Num(val value: Int) : Expr()
    class Sum(val left: Expr, val right: Expr) : Expr()
}

fun eval(e: Expr): Int =
    when (e) {
        is Expr.Num -> e.value
        is Expr.Sum -> eval(e.left) + eval(e.right)
    }

//fun evalWithLogging(e: Expr) : Int =
//    when(e) {
//        is Num -> {
//            println("Num : ${e.value}")
//            e.value
//        }
//        is Sum -> {
//            val left = evalWithLogging(e.left)
//            val right = evalWithLogging(e.right)
//            println("Sum : $left + $right")
//            left + right
//        }
//        else -> throw IllegalArgumentException("Unknown Expression")
//    }

//class User(val id: Int, val name: String, val address: String)
//fun User.validateBeforeSave() {
//    fun validate(value: String, fieldName: String) {
//        if(value.isEmpty()) {
//            throw IllegalArgumentException("Cant't save user $id: empty $fieldName")
//        }
//    }
//    validate(name, "Name")
//    validate(address, "Address")
//}
//// 코드 중복을 보여주는 예제
//fun saveUser(user: User) {
//    if(user.name.isEmpty()) {
//        throw IllegalArgumentException("Can't save user ${user.id}: empty Name")
//    }
//    if(user.address.isEmpty()) {
//        throw IllegalArgumentException("Can't save user ${user.id}: empty Address")
//    }
//    // user를 데이터베이스에 저장
//}
//// 로컬 함수를 사용해 코드 중복 줄이기
//fun saveUserLocal(user: User) {
//    fun validate(value: String, fieldName: String) {
//        if(value.isEmpty()) {
//            throw IllegalArgumentException("Cant't save user ${user.id}: empty $fieldName")
//        }
//    }
//    validate(user.name, "Name")
//    validate(user.address, "Address")
//    // user를 데이터베이스에 저장
//}

//class User (val nickname: String, val isSubscribed: Boolean = true)

open class Text

class EditText : Text()

// 주 생성자가 비공개인 클래스
//class Secretive private constructor() {}
//
//open class View {
//    constructor(ctx: Context) { // 부 생성자
//        // Code
//    }
//    constructor(ctx: Context, attr: AttributeSet) { // 부 생성자
//        // Code
//    }
//}

//class MyButton : View {
//    constructor(ctx: Context)
//        : this(ctx, MY_STYLE) { // 이 클래스의 다른 생성자에게 위임한다.
//        // ...
//    }
//    constructor(ctx: Context, attr: AttributeSet)
//        : super(ctx, attr) {
//        // ...
//    }
//}

// 인터페이스의 프로퍼티 구현하기
//interface User {
//    val email: String
//    val nickName: String
//        get() = email.substringBefore('@')
//        // 프로퍼티에 뒷받침하는 필드가 없다. 대신 매번 결과를 계산해 돌려준다.
//}
//
//class PrivateUser(override val nickName: String) : User
//
//class SubscribingUser(val email: String) : User {
//    override val nickName: String
//        get() = email.substringBefore('@')
//}
//
//class FacebookUser(val accountId: Int) : User {
//    override val nickName = getFacebookName(accountId)
//}

// Setter에서 뒷받침하는 필드 접근하기
class User(val name: String) {
    var address: String = "unspecified"
        set(value: String) {
            println("""
                Address was changed for $name:
                "$field" -> "$value".""".trimIndent())
            field = value
        }
}

// 비공개 Setter가 있는 프로퍼티 선언하기
class LengthCounter {
    var counter: Int = 0
        private set

    fun addWord(word:String) {
        counter += word.length
    }
}

fun main() {
    val user = User("Alice")
    user.address = "Elsenheimerstrasse 47, 80687 Muenchen"
}