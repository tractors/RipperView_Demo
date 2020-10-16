package com.will.ripperview_demo.bean

/**
 * 标签tag bean
 */
class ParallaxViewTag{
     var index: Float = 0f
     var xIn: Float = 0f
     var xOut: Float = 0f
     var yIn: Float = 0f
     var yOut: Float = 0f
     var alphaIn: Float = 0f
     var alphaOut: Float = 0f
    
    override fun toString(): String {
        return "ParallaxViewTag(index=$index, xIn=$xIn, xOut=$xOut, yIn=$yIn, yOut=$yOut, alphaIn=$alphaIn, alphaOut=$alphaOut)"
    }
}