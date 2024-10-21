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

    fun forwardProp(W1: Array<DoubleArray>, b1: DoubleArray, W2: Array<DoubleArray>, b2: DoubleArray, X: DoubleArray): Triple<Triple<DoubleArray, DoubleArray>, DoubleArray, DoubleArray> {
        val Z1 = W1.map { row -> row.mapIndexed { index, value -> value * X[index] }.sum() + b1[W1.indexOf(row)] }.toDoubleArray()
        val A1 = relu(Z1)
        
        val Z2 = W2.map { row -> row.mapIndexed { index, value -> value * A1[index] }.sum() + b2[W2.indexOf(row)] }.toDoubleArray()
        val A2 = softmax(Z2)
        
        return Triple(Triple(Z1, A1, Z2), A2)
    }
    
    fun getPredictions(A2: DoubleArray): Int {
        return A2.indices.maxByOrNull { A2[it] } ?: -1
    }
}