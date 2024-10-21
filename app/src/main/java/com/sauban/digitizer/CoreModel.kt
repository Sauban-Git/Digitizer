package com.sauban.digitizer

class CoreModel {

    fun ReLU(z: DoubleArray): DoubleArray {
        return z.map { maxOf(it, 0.0) }.toDoubleArray()
    }

    fun softmax(z: DoubleArray): DoubleArray {
        val expZ = z.map { Math.exp(it) }
        val sumExpZ = expZ.sum()
        return  expZ.map { it / sumExpZ }.toDoubleArray()
    }

    fun forward ( W1: Array<DoubleArray>, b1: DoubleArray,, W2: Array<DoubleArray>, b2: DoubleArray, X: DoubleArray): Triple<Triple<DoubleArray, DoubleArray>, DoubleArray, DoubleArray> {
        
    }
}