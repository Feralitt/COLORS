package com.pmc_shishki.colors

import android.content.Context

class ButtonColor: androidx.appcompat.widget.AppCompatButton {
    private var _color: MyColors? = null
    public var color: MyColors?
        get() = _color
        set(value: MyColors?) {
            _color = value
            if (value!= null) {
                setBackgroundColor(_color!!.getColor())
            }
        }

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: android.util.AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: android.util.AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}
}