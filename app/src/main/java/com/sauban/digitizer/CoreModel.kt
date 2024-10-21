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

    fun forwardProp(W1: Array<DoubleArray>, b1: DoubleArray, W2: Array<DoubleArray>, b2: DoubleArray, X: DoubleArray): Quadruple<DoubleArray, DoubleArray, DoubleArray, DoubleArray> {
        val Z1 = W1.map { row -> row.mapIndexed { index, value -> value * X[index] }.sum() + b1[W1.indexOf(row)] }.toDoubleArray()
        val A1 = ReLU(Z1)
        
        val Z2 = W2.map { row -> row.mapIndexed { index, value -> value * A1[index] }.sum() + b2[W2.indexOf(row)] }.toDoubleArray()
        val A2 = softmax(Z2)
        
        return Quadruple(Z1, A1, Z2, A2)
    }
    
    fun getPredictions(A2: DoubleArray): Int {
        return A2.indices.maxByOrNull { A2[it] } ?: -1
    }
    fun makePredictions(X: Array<DoubleArray>, W1: Array<DoubleArray>, b1: DoubleArray, W2: Array<DoubleArray>, b2: DoubleArray): Array<Double> {
        val (_, _, _, A2) = forwardProp(W1, b1, W2, b2, X)
        return getPredictions(A2)
    }
    

}

class Quadruple<A, B, C, D>(val first: A, val second: B, val third: C, val fourth: D)
