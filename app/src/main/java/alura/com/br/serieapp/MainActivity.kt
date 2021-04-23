package alura.com.br.serieapp

import alura.com.br.serieapp.adapter.MyListAdapter
import alura.com.br.serieapp.ui.mylist.MyListViewModel
import alura.com.br.serieapp.ui.mylist.MyListViewModelFactory
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_layout.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

const val TAKE_PICTURE = 1

class MainActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    var currentPath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)

        setupNavController()
        configDrawable()
    }
    private fun setupNavController() {
        val view: BottomNavigationView = findViewById(R.id.bottom_nav_bar)
        val hostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val controller = hostFragment.navController

        view.setupWithNavController(controller)
    }

    private fun configDrawable(){
        toggle = ActionBarDrawerToggle(this,container_drawer,R.string.abrir,R.string.fechar)
        container_drawer.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        menu_lateral_view.setNavigationItemSelectedListener {
            when(it.itemId) {
//                R.id.favorito_menu_lateral-> abrirFavoritos()
                R.id.camera_menu_lateral -> abrirCamera()
//                R.id.galeria_menu_lateral -> abrirGaleria()
//                R.id.config_menu_lateral -> abrirConfiguracao()
//                R.id.mapa_menu_lateral -> abrirMapa()
            }
            true
        }

    }

    private fun abrirCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(intent.resolveActivity(packageManager) != null){
            var photoFile: File? = null
            try {
                photoFile = criaImagem()
            }catch (e:IOException){
                e.printStackTrace()
            }
            if(photoFile != null){
                val photoUri = FileProvider.getUriForFile(this, "alura.com.br.serieapp.fileprovider",photoFile)
                intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri)
                startActivityForResult(intent, TAKE_PICTURE)
            }
        }
    }

    private fun criaImagem(): File {
        val timeStemp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageName = "JPEG_"+timeStemp+"_"
        var storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        var image = File.createTempFile(imageName,".jpg",storageDir)
        currentPath = image.absolutePath
        return image
    }


}