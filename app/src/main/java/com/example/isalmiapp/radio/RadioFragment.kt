package com.example.isalmiapp.radio

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.isalmiapp.api.ApiManager
import com.example.isalmiapp.api.Radio
import com.example.isalmiapp.api.RadioResponse
import com.example.isalmiapp.databinding.FragmentRadioBinding
import com.example.isalmiapp.player.PlayerService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.Connection


class RadioFragment : Fragment() {

    val adapter=RadioAdapter()
    lateinit var viewBinding: FragmentRadioBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding= FragmentRadioBinding.inflate(inflater,container,false)
        return viewBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.radioRv.adapter=adapter

        adapter.onItemClickPlay=object:RadioAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, item: Radio) {
                StartRadioServices(item)
            }

        }
        adapter.onItemClickStop=object :RadioAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, item: Radio) {
                startPlayServices()
            }
        }
        getChannelsFromApi()


    }


    private fun StartRadioServices(item: Radio) {

    }

    private fun startPlayServices() {
        if(bound)
            service.pauseMediaPlayer()
    }



    private fun getChannelsFromApi() {
        Log.e("GetApi","getapi")
        ApiManager.getWebServices()
            .getRadio()
            .enqueue(object :Callback<RadioResponse>{
                override fun onResponse(
                    call: Call<RadioResponse>,
                    response: Response<RadioResponse>
                ) {
                    Log.e("response",response.body()?.radios.toString())
                   val channel=response.body()?.radios
                    adapter.changeData(channel?: listOf())

                }

                override fun onFailure(call: Call<RadioResponse>, t: Throwable) {
                   Toast.makeText(activity,t.localizedMessage?:"error",Toast.LENGTH_LONG)
                       .show()
                }

            })

    }

    var bound=false
    lateinit var service: PlayerService
    private fun startPlayServices(item:Radio) {
      if (bound&& item.url!=null &&item.name!=null)
          service.startMediaPlayer(item.url,item.name)

    }



    override fun onStart() {
        super.onStart()
        startServices()
        bindServices()
    }

    private fun bindServices() {
         val intent=Intent(activity,PlayerService::class.java)
         activity?.bindService(intent,connection,Context.BIND_AUTO_CREATE)
    }


    private val connection=object :ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder=service as PlayerService.MYBinder
        //--->    service=binder.getServices()
            bound=true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
          bound=false
        }

    }
    private fun startServices() {
        val  intent=Intent(activity,PlayerService::class.java)
        activity?.startService(intent)
    }

    override fun onStop() {
        super.onStop()
       // activity?.unbindService(Connection)
    }


}
         