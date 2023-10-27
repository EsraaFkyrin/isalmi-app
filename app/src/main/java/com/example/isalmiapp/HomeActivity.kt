package com.example.isalmiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.isalmiapp.databinding.ActivityHomeBinding
import com.example.isalmiapp.radio.RadioFragment
import com.example.isalmiapp.tasbeh.SebhaFragment
import com.example.isalmiapp.hadeth.HadethFragment
import com.example.islamy_project.quran.QuranFragment

class HomeActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        selectedItem()
        viewBinding.contantView.bottomNavigation.selectedItemId=R.id.nav_quraan



    }

    private fun selectedItem() {
        viewBinding.contantView.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_quraan -> {
                    showfragment(QuranFragment())


                }
                R.id.nav_hadeth -> {
                    showfragment(HadethFragment())
                }

                R.id.nav_sebha -> {
                    showfragment(SebhaFragment())
                }
                R.id.nav_radio -> {
                    showfragment(RadioFragment())
                }

            }
            return@setOnItemSelectedListener true

        }
    }

        private  fun showfragment(Fragment: Fragment){
            supportFragmentManager
                .beginTransaction()
                //framelayout > the place show on the fragment
                .replace(R.id.FrameLayout_fragment_container,Fragment)
                .commit()


        }
    }
