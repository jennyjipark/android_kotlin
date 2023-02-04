package com.jenny.retrofits2

import retrofit2.Call
import retrofit2.http.*

interface LoginService {

    // 서버와 통신하는 곳
    // @FormUrlEncoded // input으로 넣은 것을 인코딩 > json 형식으로 들어가려면 없어야함
    @POST("/login/")
    fun requestLogin(
        // 1. 인풋 만들기
        // post의 json key값과 동일해야 함 > gson으로 json타입 만들 것
        @Body user: User // gson으로 만든 데이터 객체
    ): Call<Login> // 2. 아웃풋 정의하는 곳 > 반환값

}