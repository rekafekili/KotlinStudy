import string.join
import string.lastChar
import java.io.BufferedReader

fun main() {
    println("Kotlins".lastChar)
}

// 1, 2, 3 을 가진 리스트를 이용하여 “(1; 2; 3)” 출력하는 함수 만들기
//fun <T> joinToString(collection: Collection<T>, separator: String, prefix: String, postfix: String) : String {
//    val result = StringBuilder(prefix)
//    for((index, element) in collection.withIndex()) {
//        if (index > 0) result.append(separator)
//        result.append(element)
//    }
//
//    result.append(postfix)
//    return result.toString()
//}

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