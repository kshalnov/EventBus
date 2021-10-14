package ru.gb.course1.myapplication

import ru.gb.course1.myapplication.EventBus.*

const val MANUAL_COUNTER_BUS = "man_counter_bus"
const val AUTO_COUNTER_BUS = "auto_counter_bus"
class PlusEvent: Event()
class MinusEvent: Event()