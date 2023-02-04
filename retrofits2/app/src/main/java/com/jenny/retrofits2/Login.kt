package com.jenny.retrofits2

// 아웃풋을 만든다.
data class Login(
    // 서버에서 통신 호출 시 받아오는 응답값
    // api의 반환 json 키 타입과 같아야함
    var code: String,
    var msg: String
)