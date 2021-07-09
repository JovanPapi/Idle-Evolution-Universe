package com.example.idleevolution_universe.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.idleevolution_universe.R
import com.example.idleevolution_universe.entity_model.SectionElement
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ElementPopUpActivity : AppCompatActivity() {
    private var imageView: ImageView? = null
    private var costTv: TextView? = null
    private var totalTV: TextView? = null
    private var increaseTV: TextView? = null
    private var descriptionTV: TextView? = null
    private var btnClosePopUp: Button? = null
    private var btnUpgradeElement: Button? = null
    private val sectionRef = FirebaseDatabase.getInstance().reference
    private lateinit var element: SectionElement

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_element_pop_up)

        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        supportActionBar?.hide()

        val width = dm.widthPixels
        val height = dm.heightPixels

        window.setLayout((width * .8).toInt(), (height * .65).toInt())

        val params = window.attributes
        params.gravity = Gravity.CENTER
        params.x = 0
        params.y = -50

        window.attributes = params

        val bundleExtras = intent.extras
        val elementDbKey = bundleExtras!!.getString("elementDbKey")
        val sectionElement = bundleExtras.getString("sectionElement")

        costTv = findViewById(R.id.costTV)
        totalTV = findViewById(R.id.productionAfterUpgradeTV)
        increaseTV = findViewById(R.id.increaseTV)
        imageView = findViewById(R.id.popUpImage)
        descriptionTV = findViewById(R.id.descriptionTV)

        btnClosePopUp = findViewById(R.id.btnClosePopUp)
        btnUpgradeElement = findViewById(R.id.btnUpgradeElement)


        if (sectionElement != null && elementDbKey != null) {
            sectionRef.child(sectionElement).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.hasChild(elementDbKey)) {
                        element =
                            snapshot.child(elementDbKey).getValue(SectionElement::class.java)!!
                        imageView?.setImageResource(element.image)
                        costTv?.text = "Cost: " + element.currentCostUpgrade
                        descriptionTV?.text = "Description: " + element.description
                        totalTV?.text = "Total: " + element.totalProductionAfterUpgrade
                        increaseTV?.text =
                            "Increase: " + element.energyProductionIncreaseAfterUpgrade
                        return

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }
        btnClosePopUp?.setOnClickListener { finish() }

        btnUpgradeElement?.setOnClickListener {
            element.currentCostUpgrade = ((element.currentCostUpgrade + 1) * 1.3).toLong()
            element.totalProductionAfterUpgrade *= 3
            element.productionPow++

            sectionRef.child(sectionElement!!).child(elementDbKey!!).child("currentCostUpgrade")
                .setValue(element.currentCostUpgrade)
            sectionRef.child(sectionElement).child(elementDbKey)
                .child("totalProductionAfterUpgrade").setValue(element.totalProductionAfterUpgrade)
            sectionRef.child(sectionElement).child(elementDbKey).child("productionPow")
                .setValue(element.productionPow)
        }

    }
}