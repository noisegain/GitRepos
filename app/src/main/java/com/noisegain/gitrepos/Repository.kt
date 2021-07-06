package com.noisegain.gitrepos

data class Repository(val name: String, val description: String, val lang: String = "", val userName: String = "")

data class User(val name: String, val repositories: ArrayList<Repository>)