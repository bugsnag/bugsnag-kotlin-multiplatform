package com.bugsnag.kmp

internal external interface JsUser {
    var id: String?
    var name: String?
    var email: String?
}

internal fun JsUser(id: String?, email: String?, name: String?): JsUser {
    val user = Any().unsafeCast<JsUser>()
    user.id = id
    user.email = email
    user.name = name
    return user
}
