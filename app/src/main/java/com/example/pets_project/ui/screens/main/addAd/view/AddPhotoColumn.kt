package com.example.pets_project.ui.screens.main.addAd.view

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.pets_project.R
import com.example.pets_project.ui.screens.login.view.MarkButton
import com.example.pets_project.ui.screens.main.addAd.model.AddAdEvent
import com.example.pets_project.utils.ComposeFileProvider
import com.example.pets_project.viewModels.AddAdViewModel

@Composable
fun AddPhotoColumn(addAdViewModel: AddAdViewModel) {

    val context = LocalContext.current
    var imageUri: Uri? = null

    val photoLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
        when (it) {
            true -> {
                addAdViewModel.obtainEvent(AddAdEvent.PhotoChanged(imageUri!!))
            }
            false -> Log.e("take photo", "false")
        }
    }

    fun takePhoto() {
        val uri = ComposeFileProvider.getImageUri(context = context)
        imageUri = uri
        photoLauncher.launch(uri)
    }

    val getImageLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()){
        when (it!=null){
            true -> {
                addAdViewModel.obtainEvent(AddAdEvent.PhotoChanged(it))
            }
            false -> Log.e("take image", "false")
        }
    }

    fun getImage() {
        getImageLauncher.launch("image/*")
    }

    Column(
        modifier = Modifier
            .padding(start = 54.dp, end = 54.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        MarkButton(
            onClick = { takePhoto() },
            modifier = Modifier.fillMaxWidth(),
            stringResId = R.string.button_add_photo,
            painterResId = R.drawable.ic_photo,
            contentDescriptionResId = R.string.cd_add_ad_photo
        )
        MarkButton(
            onClick = { getImage() },
            modifier = Modifier.padding(top = 40.dp).fillMaxWidth(),
            stringResId = R.string.button_get_image,
            painterResId = R.drawable.ic_image,
            contentDescriptionResId = R.string.cd_add_ad_get_image
        )
    }
}
