import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.veggierecipes.R
import com.example.veggierecipes.Recipe

class IngredientsAdapter(private val recipe: Recipe) :
    RecyclerView.Adapter<IngredientsAdapter.CastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ingredients, parent, false)
        return CastViewHolder(view)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
//        holder.initialize(castList[position])
            holder.initialize(recipe.ingredients!![position])

    }

    override fun getItemCount(): Int {
        return recipe.ingredients!!.size

    }

    inner class CastViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val ingredients: TextView = view.findViewById(R.id.ingredient)
        fun initialize(ingredient:String) {

            ingredients.text = ingredient


        }
    }
}