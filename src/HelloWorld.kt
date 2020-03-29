import string.join
import string.lastChar
import java.io.BufferedReader

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

fun eval(e: Expr): Int =
    when (e) {
        is Num -> e.value
        is Sum -> eval(e.left) + eval(e.right)
        else -> throw IllegalArgumentException("Unknown Expression")
    }

fun evalWithLogging(e: Expr) : Int =
    when(e) {
        is Num -> {
            println("Num : ${e.value}")
            e.value
        }
        is Sum -> {
            val left = evalWithLogging(e.left)
            val right = evalWithLogging(e.right)
            println("Sum : $left + $right")
            left + right
        }
        else -> throw IllegalArgumentException("Unknown Expression")
    }

interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

class User(val id: Int, val name: String, val address: String)
fun User.validateBeforeSave() {
    fun validate(value: String, fieldName: String) {
        if(value.isEmpty()) {
            throw IllegalArgumentException("Cant't save user $id: empty $fieldName")
        }
    }
    validate(name, "Name")
    validate(address, "Address")
}
// 코드 중복을 보여주는 예제
fun saveUser(user: User) {
    if(user.name.isEmpty()) {
        throw IllegalArgumentException("Can't save user ${user.id}: empty Name")
    }
    if(user.address.isEmpty()) {
        throw IllegalArgumentException("Can't save user ${user.id}: empty Address")
    }
    // user를 데이터베이스에 저장
}
// 로컬 함수를 사용해 코드 중복 줄이기
fun saveUserLocal(user: User) {
    fun validate(value: String, fieldName: String) {
        if(value.isEmpty()) {
            throw IllegalArgumentException("Cant't save user ${user.id}: empty $fieldName")
        }
    }
    validate(user.name, "Name")
    validate(user.address, "Address")
    // user를 데이터베이스에 저장
}

fun main() {
    saveUserLocal(User(1, "", ""))
}