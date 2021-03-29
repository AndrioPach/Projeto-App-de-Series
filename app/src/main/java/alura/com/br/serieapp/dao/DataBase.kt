package alura.com.br.serieapp.dao
//
//import alura.com.br.serieapp.models.Series
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//
//@Database(entities = [Series::class], version = 1,exportSchema = false)
//abstract class DataBase: RoomDatabase() {
//
//    abstract fun serieDao():SeriesDao
//    companion object{
//        @Volatile
//        private var INSTANCE:DataBase?=null
//        fun getDataBase(context: Context): DataBase{
//            return INSTANCE ?: synchronized(this){
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    DataBase::class.java,
//                    "series-db")
//                    .build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
//}