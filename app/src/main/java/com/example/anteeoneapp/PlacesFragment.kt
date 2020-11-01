package com.example.anteeoneapp

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_places.*

class PlacesFragment: Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_places,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragment_places_list.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        fragment_places_list.adapter = PlaceAdapter(PlacesReposytory.getPlaces())
        var placeAdapter : PlaceAdapter = PlaceAdapter(PlacesReposytory.placesList)
        fragment_places_add_button.setOnClickListener{
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setView(R.layout.places_dialog)
            placeAdapter.updateData(PlacesReposytory.getPlaces2())
//            builder.setCancelable(false)
//                .setPositiveButton("OK",DialogInterface.OnClickListener{
//                    dialogInterface: DialogInterface, i: Int ->
//                    fun onClick(dialog: DialogInterface,i:Int){
//                        var place = Place(places_dialog_edit_position.id,
//                                          places_dialog_edit_title.text.toString(),
//                                          places_dialog_edit_description.text.toString())
//                        fragment_home_textview.text = places_dialog_edit_title.text
//                        var placeAdapter : PlaceAdapter = PlaceAdapter(PlacesReposytory.placesList)
//                        placeAdapter.updateData(PlacesReposytory.add(3,Place(3,"23423","23423")))
//
//                    }
//                })
//                .setNegativeButton("Cancel",DialogInterface.OnClickListener{
//                    dialogInterface: DialogInterface, i: Int ->
//                    fun onClick(dialog: DialogInterface,i:Int){
//                        dialog.cancel()
//                    }
//                })
//            val alertDialog:AlertDialog = builder.create()
//            alertDialog.show()
        }

        swipeRefreshLayout.setOnRefreshListener {
            placeAdapter.notifyDataSetChanged()
            swipeRefreshLayout.isRefreshing = false
        }



    }
}