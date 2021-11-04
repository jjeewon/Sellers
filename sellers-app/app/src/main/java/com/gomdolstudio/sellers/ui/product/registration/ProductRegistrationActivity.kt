package com.gomdolstudio.sellers.ui.product.registration

import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.ParcelFileDescriptor
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gomdolstudio.sellers.databinding.ActivityProductregistrationBinding
import com.gomdolstudio.sellers.ui.product.ProductMainActivity
import dagger.android.support.DaggerAppCompatActivity
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileDescriptor
import java.io.FileOutputStream
import javax.inject.Inject
import com.gomdolstudio.sellers.util.bitmapToFile

class ProductRegistrationActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var binding: ActivityProductregistrationBinding
    @Inject
    lateinit var viewModelProvider: ViewModelProvider
    private lateinit var productRegistraionViewModel: ProductRegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productRegistraionViewModel = viewModelProvider.get(ProductRegistrationViewModel::class.java)
        binding.lifecycleOwner = this
        binding.productRegistrationViewModel = productRegistraionViewModel

        productRegistraionViewModel.inputName.observe(this, Observer { name ->
            productRegistraionViewModel.updateNameLength(
                name
            )
        })
        productRegistraionViewModel.inputDescription.observe(this, Observer { description ->
            productRegistraionViewModel.updateDescriptionLength(
                description
            )
        })
         productRegistraionViewModel.getImageViewClickEvent().observe(this, Observer { num ->
             getImageFromGallery(
                 num
             )
         })
        productRegistraionViewModel.getShowToast().observe(this,
            Observer {text -> Toast.makeText(this, text, Toast.LENGTH_LONG).show()})
        productRegistraionViewModel.getMoveToProductMainEvent().observe(this,
            Observer {
                startActivity(Intent(this@ProductRegistrationActivity, ProductMainActivity::class.java))
                finish()})
    }
    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let{
            val bitmap = getBitmapFromUri(uri)
            bitmap?.let {
                when(productRegistraionViewModel.current_iv_id){
                    1 -> binding.iv1.setImageBitmap(bitmap)
                    2 -> binding.iv2.setImageBitmap(bitmap)
                    3 -> binding.iv3.setImageBitmap(bitmap)
                    4 -> binding.iv4.setImageBitmap(bitmap)
                }
                val file = bitmapToFile(bitmap, "jiji.png",getExternalFilesDir(Environment.DIRECTORY_DCIM).toString())
                productRegistraionViewModel.imageUpload(file!!)
            }
        }
    }

    fun getImageFromGallery(imageNum: Int){
        productRegistraionViewModel.current_iv_id = imageNum
        getContent.launch("image/*")
    }
    private fun getBitmapFromUri(uri: Uri): Bitmap? {
        val parcelFileDescriptor: ParcelFileDescriptor? =
            contentResolver.openFileDescriptor(uri, "r")
        parcelFileDescriptor?.let{
            val fileDescriptor: FileDescriptor = parcelFileDescriptor.fileDescriptor
            val image: Bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)
            parcelFileDescriptor.close()
            return image
        }
        return null
    }
}
