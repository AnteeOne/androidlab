package com.example.anteeoneapp

open class User (val name:String,
                 val surname:String,
                  val age:Int  ){

    fun calculateNextAge() = this.age+1

}
interface canWatchAnime{
    fun watchJoJo():String
}
class Student(name:String,surname: String,age:Int,var isHungry:Boolean): User(name,surname,age),canWatchAnime  {

    override fun watchJoJo() = "It's me,DIO!"


}
