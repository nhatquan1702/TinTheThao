package com.example.tttkotlin.activity

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tttkotlin.R
import com.example.tttkotlin.api.RetrofitInstance
import com.example.tttkotlin.model.Status
import kotlinx.android.synthetic.main.activity_dang_ky_t_k.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class DangKyTKActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dang_ky_t_k)
        val animation = AnimationUtils.loadAnimation(this, R.anim.uptodowndiagonal)
        rLayoutDK.setAnimation(animation)
        val animationx = AnimationUtils.loadAnimation(this, R.anim.tranlation_x)
        tvNhapEmail.setAnimation(animationx)
        edtNhapEmailDK.setAnimation(animationx)
        tvNhapUserName.setAnimation(animationx)
        edtNhapUsernameDK.setAnimation(animationx)
        tvNhapPass1.setAnimation(animationx)
        edtNhapPass1.setAnimation(animationx)
        tvNhapPass2.setAnimation(animationx)
        edtNhapPass2.setAnimation(animationx)
        checkShowPassDK.setAnimation(animationx)
        checkShowPassDK.setOnClickListener{
            if(checkShowPassDK.isChecked)    {
                edtNhapPass1.transformationMethod = HideReturnsTransformationMethod.getInstance()
                edtNhapPass2.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                edtNhapPass1.transformationMethod = PasswordTransformationMethod.getInstance()
                edtNhapPass2.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
        cvDKTK.setOnClickListener {
            val username = edtNhapUsernameDK.text.toString().trim()
            val email = edtNhapEmailDK.text.toString().trim()
            val pass1 = edtNhapPass1.text.toString().trim()
            val pass2 = edtNhapPass2.text.toString().trim()

            if(username.isEmpty()){
                edtNhapUsernameDK.error = "Vui l??ng ki???m tra l???i t??n ng?????i d??ng!"
                edtNhapUsernameDK.requestFocus()
                return@setOnClickListener
            }
            if(email.isEmpty()){
                edtNhapEmailDK.error = "Vui l??ng ki???m tra l???i email!"
                edtNhapEmailDK.requestFocus()
                return@setOnClickListener
            }
            if(!checkFormatEmail(email)){
                edtNhapEmailDK.error = "Email kh??ng h???p l???! V?? d??? h???p l???: Example@gmail.com"
                edtNhapEmailDK.requestFocus()
                return@setOnClickListener
            }
            if(pass1.isEmpty()){
                edtNhapPass1.error = "Vui l??ng ki???m tra l???i m???t kh???u!"
                edtNhapPass1.requestFocus()
                return@setOnClickListener
            }
            if(!checkFormatPass(pass1)){
                edtNhapPass1.error = "M???t kh???u ph???i ??t nh???t 8 k?? t??? bao g???m ch???, k?? t??? ?????c bi???t v?? s???!"
                edtNhapPass1.requestFocus()
                return@setOnClickListener
            }
            if(pass2.isEmpty()){
                edtNhapPass2.error = "Vui l??ng ki???m tra l???i m???t kh???u!"
                edtNhapPass2.requestFocus()
                return@setOnClickListener
            }
            if(!pass1.equals(pass2) || !pass2.equals(pass1))
                edtNhapPass2.error="M???t kh???u ch??a tr??ng kh???p!"
            else dangKyTaiKhoan(username, email, pass1)
        }

    }
    fun dangKyTaiKhoan(username: String, email: String, pass: String){
        RetrofitInstance.instance.dangKyTaiKhoan(username, email, pass).enqueue(object : Callback<Status> {
            override fun onResponse(call: Call<Status>, response: Response<Status>) {
                response.body()?.let {
                    if (it.getStatus() == 0) {
                        Toast.makeText(applicationContext, "????ng k?? t??i kho???n th??nh c??ng!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(applicationContext, LoginActivity::class.java)
                        startActivity(intent)
                        onBackPressed()
                    }
                    if (it.getStatus() == 1) {
                        edtNhapUsernameDK.error = "Vui l??ng ki???m tra l???i t??n ng?????i d??ng!"
                    }
                    if (it.getStatus() == 2) {
                        edtNhapUsernameDK.error = "Vui l??ng ki???m tra l???i email!"
                    }
                    if (it.getStatus() == 3) {
                        edtNhapPass1.error = "Vui l??ng ki???m tra l???i m???t kh???u!"
                        edtNhapPass2.error = "Vui l??ng ki???m tra l???i m???t kh???u!"
                    }
                    if (it.getStatus() == 4) {
                        edtNhapEmailDK.error = "Email ???? t???n t???i!"
                    }
                }
            }

            override fun onFailure(call: Call<Status>, t: Throwable) {
                Toast.makeText(applicationContext, "L???i!" + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun checkFormatEmail(email: String): Boolean {
        val EMAIL_ADDRESS_PATTERN = Pattern.compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
        )
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
//        val expression = "[a-zA-Z0-9._-]+@[a-z]+\\\\.+[a-z]+"
//        val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
//        val matcher: Matcher = pattern.matcher(email)
//        Toast.makeText(applicationContext, matcher.matches().toString(), Toast.LENGTH_SHORT).show()
//        return matcher.matches()
    }
    fun checkFormatPass(pass: String): Boolean {
        val PASS_PATTERN = Pattern.compile(
                "^(?=.*[0-9])"
                        + "(?=.*[a-z])(?=.*[A-Z])"
                        + "(?=.*[@#$%^&+=])"
                        + "(?=\\S+$).{8,20}$"
        )
        return PASS_PATTERN.matcher(pass).matches()
    }
}