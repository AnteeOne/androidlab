import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.anteeoneapp.R
import com.example.anteeoneapp.UserDetailActivity

class UserHolder (itemView: View): RecyclerView.ViewHolder(itemView){

    val userName: TextView = itemView.findViewById(R.id.userDetailItemName)
    val userInfo: TextView = itemView.findViewById(R.id.userDetailItemInfo)
    val userAvatar:ImageView = itemView.findViewById(R.id.userDetailItemAvatar)
    var position:Int? = null

    init {
        itemView.setOnClickListener{
            val context = itemView.context
            val intent = Intent(context,UserDetailActivity::class.java)
            intent.putExtra("id",position.toString())
            context.startActivity(intent)
        }
    }
}