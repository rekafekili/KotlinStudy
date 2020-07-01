package baeckjoon

fun main() {
    var x = 0
    var y = 0
    for (i in 0..1) {
        if (i == 0) x = readLine()!!.toInt()
        else
            y = readLine()!!.toInt()
    }

    if (x > 0 && y > 0) {
        println(1)
    } else if (x < 0 && y > 0) {
        println(2)
    } else if (x < 0 && y < 0) {
        println(3)
    } else {
        println(4)
    }
}