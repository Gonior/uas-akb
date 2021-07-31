import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2021 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class Anime (

	@SerializedName("mal_id") val mal_id : Int,
	@SerializedName("url") val url : String,
	@SerializedName("title") val title : String,
	@SerializedName("image_url") val image_url : String,
	@SerializedName("synopsis") val synopsis : String,
	@SerializedName("type") val type : String,
	@SerializedName("airing_start") val airing_start : String,
	@SerializedName("episodes") val episodes : Int,
	@SerializedName("members") val members : Int,
	@SerializedName("genres") val genres : List<Genres>,
	@SerializedName("source") val source : String,
	@SerializedName("producers") val producers : List<Producers>,
	@SerializedName("score") val score : Double,
	@SerializedName("r18") val r18 : Boolean,
	@SerializedName("kids") val kids : Boolean,
	@SerializedName("continuing") val continuing : Boolean,

		@SerializedName("request_hash") val request_hash : String,
@SerializedName("request_cached") val request_cached : Boolean,
@SerializedName("request_cache_expiry") val request_cache_expiry : Int,



@SerializedName("trailer_url") val trailer_url : String,

@SerializedName("title_english") val title_english : String,
@SerializedName("title_japanese") val title_japanese : String,
@SerializedName("title_synonyms") val title_synonyms : List<String>,

@SerializedName("status") val status : String,
@SerializedName("airing") val airing : Boolean,
@SerializedName("aired") val aired : Aired,
@SerializedName("duration") val duration : String,
@SerializedName("rating") val rating : String,

@SerializedName("scored_by") val scored_by : Int,
@SerializedName("rank") val rank : Int,
@SerializedName("popularity") val popularity : Int,

@SerializedName("favorites") val favorites : Int,

@SerializedName("background") val background : String,
@SerializedName("premiered") val premiered : String,
@SerializedName("broadcast") val broadcast : String,
@SerializedName("related") val related : Related,
@SerializedName("studios") val studios : List<Studios>,
@SerializedName("opening_themes") val opening_themes : List<String>,
@SerializedName("ending_themes") val ending_themes : List<String>
)