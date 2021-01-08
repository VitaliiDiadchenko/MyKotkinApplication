package com.vitaliidiadchenko.mykotkinapplication.screens

sealed class State {
    class Init : State()
    class Success : State()
    class Error : State()
    class Loading : State()
}