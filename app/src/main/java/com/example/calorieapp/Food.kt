import android.os.Parcel
import android.os.Parcelable
import org.json.JSONArray
import org.json.JSONObject

class Food : Parcelable {

    private lateinit var name: String
    private var calories: Double = 0.0
    private var fatSaturated: Double = 0.0
    private var protein: Double = 0.0
    private var carbohydrate: Double = 0.0
    private var fiber: Double = 0.0

    constructor(jsonObject: JSONObject) {
        name = jsonObject.getString("name")
        calories = jsonObject.getDouble("calories")
        fatSaturated = jsonObject.getDouble("fat_saturated_g")
        protein = jsonObject.getDouble("protein_g")
        carbohydrate = jsonObject.getDouble("carbohydrates_total_g")
        fiber = jsonObject.getDouble("fiber_g")
    }

    // Constructor for creating an instance from a Parcel
    constructor(name: String, calories: Double, fatSaturated: Double, protein: Double, carbohydrate: Double, fiber: Double) {
        this.name = name
        this.calories = calories
        this.fatSaturated = fatSaturated
        this.protein = protein
        this.carbohydrate = carbohydrate
        this.fiber = fiber
    }

    // Constructor for creating an instance from a Parcel
    private constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeDouble(calories)
        dest.writeDouble(fatSaturated)
        dest.writeDouble(protein)
        dest.writeDouble(carbohydrate)
        dest.writeDouble(fiber)
    }

    companion object CREATOR : Parcelable.Creator<Food> {
        override fun createFromParcel(parcel: Parcel): Food {
            return Food(parcel)
        }

        override fun newArray(size: Int): Array<Food?> {
            return arrayOfNulls(size)
        }

        @JvmStatic
        fun fromJSONArray(foodJSONArray: JSONArray): MutableList<Food> {
            val foodList: MutableList<Food> = mutableListOf()

            for (i in 0 until foodJSONArray.length()) {
                foodList.add(Food(foodJSONArray.getJSONObject(i)))
            }
            return foodList
        }
    }

    fun getName(): String {
        return name
    }

    fun getCalories(): Double {
        return calories
    }

    fun getFatSaturated(): Double {
        return fatSaturated
    }

    fun getProtein(): Double {
        return protein
    }

    fun getCarbohydrate(): Double {
        return carbohydrate
    }

    fun getFiber(): Double {
        return fiber
    }
}
