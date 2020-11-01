package com.example.anteeoneapp

object PlacesReposytory{

    var placesList:ArrayList<Place> = arrayListOf(Place(1,"Hello","dfds ksdjhf shdjfks djfhsj fdhjskf"),
                                                  Place(2,"Kek Sdsa","sdfsjkdlfk jdklskd jfkdlskdjf kslkd fjkdljfkdlss"),
                                                  Place(3,"Bucje","sdfsjkdlfk jdklskd jfkdlskdjf kslkd fjkdljfkdlss"),
                                                  Place(4,"Cdsa","sdfsjkdlfk jdklskd jfkdlskdjf kslkd fjkdljfkdlss"),
                                                  Place(5,"Keasa","sdfsjkdlfk jdklskd jfkdlskdjf kslkd fjkdljfkdlss"),
                                                  Place(6,"Keasda","sdfsjkdlfk jdklskd jfkdlskdjf kslkd fjkdljfkdlss"),
                                                  Place(7,"Kedssa","sdfsjkdlfk jdklskd jfkdlskdjf kslkd fjkdljfkdlss"),
                                                  Place(8,"Kesa","sdfsjkdlfk jdklskd jfkdlskdjf kslkd fjkdljfkdlss"))


    var placesList2:ArrayList<Place> = arrayListOf(Place(1,"Hello","dfds ksdjhf shdjfks djfhsj fdhjskf"),
        Place(21,"Sdsa","sdfsjkdlfk jdklskd jfkdlskdjf kslkd fjkdljfkdlss"),
        Place(13,"Be","sdfsjkdlfk jdklskd jfkdlskdjf kslkd fjkdljfkdlss"),
        Place(41,"Cdfsdsa","sdfsjkdlfk jdklskd jfkdlskdjf kslkd fjkdljfkdlss"),
        Place(51,"Keasa","sdfsjkdlfk jdklskd jfkdlskdjf kslkd fjkdljfkdlss"),
        Place(61,"Keasda","sdfsjkdlfk jdklskd jfkdlskdjf kslkd fjkdljfkdlss"),
        Place(71,"Kedssa","sdfsjkdlfk jdklskd jfkdlskdjf kslkd fjkdljfkdlss"),
        Place(73,"Kesa","sdfsjkdlfk jdklskd jfkdlskdjf kslkd fjkdljfkdlss"))

    fun getPlaces(): ArrayList<Place> = placesList

    fun getPlaces2(): ArrayList<Place> = placesList2

    fun add(index:Int,place:Place):ArrayList<Place>{
        if(index >= placesList.size){
            placesList.add(placesList.size,place)
        }
        else{
            placesList.add(index,place)
        }
        return this.placesList
    }

}