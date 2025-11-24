package com.cs407.lab09

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = 0f
    var posY = 0f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
         reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        if(isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            return
        }

        val newVelocityX = velocityX + 0.5f * (accX + xAcc) * dT
        val newPosX = velocityX * dT + (1.0f / 6.0f) * dT * dT * (3.0f * accX + xAcc)

        val newVelocityY = velocityY + 0.5f * (accY + yAcc) * dT
        val newPosY = velocityY * dT + (1.0f / 6.0f) * dT * dT * (3.0f * accY + yAcc)

        accX = xAcc
        accY = yAcc
        posX += newPosX
        posY += newPosY
        velocityX = newVelocityX
        velocityY = newVelocityY
        // v1 = v(t1) = v0 + 1/2 (a1 + a0)(t1 − t0)
        // v0 · (t1 − t0) + 1/6 · (t1 − t0)^2 · (3a0 + a1)

    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    fun checkBoundaries() {
        // TODO: implement the checkBoundaries function
        // (Check all 4 walls: left, right, top, bottom)

        var ballRight = posX + ballSize
        var ballTop = posY
        var ballBottom = posY + ballSize
        var ballLeft = posX

        if (backgroundWidth <= ballRight) {
            accX = 0f
            velocityX = 0f
        }
        if (ballLeft <= 0f) {
            accX = 0f
            velocityX = 0f
        }
        if (backgroundHeight <= ballBottom) {
            accY = 0f
            velocityY = 0f
        }
        if (ballTop <= 0f) {
            accY = 0f
            velocityY = 0f
        }

    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        // TODO: implement the reset function
        // (Reset posX, posY, velocityX, velocityY, accX, accY, isFirstUpdate)

        posY = backgroundHeight / 2
        posX = backgroundWidth / 2
        velocityY = 0f
        velocityX = 0f
        accX = 0f
        accY = 0f
        isFirstUpdate = true // not sure if this is right

    }
}