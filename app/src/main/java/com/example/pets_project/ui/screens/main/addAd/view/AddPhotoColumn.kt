package com.example.pets_project.ui.screens.main.addAd.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import com.example.pets_project.R
import com.example.pets_project.ui.screens.login.view.MarkButton
import com.example.pets_project.viewModels.AddAdViewModel

@Composable
fun AddPhotoColumn(addAdViewModel: AddAdViewModel) {

    class TakePhoto : ActivityResultContract<Int, Uri?>() {

        override fun createIntent(context: Context, input: Int): Intent {
            return Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                .putExtra(MediaStore.EXTRA_OUTPUT, input)
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
            return intent.takeIf { resultCode == Activity.RESULT_OK }?.data
        }
    }

    val launcher = rememberLauncherForActivityResult(TakePhoto()) { uri: Uri? ->
        uri?.let { imageUri ->
            when (imageUri != null) {
                true -> Log.e("take photo", "$imageUri")
                false -> Log.e("take photo", "false")
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        MarkButton(
            onClick = { launcher.launch(0) },
            modifier = Modifier,
            stringResId = R.string.button_add_photo,
            painterResId = R.drawable.ic_file_download,
            contentDescriptionResId = R.string.cd_load_image
        )
    }
}
