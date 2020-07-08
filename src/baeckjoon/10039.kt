package baeckjoon

fun main() {
    var total = 0
    for(i in 0..4) {
        val input = readLine()!!.toInt()
        total += (if (input < 40) 40 else input)
    }

    println(total/5)
}