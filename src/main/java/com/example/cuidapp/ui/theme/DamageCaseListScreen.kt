package com.example.cuidapp.ui.theme

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.example.cuidapp.data.DamageCase
import androidx.compose.ui.unit.dp


@Composable
fun DamageCaseListScreen(
    viewModel: DamageCaseViewModel = viewModel(),
    onAddCaseClick: () -> Unit // Navegación a pantalla de agregar caso
) {
    val cases by viewModel.cases.observeAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Casos de Daños") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddCaseClick) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar Caso")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            items(cases) { case ->
                DamageCaseItem(case, onClick = {
                    // Aquí puedes abrir otra pantalla con más detalles
                })
            }
        }
    }
}


@Composable
fun DamageCaseItem(case: DamageCase, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = 6.dp,
        shape = RoundedCornerShape(12.dp) // Bordes redondeados
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = case.titulo, style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = case.descripcion, style = MaterialTheme.typography.body2, maxLines = 2, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Estado: ${case.estado}", style = MaterialTheme.typography.caption, fontWeight = FontWeight.Light)
        }
    }
}

