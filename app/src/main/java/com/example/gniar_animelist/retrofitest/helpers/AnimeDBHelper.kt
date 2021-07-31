package com.example.gniar_animelist.retrofitest.helpers

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.gniar_animelist.retrofitest.models.AnimeModeLDb


class AnimeDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASENAME, null, DATABASEVERSION) {
    override fun onCreate(p0: SQLiteDatabase?) {
        val query = "CREATE TABLE ANIME (ID TEXT PRIMARY KEY,title TEXT,members INTEGER, score REAL, img TEXT, genre TEXT)"
        p0!!.execSQL(query)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXIST ANIME")
        onCreate(p0)
    }

    companion object {
        private val DATABASENAME = "animeDB"
        private val DATABASEVERSION = 1
    }

    fun populateAnime(anime: AnimeModeLDb) {
        val db = this.writableDatabase
        var values = ContentValues()
        values.put("ID", anime.mal_id)
        values.put("title", anime.title)
        values.put("img", anime.img)
        values.put("genre", anime.genre)
        values.put("score", anime.score)
        values.put("members", anime.members)
        db.insert("ANIME", null, values)
        db.close()

    }

    fun getAnimes(): List<AnimeModeLDb> {

        val db = this.writableDatabase
        val list = ArrayList<AnimeModeLDb>()
        val cursor: Cursor = db.rawQuery("SELECT * FROM ANIME", null)

        if (cursor != null) {

            if (cursor.count > 0) {

                cursor.moveToFirst()
                do {
                    val mal_id= cursor.getInt(cursor.getColumnIndex("ID"))
                    val title = cursor.getString(cursor.getColumnIndex("title"))
                    val members = cursor.getInt(cursor.getColumnIndex("members"))
                    val img = cursor.getString(cursor.getColumnIndex("img"))
                    val score = cursor.getDouble(cursor.getColumnIndex("score"))
                    val genre = cursor.getString(cursor.getColumnIndex("genre"))
                    val anime = AnimeModeLDb(mal_id, title, img, genre, score, members)
                    list.add(anime)
                } while (cursor.moveToNext())
            }
        }

        return list
    }


    fun removeFavorite(id : String) {
        val db = this.writableDatabase
        db.delete("ANIME", "ID = " + id, null)
        db.close()
    }
    fun findAnimeById(id : String) :Boolean {
        var result = false
        val db = this.writableDatabase
        var cursor = db.rawQuery("SELECT * FROM ANIME WHERE ID = $id", null)

        if (cursor.count > 0) result = true

        return result
    }
}