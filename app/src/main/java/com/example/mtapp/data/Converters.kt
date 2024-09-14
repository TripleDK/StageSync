//package com.example.mtapp.data
//
//import androidx.room.TypeConverter
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//import com.example.mtapp.Models.Group
//
//class Converters {
//
//    @TypeConverter
//    fun fromSceneList(value: List<Scene>): String {
//        val gson = Gson()
//        val type = object : TypeToken<List<Scene>>() {}.type
//        return gson.toJson(value, type)
//    }
//
//    @TypeConverter
//    fun toSceneList(value: String): List<Scene> {
//        val gson = Gson()
//        val type = object : TypeToken<List<Scene>>() {}.type
//        return gson.fromJson(value, type)
//    }
//
//    @TypeConverter
//    fun fromGroupList(value: List<Group>): String {
//        val gson = Gson()
//        val type = object : TypeToken<List<Group>>() {}.type
//        return gson.toJson(value, type)
//    }
//
//    @TypeConverter
//    fun toGroupList(value: String): List<Group> {
//        val gson = Gson()
//        val type = object : TypeToken<List<Group>>() {}.type
//        return gson.fromJson(value, type)
//    }
//}