package com.fdiazd.mycard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fdiazd.mycard.ui.theme.MyCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    CreateCard ()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun CreateCard() {
    val buttonClickedState = remember {
        mutableStateOf(value = false)
    }
    Surface(modifier= Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Card(modifier = Modifier
            .width(200.dp)
            .height(390.dp)
            .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            backgroundColor = Color.White,
            elevation = 4.dp) {

            Column(modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {
                CreateImageProfile()
                Divider()
                CreateInfo()
                Button(onClick = {
                    buttonClickedState.value = !buttonClickedState.value
                }
                ) {
                    Text(text = "Portfolio",
                    style = MaterialTheme.typography.button)
                }
                if (buttonClickedState.value){
                    Content()
                }
                else{
                    Box{}
                }
            }
        }
    }
}

@Preview
@Composable
fun Content(){
    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(5.dp)) {
        Surface(modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
                shape = RoundedCornerShape(corner = CornerSize(6.dp)),
                border = BorderStroke(width = 2.dp,
                    color = Color.LightGray)) {

                Portfolio(data = listOf())
        }
    }
    
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn {
        // Add a single item
        item {
            CreateItemPortfolio("Developer Info App",
                "Shows info and portfolio's developer", R.drawable.developer)
        }

        item {
            CreateItemPortfolio("Tax Calculator",
                "Application to calculate taxes", R.drawable.invoice)
        }

        item {
            CreateItemPortfolio("Star Wars Wiki",
                "Application to discover the Star Wars universe", R.drawable.starwars)
        }

    }
}

@Composable
private fun CreateItemPortfolio(work: String, description: String, image: Int) {
    Card(modifier = Modifier
        .padding(13.dp)
        .fillMaxWidth(),
        shape = RectangleShape,
        elevation = 4.dp) {
        Row(modifier = Modifier
            .padding(8.dp)
            .background(MaterialTheme.colors.surface)
            .padding(7.dp)) {

            Surface(modifier = Modifier
                .size(90.dp)
                .padding(5.dp),
                shape = CircleShape,
                border = BorderStroke(0.5.dp, Color.LightGray),
                elevation = 4.dp,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)) {

                Image(painter = painterResource(id = image),
                    contentDescription = "profile image",
                    modifier = Modifier.size(95.dp),
                    contentScale = ContentScale.Crop)

            }
            Column(modifier = Modifier
                .padding(7.dp)
                .align(alignment = Alignment.CenterVertically)) {

                Text(text = work,
                    fontWeight = FontWeight.Bold)
                Text(text = description,
                    style = MaterialTheme.typography.body2)
            }

        }
    }
}

@Composable
private fun CreateInfo() {
    Column(modifier = Modifier.padding(5.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Fernando Díaz",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primaryVariant)

        Text(text = "Android Developer",
            modifier = Modifier.padding(5.dp))

        Text(text = "hola@fernandodiaz.dev",
            modifier = Modifier.padding(5.dp))

    }
}

@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
    Surface(modifier = Modifier
        .size(150.dp)
        .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = 4.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)) {

        Image(painter = painterResource(id = R.drawable.avatar),
            contentDescription = "profile image",
            modifier = Modifier.size(135.dp),
            contentScale = ContentScale.Crop)
    }
}

//@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyCardTheme {
        CreateCard()
    }
}