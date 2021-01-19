package tictactoe

import java.util.*

fun main() {
    var symbol = 'X'
    var userInput = "_________"
    battlefield(userInput)
    while (gameStatus(userInput)){
    val userInputNew = takeInputUser()
    if (checkInputUser(userInputNew)) {
        if (newInput(userInput, userInputNew, symbol) != "FALSE") {
            symbol = if (symbol == 'X') 'O' else 'X'
            userInput = newInput(userInput, userInputNew, symbol)
            gameStatus(userInput)
            battlefield(userInput)
        }
    }
    }
}

//change battlefield according input also check for cells
fun newInput(userInput: String, userInputNew: String, symbol: Char): String {
    val finalInput = userInput.toCharArray().toTypedArray()
if (userInputNew == "1 1" && finalInput[0] == '_') {
    finalInput[0] = symbol
} else if (userInputNew == "1 2" && finalInput[1] == '_') {
    finalInput[1] = symbol
} else if (userInputNew == "1 3" && finalInput[2] == '_') {
    finalInput[2] = symbol
} else if (userInputNew == "2 1" && finalInput[3] == '_') {
    finalInput[3] = symbol
} else if (userInputNew == "2 2" && finalInput[4] == '_') {
    finalInput[4] = symbol
} else if (userInputNew == "2 3" && finalInput[5] == '_') {
    finalInput[5] = symbol
} else if (userInputNew == "3 1" && finalInput[6] == '_') {
    finalInput[6] = symbol
} else if (userInputNew == "3 2" && finalInput[7] == '_') {
    finalInput[7] = symbol
} else if (userInputNew == "3 3" && finalInput[8] == '_') {
    finalInput[8] = symbol
} else {
    println("This cell is occupied! Choose another one!")
    return "FALSE"
}
    return finalInput.joinToString("")
}

// take the input from the user
fun takeInputUser() : String {
    val scanner = Scanner(System.`in`)
    println("Enter the coordinates:")
    return scanner.nextLine().toUpperCase()
}

// check if the user has input the required characters YES/NO
fun checkInputUser(userInputNew : String): Boolean {
    var allIsOk = false
    val inputChars = userInputNew.split(" ")
    val x = inputChars[0].toInt()
    val y = inputChars[1].toInt()
    if (userInputNew.length == 3) {
        if (x in 1..3 && y in 1..3) {
            allIsOk = true
        } else {
            println("Coordinates should be from 1 to 3!")
        }
    } else {
        println("You should enter numbers!")
    }
    return allIsOk
}

//print the game moves and battlefield
fun battlefield(userInput : String) {
    repeat(9) {
        print("-")
    }
    println()
    println("| " + userInput[0] + " " + userInput[1] + " " + userInput[2] + " |")
    println("| " + userInput[3] + " " + userInput[4] + " " + userInput[5] + " |")
    println("| " + userInput[6] + " " + userInput[7] + " " + userInput[8] + " |")
    repeat(9) {
        print("-")
    }
    println()
}

//check the game Status still playing YES else NO
fun gameStatus(userInput: String): Boolean {
    val arrayInput = arrayOfNulls<String>(50)
    for (i in 0..8) {
        arrayInput[i] = userInput[i].toString()
    }
    val winningX = "XXX"
    val winningO = "OOO"
    var rowStatus = ""
    var colStatus = ""
    var diagStatus = ""
    var index: Int
    var countX = 0
    var countO = 0
//check each row
    for(i in 0..8 step 3) {
        index = i
        repeat(3) {
            rowStatus += arrayInput[index].toString()
            if(arrayInput[index]?.contains("X") == true) {
                countX++
            } else if(arrayInput[index]?.contains("O") == true) {
                countO++
            }
            index++
        }
        rowStatus += " "
    }
//check each column
    for (i in 0..2) {
        index = i
        repeat(3) {
            colStatus += arrayInput[index].toString()
            index += 3
        }
        colStatus += " "
    }
//check diagonal left to right
    index = 0
    repeat(3) {
        diagStatus += arrayInput[index].toString()
        index += 4
    }
    diagStatus += " "
//check diagonal right to left
    index = 2
    repeat(3) {
        diagStatus += arrayInput[index].toString()
        index += 2
    }
    diagStatus += " "

    val field = rowStatus + colStatus + diagStatus

    if ('_' in field && winningX !in field && winningO !in field && (-2 < (countX - countO) && (countX - countO) < 2)){
        return true
    } else if ('_' !in field && winningX !in field && winningO !in field) {
        print("Draw")
        return false
    } else if (winningX in field && winningO in field){
        print("Impossible")
        return false
    } else if (winningX in field) {
        print("X wins")
        return false
    } else if (winningO in field) {
        print("O wins")
        return false
    } else {
        print("Impossible")
        return false
    }
}