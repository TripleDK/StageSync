package com.example.mtapp.data.local.exercises

import com.example.mtapp.Models.exercises.MenuItem.EntreeItem
import com.example.mtapp.Models.exercises.MenuItem.AccompanimentItem
import com.example.mtapp.Models.exercises.MenuItem.SideDishItem
import com.example.mtapp.Models.exercises.Dessert
import com.example.mtapp.R

class Datasource {
    //    fun loadAffirmations(): List<Affirmation> {
//        return listOf<Affirmation>(
//            Affirmation(R.string.affirmation1, R.drawable.image1),
//            Affirmation(R.string.affirmation2, R.drawable.image2),
//            Affirmation(R.string.affirmation3, R.drawable.image3),
//            Affirmation(R.string.affirmation4, R.drawable.image4),
//            Affirmation(R.string.affirmation5, R.drawable.image5),
//            Affirmation(R.string.affirmation6, R.drawable.image6),
//            Affirmation(R.string.affirmation7, R.drawable.image7),
//            Affirmation(R.string.affirmation8, R.drawable.image8),
//            Affirmation(R.string.affirmation9, R.drawable.image9),
//            Affirmation(R.string.affirmation10, R.drawable.image10),
//        )
//    }
//
//    val topics = listOf(
//        Topic(R.string.architecture, 58, R.drawable.architecture),
//        Topic(R.string.crafts, 121, R.drawable.crafts),
//        Topic(R.string.business, 78, R.drawable.business),
//        Topic(R.string.culinary, 118, R.drawable.culinary),
//        Topic(R.string.design, 423, R.drawable.design),
//        Topic(R.string.fashion, 92, R.drawable.fashion),
//        Topic(R.string.film, 165, R.drawable.film),
//        Topic(R.string.gaming, 164, R.drawable.gaming),
//        Topic(R.string.drawing, 326, R.drawable.drawing),
//        Topic(R.string.lifestyle, 305, R.drawable.lifestyle),
//        Topic(R.string.music, 212, R.drawable.lifestyle),
//        Topic(R.string.painting, 172, R.drawable.painting),
//        Topic(R.string.photography, 321, R.drawable.photography),
//        Topic(R.string.tech, 118, R.drawable.tech)
//    )
//    val dogs = listOf(
//        Dog(R.drawable.koda, R.string.dog_name_1, 2, R.string.dog_description_1),
//        Dog(R.drawable.lola, R.string.dog_name_2, 16, R.string.dog_description_2),
//        Dog(R.drawable.frankie, R.string.dog_name_3, 2, R.string.dog_description_3),
//        Dog(R.drawable.nox, R.string.dog_name_4, 8, R.string.dog_description_4),
//        Dog(R.drawable.faye, R.string.dog_name_5, 8, R.string.dog_description_5),
//        Dog(R.drawable.bella, R.string.dog_name_6, 14, R.string.dog_description_6),
//        Dog(R.drawable.moana, R.string.dog_name_7, 2, R.string.dog_description_7),
//        Dog(R.drawable.tzeitel, R.string.dog_name_8, 7, R.string.dog_description_8),
//        Dog(R.drawable.leroy, R.string.dog_name_9, 4, R.string.dog_description_9)
//    )
//
    val dessertList = listOf(
////        Dessert(R.drawable.cupcake, 5, 0),
////        Dessert(R.drawable.donut, 10, 2),
////        Dessert(R.drawable.eclair, 15, 5),
////        Dessert(R.drawable.froyo, 30, 10),
////        Dessert(R.drawable.gingerbread, 50, 15),
////        Dessert(R.drawable.honeycomb, 100, 20),
////        Dessert(R.drawable.icecreamsandwich, 500, 25),
////        Dessert(R.drawable.jellybean, 1000, 30),
////        Dessert(R.drawable.kitkat, 2000, 35),
////        Dessert(R.drawable.lollipop, 3000, 40),
////        Dessert(R.drawable.marshmallow, 4000, 45),
////        Dessert(R.drawable.nougat, 5000, 50),
////        Dessert(R.drawable.oreo, 6000, 60)
        Dessert(0, 6000, 60)
    )

    val flavors = listOf(
        R.string.vanilla,
        R.string.chocolate,
        R.string.red_velvet,
        R.string.salted_caramel,
        R.string.coffee
    )

    //
    val quantityOptions = listOf(
        Pair(R.string.one_cupcake, 1),
        Pair(R.string.six_cupcakes, 6),
        Pair(R.string.twelve_cupcakes, 12)
    )

    val entreeMenuItems = listOf(
        EntreeItem(
            name = "Cauliflower",
            description = "Whole cauliflower, brined, roasted, and deep fire",
            price = 7.00
        ),
        EntreeItem(
            name = "Three Bean Chili",
            description = " Black beans, red beans, kidney beans, slow cooked, topped with onion",
            price = 4.00
        ),
        EntreeItem(
            name = "Mushroom Pasta",
            description = "Penne pasta, mushrooms, basil, with plum tomatoes cooke in garlic an olive oil",
            price = 5.50
        ),
        EntreeItem(
            name = "Spicy Black Bean Skillet",
            description = "Seasonal vegetables, black beans, house spice blend, served with avocado and quick pickled onions",
            price = 5.50
        )
    )

    val sideDishMenuItems = listOf(
        SideDishItem(
            name = "Summer Salad",
            description = "Heirloom tomatoes, butter lettuce, peaches, avocado, balsamic dressing",
            price = 2.50
        ),
        SideDishItem(
            name = "Butternut Squash Soup",
            description = "Roasted butternut squash, roasted peppers, chili oil",
            price = 3.00
        ),
        SideDishItem(
            name = "Spicy Potatoes",
            description = "Marble potatoes, roasted, and fried in house spice blend",
            price = 2.00
        ),
        SideDishItem(
            name = "Coconut Rice",
            description = "Rice, coconut milk, lime, and sugar",
            price = 1.50
        )
    )

    val accompanimentMenuItems = listOf(
        AccompanimentItem(
            name = "Lunch Roll",
            description = "Fresh baked roll made in house",
            price = 0.50
        ),
        AccompanimentItem(
            name = "Mixed Berries",
            description = "Strawberries, blueberries, raspberries, and huckleberries",
            price = 1.00
        ),
        AccompanimentItem(
            name = "Pickled Veggies",
            description = "Pickled cucumbers and carrots, made in house",
            price = 0.50
        )
    )
}
