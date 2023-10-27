package com.example.isalmiapp.hadeth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.isalmiapp.constant
import com.example.isalmiapp.databinding.FragmentHadethBinding


class HadethFragment : Fragment() {
    lateinit var ViewBinding: FragmentHadethBinding
    lateinit var adapter: AdapterHadeth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ViewBinding = FragmentHadethBinding.inflate(inflater, container, false)
        return ViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // readfile()

        adapter = AdapterHadeth(listhadeth)

        adapter.OnHadethClickListner = object : AdapterHadeth.OnItemClickListner {
            override fun OnItemClick(position: Int, items: String) {

                var Intent = Intent(activity, HadethContentActivity::class.java)

                Intent.putExtra(constant.EXTRA_HADETH_POSITION,position)
                Intent.putExtra(constant.EXTRA_HADETH_NAME, items)

                startActivity(Intent)
            }
        }


        ViewBinding.recyclerview.adapter = adapter
    }

        val allAhadeth= mutableListOf<DataClassHadeth>()
    fun readfile()
    {

        var fileName="ahadeth.txt"
        var filcontant=activity?.assets?.open(fileName)?.bufferedReader().use {
        it?.readText()
        }
        if (filcontant==null) return
         val ahadethContant=filcontant.trim().split("#")
        ahadethContant.forEach{singleHadethContent->

            val title =singleHadethContent.trim().substringBefore('\n')
            val content =singleHadethContent.trim().substringAfter('\n')

            Log.e("title",title)
            Log.e("content",content)
            val hadeth= DataClassHadeth(title, content)
            allAhadeth.add(hadeth)


        }

    }



}
         