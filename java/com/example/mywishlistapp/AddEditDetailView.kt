package com.example.mywishlistapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.material.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mywishlistapp.Data.Wish
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: WishViewModel,
    navController: NavController
) {
    val snackmessage = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    if (id != 0L) {
        val wish = viewModel.getAWishbyid(id).collectAsState(initial = Wish(0L, "", ""))
        viewModel.WishTitleState = wish.value.title
        viewModel.WishDescriptionState = wish.value.description
    }else {
        viewModel.WishTitleState = ""
        viewModel.WishDescriptionState = ""
    }

    Scaffold(
        topBar = {
            AppBarView(
                title = if (id != 0L) stringResource(id = R.string.update_wish)
                else stringResource(id = R.string.add_wish)
            )
            {
                navController.navigateUp()
            }},
        scaffoldState = scaffoldState
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            WishTextField(
                label = "Title",
                value = viewModel.WishTitleState,
                onValueChange = { viewModel.WishTitleChange(it) }
            )

            Spacer(modifier = Modifier.height(10.dp))

            WishTextField(
                label = "Description",
                value = viewModel.WishDescriptionState,
                onValueChange = { viewModel.WishDescriptionChange(it) }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = {
                if (viewModel.WishTitleState.isNotEmpty() && viewModel.WishDescriptionState.isNotEmpty()) {
                    if (id != 0L) {
                        // Update the wishlist
                        viewModel.updateAWish(
                            Wish(
                                id = id,
                                title = viewModel.WishTitleState.trim(),
                                description = viewModel.WishDescriptionState.trim()
                            )
                        )
                    }
                    else{
                        // Add the wishlist
                        viewModel.addAWish(
                            Wish(
                                title = viewModel.WishTitleState.trim(),
                                description = viewModel.WishDescriptionState.trim()
                            )
                        )
                        snackmessage.value = "Wish has been created"
                    }
                }
                else {
                    //Enter fields to create a wish
                    snackmessage.value = "Enter the fields to create a wish"
                }
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(snackmessage.value)
                    navController.navigateUp()
                }

            }) {
                Text(
                    text = if (id != 0L) stringResource(id = R.string.update_wish)
                    else stringResource(id = R.string.add_wish),
                    style = TextStyle(fontSize = 18.sp)
                )
            }
        }
    }
}

@Composable
fun WishTextField(  // Custom TextField for Wish
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, color = Color.Black) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),  // Set the user's keyboard type to Text
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            focusedLabelColor = colorResource(id = R.color.black),
            unfocusedBorderColor = Color.Black
        )
    )
}
