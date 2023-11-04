package com.example.homework2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "WelcomeScreen"
            ){
                composable("WelcomeScreen"){
                    WelcomeScreen(navController)
                }
                composable("SecondScreen"){
                    SecondScreen(navController)
                }

            }

        }
    }
}

@Composable
fun WelcomeScreen(navController: NavHostController) {
    Column (
    modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Welcome",
            //style = MaterialTheme.typography.titleMedium
            style = TextStyle(fontSize = 40.sp, color = Color.Black)
            )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("SecondScreen") // Navigate to the second screen
            }
        ) {
            Text("Continue")
        }

    }
}

@Composable
fun SecondScreen(navController: NavHostController){

    val citiesWithDescriptions = listOf(
        CityWithDescription("Yerevan", "The capital of Armenia with a rich history. Yerevan is one of the world's oldest continuously inhabited cities.",R.drawable.yerevan_city),
        CityWithDescription("Washington", "The capital of the United States, known for iconic landmarks like the White House and the Washington Monument.",R.drawable.washington_city),
        CityWithDescription("Madrid", "The vibrant capital of Spain, famous for its art, culture, and lively street life.",R.drawable.madrid_city),
        CityWithDescription("Paris", "The romantic city known for the Eiffel Tower, Louvre Museum, and exquisite cuisine.",R.drawable.paris_city),
        CityWithDescription("London", "The historic and diverse city in the UK, home to Buckingham Palace, the British Museum, and more.",R.drawable.london_city),
        CityWithDescription("Berlin", "The capital of Germany with a rich cultural scene, famous for the Berlin Wall and contemporary art.",R.drawable.berlin_city),
        CityWithDescription("Rome", "The eternal city with ancient history, including iconic sites like the Colosseum and Roman Forum.",R.drawable.rome_city),
        CityWithDescription("Tokyo", "The bustling metropolis of Japan, a city where tradition meets modern technology.",R.drawable.tokyo_city)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Cities",
            style = TextStyle(fontSize = 40.sp,color = Color.Black)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(){
            items(citiesWithDescriptions){city -> Cities(city)
            }
        }
        Button(onClick = {
            navController.navigate("WelcomeScreen")
        }
        ){
            Text(text = "Back")
        }


    }
}



@Composable
fun Cities(cityWithDescription: CityWithDescription){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = cityWithDescription.imageResource),
            contentDescription = "City Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = cityWithDescription.name,
            style = TextStyle(fontSize = 24.sp, color = Color.Black)
        )
        Text(
            text = cityWithDescription.description,
            style = TextStyle(fontSize = 16.sp, color = Color.Gray)
        )
        Divider(color = Color.Gray, thickness = 1.dp)
    }
}
@Preview
@Composable
fun WelcomeScreenPreview() {
    val navController = rememberNavController()
    WelcomeScreen(navController)
}
@Preview
@Composable
fun SecondScreenPreview(){
    val navController = rememberNavController()
    SecondScreen(navController)
}
data class CityWithDescription( val name: String,
                                val description: String,
                                val imageResource: Int
)
