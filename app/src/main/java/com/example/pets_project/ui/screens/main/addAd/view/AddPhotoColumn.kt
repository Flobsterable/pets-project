package com.example.pets_project.ui.screens.main.addAd.view

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import android.widget.Toast
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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState

@Composable
@OptIn(ExperimentalPermissionsApi::class)
fun AddPhotoColumn(addAdViewModel: AddAdViewModel) {

    val context = LocalContext.current
    val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    val galleryPermissionState = rememberPermissionState(permission = Manifest.permission.READ_EXTERNAL_STORAGE)
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

        when (cameraPermissionState.status) {
            is PermissionStatus.Denied -> {
                cameraPermissionState.launchPermissionRequest()
            }
            PermissionStatus.Granted -> {
                val uri = ComposeFileProvider.getImageUri(context = context)
                imageUri = uri
                photoLauncher.launch(uri)
            }
        }
    }

    val getImageLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {

        when (it != null) {
            true -> {
                addAdViewModel.obtainEvent(AddAdEvent.PhotoChanged(it))
            }
            false -> Log.e("take image", "false")
        }
    }

    fun getImage() {

        when (galleryPermissionState.status) {
            is PermissionStatus.Denied -> {
                galleryPermissionState.launchPermissionRequest()
            }
            PermissionStatus.Granted -> {
                getImageLauncher.launch("image/*")
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(start = 47.dp, end = 47.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        MarkButton(
            onClick = { takePhoto() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            stringResId = R.string.button_add_photo,
            painterResId = R.drawable.ic_photo,
            contentDescriptionResId = R.string.cd_add_ad_photo
        )
        MarkButton(
            onClick = { getImage() },
            modifier = Modifier
                .padding(top = 40.dp)
                .fillMaxWidth()
                .height(56.dp),
            stringResId = R.string.button_get_image,
            painterResId = R.drawable.ic_image,
            contentDescriptionResId = R.string.cd_add_ad_get_image
        )
    }
}

fun Context.openSettings() {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    val uri = Uri.fromParts("package", packageName, null)
    intent.data = uri
    startActivity(intent)
}
