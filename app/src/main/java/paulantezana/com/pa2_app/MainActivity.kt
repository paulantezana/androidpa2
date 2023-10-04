package paulantezana.com.pa2_app

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment()).commit()
            navigationView.setCheckedItem(R.id.nav_home)
        }
    }

    private fun checkPermition(){
//        ContextCompat.checkSelfPermission(
//            this,
//            Manifest.permission.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION
//        ) == PackageManager.PERMISSION_GRANTED -> {
//            layout.showSnackbar(
//                view,
//                getString(R.string.permission_granted),
//                Snackbar.LENGTH_INDEFINITE,
//                null
//            ) {}
//        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment()).commit()
            R.id.nav_file -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ArchivoFragment()).commit()
            R.id.nav_memory -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LibroFragment()).commit()
            R.id.nav_customer -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CustomerFragment()).commit()
            R.id.nav_product -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProductFragment()).commit()
            R.id.nav_sqlite -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SqLiteFragment()).commit()
        }
        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}