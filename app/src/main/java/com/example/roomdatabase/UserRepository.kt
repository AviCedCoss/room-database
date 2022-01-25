package com.example.roomdatabase

import android.content.Context
import android.os.AsyncTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserRepository(context: Context) {

    var db: UserDao = AppDatabase.getInstance(context)?.userDao()!!


    //Fetch All the Users
    fun getAllUsers(): List<Users> {
        return db.gelAllUsers()
    }

    // Insert new user
    fun insertUser(users: Users) {
        insertAsyncTask(db).execute(users)
    }

    // update user
    fun updateUser(users: Users) {
        db.updateUser(users)
    }

    // Delete user
    fun deleteUser(users: Users) {
        db.deleteUser(users)
    }

    private class insertAsyncTask internal constructor(private val usersDao: UserDao) :
            AsyncTask<Users, Void, Void>() {

        override fun doInBackground(vararg params: Users): Void? {
            GlobalScope.launch(Dispatchers.IO) {
                usersDao.insertUser(params[0])
            }
            return null
        }
    }
}