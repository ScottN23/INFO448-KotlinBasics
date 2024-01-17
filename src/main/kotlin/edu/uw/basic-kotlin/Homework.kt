package edu.uw.basickotlin

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(arg: Any): String {
    when (arg) {
        is String -> return if (arg === "Hello") "world" else "Say what?"
        is Int -> {
            return when (arg) {
                0 -> "zero"
                1 -> "one"
                in 2..10 -> "low number"
                else -> "a number"
            }
        }
        else -> return "I don't understand"
    }
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(lhs: Int, rhs: Int): Int {
    return lhs + rhs
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(lhs: Int, rhs: Int): Int {
    return lhs - rhs
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(lhs: Int, rhs: Int, op: (Int, Int) -> Int): Int {
    return op(lhs, rhs)
}

// write a class "Person" with first name, last name and age
class Person(val firstName: String, val lastName: String, val age: Int) {
    val debugString: String
        get() = "[Person firstName:$firstName lastName:$lastName age:$age]"
}

// write a class "Money"
class Money(val amount: Int, val currency: String) {
    init {
        if (amount < 0) {
            throw IllegalArgumentException("Amount cannot be less than zero")
        }
        if (currency !in setOf("USD", "EUR", "CAN", "GBP")) {
            throw IllegalArgumentException("Invalid currency")
        }
    }

    fun convert(currentType: String): Money {
        if (currentType !in setOf("USD", "EUR", "CAN", "GBP")) {
            throw IllegalArgumentException("Invalid target currency")
        }

        return when {
            currency == currentType -> Money(amount, currentType)
            currency == "USD" && currentType == "EUR" ->
                Money((amount * 1.5).toInt(), "EUR")
            currency == "EUR" && currentType == "USD" ->
                Money((amount * 0.67).toInt(), "USD")
            currency == "USD" && currentType == "CAN" ->
                Money((amount * 1.25).toInt(), "CAN")
            currency == "CAN" && currentType == "USD" ->
                Money((amount * 1.25).toInt(), "USD")
            currency == "USD" && currentType == "GBP" ->
                Money((amount * 0.5).toInt(), "GBP")
            currency == "GBP" && currentType == "USD" ->
                Money((amount * 2.0).toInt(), "USD")
            else -> convert("USD").convert(currentType)
        }
    }

    operator fun plus(other: Money): Money {
        return Money(this.amount + (other.convert(this.currency)).amount, this.currency)
    }
}
