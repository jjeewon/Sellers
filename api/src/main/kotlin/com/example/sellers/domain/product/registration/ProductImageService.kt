package com.example.sellers.domain.product.registration

import com.example.sellers.SellersException
import com.example.sellers.domain.product.ProductImage
import com.example.sellers.domain.product.ProductImageRepository
import net.coobird.thumbnailator.Thumbnails
import net.coobird.thumbnailator.geometry.Positions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import sun.rmi.runtime.Log
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@Service
class ProductImageService @Autowired constructor(
    private val productImageRepository: ProductImageRepository
){
    @Value("\${sellers.file-upload.default-dir}")
    var uploadPath: String? = ""

    fun uploadImage(image: MultipartFile)
    :ProductImageUploadResponse{
        val filePath = saveImageFile(image)
        val productImage = saveImageData(filePath)

        return productImage.id?.let {
            ProductImageUploadResponse(it, filePath)
        } ?: throw SellersException("이미지 저장 실패. 다시 시도해주세요. ")
    }

    private fun saveImageFile(image: MultipartFile): String{
        val extension = image.originalFilename
            ?.takeLastWhile { it != '.' }
            ?: throw SellersException("다른 이미지로 다시 시도해주세요.")
        val uuid = UUID.randomUUID().toString()
        val date = SimpleDateFormat("yyyyMMdd").format(Date())

        //val filePath = "/images/$date/$uuid.$extension"
        val filePath  = "/Users/jiwon/Documents/documents/GitHub/Sellers/$date/$uuid.$extension"
        //val targetFile = File("$uploadPath/$filePath")
        val targetFile = File("$filePath")
        val thumbnail = targetFile.absolutePath
            .replace(uuid, "$uuid-thumb")
            .let(::File)
        val isOk = targetFile.parentFile.mkdirs()
        try{
            image.transferTo(targetFile)
        }catch (e: Exception){
            System.out.println("error"+e.message)
        }
        Thumbnails.of(targetFile)
            .crop(Positions.CENTER)
            .size(300, 300)
            .outputFormat("jpg")
            .outputQuality(0.8f)
            .toFile(thumbnail)
        return filePath
    }

    private fun saveImageData(filePath: String): ProductImage {
        val productImage = ProductImage(filePath)
        return productImageRepository.save(productImage)
    }
}