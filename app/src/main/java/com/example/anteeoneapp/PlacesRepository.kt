package com.example.anteeoneapp

object PlacesRepository{

    var placesList:ArrayList<Place> = arrayListOf(Place(1,"Hello","dfds ksdjhf shdjfks djfhsj fdhjskf"),
                                                  Place(2,"Kek Sdsa","sdfsjkdlfk jdklskd jfkdlskdjf kslkd fjkdljfkdlss"),
                                                  Place(3,"Bucje","sdfsjkdlfk jdklskd jfkdlskdjf kslkd fjkdljfkdlss"),
                                                  Place(4,"Cdsa","sdfsjkdlfk jdklskd jfkdlskdjf kslkd fjkdljfkdlss"),
                                                  Place(5,"Keasa","sdfsjkdlfk jdklskd jfkdlskdjf kslkd fjkdljfkdlss"),
                                                  Place(6,"Keasda","sdfsjkdlfk jdklskd jfkdlskdjf kslkd fjkdljfkdlss"),
                                                  Place(7,"Kedssa","sdfsjkdlfk jdklskd jfkdlskdjf kslkd fjkdljfkdlss"),
                                                  Place(8,"Kesa","sdfsjkdlfk jdklskd jfkdlskdjf kslkd fjkdljfkdlss"))

    fun addPlace(index:String?,place:Place){
        if(index!=null){
            if (index==""){
                placesList.add(place)
            }
            else{
                if (index.toInt() <= placesList.size){
                    placesList.add(index.toInt(),place)
                }
                else{
                    placesList.add(place)
                }
            }
        }
        else{
            placesList.add(place)
        }

    }
}