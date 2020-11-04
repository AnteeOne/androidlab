package com.example.anteeoneapp

object CardsRepository {

    val imagesList_1 : List<Int> = listOf(R.drawable.photo1,
                                            R.drawable.photo2,
                                            R.drawable.photo3,
                                            R.drawable.photo4)
    val imagesList_2 : List<Int> = listOf(R.drawable.photo1,
                                            R.drawable.photo2,
                                            R.drawable.photo3)
    val cardsList : List<PhotosCard> = listOf(
        PhotosCard("Post #1","sjdfls;dkf d df d s fd sd a dsdsasd sd a s sd al;sdkfl;sdkf", imagesList_1),
        PhotosCard("Post #2","cxnvmxc,xsdfsdfsdfsdfsdfs df d d fd ds s dncvxcv c c  cx  kf", imagesList_2),
        PhotosCard("Post #3","werw er  we re were we rew er ", imagesList_2),
        PhotosCard("Post #4","wew eee rerere erweasds sddsds asdasd asdasd dsasd ds asrwerwerwer", imagesList_1)
    )
}