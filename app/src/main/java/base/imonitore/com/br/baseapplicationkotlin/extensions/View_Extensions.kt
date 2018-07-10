package br.com.imonitore.extensions

import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by ccouto on 25/09/2017.
 */

fun View.hideKeyboard(inputMethodManager: InputMethodManager) {
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}