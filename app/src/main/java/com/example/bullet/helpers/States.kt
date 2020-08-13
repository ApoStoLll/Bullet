package com.example.bullet.helpers

sealed class OrderListState{
    object LoadingState : OrderListState()
    class LoadedState<T>(val data : List<T>) : OrderListState()
    class ErrorState(val message : String) : OrderListState()
    object NoItemState : OrderListState()
}
