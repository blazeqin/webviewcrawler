package com.lion.webviewcrawlerdemo.interfaces


interface PermissionListener{
    fun onGranted( permissions: Array<out String>)
    fun onDenied( permissions:Array<out String>,hint: String )
}