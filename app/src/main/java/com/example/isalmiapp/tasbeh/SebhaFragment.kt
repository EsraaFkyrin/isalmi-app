package com.example.isalmiapp.tasbeh

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.isalmiapp.R
import com.example.isalmiapp.constant
import com.example.isalmiapp.databinding.FragmentHadethBinding
import com.example.isalmiapp.databinding.FragmentSebhaBinding


class SebhaFragment : Fragment() {

    lateinit var img_view: ImageView
    lateinit var txtview_convert: TextView
    lateinit var txtview_counter: TextView
    lateinit var viewBinding: FragmentSebhaBinding
    var counter = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentSebhaBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtview_convert = viewBinding.tvConvertSobhanAaalh
        txtview_convert.text= constant.SOBHANALLAH
        img_view = viewBinding.imgBodySebha
        txtview_counter = viewBinding.TheNumber
        txtview_counter.text = "$counter"



        img_view.setOnClickListener(View.OnClickListener {
            OnClickSebhaImage()

        })
    }


    fun OnClickSebhaImage() {
        img_view.rotation = img_view.rotation + 5

        counter++
        txtview_counter.text = "$counter"

        if(txtview_convert.text==constant.ELKETMA)
        {
            txtview_convert.text = constant.SOBHANALLAH
            counter = 0
            txtview_counter.text = "$counter"
        }

        if (counter == 33) {
            when (txtview_convert.text) {
                constant.SOBHANALLAH -> {
                    txtview_convert.text = constant.ELHAMDOLLAH
                    counter = 0
                    txtview_counter.text = "$counter"
                }
                constant.ELHAMDOLLAH -> {
                    txtview_convert.text=constant.ALLAHAKBR
                    counter = 0
                    txtview_counter.text = "$counter"

                }
                constant.ALLAHAKBR -> {
                    txtview_convert.text=constant.AISTIGHFAR
                    counter = 0
                    txtview_counter.text = "$counter"
                }
                constant.AISTIGHFAR -> {
                    txtview_convert.text=constant.SALAH
                    counter = 0
                    txtview_counter.text = "$counter"
                }
                constant.SALAH -> {
                    txtview_convert.text=constant.ELKETMA
                    counter = 0
                    txtview_counter.text = "$counter"
                }
                constant.ELKETMA -> {
                    txtview_convert.text=constant.ELHAMDOLLAH
                    counter = 0
                    txtview_counter.text = "$counter"
                }

            }

        }

    }






                }
         