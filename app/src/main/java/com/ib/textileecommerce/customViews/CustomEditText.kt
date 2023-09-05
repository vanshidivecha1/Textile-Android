package com.ib.textileecommerce.customViews

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import android.util.SparseArray
import androidx.appcompat.widget.AppCompatEditText
import com.ib.textileecommerce.R
import com.ib.textileecommerce.utils.Constants

/**
 * Created by Sakshi Soni
 */
class CustomEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    private val mTypefaces = SparseArray<Typeface>(16)

    init {
        setTypeface(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : this(context, attrs) {
        Log.isLoggable("Button", defStyle)
        setTypeface(context, attrs)
    }

    private fun setTypeface(context: Context, attrs: AttributeSet) {
        val values = context.obtainStyledAttributes(attrs, R.styleable.CustomEditText)

        val typefaceValue = values.getInt(R.styleable.CustomEditText_typeface, 0)
        values.recycle()

        typeface = obtainTypeface(context, typefaceValue)
    }

    @Throws(IllegalArgumentException::class)
    private fun obtainTypeface(context: Context, typefaceValue: Int): Typeface? {
        var typeface: Typeface? = mTypefaces.get(typefaceValue)
        if (typeface == null) {
            typeface = createTypeface(context, typefaceValue)
            mTypefaces.put(typefaceValue, typeface)
        }
        return typeface
    }

    @Throws(IllegalArgumentException::class)
    private fun createTypeface(context: Context, typefaceValue: Int): Typeface {
        return when (typefaceValue) {
            Constants.SEGOE_REGULAR -> Typeface.createFromAsset(context.assets, "SEGOEUI.TTF")

            Constants.SEGOE_LIGHT -> Typeface.createFromAsset(context.assets, "SEGOEUIL.TTF")

            Constants.SEGOE_BOLD -> Typeface.createFromAsset(context.assets, "SEGOEUIB.TTF")

            Constants.SEGOE_SEMI_BOLD -> Typeface.createFromAsset(
                context.assets,
                "SEGUISB.TTF"
            )

            Constants.SEGOE_LIGHT_ITALIC -> Typeface.createFromAsset(
                context.assets,
                "SEGUILI.TTF"
            )

            else -> Typeface.createFromAsset(context.assets, "SEGOEUI.TTF")
        }
    }
}