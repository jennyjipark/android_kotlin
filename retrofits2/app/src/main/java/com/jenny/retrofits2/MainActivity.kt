package com.jenny.retrofits2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 레트로핏 객체 만들기
        var retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000/login/") // 서버주소
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // 레트로핏에 서비스 붙이기
        var loginService = retrofit.create(LoginService::class.java)

        var id = findViewById<EditText>(R.id.id)
        var pw = findViewById<EditText>(R.id.pw)
        var login = findViewById<Button>(R.id.login)

        login.setOnClickListener {
            var textId = id.text.toString()
            var textPw = pw.text.toString()
            var user = User(textId, textPw) // 아이디, 비번이 들어가면 Gson으로 직렬화된 객체 반환

            // que에 넣어서 다른 스레드가 처리하게 한다.
            loginService.requestLogin(user).enqueue(object: Callback<Login> {
                // 웹통신 성공 시 - 응답값을 받아옴
                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    if(response.isSuccessful) {
                        var login = response.body() // code, msg
                        var dialog = AlertDialog.Builder(this@MainActivity)
                        dialog.setTitle("성공!")
                        dialog.setMessage("code=${login?.code}, msg=${login?.msg}")
                        dialog.show()
                    } else {
                        var dialog = AlertDialog.Builder(this@MainActivity)
                        dialog.setTitle("에러야!")
                        dialog.setMessage(response.errorBody().toString())
                        dialog.show()
                        Log.d("ㅇㅇ", response.errorBody()!!.string())
                    }

                }

                // 웹통신 실패 시
                override fun onFailure(call: Call<Login>, t: Throwable) {
                    t.message?.let { it1 -> Log.d("debug", it1) }
                    var dialog = AlertDialog.Builder(this@MainActivity)
                    dialog.setTitle("실패!")
                    dialog.setMessage("통신에 실패했습니다.")
                    dialog.show()
                }
            })


        }


    }
}