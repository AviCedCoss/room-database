package com.example.roomdatabase


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.databinding.LayoutUserListBinding

class UserListAdapter : RecyclerView.Adapter<ViewHolder>() {

    var userList = mutableListOf<Users>()

    var clickListener: ListClickListener<Users>? = null

    fun setUsers(users: List<Users>) {
        this.userList = users.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<LayoutUserListBinding>(
            layoutInflater,
            R.layout.layout_user_list,
            parent,
            false
        )

        return ViewHolder(binding)
        /*val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_user_list, parent, false)
        return MyViewHolder(view)*/
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val user = userList[position]
        holder.location.text = user.location
        holder.username.text = user.userName
        holder.email.text = user.email
        holder.layout.setOnClickListener {
            clickListener?.onClick(user,position)
        }

        holder.imgDelete.setOnClickListener {
            clickListener?.onDelete(user)
        }

    }

    fun setOnItemClick(listClickListener: ListClickListener<Users>) {
        this.clickListener = listClickListener
    }

}



class ViewHolder(binding: LayoutUserListBinding): RecyclerView.ViewHolder(binding.root) {

    val username = binding.textUsername
    val location = binding.textLocation
    val email = binding.textEmail
    val layout = binding.layout
    val imgDelete = binding.imgDelete
}


interface ListClickListener<T> {
    fun onClick(data: T, position: Int)
    fun onDelete(user: T)
}

