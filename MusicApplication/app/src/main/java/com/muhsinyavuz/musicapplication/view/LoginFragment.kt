package com.muhsinyavuz.musicapplication.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.Navigation
import com.muhsinyavuz.musicapplication.R
import com.muhsinyavuz.musicapplication.databinding.FragmentLoginBinding
import com.muhsinyavuz.musicapplication.databinding.FragmentMusicDetailBinding
import com.muhsinyavuz.musicapplication.databinding.FragmentMusicListBinding
import com.muhsinyavuz.musicapplication.model.User
import com.muhsinyavuz.musicapplication.util.toast


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var arrList : ArrayList<User>
    private var isEmail :Boolean?=null
    private var isloginu : Boolean?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
        arrList = ArrayList<User>()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       binding.toSignUp.setOnClickListener {
           val action = LoginFragmentDirections.actionLoginFragmentToSigninFragment()
           Navigation.findNavController(it).navigate(action)
       }
        loginButton()
    }
    private fun passwordControl(password :CharSequence) : Boolean{
        return if(password.isEmpty()){
            context?.toast("Şifre boş bırakılamaz")
            false
        } else {
            true
        }
    }
    private fun loginButton(){

        binding.loginButton.setOnClickListener {

            isvalidEmail(binding.userName.text.toString())
            passwordControl(binding.userPassword.text)
            val database = context?.openOrCreateDatabase("userInformation", Context.MODE_PRIVATE,null)
            val emailT = binding.userName.text
            val cursor =
                database?.rawQuery("SELECT * FROM userinformation WHERE email = '$emailT' ",null)

            if(cursor != null){

                val artEmail = cursor.getColumnIndex("email")
                val artPassword = cursor.getColumnIndex("password")

            while(cursor.moveToNext()){

                val email = cursor.getString(artEmail)
                val password = cursor.getString(artPassword)
                val user = User(email,password)

                if(binding.userName.text.toString() == email){
                    isEmail = true

                    if(binding.userPassword.text.toString() == password){
                        context?.toast("Giriş yapıldı")
                        isloginu = true
                           val intent = Intent(context,MainActivity::class.java)
                          startActivity(intent)
                    }
                }
            }
            }
            if(isloginu != true){
                context?.toast("Kullanıcı şifresi hatalı")
            }
            if(isEmail != true){
                context?.toast("Kullanıcı bulunamadı")
            }
        }
    }
    private fun isvalidEmail(target : CharSequence) : Boolean{
        return if(target.isEmpty()){
            context?.toast("Kullanıcı adı boş bırakılamaz")
            false
        }else {
            android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }

    }

}