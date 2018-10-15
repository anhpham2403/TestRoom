package com.example.anhpham.testroomdatabase.utils


import com.example.anhpham.testroomdatabase.database.AppDatabase
import com.example.anhpham.testroomdatabase.entity.User

object DatabaseInitializer {
    private fun addUser(db: AppDatabase, user: User): User {
        db.userDao().insertAll(user)
        return user
    }

    fun populateWithTestData(db: AppDatabase) {
        val user = User()
        user.firstName = "test"
        user.lastName = "test"
        user.age = 25
        for (i in 1..100) {
            addUser(db, user)
        }
    }

    fun getUsers(db: AppDatabase): List<User> {
       return db.userDao().all
    }
}
