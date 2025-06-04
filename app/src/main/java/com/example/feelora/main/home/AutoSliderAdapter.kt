import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.feelora.databinding.SlideItemBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AutoSliderAdapter(
    // private val images: List<Int>,
    private val images: List<String>,
    private val viewPager: ViewPager2
) : RecyclerView.Adapter<AutoSliderAdapter.ViewHolder>() {
    private var currentPosition = 0

    init {
        startAutoSlider()
    }

    class ViewHolder(val binding: SlideItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SlideItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(images[position]).into(holder.binding.slideImage)
    }

//    class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
//        val slideImage: ImageView = itemView.findViewById(R.id.slideImage)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.slide_item, parent, false)
//        return SliderViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
//        holder.slideImage.setImageResource(images[position])
//        // kalau mau ambil gambar dari link
//        // Picasso.get().load(images[position]).into(holder.slideImage)
//    }

    override fun getItemCount(): Int {
        return images.size
    }

    private fun startAutoSlider() {
        CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                delay(3000)
                currentPosition = (currentPosition + 1) % itemCount
                viewPager.setCurrentItem(currentPosition, true)
            }
        }
    }
}