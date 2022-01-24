package com.example.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.roomdatabase.databinding.ActivityAddUserBinding

class AddUserActivity : AppCompatActivity() {

    var user: Users? = null
    private lateinit var binding: ActivityAddUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_add_user)
        val binding: ActivityAddUserBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_add_user)
        user = intent.getParcelableExtra("user")

        user?.let {
            binding.edUsername.setText(it.userName)
            binding.edLocation.setText(it.location)
            binding.edEmail.setText(it.email)
        } ?: kotlin.run {

        }

        val repo = UserRepository(this)

        binding.buttonSaveUser.setOnClickListener {

            if (binding.edUsername.text.isNotEmpty() && binding.edEmail.text.isNotEmpty() &&
                binding.edLocation.text.isNotEmpty()) {

                user?.let {
                    val user = Users(userId = it.userId,
                        userName = binding.edUsername.text.toString(),
                        location = binding.edLocation.text.toString(),
                        email = binding.edEmail.text.toString()
                    )
                    repo.updateUser(user)
                } ?: kotlin.run {
                    val user = Users(
                        userName = binding.edUsername.text.toString(),
                        location = binding.edLocation.text.toString(),
                        email = binding.edEmail.text.toString()
                    )
                    repo.insertUser(user)
                }

            } else {
                Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }
}