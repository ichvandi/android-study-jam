package com.example.myfirstapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myfirstapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val counter = mutableMapOf(
        Menu.SATE to 0,
        Menu.BURGER to 0,
        Menu.ES_CAMPUR to 0
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Old way
        // setContentView(R.layout.activity_main)

        // New way
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.setupViews()
    }

    private fun ActivityMainBinding.setupViews() {
        setupSateListener()
        setupBurgerListener()
        setupEsCampurListener()
        setupOrderListener()
    }

    private fun ActivityMainBinding.setupSateListener() {
        btnMinusSate.setOnClickListener {
            // First Method
            // val currentValue = tvCounterSate.text.toString().toInt()

            // Second Method
            val currentValue = counter[Menu.SATE] ?: 0
            val newValue = handleAction(currentValue, Action.DECREASE)

            counter[Menu.SATE] = newValue

            // First Method
            // tvCounterSate.text = newValue.toString()

            // Second Method
            tvCounterSate.text = "$newValue"

            val totalPrice = calculatePrice(counter)
            tvTotalPrice.text = String.format("Rp%d", totalPrice)

            btnOrder.isEnabled = totalPrice > 0
        }

        btnPlusSate.setOnClickListener {
            val currentValue = counter[Menu.SATE] ?: 0
            val newValue = handleAction(currentValue, Action.INCREASE)

            counter[Menu.SATE] = newValue

            tvCounterSate.text = "$newValue"

            val totalPrice = calculatePrice(counter)
            tvTotalPrice.text = String.format("Rp%d", totalPrice)

            btnOrder.isEnabled = totalPrice > 0
        }

        ivDeleteSate.setOnClickListener {
            val currentValue = counter[Menu.SATE] ?: 0
            val newValue = handleAction(currentValue, Action.RESET)

            counter[Menu.SATE] = newValue

            tvCounterSate.text = "$newValue"

            val totalPrice = calculatePrice(counter)
            tvTotalPrice.text = String.format("Rp%d", totalPrice)

            btnOrder.isEnabled = totalPrice > 0
        }
    }

    private fun ActivityMainBinding.setupBurgerListener() {
        btnMinusBurger.setOnClickListener {
            val currentValue = counter[Menu.BURGER] ?: 0
            val newValue = handleAction(currentValue, Action.DECREASE)

            counter[Menu.BURGER] = newValue

            tvCounterBurger.text = "$newValue"

            val totalPrice = calculatePrice(counter)
            tvTotalPrice.text = String.format("Rp%d", totalPrice)

            btnOrder.isEnabled = totalPrice > 0
        }

        btnPlusBurger.setOnClickListener {
            val currentValue = counter[Menu.BURGER] ?: 0
            val newValue = handleAction(currentValue, Action.INCREASE)

            counter[Menu.BURGER] = newValue

            tvCounterBurger.text = "$newValue"

            val totalPrice = calculatePrice(counter)
            tvTotalPrice.text = String.format("Rp%d", totalPrice)

            btnOrder.isEnabled = totalPrice > 0
        }

        ivDeleteBurger.setOnClickListener {
            val currentValue = counter[Menu.BURGER] ?: 0
            val newValue = handleAction(currentValue, Action.RESET)

            counter[Menu.BURGER] = newValue

            tvCounterBurger.text = "$newValue"

            val totalPrice = calculatePrice(counter)
            tvTotalPrice.text = String.format("Rp%d", totalPrice)

            btnOrder.isEnabled = totalPrice > 0
        }
    }

    private fun ActivityMainBinding.setupEsCampurListener() {
        btnMinusEs.setOnClickListener {
            val currentValue = counter[Menu.ES_CAMPUR] ?: 0
            val newValue = handleAction(currentValue, Action.DECREASE)

            counter[Menu.ES_CAMPUR] = newValue

            tvCounterEs.text = "$newValue"

            val totalPrice = calculatePrice(counter)
            tvTotalPrice.text = String.format("Rp%d", totalPrice)

            btnOrder.isEnabled = totalPrice > 0
        }

        btnPlusEs.setOnClickListener {
            val currentValue = counter[Menu.ES_CAMPUR] ?: 0
            val newValue = handleAction(currentValue, Action.INCREASE)

            counter[Menu.ES_CAMPUR] = newValue

            tvCounterEs.text = "$newValue"

            val totalPrice = calculatePrice(counter)
            tvTotalPrice.text = String.format("Rp%d", totalPrice)

            btnOrder.isEnabled = totalPrice > 0
        }

        ivDeleteEs.setOnClickListener {
            val currentValue = counter[Menu.ES_CAMPUR] ?: 0
            val newValue = handleAction(currentValue, Action.RESET)

            counter[Menu.ES_CAMPUR] = newValue

            tvCounterEs.text = "$newValue"

            val totalPrice = calculatePrice(counter)
            tvTotalPrice.text = String.format("Rp%d", totalPrice)

            btnOrder.isEnabled = totalPrice > 0
        }
    }

    private fun ActivityMainBinding.setupOrderListener() {
        btnOrder.setOnClickListener {
            counter[Menu.SATE] = 0
            tvCounterSate.text = "0"

            counter[Menu.BURGER] = 0
            tvCounterBurger.text = "0"

            counter[Menu.ES_CAMPUR] = 0
            tvCounterEs.text = "0"

            tvTotalPrice.text = "0"

            btnOrder.isEnabled = false

            Toast.makeText(applicationContext, "Pesanan Berhasil Dibuat!", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun calculatePrice(cart: Map<Menu, Int>): Int {
        val totalSate = (cart[Menu.SATE] ?: 0) * Menu.SATE.price
        val totalBurger = (cart[Menu.BURGER] ?: 0) * Menu.BURGER.price
        val totalEsCampur = (cart[Menu.ES_CAMPUR] ?: 0) * Menu.ES_CAMPUR.price

        return totalSate + totalBurger + totalEsCampur
    }

    private fun handleAction(currentValue: Int, action: Action): Int {
        return when (action) {
            Action.INCREASE -> currentValue + 1
            Action.DECREASE -> if (currentValue > 0) currentValue - 1 else 0
            Action.RESET -> 0
        }
    }

    enum class Action {
        INCREASE,
        DECREASE,
        RESET;
    }

    enum class Menu(val price: Int) {
        SATE(15_000),
        BURGER(25_000),
        ES_CAMPUR(10_000);
    }

}