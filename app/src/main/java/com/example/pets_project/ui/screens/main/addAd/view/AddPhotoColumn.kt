package com.example.pets_project.ui.screens.main.addAd.view

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.pets_project.R
import com.example.pets_project.ui.screens.login.view.MarkButton
import com.example.pets_project.ui.screens.main.addAd.model.AddAdEvent
import com.example.pets_project.utils.ComposeFileProvider
import com.example.pets_project.viewModels.AddAdViewModel

@Composable
fun AddPhotoColumn(addAdViewModel: AddAdViewModel) {

    val context = LocalContext.current
    var imageUri: Uri? = null

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
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
        launcher.launch(uri)
    }

    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        MarkButton(
            onClick = { takePhoto() },
            modifier = Modifier,
            stringResId = R.string.button_add_photo,
            painterResId = R.drawable.ic_file_download,
            contentDescriptionResId = R.string.cd_add_ad_photo
        )
    }
}
