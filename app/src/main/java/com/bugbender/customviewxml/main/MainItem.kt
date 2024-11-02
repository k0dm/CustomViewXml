package com.bugbender.customviewxml.main

import androidx.fragment.app.Fragment

data class MainItem(val name: String, val fragmentClass: Class<out Fragment>)