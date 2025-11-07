package com.example.baitap01

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baitap01.ui.theme.BaiTap01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaiTap01Theme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    var numberInput by remember { mutableStateOf("") }
    var stringInput by remember { mutableStateOf("") }
    var outputText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.student),
            contentDescription = "Hình sinh viên",
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
        )
        Text(
            text = "Nguyễn Thái Hưng",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Sinh viên Khoa CNTT - ĐHSPKT TPHCM",
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        Divider(thickness = 2.dp)
        TextField(
            value = numberInput,
            onValueChange = { numberInput = it },
            label = { Text("Nhập dãy số cách nhau bởi khoảng trắng") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = {
            val arr = numberInput.split(" ").mapNotNull { it.toIntOrNull() }
            val chan = arr.filter { it % 2 == 0 }
            val le = arr.filter { it % 2 != 0 }
            Log.d("BaiTap01", "Số chẵn: $chan")
            Log.d("BaiTap01", "Số lẻ: $le")
            outputText = "Số chẵn: $chan\nSố lẻ: $le"
        })
        {
            Text("In Log.d số lẻ / chẵn")
        }
        TextField(
            value = stringInput,
            onValueChange = { stringInput = it },
            label = { Text("Nhập chuỗi bất kỳ") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = {
            val reversed = stringInput
                .trim()
                .split(" ")
                .reversed()
                .joinToString(" ")
                .uppercase()
            Toast.makeText(context, reversed, Toast.LENGTH_SHORT).show()
            outputText = "Chuỗi đảo ngược: $reversed"
        })
        {
            Text("Đảo ngược chuỗi")
        }
        Text(
            text = outputText,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }
}
