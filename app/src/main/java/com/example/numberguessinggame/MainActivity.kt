package com.example.numberguessinggame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numberguessinggame.ui.theme.NumberGuessingGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumberGuessingGameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    NumberGuessingGameScreen()
                }
            }
        }
    }
}

var randNum: Int = (1..1000).random()
var count = 0

@Composable
fun NumberGuessingGameScreen() {
    var numGuess by remember { mutableStateOf("") }
    val Guess = numGuess.toIntOrNull() ?: 0
    val output = randomGuess(Guess, randNum)


    Column(
        modifier = Modifier.padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.title_game),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(194.dp))
        EnterNumber(value = numGuess, onValueChange = { numGuess = it })
        Spacer(Modifier.height(10.dp))
        Button(onClick = { randNum = (1..1000).random() }) {
            Text(text = stringResource(R.string.try_again), fontSize = 10.sp)
        }
        Spacer(Modifier.height(10.dp))

        if (Guess != randNum) {
            Text(
                text = stringResource(R.string.guess_output, output),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = colorResource(R.color.grey)
            )
            count++
//            println(randNum)
//            println(count)

        } else {
            Text(
                text = stringResource(R.string.correct, count),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            count = 0
        }
    }
}

@Composable
fun EnterNumber(
    value: String,
    onValueChange: (String) -> Unit,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.input_guess)) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = colorResource(R.color.white)
        )
    )
}

private fun randomGuess(
    Guess: Int, randNum: Int
): String {
    var result: String = ""
    if (Guess > randNum) {
        result = " lower!"
    } else if (Guess < randNum) {
        result = " higher!"
    } else {
        result = " correct!"
    }
    return result
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NumberGuessingGameTheme {
        NumberGuessingGameScreen()
    }
}