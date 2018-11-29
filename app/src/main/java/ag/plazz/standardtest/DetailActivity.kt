package ag.plazz.standardtest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {

        const val ARG_INPUT ="arg.Input"

        fun newIntent(context: Context, input: String) : Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(ARG_INPUT, input)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var input = ""
        intent?.apply {
            input = getStringExtra(ARG_INPUT)
        }

        setContentView(R.layout.activity_detail)

        resultDataLabel.text = input
    }
}
