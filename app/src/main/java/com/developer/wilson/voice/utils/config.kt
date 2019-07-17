package com.developer.wilson.voice.utils

class config {

    fun convertData(data: String) : String{
        if (data.length <= 0) return ""
        if (!data.contains("/")) return ""

        var date = data.split("/")

        return date[2]+"-"+date[1]+"-"+date[0]

    }

    fun convertDataNormal(data: String) : String{
        if (data.length <= 0) return ""
        if (!data.contains("-")) return ""

        var date = data.split("-")
        return date[2]+"/"+date[1]+"/"+date[0]
    }

}