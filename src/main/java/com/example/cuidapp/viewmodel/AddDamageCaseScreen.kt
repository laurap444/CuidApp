package com.example.cuidapp.viewmodel

import android.app.Activity
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cuidapp.viewmodel.DamageCaseViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.example.cuidapp.data.DamageCase
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import android.content.Intent
import android.provider.MediaStore


@Composable
fun AddDamageCaseScreen(
    viewModel: DamageCaseViewModel = viewModel(),
    onReportSaved: () -> Unit // Acción después de guardar el reporte
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Nuevo Reporte", style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 3
        )

        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                val REQUEST_IMAGE_PICK = 100
                (context as? Activity)?.startActivityForResult(intent, REQUEST_IMAGE_PICK)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Seleccionar Imagen")
        }

        imageUri?.let { uri ->
            Image(
                painter = rememberImagePainter(uri),
                contentDescription = "Imagen seleccionada",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
            )
        }

        Button(
            onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    viewModel.addDamageCase(title, description, imageUri?.toString() ?: "")
                    onReportSaved()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Reporte")
        }
    }
}

