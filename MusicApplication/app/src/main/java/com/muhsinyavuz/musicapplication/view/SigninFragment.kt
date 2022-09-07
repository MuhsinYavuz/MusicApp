package com.muhsinyavuz.musicapplication.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.muhsinyavuz.musicapplication.R
import com.muhsinyavuz.musicapplication.databinding.FragmentLoginBinding
import com.muhsinyavuz.musicapplication.databinding.FragmentSigninBinding
import com.muhsinyavuz.musicapplication.model.User
import com.muhsinyavuz.musicapplication.util.toast
import kotlinx.android.synthetic.main.fragment_login.*


class SigninFragment : Fragment() {

    private lateinit var binding: FragmentSigninBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding.userNameSign.setText("")
        binding.userPasswordSign.setText("")
        loginButton()
        binding.toLoginUp.setOnClickListener {
            val action = SigninFragmentDirections.actionSigninFragmentToLoginFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun passwordControl(password :CharSequence) : Boolean{
        return if(password.isEmpty()){
            context?.toast("Şifre boş bırakılamaz")
            false
        } else {
            if(password.length < 8){
                context?.toast("Şifre en az 8 karakter içermelidir.")
                false
            }else {
                true
            }
        }
    }
     fun loginButton(){

        binding.singinButton.setOnClickListener {
            isvalidEmail(binding.userNameSign.text)
            passwordControl(binding.userPasswordSign.text)
        if(!isvalidEmail(binding.userNameSign.text)){
            context?.toast("Kullanıcı emailini kontrol ediniz.")
        }

        if(isvalidEmail(binding.userNameSign.text) && passwordControl(binding.userPasswordSign.text) ){

            var isEmail = false

                val database = context?.openOrCreateDatabase("userInformation", Context.MODE_PRIVATE,null)
                database?.execSQL("CREATE TABLE IF NOT EXISTS userinformation(email VARCHAR , password VARCHAR)")
                //  val database = context?.openOrCreateDatabase("userInformation", Context.MODE_PRIVATE,null)
                val emailT = binding.userNameSign.text
                val cursor =
                    database?.rawQuery("SELECT * FROM userinformation WHERE email = '$emailT' ",null)
                println("cursor   $cursor.toString()")
                if(cursor != null){
                    val artEmail = cursor.getColumnIndex("email")
                    while(cursor.moveToNext()) {
                        var i = 0
                        println("sayımız $i" )
                        i += 1
                        val email = cursor.getString(artEmail)
                        if(binding.userNameSign.text.toString() == email){
                            context?.toast("Bu email zaten sisteme kayıtlı")
                            println("zten kayıtlı..")
                            isEmail = true
                        }
                    }
                }
                println("sememem $isEmail")
            if(!isEmail){
                println("üye olundu kullanıcı kayıt22.")

                val sqlString = "INSERT INTO userInformation(email,password) VALUES (?,?)"
                val statement = database?.compileStatement(sqlString)
                statement?.bindString(1,binding.userNameSign.text.toString())
                statement?.bindString(2,binding.userPasswordSign.text.toString())
                statement?.execute()
                context?.toast("Üye olundu")
                val intent = Intent(context,MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                activity?.onBackPressed()
                startActivity(intent)


            }





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